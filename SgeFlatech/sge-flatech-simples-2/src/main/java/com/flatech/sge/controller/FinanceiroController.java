package com.flatech.sge.controller;

import com.flatech.sge.dao.FinanceiroDAO;
import com.flatech.sge.model.Financeiro;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * CONTROLLER: FINANCEIRO
 *
 * Recebe as requisições HTTP e retorna páginas HTML.
 *
 * ROTAS:
 * GET  /financeiro        - Lista todos os registros financeiros
 * POST /financeiro/salvar - Salva novo registro
 * GET  /financeiro/deletar/{id} - Deleta registro
 */
@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {

    // DAO para acessar o banco
    private FinanceiroDAO financeiroDAO = new FinanceiroDAO();

    /**
     * LISTAR REGISTROS FINANCEIROS
     *
     * Rota: GET /financeiro
     * Retorna: pagina-financeiro.html
     */
    @GetMapping
    public String listar(Model model) {
        // 1. Buscar registros no banco (via DAO)
        List<Financeiro> registros = financeiroDAO.listarTodos();

        // 2. Enviar para a página HTML
        model.addAttribute("registros", registros);

        // 3. Retornar o nome da página
        return "pagina-financeiro";
    }

    /**
     * SALVAR REGISTRO FINANCEIRO
     *
     * Rota: POST /financeiro/salvar
     * Recebe: dados do formulário
     * Redireciona: de volta para /financeiro
     */
    @PostMapping("/salvar")
    public String salvar(@RequestParam String tipo,
                        @RequestParam String categoria,
                        @RequestParam Double valor,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataMovimento,
                        @RequestParam String descricao,
                        @RequestParam(required = false) Long ordemProducaoId) {

        // 1. Criar objeto Financeiro
        Financeiro financeiro = new Financeiro();
        financeiro.setTipo(tipo);
        financeiro.setCategoria(categoria);
        financeiro.setValor(valor);
        financeiro.setDataMovimento(dataMovimento);
        financeiro.setDescricao(descricao);
        financeiro.setOrdemProducaoId(ordemProducaoId);

        // 2. Inserir no banco (via DAO)
        financeiroDAO.inserir(financeiro);

        // 3. Redirecionar de volta para lista
        return "redirect:/financeiro";
    }

    /**
     * DELETAR REGISTRO FINANCEIRO
     *
     * Rota: GET /financeiro/deletar/{id}
     * Redireciona: de volta para /financeiro
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        // 1. Deletar do banco (via DAO)
        financeiroDAO.deletar(id);

        // 2. Redirecionar de volta para lista
        return "redirect:/financeiro";
    }
}
