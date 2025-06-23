document.addEventListener('DOMContentLoaded', function() {
    // Variables globales
    let selectedFlight = null;
    let reservations = [];
    let flights = [];
    let aeropuertos = [];

    // Elementos del DOM
    const userForm = document.getElementById('user-form');
    const vuelosContainer = document.getElementById('vuelos-container');
    const reservasContainer = document.getElementById('reservas-container');
    const reservarBtn = document.getElementById('reservar-btn');
    const buscarBtn = document.getElementById('buscar-btn');
    const origenSelect = document.getElementById('origen');
    const destinoSelect = document.getElementById('destino');
    const confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));

    // Inicialización
    init();

    async function init() {
        await loadAirports();
        await loadFlights();
        await loadReservations();
        setupEventListeners();
    }

    // Cargar aeropuertos para los filtros
    async function loadAirports() {
        try {
            const response = await fetch('http://localhost:9000/api/v1/aeropuertos');
            aeropuertos = await response.json();

            origenSelect.innerHTML = '<option value="">Todos los orígenes</option>';
            destinoSelect.innerHTML = '<option value="">Todos los destinos</option>';

            aeropuertos.forEach(aeropuerto => {
                origenSelect.innerHTML += `<option value="${aeropuerto.id}">${aeropuerto.ciudad} (${aeropuerto.nombre})</option>`;
                destinoSelect.innerHTML += `<option value="${aeropuerto.id}">${aeropuerto.ciudad} (${aeropuerto.nombre})</option>`;
            });
        } catch (error) {
            console.error('Error cargando aeropuertos:', error);
        }
    }

    // Cargar vuelos disponibles
    async function loadFlights() {
        try {
            const response = await fetch('http://localhost:9000/api/v1/vuelos');
            flights = await response.json();
            renderFlights(flights);
        } catch (error) {
            console.error('Error cargando vuelos:', error);
        }
    }

    // Cargar reservas existentes
    async function loadReservations() {
        try {
            const response = await fetch('http://localhost:9000/api/v1/reservas');
            reservations = await response.json();
            renderReservations();
        } catch (error) {
            console.error('Error cargando reservas:', error);
        }
    }

    // Renderizar vuelos en el panel izquierdo
    function renderFlights(flightsToRender) {
        vuelosContainer.innerHTML = '';
        document.getElementById('resultados-contador').textContent = `${flightsToRender.length} vuelos encontrados`;

        flightsToRender.forEach(flight => {
            const origen = aeropuertos.find(a => a.id === flight.salida.id) || flight.salida;
            const destino = aeropuertos.find(a => a.id === flight.destino.id) || flight.destino;

            const flightElement = document.createElement('div');
            flightElement.className = `flight-card ${selectedFlight?.id === flight.id ? 'selected' : ''}`;
            flightElement.innerHTML = `
                <div class="d-flex justify-content-between">
                    <h5>${flight.avion.nombre}</h5>
                    <span class="badge bg-info">Capacidad: ${flight.avion.capacidad}</span>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6">
                        <p><strong>Salida:</strong> ${origen.ciudad} (${origen.nombre})</p>
                        <p>${new Date(flight.fechaSalida).toLocaleString()}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Llegada:</strong> ${destino.ciudad} (${destino.nombre})</p>
                        <p>${new Date(flight.fechaLlegada).toLocaleString()}</p>
                    </div>
                </div>
                <button class="btn btn-sm ${selectedFlight?.id === flight.id ? 'btn-primary' : 'btn-outline-primary'} mt-2 w-100">
                    ${selectedFlight?.id === flight.id ? 'Seleccionado' : 'Seleccionar'}
                </button>
            `;

            flightElement.addEventListener('click', () => {
                selectedFlight = flight;
                renderFlights(flightsToRender);
                document.getElementById('reserva-section').classList.remove('d-none');
            });

            vuelosContainer.appendChild(flightElement);
        });
    }

    // Renderizar reservas en el panel derecho
    function renderReservations() {
        reservasContainer.innerHTML = '';
        document.getElementById('reservas-contador').textContent = `${reservations.length} reservas`;

        reservations.forEach(reservation => {
            const flight = reservation.vuelo;
            const user = reservation.usuario; // Asegúrate que esto no sea null
            const origen = aeropuertos.find(a => a.id === flight.salida.id) || flight.salida;
            const destino = aeropuertos.find(a => a.id === flight.destino.id) || flight.destino;

            const reservationElement = document.createElement('div');
            reservationElement.className = 'reservation-card';
            reservationElement.innerHTML = `
            <div class="d-flex justify-content-between">
                <h5>Reserva #${reservation.id}</h5>
                <span class="badge bg-success">Confirmada</span>
            </div>
            <p><strong>Pasajero:</strong> ${user.nombre || 'N/A'} ${user.apellido || 'N/A'}</p>
            <div class="row mt-2">
                <div class="col-md-6">
                    <p><strong>Vuelo:</strong> ${flight.avion.nombre}</p>
                    <p><strong>Salida:</strong> ${origen.ciudad}</p>
                </div>
                <div class="col-md-6">
                    <p><strong>Fecha:</strong> ${new Date(flight.fechaSalida).toLocaleDateString()}</p>
                    <p><strong>Destino:</strong> ${destino.ciudad}</p>
                </div>
            </div>
            <button class="btn btn-danger btn-sm mt-2 w-100 cancel-btn" data-id="${reservation.id}">
                Cancelar Reserva
            </button>
        `;

            reservasContainer.appendChild(reservationElement);
        });
    }

    // Crear una nueva reserva
    async function createReservation() {
        const userData = {
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value,
            dni: document.getElementById('dni').value,
            mail: document.getElementById('email').value
        };

        if (!selectedFlight || !userData.nombre || !userData.apellido || !userData.dni || !userData.mail) {
            alert('Por favor complete todos los campos y seleccione un vuelo');
            return;
        }

        try {
            // 1. Crear usuario
            const userResponse = await fetch('http://localhost:9000/api/v1/usuarios', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(userData)
            });

            if (!userResponse.ok) throw new Error('Error al crear usuario');

            const newUser = await userResponse.json();
            console.log("Usuario creado:", newUser); // Debug

            // 2. Crear reserva
            const reservationData = {
                usuario: { id: newUser.id },
                vuelo: { id: selectedFlight.id }
            };

            console.log("Enviando reserva:", reservationData); // Debug

            const reservationResponse = await fetch('http://localhost:9000/api/v1/reservas', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(reservationData)
            });

            if (!reservationResponse.ok) {
                const error = await reservationResponse.json();
                throw new Error(error.error || 'Error al crear reserva');
            }

            await loadReservations();


            // Mostrar confirmación
            document.getElementById('reserva-details').innerHTML = `
                <strong>Vuelo:</strong> ${selectedFlight.avion.nombre}<br>
                <strong>Pasajero:</strong> ${userData.nombre} ${userData.apellido}<br>
                <strong>Fecha:</strong> ${new Date(selectedFlight.fechaSalida).toLocaleDateString()}
            `;
            confirmationModal.show();

            // Resetear selección
            selectedFlight = null;
            userForm.reset();
            document.getElementById('reserva-section').classList.add('d-none');
            renderFlights(flights);

        } catch (error) {
            console.error('Error completo:', error);
            alert('Error al crear reserva: ' + error.message); // Muestra el error real
        }
    }

    // Cancelar una reserva
    async function cancelReservation(reservationId) {
        if (!confirm('¿Está seguro que desea cancelar esta reserva?')) return;

        try {
            await fetch(`http://localhost:9000/api/v1/reservas/${reservationId}`, {
                method: 'DELETE'
            });

            // Actualizar UI
            reservations = reservations.filter(r => r.id != reservationId);
            renderReservations();

        } catch (error) {
            console.error('Error cancelando reserva:', error);
            alert('Error al cancelar la reserva');
        }
    }

    // Filtrar vuelos
    function filterFlights() {
        const origenId = origenSelect.value;
        const destinoId = destinoSelect.value;

        let filteredFlights = flights;

        if (origenId) {
            filteredFlights = filteredFlights.filter(f => f.salida.id == origenId);
        }

        if (destinoId) {
            filteredFlights = filteredFlights.filter(f => f.destino.id == destinoId);
        }

        renderFlights(filteredFlights);
    }

    // Configurar event listeners
    function setupEventListeners() {
        reservarBtn.addEventListener('click', createReservation);
        buscarBtn.addEventListener('click', filterFlights);

        document.querySelector('.clear-filters').addEventListener('click', () => {
            origenSelect.value = '';
            destinoSelect.value = '';
            renderFlights(flights);
        });
    }
});