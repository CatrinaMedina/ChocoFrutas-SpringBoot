package com.backend.chocofruta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.chocofruta.entities.ItemCarrito;
import java.util.List;
import java.util.Optional;

public interface ItemCarritoRepositories extends JpaRepository<ItemCarrito, Long> {
    List<ItemCarrito> findByCarrito_Id(Long carritoId);
    Optional<ItemCarrito> findByCarrito_IdAndProducto_Id(Long carritoId, Long productoId);
    void deleteByCarrito_Id(Long carritoId);
}
