package com.flatech.sge.controller;

import com.flatech.sge.dao.ComponenteDAO;
import com.flatech.sge.model.Componente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER: COMPONENTE
 *
 * Recebe as requisições HTTP e retorna páginas HTML.
 *
 * ROTAS:
 * GET  /componentes        - Lista todos os componentes
 * POST /componentes/salvar - Salva novo componente
 * GET  /componentes/deletar/{id} - Deleta componente
 */
@Controller
@RequestMapping("/componentes")
public class ComponenteController {

    // DAO para acessar o banco
    private ComponenteDAO componenteDAO = new ComponenteDAO();

    /**
     * LISTAR COMPONENTES
     *
     * Rota: GET /componentes
     * Retorna: pagina-componentes.html
     */
    @GetMapping
    public String listar(Model model) {
        // 1. Buscar componentes no banco (via DAO)
        List<Componente> componentes = componenteDAO.listarTodos();

        // 2. Enviar para a página HTML
        model.addAttribute("componentes", componentes);

        // 3. Retornar o nome da página
        return "pagina-componentes";
    }

    /**
     * SALVAR COMPONENTE
     *
     * Rota: POST /componentes/salvar
     * Recebe: dados do formulário
     * Redireciona: de volta para /componentes
     */
    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                        @RequestParam String tipo,
                        @RequestParam Integer quantidade,
                        @RequestParam Double valorUnitario,
                        @RequestParam String fornecedor) {

        // 1. Criar objeto Componente
        Componente componente = new Componente();
        componente.setNome(nome);
        componente.setTipo(tipo);
        componente.setQuantidade(quantidade);
        componente.setValorUnitario(valorUnitario);
        componente.setFornecedor(fornecedor);

        // 2. Inserir no banco (via DAO)
        componenteDAO.inserir(componente);

        // 3. Redirecionar de volta para lista
        return "redirect:/componentes";
    }

    /**
     * DELETAR COMPONENTE
     *
     * Rota: GET /componentes/deletar/{id}
     * Redireciona: de volta para /componentes
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        // 1. Deletar do banco (via DAO)
        componenteDAO.deletar(id);

        // 2. Redirecionar de volta para lista
        return "redirect:/componentes";
    }
}
