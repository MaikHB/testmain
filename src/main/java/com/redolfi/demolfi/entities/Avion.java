package com.redolfi.demolfi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="avion")
public class Avion extends Base {
    private String nombre;
    private int capacidad;

    //@OneToMany(mappedBy = "avion",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Asiento> asientos;
}
