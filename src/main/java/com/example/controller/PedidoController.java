package com.example.controller;

import com.example.dto.PedidoDTO;
import com.example.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos(){
        return pedidoService.pedidosRealizados();
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> fazerPedido(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService.realizarPedido(pedidoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> alterarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.atualizarPedido(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        return pedidoService.deletarPedido(id);
    }

}
