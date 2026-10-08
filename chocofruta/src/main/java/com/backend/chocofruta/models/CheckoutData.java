package com.backend.chocofruta.models;

import lombok.Data;

@Data
public class CheckoutData {
    private String direccion;
    private String region;
    private String comuna;
    private String metodoPago;
}
