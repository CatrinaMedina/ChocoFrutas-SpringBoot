package com.backend.chocofruta.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.backend.chocofruta.entities.Producto;
import com.backend.chocofruta.repositories.ProductoRepositories;

@Service
public class ProductoServicesImpl implements ProductoServices{

 @Autowired
    private ProductoRepositories productoRepositories;

    @Override
    public Producto crear(Producto producto){
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }
        Producto unProducto = productoRepositories.save(producto);

        return Objects.requireNonNull(unProducto, "El producto guardado no puede ser null");
    }

    @Override
    public Producto obtenerId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null.");
        }  
        
        return productoRepositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
     

    @Override
    public List<Producto> listarTodas() {
        return productoRepositories.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    @Override
    public List<Producto> listarPagina(Pageable pageable) {
        return productoRepositories.findAll(pageable).getContent();
    }


    @Override
    public void eliminar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null.");
        }

        if (!productoRepositories.existsById(id)) {
           throw new RuntimeException("Producto no encontrado");
        }
       productoRepositories.deleteById(id);
    }

    @Override
    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto existente = obtenerId(id);
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        return productoRepositories.save(existente);
    }

    @Override
    public Producto desactivar(Long id){
        Producto producto = obtenerId(id);
        producto.setActivo(false);
        return productoRepositories.save(producto);
    }

    @Override
    public Producto activar(Long id) {
        Producto producto = obtenerId(id);
        producto.setActivo(true);
        return productoRepositories.save(producto);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepositories.findByNombreContainingIgnoreCase(nombre == null ? "" : nombre.trim());
    }

    @Override
    public List<Producto> filtrarPorCategoria(Long categoriaId) {
        return productoRepositories.findByCategoria_Id(categoriaId);
    }

    @Override
    public List<Producto> obtenerStockBajo() {
        return productoRepositories.findByStockLessThanAndActivoTrue(5);
    }
}
