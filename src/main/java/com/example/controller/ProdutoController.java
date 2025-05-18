package com.example.controller;

import com.example.model.Produto;
import com.example.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public Produto criarProduto (@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto (@PathVariable Long id, @RequestBody Produto produto) {
       Produto produtoAtualizado = produtoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

       produtoAtualizado.setNomeProduto(produto.getNomeProduto());
       produtoAtualizado.setPreco(produto.getPreco());
       produtoAtualizado.setCategoria(produtoAtualizado.getCategoria());
       return produtoRepository.save(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarproduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);

    }

}
