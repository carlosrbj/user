package com.hsob.user.model.HERANCA_POLIM.funcionario;

public class ControleBonificacao {
    private double soma;

    public void registraBonificacao(Funcionario funcionario){
        this.soma = this.soma + funcionario.getBonificacao();
    }

    public double getSoma() {
        return soma;
    }
}
