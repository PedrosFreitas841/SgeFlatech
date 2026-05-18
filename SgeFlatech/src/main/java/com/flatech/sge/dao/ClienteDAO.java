package com.flatech.sge.dao;

import com.flatech.sge.conexao.ConexaoDB;
import com.flatech.sge.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: CLIENTE
 *
 * Data Access Object - Acessa o banco de dados.
 * Todas as operações com a tabela 'cliente' ficam aqui.
 *
 * MÉTODOS:
 * - listarTodos() - SELECT * FROM cliente
 * - buscarPorId(id) - SELECT * FROM cliente WHERE id = ?
 * - inserir(cliente) - INSERT INTO cliente
 * - atualizar(cliente) - UPDATE cliente
 * - deletar(id) - DELETE FROM cliente WHERE id = ?
 */
public class ClienteDAO {

    /**
     * LISTAR TODOS OS CLIENTES
     *
     * SQL: SELECT * FROM cliente
     */
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        Connection conexao = null;

        try {
            // 1. Conectar no banco
            conexao = ConexaoDB.getConexao();

            // 2. Preparar o SQL
            String sql = "SELECT * FROM cliente ORDER BY nome";
            Statement stmt = conexao.createStatement();

            // 3. Executar a query
            ResultSet rs = stmt.executeQuery(sql);

            // 4. Percorrer os resultados
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCidade(rs.getString("cidade"));

                clientes.add(cliente);
            }

            System.out.println("✓ " + clientes.size() + " clientes encontrados");

        } catch (SQLException e) {
            System.err.println("✗ Erro ao listar clientes!");
            e.printStackTrace();

        } finally {
            // 5. Fechar conexão
            ConexaoDB.fecharConexao(conexao);
        }

        return clientes;
    }

    /**
     * BUSCAR CLIENTE POR ID
     *
     * SQL: SELECT * FROM cliente WHERE id = ?
     */
    public Cliente buscarPorId(Long id) {
        Cliente cliente = null;
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCidade(rs.getString("cidade"));
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar cliente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return cliente;
    }

    /**
     * INSERIR NOVO CLIENTE
     *
     * SQL: INSERT INTO cliente (nome, cpf_cnpj, ...) VALUES (?, ?, ...)
     */
    public boolean inserir(Cliente cliente) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "INSERT INTO cliente (nome, cpf_cnpj, telefone, email, cidade) " +
                        "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getCidade());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Cliente inserido com sucesso!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao inserir cliente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * ATUALIZAR CLIENTE
     *
     * SQL: UPDATE cliente SET nome = ?, ... WHERE id = ?
     */
    public boolean atualizar(Cliente cliente) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "UPDATE cliente SET nome = ?, cpf_cnpj = ?, telefone = ?, " +
                        "email = ?, cidade = ? WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getCidade());
            stmt.setLong(6, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Cliente atualizado!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao atualizar cliente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * DELETAR CLIENTE
     *
     * SQL: DELETE FROM cliente WHERE id = ?
     */
    public boolean deletar(Long id) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Cliente deletado!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao deletar cliente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }
}
