package com.backend.chocofruta.repositories;

import org.springframework.data.repository.CrudRepository;
import com.backend.chocofruta.entities.Usuario;
import java.util.Optional;

public interface UsuarioRepositories extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); 
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmailIgnoreCase(String email);
    Optional<Usuario> findByUsernameIgnoreCase(String username);
    boolean existsByEmail(String email); 
    boolean existsByUsername(String username);
}
