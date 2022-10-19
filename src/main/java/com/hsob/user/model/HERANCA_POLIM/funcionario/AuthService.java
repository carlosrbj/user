package com.hsob.user.model.HERANCA_POLIM.funcionario;

import java.util.Objects;

public class AuthService {
    private String senha;

    public boolean autentica(String senha) {
        return Objects.equals(this.senha, senha);
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
