package com.redolfi.demolfi.config;

import com.redolfi.demolfi.entities.*;
import com.redolfi.demolfi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Configuration
public class DataLoader {

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    @Autowired
    private AvionRepository avionRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Bean
    @Transactional
    public CommandLineRunner initData() {
        return args -> {
            // Crear aeropuertos
            Aeropuerto aeropuerto1 = new Aeropuerto();
            aeropuerto1.setNombre("Aeropuerto Internacional Ezeiza");
            aeropuerto1.setCiudad("Buenos Aires");
            aeropuerto1.setPais("Argentina");

            Aeropuerto aeropuerto2 = new Aeropuerto();
            aeropuerto2.setNombre("Aeroparque Jorge Newbery");
            aeropuerto2.setCiudad("Buenos Aires");
            aeropuerto2.setPais("Argentina");

            // ... (similar para aeropuerto3 y aeropuerto4)
            Aeropuerto aeropuerto3 = new Aeropuerto();
            aeropuerto3.setNombre("Aeropuerto Internacional de Miami");
            aeropuerto3.setCiudad("Miami");
            aeropuerto3.setPais("EEUU");

            Aeropuerto aeropuerto4 = new Aeropuerto();
            aeropuerto4.setNombre("Aeropuerto Internacional Barajas");
            aeropuerto4.setCiudad("Madrid");
            aeropuerto4.setPais("España");

            aeropuertoRepository.saveAll(Arrays.asList(aeropuerto1, aeropuerto2, aeropuerto3, aeropuerto4));

            // Crear aviones con capacidades diferentes
            Avion avion1 = new Avion();
            avion1.setNombre("Boeing 737");
            avion1.setCapacidad(2);

            Avion avion2 = new Avion();
            avion2.setNombre("Airbus A320");
            avion2.setCapacidad(3);

            Avion avion3 = new Avion();
            avion3.setNombre("Boeing 747");
            avion3.setCapacidad(4);

            Avion avion4 = new Avion();
            avion4.setNombre("Airbus A380");
            avion4.setCapacidad(5);

            avionRepository.saveAll(Arrays.asList(avion1, avion2, avion3, avion4));

            // Fechas para los vuelos
            Date hoy = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoy);

            // Crear vuelos con setters
            Vuelo vuelo1 = new Vuelo();
            vuelo1.setAvion(avion1);
            vuelo1.setFechaSalida(hoy);
            vuelo1.setFechaLlegada(agregarHoras(cal, 2));
            vuelo1.setSalida(aeropuerto1);
            vuelo1.setDestino(aeropuerto3);

            Vuelo vuelo2 = new Vuelo();
            vuelo2.setAvion(avion2);
            vuelo2.setFechaSalida(agregarHoras(cal, 24));
            vuelo2.setFechaLlegada(agregarHoras(cal, 26));
            vuelo2.setSalida(aeropuerto2);
            vuelo2.setDestino(aeropuerto4);

            Vuelo vuelo3 = new Vuelo();
            vuelo3.setAvion(avion3);
            vuelo3.setFechaSalida(agregarHoras(cal, 48));
            vuelo3.setFechaLlegada(agregarHoras(cal, 51));
            vuelo3.setSalida(aeropuerto3);
            vuelo3.setDestino(aeropuerto1);

            Vuelo vuelo4 = new Vuelo();
            vuelo4.setAvion(avion4);
            vuelo4.setFechaSalida(agregarHoras(cal, 72));
            vuelo4.setFechaLlegada(agregarHoras(cal, 76));
            vuelo4.setSalida(aeropuerto4);
            vuelo4.setDestino(aeropuerto2);

            vueloRepository.saveAll(Arrays.asList(vuelo1, vuelo2, vuelo3, vuelo4));

            // Crear usuario con setters
            Usuario usuario = new Usuario();
            usuario.setNombre("Juan");
            usuario.setApellido("Perez");
            usuario.setDni("12345678");
            usuario.setMail("juan@example.com");

            usuarioRepository.save(usuario);

            // Crear reserva para el usuario
            Reserva reserva = new Reserva();
            reserva.setVuelo(vuelo1);
            reserva.setUsuario(usuario);

            reservaRepository.save(reserva);

            System.out.println("¡Datos iniciales cargados exitosamente!");
        };
    }

    private Date agregarHoras(Calendar cal, int horas) {
        Calendar newCal = (Calendar) cal.clone();
        newCal.add(Calendar.HOUR_OF_DAY, horas);
        return newCal.getTime();
    }
}
