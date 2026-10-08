package com.backend.chocofruta.security;

import com.backend.chocofruta.entities.Usuario;
import com.backend.chocofruta.repositories.UsuarioRepositories;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepositories usuarioRepo;

    public CustomUserDetailsService(UsuarioRepositories usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String normalized = username == null ? "" : username.trim();
        Usuario usuario = usuarioRepo.findByUsernameIgnoreCase(normalized)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + normalized));
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()));
        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}
