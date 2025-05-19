package com.example.controller;

import com.example.model.Categoria;
import com.example.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaAtualizada.setNomeCategoria(categoria.getNomeCategoria());
        categoriaAtualizada.setDescricao(categoria.getDescricao());

        return categoriaRepository.save(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
    }
}
