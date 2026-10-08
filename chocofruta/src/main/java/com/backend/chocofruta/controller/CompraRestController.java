package com.backend.chocofruta.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.backend.chocofruta.entities.Boleta;
import com.backend.chocofruta.models.CheckoutData;
import com.backend.chocofruta.services.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Boletas y Compras", description = "Generación de boletas e historial de compras")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CompraRestController {

    private final CompraService compraService;

    public CompraRestController(CompraService compraService) {
        this.compraService = compraService;
    }

    @Operation(summary = "Generar boleta desde carrito")
    @PostMapping("/boletas")
    public ResponseEntity<Boleta> generar(Authentication auth, @RequestBody(required = false) CheckoutData data) {
        return ResponseEntity.ok(compraService.checkout(auth.getName(), data));
    }

    @Operation(summary = "Historial del usuario autenticado")
    @GetMapping("/boletas/historial")
    public ResponseEntity<List<Boleta>> historialBoletas(Authentication auth) {
        return ResponseEntity.ok(compraService.historial(auth.getName()));
    }
}
