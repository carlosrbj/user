package com.hsob.user.model.HERANCA_POLIM.funcionario;

//uma interface é um contrato
//    quem assinar esse contrato precisa implementar:
//      setSenha
//      autentica

public interface Autenticavel{
    boolean autentica(String senha);
    void setSenha(String senha) ;
}
