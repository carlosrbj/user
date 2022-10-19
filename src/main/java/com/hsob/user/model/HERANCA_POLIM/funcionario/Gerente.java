package com.hsob.user.model.HERANCA_POLIM.funcionario;

public class Gerente extends Funcionario implements Autenticavel {
    private AuthService authService;

    @Override
    public double getBonificacao() {
        return this.getSalario() + 200;
    }

    public Gerente() {
        this.authService = new AuthService();
        super.setTipo("GERENTE");
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