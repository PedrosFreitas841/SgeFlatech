package com.flatech.sge.model;

/**
 * MODEL: COMPONENTE
 *
 * Representa um componente eletrônico do estoque.
 * Esta classe é um POJO (Plain Old Java Object) simples.
 */
public class Componente {

    // ATRIBUTOS (campos da tabela)
    private Long id;
    private String nome;
    private String tipo;
    private Integer quantidade;
    private Double valorUnitario;
    private String fornecedor;

    // CONSTRUTORES

    public Componente() {
        // Construtor vazio
    }

    public Componente(Long id, String nome, String tipo, Integer quantidade, Double valorUnitario, String fornecedor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.fornecedor = fornecedor;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}
