package com.backend.chocofruta.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.chocofruta.entities.Usuario;
import com.backend.chocofruta.repositories.UsuarioRepositories;

@SpringBootTest 
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepositories usuarioRepositories;

    @Mock
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServicesImpl usuarioServiceImpl;

    private Usuario usuario; 

    @BeforeEach
    public void inicio() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("juan");
        usuario.setEmail("juan@mail.com");
        usuario.setPassword("cliente123");
        usuario.setRol(Usuario.Rol.CLIENTE);
        usuario.setEnabled(true);
        usuario.setCreatedAt(LocalDateTime.now());
    }

    @Test 
    public void findByAllTest() {
        List<Usuario> lista = Arrays.asList(usuario);
        when(usuarioRepositories.findAll()).thenReturn(lista);
        List<Usuario> resultado = usuarioServiceImpl.findAll();
        assertEquals(1, resultado.size());
        verify(usuarioRepositories).findAll();
    }

    @Test 
    public void findByIdTest() {
        when(usuarioRepositories.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> resultado = usuarioServiceImpl.findById(1L);
        assertNotNull(resultado);
        assertEquals("juan", resultado.get().getUsername());
        verify(usuarioRepositories).findById(1L);
    }

    @Test 
    public void saveTest() {
        when(passwordEncoder.encode(any(String.class))).thenReturn("$2a$dummyhash");
        when(usuarioRepositories.save(any(Usuario.class))).thenReturn(usuario);
        Usuario usuarioTest = usuarioServiceImpl.save(usuario);
        assertNotNull(usuarioTest);
        assertEquals("juan", usuarioTest.getUsername());
        verify(usuarioRepositories).save(any(Usuario.class));
    }

    @Test 
    public void findByEmailTest() {
        when(usuarioRepositories.findByEmail("juan@mail.com")).thenReturn(Optional.of(usuario));
        Optional<Usuario> resultado = usuarioServiceImpl.findByEmail("juan@mail.com");
        assertNotNull(resultado);
        assertEquals("juan@mail.com", resultado.get().getEmail());
        verify(usuarioRepositories).findByEmail("juan@mail.com");
    }
}
