package com.flatech.sge.model;

import java.time.LocalDate;

/**
 * MODEL: FINANCEIRO
 *
 * Representa uma movimentação financeira do sistema.
 * Esta classe é um POJO (Plain Old Java Object) simples.
 */
public class Financeiro {

    // ATRIBUTOS (campos da tabela)
    private Long id;
    private String tipo;  // 'entrada' ou 'saida'
    private String categoria;
    private Double valor;
    private LocalDate dataMovimento;
    private String descricao;
    private Long ordemProducaoId;  // Pode ser null (opcional)

    // CONSTRUTORES

    public Financeiro() {
        // Construtor vazio
    }

    public Financeiro(Long id, String tipo, String categoria, Double valor,
                     LocalDate dataMovimento, String descricao, Long ordemProducaoId) {
        this.id = id;
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.dataMovimento = dataMovimento;
        this.descricao = descricao;
        this.ordemProducaoId = ordemProducaoId;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getOrdemProducaoId() {
        return ordemProducaoId;
    }

    public void setOrdemProducaoId(Long ordemProducaoId) {
        this.ordemProducaoId = ordemProducaoId;
    }

    @Override
    public String toString() {
        return "Financeiro{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", valor=" + valor +
                ", dataMovimento=" + dataMovimento +
                '}';
    }
}
