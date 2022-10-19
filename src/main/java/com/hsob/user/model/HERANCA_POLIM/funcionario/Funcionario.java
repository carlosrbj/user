package com.hsob.user.model.HERANCA_POLIM.funcionario;

public abstract class Funcionario {
    private String nome;
    private String cpf;
    private Double salario;
    private String tipo;

    public abstract double getBonificacao();

    public Funcionario() {
        this.setTipo("FUNCIONARIO");
    }

    public Funcionario(String nome, String cpf, Double salario, String tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
