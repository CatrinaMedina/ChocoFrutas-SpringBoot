package com.backend.chocofruta.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private Boolean esAdmin;
    private String mensaje;
}