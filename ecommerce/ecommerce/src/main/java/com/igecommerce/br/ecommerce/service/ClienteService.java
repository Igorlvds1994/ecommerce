package com.igecommerce.br.ecommerce.service;

import com.igecommerce.br.ecommerce.dto.ClienteDTO;
import com.igecommerce.br.ecommerce.exceptions.RegistroDuplicadoException;
import com.igecommerce.br.ecommerce.model.Cliente;
import com.igecommerce.br.ecommerce.repository.ClienteRepository;
import com.igecommerce.br.ecommerce.validator.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteValidator validator;

    public ClienteService(ClienteRepository repository, ClienteValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Cliente salvar(Cliente cliente) {
        validator.validar(cliente);
        try {
            return repository.save(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw new RegistroDuplicadoException("CPF ou outro campo único já cadastrado!");
        }
    }

    public Cliente atualizar(UUID id, Cliente clienteAtualizado) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualiza os campos do cliente existente
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        clienteExistente.setNacionalidade(clienteAtualizado.getNacionalidade());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());

        // Valida o cliente atualizado
        validator.validar(clienteExistente);

        try {
            return repository.save(clienteExistente);
        } catch (DataIntegrityViolationException ex) {
            throw new RegistroDuplicadoException("CPF ou outro campo único já cadastrado!");
        }
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado para deletar");
        }
        repository.deleteById(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }
}