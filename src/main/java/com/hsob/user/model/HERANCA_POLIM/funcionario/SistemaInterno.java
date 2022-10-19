package com.hsob.user.model.HERANCA_POLIM.funcionario;

public class SistemaInterno {
    String senha = "123";
    public void autentica(Autenticavel funcionario){
        if (funcionario.autentica("123")){
            System.out.println("Autenticado");
        } else {
            System.out.println("não autenticado");
        }
    }
}
