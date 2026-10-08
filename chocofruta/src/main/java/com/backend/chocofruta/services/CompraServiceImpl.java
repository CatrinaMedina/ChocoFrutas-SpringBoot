package com.backend.chocofruta.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.chocofruta.entities.*;
import com.backend.chocofruta.models.CheckoutData;
import com.backend.chocofruta.repositories.*;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CarritoRepositories carritoRepo;

    @Autowired
    private ItemCarritoRepositories itemRepo;

    @Autowired
    private BoletaRepositories boletaRepo;

    @Autowired
    private DetalleBoletaRepositories detalleRepo;

    @Autowired
    private UsuarioRepositories usuarioRepo;

    @Autowired
    private ProductoRepositories productoRepo;

    @Override
    public Boleta checkout(String username, CheckoutData data) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        Carrito c = carritoRepo.findByUsuario_Username(username)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(u);
                    return carritoRepo.save(nuevo);
                });
        List<ItemCarrito> items = itemRepo.findByCarrito_Id(c.getId());
        if (items.isEmpty()) {
            throw new RuntimeException("Carrito vacío");
        }

        long neto = items.stream().mapToLong(i -> i.getSubtotal()).sum();
        long iva = Math.round(neto * 0.19);
        long total = neto + iva;

        Long numero = boletaRepo.findTopByOrderByNumeroDesc()
                .map(b -> b.getNumero() + 1)
                .orElse(1L);

        Boleta b = new Boleta();
        b.setNumero(numero);
        b.setUsuario(u);
        b.setFecha(LocalDateTime.now());
        b.setNeto(neto);
        b.setIva(iva);
        b.setTotal(total);
        if (data != null) {
            b.setDireccion(data.getDireccion());
            b.setRegion(data.getRegion());
            b.setComuna(data.getComuna());
            b.setMetodoPago(data.getMetodoPago());
        }
        Boleta saved = boletaRepo.save(b);

        for (ItemCarrito i : items) {
            DetalleBoleta d = new DetalleBoleta();
            d.setBoleta(saved);
            d.setProducto(i.getProducto());
            d.setCantidad(i.getCantidad());
            d.setPrecioUnitario(i.getPrecioUnitario());
            d.setSubtotal(i.getSubtotal());
            detalleRepo.save(d);

            Producto p = i.getProducto();
            if (p != null) {
                int actual = p.getStock() == null ? 0 : p.getStock();
                int comprado = i.getCantidad() == null ? 0 : i.getCantidad();
                int nuevoStock = Math.max(0, actual - comprado);
                p.setStock(nuevoStock);
                productoRepo.save(p);
            }
        }

        itemRepo.deleteAll(items);
        return boletaRepo.findById(saved.getId()).orElseThrow();
    }

    @Override
    public List<Boleta> historial(String username) {
        return boletaRepo.findByUsuario_UsernameOrderByFechaDesc(username);
    }
}
