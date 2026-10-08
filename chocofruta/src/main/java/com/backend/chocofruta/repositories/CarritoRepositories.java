package com.backend.chocofruta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.chocofruta.entities.Carrito;
import java.util.Optional;

public interface CarritoRepositories extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuario_Username(String username);
}