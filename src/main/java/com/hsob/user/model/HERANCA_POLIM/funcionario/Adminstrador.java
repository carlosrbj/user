package com.hsob.user.model.HERANCA_POLIM.funcionario;


public class Adminstrador extends Funcionario implements Autenticavel {
    private AuthService authService;

    public Adminstrador(){
        this.authService = new AuthService();
        super.setTipo("ADMINISTRADOR");
    }


    @Override
    public boolean autentica(String senha) {
        return this.authService.autentica(senha);
    }

    @Override
    public void setSenha(String senha) {
        this.authService.setSenha(senha);
    }

    @Override
    public double getBonificacao() {
        return 50;
    }

}
