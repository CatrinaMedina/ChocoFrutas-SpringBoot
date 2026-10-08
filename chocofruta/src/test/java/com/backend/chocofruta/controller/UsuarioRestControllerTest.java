package com.backend.chocofruta.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.backend.chocofruta.entities.Usuario;
import com.backend.chocofruta.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc 
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean 
    private UsuarioServices usuarioService;

    private List<Usuario> usuariosLista;

    @BeforeEach
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        usuariosLista = new ArrayList<>();

        Usuario u1 = new Usuario();
        u1.setId(1L);
        u1.setUsername("admin");
        u1.setEmail("admin@chocofruta.cl");
        u1.setPassword("admin123");
        u1.setRol(Usuario.Rol.ADMIN);
        u1.setEnabled(true);
        u1.setCreatedAt(LocalDateTime.now());

        Usuario u2 = new Usuario();
        u2.setId(2L);
        u2.setUsername("juan");
        u2.setEmail("juan@gmail.com");
        u2.setPassword("cliente123");
        u2.setRol(Usuario.Rol.CLIENTE);
        u2.setEnabled(true);
        u2.setCreatedAt(LocalDateTime.now());

        usuariosLista.add(u1);
        usuariosLista.add(u2);
    }

    @Test
    public void testListarUsuarios() throws Exception {
        when(usuarioService.findAll()).thenReturn(usuariosLista);

        mockMvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCrearUsuario() throws Exception {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername("maria");
        nuevoUsuario.setEmail("maria@gmail.com");
        nuevoUsuario.setPassword("cliente123");
        nuevoUsuario.setRol(Usuario.Rol.CLIENTE);

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(3L);
        usuarioGuardado.setUsername(nuevoUsuario.getUsername());
        usuarioGuardado.setEmail(nuevoUsuario.getEmail());
        usuarioGuardado.setPassword(nuevoUsuario.getPassword());
        usuarioGuardado.setRol(nuevoUsuario.getRol());
        usuarioGuardado.setEnabled(true);
        usuarioGuardado.setCreatedAt(LocalDateTime.now());

        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoUsuario)))
                .andExpect(status().isOk());
    }

    @Test
    public void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = usuariosLista.get(0);
        
        when(usuarioService.findById(1L)).thenReturn(java.util.Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
