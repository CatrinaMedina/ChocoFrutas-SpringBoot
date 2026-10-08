package com.backend.chocofruta.services;

import com.backend.chocofruta.entities.Categoria;
import java.util.List;

public interface CategoriaService {
    Categoria crear(Categoria categoria); 
    Categoria obtenerId(Long id); 
    List<Categoria> listarTodas();
    void eliminar(Long id);
    Categoria actualizar(Long id, Categoria categoriaActualizada);
}