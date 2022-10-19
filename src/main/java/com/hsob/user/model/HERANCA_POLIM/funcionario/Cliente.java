package com.hsob.user.model.HERANCA_POLIM.funcionario;


public class Cliente implements Autenticavel{
    private final AuthService authService;

    public Cliente() {
        this.authService = new AuthService();
    }

    @Override
    public boolean autentica(String senha) {
        return this.authService.autentica(senha);
    }

    @Override
    public void setSenha(String senha) {
        this.authService.setSenha(senha);
    }
}
