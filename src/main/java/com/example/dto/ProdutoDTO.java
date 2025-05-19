package com.example.dto;

import com.example.model.Categoria;
import com.example.model.Produto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.net.ssl.SSLSession;

@Getter
@Setter
@RequiredArgsConstructor
public class ProdutoDTO {
        private Long id;
        private String nomeProduto;
        private float preco;
        private Categoria categoria;

        public ProdutoDTO(Produto produto) {
            this.id = produto.getId();
            this.nomeProduto = produto.getNomeProduto();
            this.preco = produto.getPreco();
            this.categoria = produto.getCategoria();
        }

        public static Produto toEntity(ProdutoDTO produtoDTO) {
            Produto produto = new Produto();
            Categoria categoria = new Categoria();
            categoria.setId(produtoDTO.getCategoria().getId());

            produto.setNomeProduto(produtoDTO.getNomeProduto());
            produto.setPreco(produtoDTO.getPreco());
            produto.setCategoria(categoria);
            return produto;
        }



}
