package com.example.service;

import com.example.dto.ClienteDTO;
import com.example.model.Cliente;
import com.example.repository.ClienteRepository;
import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository clienteRepository;

    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = clientes.stream()
                .map(ClienteDTO::new)
                .toList();
        return ResponseEntity.ok(clienteDTOS);
    }

    public ResponseEntity<ClienteDTO> verificarCliente(@PathVariable Long id){
        Optional<Cliente> clienteId = clienteRepository.findById(id);
        if (clienteId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteId.get();
        return ResponseEntity.ok(new ClienteDTO(cliente));
    }

    public ResponseEntity<ClienteDTO> adicionarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = ClienteDTO.toEntity(clienteDTO);
        Cliente save = clienteRepository.save(cliente);

        return ResponseEntity.ok(new ClienteDTO(save));
    }

    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.getReferenceById(clienteDTO.getId());
        if(clienteDTO.getNome() != null) {
            cliente.setNome(clienteDTO.getNome());
        }
        if(clienteDTO.getEmail() != null) {
            cliente.setEmail(clienteDTO.getEmail());
        }
        cliente = clienteRepository.save(cliente);
        return  ResponseEntity.ok(new ClienteDTO(cliente));
    }

    public ResponseEntity<ClienteDTO> deletarCliente(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteOptional.get();
        clienteRepository.deleteById(id);
         return ResponseEntity.ok(new ClienteDTO(cliente));
    }


}