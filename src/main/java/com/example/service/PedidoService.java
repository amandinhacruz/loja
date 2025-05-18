package com.example.service;

import com.example.dto.PedidoDTO;
import com.example.model.Pedido;
import com.example.model.Produto;
import com.example.repository.PedidoRepository;
import com.example.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setDataDoPedido(pedidoDTO.getDataDoPedido());
        pedido.setQuantidade(pedidoDTO.getQuantidade());
        pedido.setValorTotal(pedidoDTO.getValorTotal());

        List<Produto> produtos = produtoRepository.findAllById(pedidoDTO.getProdutosIds());
        pedido.setProduto(produtos);
        return pedido;
    }
}
