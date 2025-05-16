package com.example.service;

import com.example.dto.ClienteDTO;
import com.example.model.Cliente;
import com.example.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public ResponseEntity<ClienteDTO> adicionarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = ClienteDTO.toEntity(clienteDTO);
        Cliente save = clienteRepository.save(cliente);

        return ResponseEntity.ok(new ClienteDTO(save));
    }

    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteDTO.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return  ResponseEntity.ok(new ClienteDTO(cliente));

    }


}