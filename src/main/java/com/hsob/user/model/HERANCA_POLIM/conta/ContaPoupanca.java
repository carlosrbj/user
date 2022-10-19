package com.hsob.user.model.HERANCA_POLIM.conta;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int agencia, int numero) {
        super(agencia, numero);
    }

    @Override
    public void deposita(double valor) {
        super.saldo = super.saldo + valor;
    }

}