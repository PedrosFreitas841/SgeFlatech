package com.flatech.sge.controller;

import com.flatech.sge.dao.OrdemProducaoDAO;
import com.flatech.sge.model.OrdemProducao;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * CONTROLLER: ORDEM DE PRODUCAO
 *
 * Recebe as requisições HTTP e retorna páginas HTML.
 *
 * ROTAS:
 * GET  /ordens        - Lista todas as ordens
 * POST /ordens/salvar - Salva nova ordem
 * GET  /ordens/deletar/{id} - Deleta ordem
 */
@Controller
@RequestMapping("/ordens")
public class OrdemProducaoController {

    // DAO para acessar o banco
    private OrdemProducaoDAO ordemDAO = new OrdemProducaoDAO();

    /**
     * LISTAR ORDENS
     *
     * Rota: GET /ordens
     * Retorna: pagina-ordens.html
     */
    @GetMapping
    public String listar(Model model) {
        // 1. Buscar ordens no banco (via DAO)
        List<OrdemProducao> ordens = ordemDAO.listarTodos();

        // 2. Enviar para a página HTML
        model.addAttribute("ordens", ordens);

        // 3. Retornar o nome da página
        return "pagina-ordens";
    }

    /**
     * SALVAR ORDEM
     *
     * Rota: POST /ordens/salvar
     * Recebe: dados do formulário
     * Redireciona: de volta para /ordens
     */
    @PostMapping("/salvar")
    public String salvar(@RequestParam String codigo,
                        @RequestParam Long clienteId,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEmissao,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPrevista,
                        @RequestParam String status,
                        @RequestParam String prioridade,
                        @RequestParam String descricao) {

        // 1. Criar objeto OrdemProducao
        OrdemProducao ordem = new OrdemProducao();
        ordem.setCodigo(codigo);
        ordem.setClienteId(clienteId);
        ordem.setDataEmissao(dataEmissao);
        ordem.setDataPrevista(dataPrevista);
        ordem.setStatus(status);
        ordem.setPrioridade(prioridade);
        ordem.setDescricao(descricao);

        // 2. Inserir no banco (via DAO)
        ordemDAO.inserir(ordem);

        // 3. Redirecionar de volta para lista
        return "redirect:/ordens";
    }

    /**
     * DELETAR ORDEM
     *
     * Rota: GET /ordens/deletar/{id}
     * Redireciona: de volta para /ordens
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        // 1. Deletar do banco (via DAO)
        ordemDAO.deletar(id);

        // 2. Redirecionar de volta para lista
        return "redirect:/ordens";
    }
}
