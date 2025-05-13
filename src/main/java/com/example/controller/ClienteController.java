package com.example.controller;


import com.example.model.Cliente;
import com.example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/{clientes}")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarSala(@PathVariable Long id){
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return ResponseEntity.ok(clienteRepository.save(cliente));

    }


    @DeleteMapping("/{id}")
    public String removerCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return "Cliente deletado com sucesso";
    }





}
