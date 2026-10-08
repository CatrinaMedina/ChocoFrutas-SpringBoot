package com.backend.chocofruta.services;

import com.backend.chocofruta.entities.ItemCarrito;
import com.backend.chocofruta.entities.Carrito;
import java.util.List;

public interface CarritoService {
    Carrito obtenerCarrito(String username);
    List<ItemCarrito> listarItems(String username);
    ItemCarrito agregarItem(String username, Long productoId, Integer cantidad);
    void eliminarItem(String username, Long itemId);
    ItemCarrito actualizarCantidad(String username, Long itemId, Integer cantidad);
    void vaciar(String username);
}
