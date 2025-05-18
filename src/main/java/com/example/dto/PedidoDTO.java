package com.example.dto;

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
    private List<Long> produtosIds;
    private float quantidade;
    private  float valorTotal;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataDoPedido = pedido.getDataDoPedido();
        this.quantidade = pedido.getQuantidade();
        this.valorTotal = pedido.getValorTotal();
        this.produtosIds = pedido.getProduto()
                .stream()
                .map(Produto::getId)
                .toList();
    }


}
