package com.example.controller;

import com.example.dto.ProdutoDTO;
import com.example.model.Produto;
import com.example.repository.ProdutoRepository;
import com.example.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto (@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.criarProduto(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto (@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.alterarProduto(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDTO> excluirProduto(@PathVariable Long id) {
       
        return produtoService.deletarProduto(id);

    }

}
