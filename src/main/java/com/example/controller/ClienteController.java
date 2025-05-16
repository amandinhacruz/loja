package com.example.controller;


import com.example.dto.ClienteDTO;
import com.example.model.Cliente;
import com.example.repository.ClienteRepository;
import com.example.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/clientes")
public class ClienteController {


    private final ClienteService clienteService;

   @GetMapping
   public ResponseEntity<List<ClienteDTO>> exibirTodos() {
       return clienteService.listarTodos();
   }

   @PostMapping
   public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO){
       return clienteService.adicionarCliente(clienteDTO);
   }

   @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable  Long id, @RequestBody ClienteDTO clienteDTO) {
       return clienteService.atualizarCliente(clienteDTO);
   }
   
}
