package com.igecommerce.br.ecommerce.exceptions;
import lombok.Getter;


public class CampoInvalidoException extends RuntimeException{

    @Getter
    private String campo;

    public CampoInvalidoException(String campo, String mensagem) {
        super(mensagem);
        this.campo =campo;
    }
}
