package com.igecommerce.br.ecommerce.validator;

import ch.qos.logback.core.net.server.Client;
import com.igecommerce.br.ecommerce.exceptions.CampoInvalidoException;
import com.igecommerce.br.ecommerce.exceptions.RegistroDuplicadoException;
import com.igecommerce.br.ecommerce.model.Cliente;
import com.igecommerce.br.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ClienteValidator {

    private final ClienteRepository repository;

    public ClienteValidator(ClienteRepository repository) {
        this.repository = repository;
    }

    public void validar(Cliente cliente) {
        validarDuplicidade(cliente);
        validarNome(cliente.getNome());
        validarCpf(cliente.getCpf());
        validarEmail(cliente.getEmail());
        validarDataNascimento(cliente.getDataNascimento());
        validarNacionalidade(cliente.getNacionalidade());
        validarTelefone(cliente.getTelefone());
    }

    private void validarDuplicidade(Cliente cliente) {
        Optional<Cliente> clienteEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                cliente.getNome(), cliente.getDataNascimento(), cliente.getNacionalidade());

        if (cliente.getId() == null && clienteEncontrado.isPresent()) {
            throw new RegistroDuplicadoException("Cliente já cadastrado com esses dados!");
        }

        if (cliente.getId() != null && clienteEncontrado.isPresent() &&
                !cliente.getId().equals(clienteEncontrado.get().getId())) {
            throw new RegistroDuplicadoException("Cliente já cadastrado com esses dados!");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new CampoInvalidoException("nome", "Nome é obrigatório");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new CampoInvalidoException("cpf", "CPF é obrigatório");
        }
        // Regex simples para CPF no formato XXX.XXX.XXX-XX
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new CampoInvalidoException("cpf", "CPF deve estar no formato XXX.XXX.XXX-XX");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new CampoInvalidoException("email", "E-mail é obrigatório");
        }
        // Regex básico para email válido
        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$")) {
            throw new CampoInvalidoException("email", "E-mail inválido");
        }
    }

    private void validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new CampoInvalidoException("dataNascimento", "Data de nascimento é obrigatória");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new CampoInvalidoException("dataNascimento", "Data de nascimento não pode ser no futuro");
        }
    }

    private void validarNacionalidade(String nacionalidade) {
        if (nacionalidade == null || nacionalidade.trim().isEmpty()) {
            throw new CampoInvalidoException("nacionalidade", "Nacionalidade é obrigatória");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new CampoInvalidoException("telefone", "Telefone é obrigatório");
        }
        // Opcional: regex para telefone
        // if (!telefone.matches(...)) throw ...
    }
}