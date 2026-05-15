package com.flatech.sge.controller;

import com.flatech.sge.dao.ClienteDAO;
import com.flatech.sge.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER: CLIENTE
 *
 * Recebe as requisições HTTP e retorna páginas HTML.
 *
 * ROTAS:
 * GET  /clientes        - Lista todos os clientes
 * POST /clientes/salvar - Salva novo cliente
 * GET  /clientes/deletar/{id} - Deleta cliente
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    // DAO para acessar o banco
    private ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * LISTAR CLIENTES
     *
     * Rota: GET /clientes
     * Retorna: pagina-clientes.html
     */
    @GetMapping
    public String listar(Model model) {
        // 1. Buscar clientes no banco (via DAO)
        List<Cliente> clientes = clienteDAO.listarTodos();

        // 2. Enviar para a página HTML
        model.addAttribute("clientes", clientes);

        // 3. Retornar o nome da página
        return "pagina-clientes";
    }

    /**
     * SALVAR CLIENTE
     *
     * Rota: POST /clientes/salvar
     * Recebe: dados do formulário
     * Redireciona: de volta para /clientes
     */
    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                        @RequestParam String cpfCnpj,
                        @RequestParam String telefone,
                        @RequestParam String email,
                        @RequestParam String cidade) {

        // 1. Criar objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpfCnpj(cpfCnpj);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setCidade(cidade);

        // 2. Inserir no banco (via DAO)
        clienteDAO.inserir(cliente);

        // 3. Redirecionar de volta para lista
        return "redirect:/clientes";
    }

    /**
     * DELETAR CLIENTE
     *
     * Rota: GET /clientes/deletar/{id}
     * Redireciona: de volta para /clientes
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        // 1. Deletar do banco (via DAO)
        clienteDAO.deletar(id);

        // 2. Redirecionar de volta para lista
        return "redirect:/clientes";
    }
}
