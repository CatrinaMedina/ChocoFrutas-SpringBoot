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

import com.backend.chocofruta.entities.Categoria;
import com.backend.chocofruta.entities.Producto;
import com.backend.chocofruta.repositories.ProductoRepositories;

@SpringBootTest 
public class ProductoServiceTest {

    @Mock 
    private ProductoRepositories productoRepositories;

    @InjectMocks 
    private ProductoServicesImpl productoServiceImpl;
 
    private Producto producto; 
    private Categoria categoria; 

    @BeforeEach
    public void inicio() {
        categoria = new Categoria(1L, "Chocolates", null);
        producto = new Producto(1L, "Chocolate Bitter", "Chocolate 70% cacao", 2500L, 10, "chocolate70.jpg", true, LocalDateTime.now(), categoria);
    }

    @Test 
    public void findByAllTest() {
        List<Producto> lista = Arrays.asList(producto);
        when(productoRepositories.findAll()).thenReturn(lista);
        List<Producto> resultado = productoServiceImpl.listarTodas();
        assertEquals(1, resultado.size());
        verify(productoRepositories).findAll();
    }

    @Test 
    public void findByIdTest() {
        when(productoRepositories.findById(1L)).thenReturn(Optional.of(producto));
        Producto resultado = productoServiceImpl.obtenerId(1L);
        assertNotNull(resultado);
        assertEquals("Chocolate Bitter", resultado.getNombre());
        verify(productoRepositories).findById(1L);
    }

    @Test 
    public void saveTest() {
        when(productoRepositories.save(any(Producto.class))).thenReturn(producto);
        Producto productoTest = productoServiceImpl.crear(producto);
        assertNotNull(productoTest);
        assertEquals("Chocolate Bitter", productoTest.getNombre());
        verify(productoRepositories).save(any(Producto.class));
    }

    @Test 
    public void buscarPorNombreTest() {
        List<Producto> lista = Arrays.asList(producto);
        when(productoRepositories.findByNombreContainingIgnoreCase("Chocolate")).thenReturn(lista);
        List<Producto> resultado = productoServiceImpl.buscarPorNombre("Chocolate");
        assertEquals(1, resultado.size());
        assertEquals("Chocolate Bitter", resultado.get(0).getNombre());
        verify(productoRepositories).findByNombreContainingIgnoreCase("Chocolate");
    }

    @Test 
    public void obtenerStockBajoTest() {
        Producto productoStockBajo = new Producto(2L, "Producto Bajo", "Descripción", 1500L, 3, "chocolate70.jpg", true, LocalDateTime.now(), categoria);
        List<Producto> lista = Arrays.asList(productoStockBajo);
        when(productoRepositories.findByStockLessThanAndActivoTrue(5)).thenReturn(lista);
        List<Producto> resultado = productoServiceImpl.obtenerStockBajo();
        assertEquals(1, resultado.size());
        verify(productoRepositories).findByStockLessThanAndActivoTrue(5);
    }
}
