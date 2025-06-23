package com.redolfi.demolfi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reserva")
public class Reserva extends Base {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"reservas"})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}