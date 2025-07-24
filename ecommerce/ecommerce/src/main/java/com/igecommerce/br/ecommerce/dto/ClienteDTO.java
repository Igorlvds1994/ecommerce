package com.igecommerce.br.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteDTO(



        UUID id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDate dataNascimento,
        @NotBlank(message = "Nacionalidade é obrigatória")
        String nacionalidade,
        @Email(message = "Email inválido") @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "CPF é obrigatório") @Size(min = 14, max = 14, message = "CPF deve estar no formato XXX.XXX.XXX-XX")
        String cpf,
        @NotBlank(message = "Telefone é obrigatório")
        String telefone
) {
}
