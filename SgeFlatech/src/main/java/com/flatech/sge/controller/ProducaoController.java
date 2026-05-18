package com.flatech.sge.controller;

import com.flatech.sge.dao.ProducaoDAO;
import com.flatech.sge.model.Producao;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CONTROLLER: PRODUCAO
 *
 * Recebe as requisições HTTP e retorna páginas HTML.
 *
 * ROTAS:
 * GET  /producao        - Lista todas as produções
 * POST /producao/salvar - Salva nova produção
 * GET  /producao/deletar/{id} - Deleta produção
 */
@Controller
@RequestMapping("/producao")
public class ProducaoController {

    // DAO para acessar o banco
    private ProducaoDAO producaoDAO = new ProducaoDAO();

    /**
     * LISTAR PRODUCOES
     *
     * Rota: GET /producao
     * Retorna: pagina-producao.html
     */
    @GetMapping
    public String listar(Model model) {
        // 1. Buscar produções no banco (via DAO)
        List<Producao> producoes = producaoDAO.listarTodos();

        // 2. Enviar para a página HTML
        model.addAttribute("producoes", producoes);

        // 3. Retornar o nome da página
        return "pagina-producao";
    }

    /**
     * SALVAR PRODUCAO
     *
     * Rota: POST /producao/salvar
     * Recebe: dados do formulário
     * Redireciona: de volta para /producao
     */
    @PostMapping("/salvar")
    public String salvar(@RequestParam Long ordemProducaoId,
                        @RequestParam String etapa,
                        @RequestParam String operador,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
                        @RequestParam String status,
                        @RequestParam String observacoes) {

        // 1. Criar objeto Producao
        Producao producao = new Producao();
        producao.setOrdemProducaoId(ordemProducaoId);
        producao.setEtapa(etapa);
        producao.setOperador(operador);
        producao.setDataInicio(dataInicio);
        producao.setDataFim(dataFim);
        producao.setStatus(status);
        producao.setObservacoes(observacoes);

        // 2. Inserir no banco (via DAO)
        producaoDAO.inserir(producao);

        // 3. Redirecionar de volta para lista
        return "redirect:/producao";
    }

    /**
     * DELETAR PRODUCAO
     *
     * Rota: GET /producao/deletar/{id}
     * Redireciona: de volta para /producao
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        // 1. Deletar do banco (via DAO)
        producaoDAO.deletar(id);

        // 2. Redirecionar de volta para lista
        return "redirect:/producao";
    }
}
