package com.hsob.user.model.HERANCA_POLIM.funcionario;

public class Diretor extends Funcionario{

    public double getBonificacao() {
        return this.getSalario() + 1000;
    }

    public Diretor() {
        super.setTipo("DIRETOR");
    }
}

