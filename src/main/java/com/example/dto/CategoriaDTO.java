package com.example.dto;

import com.example.model.Categoria;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoriaDTO {
        private Long id;
        private String nomeCategoria;
        private String descricao;

        public CategoriaDTO(Categoria categoria) {
            this.id = categoria.getId();
            this.nomeCategoria = categoria.getNomeCategoria();
            this.descricao = categoria.getDescricao();
        }

        public static Categoria toEntity(CategoriaDTO categoriaDTO) {
            Categoria categoria = new Categoria();
            categoria.setNomeCategoria(categoria.getNomeCategoria());
            categoria.setDescricao(categoriaDTO.getDescricao());
            return categoria;
        }





}
