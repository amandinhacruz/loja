package com.example.controller;

import com.example.dto.CategoriaDTO;
import com.example.model.Categoria;
import com.example.repository.CategoriaRepository;
import com.example.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategoria() {
        return categoriaService.listarCategoria();
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.criarCategoria(categoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.atualizarCategoria(id, categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria (@PathVariable Long id) {
       return categoriaService.deletarCategoria(id);
    }
}
