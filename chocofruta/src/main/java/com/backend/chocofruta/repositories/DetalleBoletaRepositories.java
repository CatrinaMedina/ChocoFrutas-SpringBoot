package com.backend.chocofruta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.chocofruta.entities.DetalleBoleta;

public interface DetalleBoletaRepositories extends JpaRepository<DetalleBoleta, Long> {
    void deleteByBoleta_Id(Long boletaId);
}
