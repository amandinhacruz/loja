package com.example.dto;

import ch.qos.logback.core.net.server.Client;
import com.example.model.Cliente;
import com.example.model.Pedido;
import com.example.model.Produto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PedidoDTO {
    private Long id;
    private LocalDate dataDoPedido;
    private Produto produto;
    private Cliente cliente;
    private float quantidade;
    private  float valorTotal;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataDoPedido = pedido.getDataDoPedido();
        this.produto = pedido.getProduto();
        this.cliente = pedido.getCliente();
        this.quantidade = pedido.getQuantidade();
        this.valorTotal = pedido.getValorTotal();
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();

        Produto produto = new Produto();
        produto.setId(pedidoDTO.getProduto().getId());

        Cliente cliente = new Cliente();
        cliente.setId(pedidoDTO.getCliente().getId());

        pedido.setDataDoPedido(pedidoDTO.getDataDoPedido());
        pedido.setProduto(produto);
        pedido.setCliente(cliente);
        pedido.setQuantidade(pedidoDTO.getQuantidade());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        return pedido;
    }


}
