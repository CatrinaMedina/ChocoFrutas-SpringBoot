package com.backend.chocofruta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.chocofruta.entities.Boleta;
import java.util.List;
import java.util.Optional;

public interface BoletaRepositories extends JpaRepository<Boleta, Long> {
    Optional<Boleta> findTopByOrderByNumeroDesc();
    List<Boleta> findByUsuario_UsernameOrderByFechaDesc(String username);
    Optional<Boleta> findByNumero(Long numero);
}
