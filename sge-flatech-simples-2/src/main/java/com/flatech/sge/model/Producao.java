package com.flatech.sge.model;

import java.time.LocalDateTime;

/**
 * MODEL: PRODUCAO
 *
 * Representa um registro de produção (etapa de uma ordem).
 * Esta classe é um POJO (Plain Old Java Object) simples.
 */
public class Producao {

    // ATRIBUTOS (campos da tabela)
    private Long id;
    private Long ordemProducaoId;
    private String etapa;
    private String operador;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private String observacoes;

    // CONSTRUTORES

    public Producao() {
        // Construtor vazio
    }

    public Producao(Long id, Long ordemProducaoId, String etapa, String operador,
                   LocalDateTime dataInicio, LocalDateTime dataFim, String status, String observacoes) {
        this.id = id;
        this.ordemProducaoId = ordemProducaoId;
        this.etapa = etapa;
        this.operador = operador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.observacoes = observacoes;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdemProducaoId() {
        return ordemProducaoId;
    }

    public void setOrdemProducaoId(Long ordemProducaoId) {
        this.ordemProducaoId = ordemProducaoId;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Producao{" +
                "id=" + id +
                ", ordemProducaoId=" + ordemProducaoId +
                ", etapa='" + etapa + '\'' +
                ", operador='" + operador + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
