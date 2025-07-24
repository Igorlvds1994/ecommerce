package com.igecommerce.br.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

    @Entity
    @Table(name = "cliente")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Cliente {

        @Id
        @Column(columnDefinition = "RAW(16)", updatable = false, nullable = false)
        private UUID id;

        @PrePersist
        public void gerarId() {
            if (id == null) {
                id = UUID.randomUUID();
            }
        }

        @NotBlank(message = "Nome é obrigátorio")
        @Size(max = 100, message = "Nome deve ter no maximo 100 caracteres")
        private String nome;

        @NotNull(message = "Data de nascimento é obrigatória")
        @Column(name = "data_nascimento")
        private LocalDate dataNascimento;

        @NotBlank(message = "Nacionalidade é obrigatória")
        private String nacionalidade;

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        private String email;

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 14, max = 14, message = "CPF deve estar no formato XXX.XXX.XXX-XX")
        private String cpf;

        @NotBlank(message = "Telefone é obrigatório")
        private String telefone;

    }
