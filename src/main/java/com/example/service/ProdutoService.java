package com.example.service;

import com.example.dto.PedidoDTO;
import com.example.dto.ProdutoDTO;
import com.example.model.Categoria;
import com.example.model.Pedido;
import com.example.model.Produto;
import com.example.repository.CategoriaRepository;
import com.example.repository.ProdutoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        List<Produto> listaProdutos = produtoRepository.findAll();
        List<ProdutoDTO> listaProdutosDTO = listaProdutos.stream().map(ProdutoDTO::new).toList();
        return ResponseEntity.ok(listaProdutosDTO);
    }

    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produtoCriado = ProdutoDTO.toEntity(produtoDTO);

        Categoria categoria = categoriaRepository.findById(produtoCriado.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));

        produtoCriado.setCategoria(categoria);
        Produto produtoSalvo = produtoRepository.save(produtoCriado);
        return ResponseEntity.ok(new ProdutoDTO(produtoSalvo));
    }

    public ResponseEntity<ProdutoDTO> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
       Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Produto produtoAtualizado = ProdutoDTO.toEntity(produtoDTO);
        produtoAtualizado.setId(id);
        Produto produtoAtualizadoSalvo = produtoRepository.save(produtoAtualizado);
        return ResponseEntity.ok(new ProdutoDTO(produtoAtualizadoSalvo));
    }

    public ResponseEntity<ProdutoDTO> deletarProduto(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }


}
