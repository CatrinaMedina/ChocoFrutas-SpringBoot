package com.backend.chocofruta.controller;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.chocofruta.entities.Boleta;
import com.backend.chocofruta.repositories.BoletaRepositories;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin - Compras", description = "Operaciones administrativas sobre compras")
@RestController
@RequestMapping("/api/admin/compras")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminComprasRestController {

    private final BoletaRepositories boletaRepo;

    public AdminComprasRestController(BoletaRepositories boletaRepo) {
        this.boletaRepo = boletaRepo;
    }

    @Operation(summary = "Listar compras")
    @GetMapping
    public ResponseEntity<List<Boleta>> listar(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fecha"));
        return ResponseEntity.ok(boletaRepo.findAll(pr).getContent());
    }

    

    
}
