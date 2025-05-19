package com.example.service;

import com.example.dto.CategoriaDTO;
import com.example.model.Categoria;
import com.example.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public ResponseEntity<List<CategoriaDTO>> listarCategoria(){
        List<Categoria> listaDeCategoria = categoriaRepository.findAll();
        List<CategoriaDTO> listaDTO = listaDeCategoria.stream().map(CategoriaDTO::new).toList();
        return ResponseEntity.ok(listaDTO);
    }

    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = CategoriaDTO.toEntity(categoriaDTO);
        categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
        categoria.setDescricao(categoria.getDescricao());
        Categoria salvarCategoria = categoriaRepository.save(categoria);

        return ResponseEntity.ok(new CategoriaDTO(salvarCategoria));
    }

    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Categoria categoriAtualizada = CategoriaDTO.toEntity(categoriaDTO);
        categoriAtualizada.setId(id);
        categoriAtualizada.setNomeCategoria(categoriaDTO.getNomeCategoria());
        categoriAtualizada.setDescricao(categoriaDTO.getDescricao());
        Categoria categoriaAtualizadaSalva = categoriaRepository.save(categoriAtualizada);
        return ResponseEntity.ok(new CategoriaDTO(categoriaAtualizadaSalva));


    }

    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
