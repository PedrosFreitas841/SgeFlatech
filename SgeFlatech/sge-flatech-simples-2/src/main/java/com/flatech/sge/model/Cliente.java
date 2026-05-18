package com.flatech.sge.model;

/**
 * MODEL: CLIENTE
 *
 * Representa um cliente da empresa.
 * Esta classe é um POJO (Plain Old Java Object) simples.
 */
public class Cliente {

    // ATRIBUTOS (campos da tabela)
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String cidade;

    // CONSTRUTORES

    public Cliente() {
        // Construtor vazio
    }

    public Cliente(Long id, String nome, String cpfCnpj, String telefone, String email, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
