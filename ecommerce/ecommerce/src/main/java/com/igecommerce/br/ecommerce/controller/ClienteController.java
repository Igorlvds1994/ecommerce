package com.igecommerce.br.ecommerce.controller;

import ch.qos.logback.core.net.server.Client;
import com.igecommerce.br.ecommerce.dto.ClienteDTO;
import com.igecommerce.br.ecommerce.exceptions.CampoInvalidoException;
import com.igecommerce.br.ecommerce.exceptions.RegistroDuplicadoException;
import com.igecommerce.br.ecommerce.model.Cliente;
import com.igecommerce.br.ecommerce.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente();
        // Não setar id no salvar, ele será gerado no @PrePersist
        cliente.setNome(dto.nome());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setNacionalidade(dto.nacionalidade());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setTelefone(dto.telefone());

        cliente = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable UUID id, @Valid @RequestBody ClienteDTO dto) {
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(id);
        clienteAtualizado.setNome(dto.nome());
        clienteAtualizado.setDataNascimento(dto.dataNascimento());
        clienteAtualizado.setNacionalidade(dto.nacionalidade());
        clienteAtualizado.setEmail(dto.email());
        clienteAtualizado.setCpf(dto.cpf());
        clienteAtualizado.setTelefone(dto.telefone());

        Cliente clienteSalvo = clienteService.atualizar(id, clienteAtualizado);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/buscar/nome")
    public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam String nome) {
        List<Cliente> clientes = clienteService.buscarPorNome(nome);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }
}
