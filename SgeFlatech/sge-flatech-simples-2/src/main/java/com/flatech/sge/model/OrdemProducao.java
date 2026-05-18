package com.flatech.sge.model;

import java.time.LocalDate;

/**
 * MODEL: ORDEM DE PRODUCAO
 *
 * Representa uma ordem de produção do sistema.
 * Esta classe é um POJO (Plain Old Java Object) simples.
 */
public class OrdemProducao {

    // ATRIBUTOS (campos da tabela)
    private Long id;
    private String codigo;
    private Long clienteId;
    private LocalDate dataEmissao;
    private LocalDate dataPrevista;
    private String status;
    private String prioridade;
    private String descricao;

    // CONSTRUTORES

    public OrdemProducao() {
        // Construtor vazio
    }

    public OrdemProducao(Long id, String codigo, Long clienteId, LocalDate dataEmissao,
                        LocalDate dataPrevista, String status, String prioridade, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.clienteId = clienteId;
        this.dataEmissao = dataEmissao;
        this.dataPrevista = dataPrevista;
        this.status = status;
        this.prioridade = prioridade;
        this.descricao = descricao;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "OrdemProducao{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", clienteId=" + clienteId +
                ", status='" + status + '\'' +
                ", prioridade='" + prioridade + '\'' +
                '}';
    }
}
