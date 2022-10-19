package com.hsob.user.model.HERANCA_POLIM.conta;

public class TributacaoService {
    private double totalImposto;

    public void registraTributacao(Tributavel tributavel){
        this.totalImposto += tributavel.getValorImposto();
    }

    public double getTotalImposto() {
        return totalImposto;
    }
}
