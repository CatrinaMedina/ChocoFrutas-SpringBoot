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

import com.backend.chocofruta.entities.Categoria;
import com.backend.chocofruta.entities.Producto;
import com.backend.chocofruta.services.ProductoServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductoServices productoService;

    private List<Producto> productosLista;
    private Categoria categoria;

    @BeforeEach
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Chocolates");

        productosLista = new ArrayList<>();

        Producto p1 = new Producto();
        p1.setId(1L);
        p1.setNombre("Chocolate Amargo");
        p1.setDescripcion("Chocolate 70% cacao");
        p1.setPrecio(2500L);
        p1.setStock(10);
        p1.setActivo(true);
        p1.setCategoria(categoria);
        p1.setFechaCreacion(LocalDateTime.now());

        Producto p2 = new Producto();
        p2.setId(2L);
        p2.setNombre("Chocolate Blanco");
        p2.setDescripcion("Chocolate blanco premium");
        p2.setPrecio(2800L);
        p2.setStock(3);
        p2.setActivo(true);
        p2.setCategoria(categoria);
        p2.setFechaCreacion(LocalDateTime.now());

        productosLista.add(p1);
        productosLista.add(p2);
    }

    @Test
    public void testListarProductos() throws Exception {
        when(productoService.listarTodas()).thenReturn(productosLista);

        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testObtenerProductoPorId() throws Exception {
        Producto producto = productosLista.get(0);
        
        when(productoService.obtenerId(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCrearProducto() throws Exception {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Chocolate con Leche");
        nuevoProducto.setDescripcion("Chocolate suave y cremoso - 100g");
        nuevoProducto.setPrecio(2000L);
        nuevoProducto.setStock(35);
        nuevoProducto.setCategoria(categoria);

        Producto productoGuardado = new Producto();
        productoGuardado.setId(3L);
        productoGuardado.setNombre(nuevoProducto.getNombre());
        productoGuardado.setDescripcion(nuevoProducto.getDescripcion());
        productoGuardado.setPrecio(nuevoProducto.getPrecio());
        productoGuardado.setStock(nuevoProducto.getStock());
        productoGuardado.setActivo(true);
        productoGuardado.setCategoria(categoria);
        productoGuardado.setFechaCreacion(LocalDateTime.now());

        when(productoService.crear(any(Producto.class))).thenReturn(productoGuardado);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testObtenerStockBajo() throws Exception {
        List<Producto> stockBajo = new ArrayList<>();
        stockBajo.add(productosLista.get(1)); 

        when(productoService.obtenerStockBajo()).thenReturn(stockBajo);

        mockMvc.perform(get("/api/productos/stock-bajo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
