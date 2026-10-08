package com.backend.chocofruta.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.chocofruta.entities.*;
import com.backend.chocofruta.repositories.*;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepositories carritoRepo;

    @Autowired
    private ItemCarritoRepositories itemRepo;

    @Autowired
    private ProductoRepositories productoRepo;

    @Autowired
    private UsuarioRepositories usuarioRepo;

    @Override
    public Carrito obtenerCarrito(String username) {
        return carritoRepo.findByUsuario_Username(username)
                .orElseGet(() -> {
                    Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
                    Carrito c = new Carrito();
                    c.setUsuario(u);
                    return carritoRepo.save(c);
                });
    }

    @Override
    public List<ItemCarrito> listarItems(String username) {
        Carrito c = obtenerCarrito(username);
        return itemRepo.findByCarrito_Id(c.getId());
    }

    @Override
    public ItemCarrito agregarItem(String username, Long productoId, Integer cantidad) {
        Carrito c = obtenerCarrito(username);
        Producto p = productoRepo.findById(productoId).orElseThrow();

        return itemRepo.findByCarrito_IdAndProducto_Id(c.getId(), productoId)
                .map(existing -> {
                    int nuevaCantidad = (existing.getCantidad() == null ? 0 : existing.getCantidad()) + cantidad;
                    existing.setCantidad(nuevaCantidad);
                    existing.setPrecioUnitario(p.getPrecio());
                    existing.setSubtotal(p.getPrecio() * nuevaCantidad);
                    return itemRepo.save(existing);
                })
                .orElseGet(() -> {
                    ItemCarrito item = new ItemCarrito();
                    item.setCarrito(c);
                    item.setProducto(p);
                    item.setCantidad(cantidad);
                    item.setPrecioUnitario(p.getPrecio());
                    item.setSubtotal(p.getPrecio() * cantidad);
                    return itemRepo.save(item);
                });
    }

    @Override
    public void eliminarItem(String username, Long itemId) {
        Carrito c = obtenerCarrito(username);
        ItemCarrito item = itemRepo.findById(itemId).orElseThrow();
        if (!item.getCarrito().getId().equals(c.getId())) {
            throw new RuntimeException("Item no pertenece al carrito del usuario");
        }
        itemRepo.deleteById(itemId);
    }

    @Override
    public ItemCarrito actualizarCantidad(String username, Long itemId, Integer cantidad) {
        Carrito c = obtenerCarrito(username);
        ItemCarrito item = itemRepo.findById(itemId).orElseThrow();
        if (!item.getCarrito().getId().equals(c.getId())) {
            throw new RuntimeException("Item no pertenece al carrito del usuario");
        }
        if (cantidad == null || cantidad < 1) {
            throw new RuntimeException("Cantidad inválida");
        }
        int nueva = cantidad;
        item.setCantidad(nueva);
        long precio = item.getPrecioUnitario();
        item.setSubtotal(precio * nueva);
        return itemRepo.save(item);
    }

    @Override
    public void vaciar(String username) {
        Carrito c = obtenerCarrito(username);
        List<ItemCarrito> items = itemRepo.findByCarrito_Id(c.getId());
        itemRepo.deleteAll(items);
    }
}
