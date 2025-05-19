package com.example.service;

import com.example.dto.PedidoDTO;
import com.example.model.Cliente;
import com.example.model.Pedido;
import com.example.model.Produto;
import com.example.repository.ClienteRepository;
import com.example.repository.PedidoRepository;
import com.example.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public ResponseEntity<List<PedidoDTO>> pedidosRealizados() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOS = pedidos.stream().map(PedidoDTO::new).toList();
        return ResponseEntity.ok(pedidoDTOS);
    }

    public  ResponseEntity<PedidoDTO> realizarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedidoRealizado = PedidoDTO.toEntity(pedidoDTO);

        Produto produto = produtoRepository.findById(pedidoRealizado.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Cliente cliente = clienteRepository.findById(pedidoRealizado.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        pedidoRealizado.setProduto(produto);
        pedidoRealizado.setCliente(cliente);
        pedidoRealizado.setValorTotal(produto.getPreco() * pedidoRealizado.getQuantidade());
        Pedido pedidoSalvo = pedidoRepository.save(pedidoRealizado);
        return ResponseEntity.ok(new PedidoDTO(pedidoSalvo));
    }

    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Pedido pedidoAtualizado = PedidoDTO.toEntity(pedidoDTO);
        pedidoAtualizado.setId(id);
        Pedido pedidoAtualizadoSalvo = pedidoRepository.save(pedidoAtualizado);
        return ResponseEntity.ok(new PedidoDTO(pedidoAtualizadoSalvo));
    }

    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}

