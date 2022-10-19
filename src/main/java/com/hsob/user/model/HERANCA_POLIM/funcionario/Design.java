package com.hsob.user.model.HERANCA_POLIM.funcionario;

public class Design extends Funcionario{

    public double getBonificacao() {
        return 200;
    }

    public Design() {
        super.setTipo("DESIGN");
    }
}

