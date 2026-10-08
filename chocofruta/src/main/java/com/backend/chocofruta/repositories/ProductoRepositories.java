package com.backend.chocofruta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.chocofruta.entities.Producto;
import java.util.List;

@Repository 
public interface  ProductoRepositories extends JpaRepository<Producto, Long>{
    List<Producto> findByNombreContainingIgnoreCase(String nombre); 
    List<Producto> findByCategoria_Id(Long categoriaId); 
    List<Producto> findByStockLessThanAndActivoTrue(Integer stock); 
}

