package com.backend.chocofruta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.backend.chocofruta.entities.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Map;
import com.backend.chocofruta.repositories.UsuarioRepositories;

@RestController
@RequestMapping("/api/perfil")
@CrossOrigin(origins = {"http://localhost:5173"})
public class PerfilRestController {

    private final UsuarioRepositories usuarioRepo;
    private final PasswordEncoder encoder;

    public PerfilRestController(UsuarioRepositories usuarioRepo, PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.encoder = encoder;
    }

    @GetMapping
    public ResponseEntity<Usuario> obtener(Authentication auth) {
        Usuario u = usuarioRepo.findByUsername(auth.getName()).orElseThrow();
        return ResponseEntity.ok(u);
    }

    @PutMapping
    public ResponseEntity<Usuario> actualizar(Authentication auth, @RequestBody Usuario datos) {
        Usuario u = usuarioRepo.findByUsername(auth.getName()).orElseThrow();
        if (datos.getEmail() != null && !datos.getEmail().isBlank()) {
            String nuevoEmail = datos.getEmail().trim();
            if (!nuevoEmail.equalsIgnoreCase(u.getEmail())) {
                var existente = usuarioRepo.findByEmailIgnoreCase(nuevoEmail);
                if (existente.isPresent() && !existente.get().getId().equals(u.getId())) {
                    return ResponseEntity.status(409).build();
                }
                u.setEmail(nuevoEmail);
            }
        }
        if (datos.getUsername() != null && !datos.getUsername().isBlank()) {
            String nuevoUser = datos.getUsername().trim();
            if (!nuevoUser.equalsIgnoreCase(u.getUsername())) {
                var existente = usuarioRepo.findByUsernameIgnoreCase(nuevoUser);
                if (existente.isPresent() && !existente.get().getId().equals(u.getId())) {
                    return ResponseEntity.status(409).build();
                }
                u.setUsername(nuevoUser);
            }
        }
        return ResponseEntity.ok(usuarioRepo.save(u));
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> cambiarPassword(Authentication auth, @RequestBody Map<String, String> body) {
        Usuario u = usuarioRepo.findByUsername(auth.getName()).orElseThrow();
        String actual = body.getOrDefault("actual", body.getOrDefault("password", "")).trim();
        String nueva = body.getOrDefault("nueva", body.getOrDefault("newPassword", "")).trim();
        if (actual.isBlank() || nueva.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (!encoder.matches(actual, u.getPassword())) {
            return ResponseEntity.status(403).build();
        }
        if (nueva.length() < 8) {
            return ResponseEntity.badRequest().build();
        }
        u.setPassword(encoder.encode(nueva));
        usuarioRepo.save(u);
        return ResponseEntity.ok().build();
    }
}
