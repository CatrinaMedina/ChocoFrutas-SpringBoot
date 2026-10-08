package com.backend.chocofruta.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long numero;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime fecha;

    private Long neto;
    private Long iva;
    private Long total;

    private String direccion;
    private String region;
    private String comuna;
    private String metodoPago;

    @OneToMany(mappedBy = "boleta")
    @JsonManagedReference
    private List<DetalleBoleta> detalles;
}
