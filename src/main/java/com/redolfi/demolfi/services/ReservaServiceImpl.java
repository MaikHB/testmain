package com.redolfi.demolfi.services;

import com.redolfi.demolfi.entities.Reserva;
import com.redolfi.demolfi.entities.Vuelo;
import com.redolfi.demolfi.repositories.ReservaRepository;
import com.redolfi.demolfi.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Long> implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        super(reservaRepository);
        this.reservaRepository = reservaRepository;
    }
    @Override
    @Transactional
    public Reserva save(Reserva reserva) throws Exception {
        // Carga el vuelo con las reservas usando JOIN FETCH
        Vuelo vuelo = reservaRepository.findVueloWithReservas(reserva.getVuelo().getId())
                .orElseThrow(() -> new Exception("Vuelo no encontrado"));

        if (vuelo.getReservas().size() >= vuelo.getAvion().getCapacidad()) {
            throw new Exception("Avión lleno. Capacidad: " + vuelo.getAvion().getCapacidad());
        }

        reserva.setVuelo(vuelo);
        return super.save(reserva);
    }
    @Override
    public List<Reserva> findAll() throws Exception {
        return reservaRepository.findAllWithVueloDetails();
    }
}
