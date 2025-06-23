package com.redolfi.demolfi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="usuario")
public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String dni;
    private String mail;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();
}
