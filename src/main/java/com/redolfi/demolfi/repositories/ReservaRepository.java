package com.redolfi.demolfi.repositories;

import com.redolfi.demolfi.entities.Reserva;
import com.redolfi.demolfi.entities.Vuelo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r JOIN FETCH r.vuelo v JOIN FETCH v.avion JOIN FETCH r.usuario")
    List<Reserva> findAllWithVueloDetails();

    Long countByVueloId(Long vueloId);

    @Query("SELECT v FROM Vuelo v LEFT JOIN FETCH v.reservas WHERE v.id = :id")
    Optional<Vuelo> findVueloWithReservas(Long id);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.vuelo v JOIN FETCH v.avion JOIN FETCH v.salida JOIN FETCH v.destino")
    List<Reserva> findAllWithDetails();

}
