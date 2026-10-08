package com.backend.chocofruta.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.backend.chocofruta.entities.ItemCarrito;
import com.backend.chocofruta.services.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Carrito de Compras", description = "Gestión del carrito del usuario")
@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "http://localhost:5173")
public class CarritoRestController {

    private final CarritoService carritoService;

    public CarritoRestController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @Operation(summary = "Ver carrito")
    @GetMapping
    public ResponseEntity<List<ItemCarrito>> listar(Authentication auth) {
        return ResponseEntity.ok(carritoService.listarItems(auth.getName()));
    }

    @Operation(summary = "Agregar 1 producto al carrito")
    @PostMapping
    public ResponseEntity<ItemCarrito> agregarBody(Authentication auth, @RequestBody CarritoItemAdd body) {
        return ResponseEntity.ok(carritoService.agregarItem(auth.getName(), body.productoId(), body.cantidad()));
    }

    @Operation(summary = "Actualizar cantidad de un ítem")
    @PutMapping("/{itemId}")
    public ResponseEntity<ItemCarrito> actualizar(Authentication auth, @PathVariable Long itemId, @RequestBody CarritoItemUpdate body) {
        return ResponseEntity.ok(carritoService.actualizarCantidad(auth.getName(), itemId, body.cantidad()));
    }

    @Operation(summary = "Eliminar ítem del carrito")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> eliminarRest(Authentication auth, @PathVariable Long itemId) {
        carritoService.eliminarItem(auth.getName(), itemId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Vaciar carrito")
    @DeleteMapping
    public ResponseEntity<Void> vaciar(Authentication auth) {
        carritoService.vaciar(auth.getName());
        return ResponseEntity.noContent().build();
    }

    public record CarritoItemAdd(Long productoId, Integer cantidad) {}
    public record CarritoItemUpdate(Integer cantidad) {}
}
