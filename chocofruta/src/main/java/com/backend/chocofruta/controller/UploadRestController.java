package com.backend.chocofruta.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Carga de Imágenes", description = "API para subir y obtener imágenes de productos")
@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/api/upload")
public class UploadRestController {

    private static final String uri = "uploads/";

    @Operation(summary = "Subir imagen", description = "Sube una imagen al servidor y retorna la ruta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "No se envió ninguna imagen"),
        @ApiResponse(responseCode = "500", description = "Error al guardar la imagen")
    })
    @PostMapping(value = "/imagenes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagen(@RequestParam("imagen") MultipartFile archivo){
        if (archivo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha enviado ninguna imagen.");
        }

        String originalFilename = archivo.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre de archivo inválido.");
        }

        String contentType = archivo.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El archivo debe ser una imagen.");
        }

        try{
            Path uploadDir = Paths.get(uri);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String safeFilename = Paths.get(originalFilename).getFileName().toString();
            Path ruta = uploadDir.resolve(safeFilename).normalize();
            Files.write(ruta, archivo.getBytes());

            return ResponseEntity.ok("/uploads/" + safeFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Se ha producido un error al guardar la imagen.");
        }
    }

    @Operation(summary = "Obtener imagen", description = "Obtiene una imagen del servidor por nombre de archivo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagen obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "Imagen no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error al leer la imagen")
    })
    @GetMapping("/imagenes/{filename:.+}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable String filename) {
        try {
            Path ruta = Paths.get(uri + filename);
            
            if (!Files.exists(ruta)) {
                return ResponseEntity.notFound().build();
            }

            byte[] imagen = Files.readAllBytes(ruta);
            
            String contentType = Files.probeContentType(ruta);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imagen);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
