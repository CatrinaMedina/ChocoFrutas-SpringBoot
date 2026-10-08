package com.backend.chocofruta.services;

import com.backend.chocofruta.entities.Boleta;
import com.backend.chocofruta.models.CheckoutData;

import java.util.List;

public interface CompraService {
    Boleta checkout(String username, CheckoutData data);
    List<Boleta> historial(String username);
}
