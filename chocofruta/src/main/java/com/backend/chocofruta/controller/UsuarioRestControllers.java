package com.backend.chocofruta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.backend.chocofruta.entities.Usuario;
import com.backend.chocofruta.services.UsuarioServices;
import com.backend.chocofruta.repositories.CarritoRepositories;
import com.backend.chocofruta.repositories.BoletaRepositories;
import com.backend.chocofruta.repositories.ItemCarritoRepositories;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"http://localhost:5173"})
public class UsuarioRestControllers {

     private final UsuarioServices usuarioServices;

    

    private final CarritoRepositories carritoRepo;
    private final BoletaRepositories boletaRepo;
    private final ItemCarritoRepositories itemRepo;

    public UsuarioRestControllers(UsuarioServices usuarioServices, CarritoRepositories carritoRepo, BoletaRepositories boletaRepo, ItemCarritoRepositories itemRepo) {
        this.usuarioServices = usuarioServices;
        this.carritoRepo = carritoRepo;
        this.boletaRepo = boletaRepo;
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioServices.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        if (usuarioServices.existsByUsername(usuario.getUsername())
                || usuarioServices.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();  
        }
        Usuario saved = usuarioServices.save(usuario);
        return ResponseEntity.ok(saved);
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioServices.findAll().forEach(usuarios::add);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        return usuarioServices.findById(id)
                .map(existing -> {
                    usuarioActualizado.setId(id);
                    Usuario updated = usuarioServices.save(usuarioActualizado);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Usuario> activar(@PathVariable Long id) {
        return usuarioServices.findById(id)
                .map(u -> {
                    u.setEnabled(true);
                    return ResponseEntity.ok(usuarioServices.save(u));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivar(@PathVariable Long id) {
        return usuarioServices.findById(id)
                .map(u -> {
                    u.setEnabled(false);
                    return ResponseEntity.ok(usuarioServices.save(u));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return usuarioServices.findById(id)
                .map(user -> {
                    String username = user.getUsername();
                    boolean tieneBoletas = boletaRepo.findByUsuario_UsernameOrderByFechaDesc(username).size() > 0;
                    if (tieneBoletas) {
                        return ResponseEntity.status(409).body("Usuario con compras registradas");
                    }
                    carritoRepo.findByUsuario_Username(username).ifPresent(c -> {
                        try {
                            itemRepo.deleteByCarrito_Id(c.getId());
                            carritoRepo.deleteById(c.getId());
                        } catch (Exception ignored) {}
                    });
                    try {
                        usuarioServices.deleteById(id);
                        return ResponseEntity.noContent().build();
                    } catch (DataIntegrityViolationException ex) {
                        return ResponseEntity.status(409).body("No se puede eliminar por referencias");
                    } catch (Exception e) {
                        return ResponseEntity.status(500).body("Error al eliminar: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuario no encontrado"));
    }

}

