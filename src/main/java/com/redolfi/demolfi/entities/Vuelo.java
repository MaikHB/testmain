package com.redolfi.demolfi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vuelo")
public class Vuelo extends Base {
    @ManyToOne
    private Avion avion;
    @Column(name="fecha_salida")
    private Date fechaSalida;
    @Column(name="fecha_llegada")
    private Date fechaLlegada;

    @ManyToOne
    private Aeropuerto salida;

    @ManyToOne
    private Aeropuerto destino;

    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();
}
