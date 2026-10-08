package com.backend.chocofruta.services;

import com.backend.chocofruta.entities.Producto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProductoServices {
    Producto crear(Producto producto); 
    Producto obtenerId(Long id);
    List<Producto> listarTodas(); 
    List<Producto> listarPagina(Pageable pageable);
    void eliminar(Long id); 
    Producto actualizar(Long id, Producto productoActualizado); 
    Producto desactivar(Long id); 
    Producto activar(Long id);
    List<Producto> buscarPorNombre(String nombre); 
    List<Producto> filtrarPorCategoria(Long categoriaId); 
    List<Producto> obtenerStockBajo();
}
