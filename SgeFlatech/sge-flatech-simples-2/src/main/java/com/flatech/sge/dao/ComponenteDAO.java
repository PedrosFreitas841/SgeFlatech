package com.flatech.sge.dao;

import com.flatech.sge.conexao.ConexaoDB;
import com.flatech.sge.model.Componente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: COMPONENTE
 *
 * Data Access Object - Acessa o banco de dados.
 * Todas as operações com a tabela 'componente' ficam aqui.
 *
 * MÉTODOS:
 * - listarTodos() - SELECT * FROM componente
 * - buscarPorId(id) - SELECT * FROM componente WHERE id = ?
 * - inserir(componente) - INSERT INTO componente
 * - atualizar(componente) - UPDATE componente
 * - deletar(id) - DELETE FROM componente WHERE id = ?
 */
public class ComponenteDAO {

    /**
     * LISTAR TODOS OS COMPONENTES
     *
     * SQL: SELECT * FROM componente
     */
    public List<Componente> listarTodos() {
        List<Componente> componentes = new ArrayList<>();
        Connection conexao = null;

        try {
            // 1. Conectar no banco
            conexao = ConexaoDB.getConexao();

            // 2. Preparar o SQL
            String sql = "SELECT * FROM componente ORDER BY nome";
            Statement stmt = conexao.createStatement();

            // 3. Executar a query
            ResultSet rs = stmt.executeQuery(sql);

            // 4. Percorrer os resultados
            while (rs.next()) {
                Componente componente = new Componente();
                componente.setId(rs.getLong("id"));
                componente.setNome(rs.getString("nome"));
                componente.setTipo(rs.getString("tipo"));
                componente.setQuantidade(rs.getInt("quantidade"));
                componente.setValorUnitario(rs.getDouble("valor_unitario"));
                componente.setFornecedor(rs.getString("fornecedor"));

                componentes.add(componente);
            }

            System.out.println("✓ " + componentes.size() + " componentes encontrados");

        } catch (SQLException e) {
            System.err.println("✗ Erro ao listar componentes!");
            e.printStackTrace();

        } finally {
            // 5. Fechar conexão
            ConexaoDB.fecharConexao(conexao);
        }

        return componentes;
    }

    /**
     * BUSCAR COMPONENTE POR ID
     *
     * SQL: SELECT * FROM componente WHERE id = ?
     */
    public Componente buscarPorId(Long id) {
        Componente componente = null;
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "SELECT * FROM componente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                componente = new Componente();
                componente.setId(rs.getLong("id"));
                componente.setNome(rs.getString("nome"));
                componente.setTipo(rs.getString("tipo"));
                componente.setQuantidade(rs.getInt("quantidade"));
                componente.setValorUnitario(rs.getDouble("valor_unitario"));
                componente.setFornecedor(rs.getString("fornecedor"));
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar componente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return componente;
    }

    /**
     * INSERIR NOVO COMPONENTE
     *
     * SQL: INSERT INTO componente (nome, tipo, ...) VALUES (?, ?, ...)
     */
    public boolean inserir(Componente componente) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "INSERT INTO componente (nome, tipo, quantidade, valor_unitario, fornecedor) " +
                        "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, componente.getNome());
            stmt.setString(2, componente.getTipo());
            stmt.setInt(3, componente.getQuantidade());
            stmt.setDouble(4, componente.getValorUnitario());
            stmt.setString(5, componente.getFornecedor());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Componente inserido com sucesso!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao inserir componente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * ATUALIZAR COMPONENTE
     *
     * SQL: UPDATE componente SET nome = ?, ... WHERE id = ?
     */
    public boolean atualizar(Componente componente) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "UPDATE componente SET nome = ?, tipo = ?, quantidade = ?, " +
                        "valor_unitario = ?, fornecedor = ? WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, componente.getNome());
            stmt.setString(2, componente.getTipo());
            stmt.setInt(3, componente.getQuantidade());
            stmt.setDouble(4, componente.getValorUnitario());
            stmt.setString(5, componente.getFornecedor());
            stmt.setLong(6, componente.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Componente atualizado!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao atualizar componente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * DELETAR COMPONENTE
     *
     * SQL: DELETE FROM componente WHERE id = ?
     */
    public boolean deletar(Long id) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "DELETE FROM componente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Componente deletado!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao deletar componente!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }
}
