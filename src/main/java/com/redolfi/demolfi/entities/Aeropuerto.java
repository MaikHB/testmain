package com.redolfi.demolfi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="aeropuerto")
public class Aeropuerto extends Base {
    private String nombre;
    private String ciudad;
    private String pais;
}
