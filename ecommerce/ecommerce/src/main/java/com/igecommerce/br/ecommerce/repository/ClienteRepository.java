package com.igecommerce.br.ecommerce.repository;

import com.igecommerce.br.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente , UUID> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    List<Cliente> findByNacionalidade(String nacionalidade);

    Optional<Cliente> findByNomeAndDataNascimentoAndNacionalidade(String nome, LocalDate dataNascimento, String nacionalidade);

    Optional<Cliente> findByCpf(String cpf);


}
