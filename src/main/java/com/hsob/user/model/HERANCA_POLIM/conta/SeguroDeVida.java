package com.hsob.user.model.HERANCA_POLIM.conta;

public class SeguroDeVida implements Tributavel{
    @Override
    public double getValorImposto() {
        return 42;
    }
}
