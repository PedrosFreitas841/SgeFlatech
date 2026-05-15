package com.flatech.sge.dao;

import com.flatech.sge.conexao.ConexaoDB;
import com.flatech.sge.model.OrdemProducao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: ORDEM DE PRODUCAO
 *
 * Data Access Object - Acessa o banco de dados.
 * Todas as operações com a tabela 'ordem_producao' ficam aqui.
 *
 * MÉTODOS:
 * - listarTodos() - SELECT * FROM ordem_producao
 * - buscarPorId(id) - SELECT * FROM ordem_producao WHERE id = ?
 * - inserir(ordem) - INSERT INTO ordem_producao
 * - atualizar(ordem) - UPDATE ordem_producao
 * - deletar(id) - DELETE FROM ordem_producao WHERE id = ?
 */
public class OrdemProducaoDAO {

    /**
     * LISTAR TODAS AS ORDENS
     *
     * SQL: SELECT * FROM ordem_producao
     */
    public List<OrdemProducao> listarTodos() {
        List<OrdemProducao> ordens = new ArrayList<>();
        Connection conexao = null;

        try {
            // 1. Conectar no banco
            conexao = ConexaoDB.getConexao();

            // 2. Preparar o SQL
            String sql = "SELECT * FROM ordem_producao ORDER BY data_emissao DESC";
            Statement stmt = conexao.createStatement();

            // 3. Executar a query
            ResultSet rs = stmt.executeQuery(sql);

            // 4. Percorrer os resultados
            while (rs.next()) {
                OrdemProducao ordem = new OrdemProducao();
                ordem.setId(rs.getLong("id"));
                ordem.setCodigo(rs.getString("codigo"));
                ordem.setClienteId(rs.getLong("cliente_id"));

                // Converter Date para LocalDate
                Date dataEmissao = rs.getDate("data_emissao");
                if (dataEmissao != null) {
                    ordem.setDataEmissao(dataEmissao.toLocalDate());
                }

                Date dataPrevista = rs.getDate("data_prevista");
                if (dataPrevista != null) {
                    ordem.setDataPrevista(dataPrevista.toLocalDate());
                }

                ordem.setStatus(rs.getString("status"));
                ordem.setPrioridade(rs.getString("prioridade"));
                ordem.setDescricao(rs.getString("descricao"));

                ordens.add(ordem);
            }

            System.out.println("✓ " + ordens.size() + " ordens encontradas");

        } catch (SQLException e) {
            System.err.println("✗ Erro ao listar ordens!");
            e.printStackTrace();

        } finally {
            // 5. Fechar conexão
            ConexaoDB.fecharConexao(conexao);
        }

        return ordens;
    }

    /**
     * BUSCAR ORDEM POR ID
     *
     * SQL: SELECT * FROM ordem_producao WHERE id = ?
     */
    public OrdemProducao buscarPorId(Long id) {
        OrdemProducao ordem = null;
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "SELECT * FROM ordem_producao WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ordem = new OrdemProducao();
                ordem.setId(rs.getLong("id"));
                ordem.setCodigo(rs.getString("codigo"));
                ordem.setClienteId(rs.getLong("cliente_id"));

                Date dataEmissao = rs.getDate("data_emissao");
                if (dataEmissao != null) {
                    ordem.setDataEmissao(dataEmissao.toLocalDate());
                }

                Date dataPrevista = rs.getDate("data_prevista");
                if (dataPrevista != null) {
                    ordem.setDataPrevista(dataPrevista.toLocalDate());
                }

                ordem.setStatus(rs.getString("status"));
                ordem.setPrioridade(rs.getString("prioridade"));
                ordem.setDescricao(rs.getString("descricao"));
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar ordem!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return ordem;
    }

    /**
     * INSERIR NOVA ORDEM
     *
     * SQL: INSERT INTO ordem_producao (codigo, cliente_id, ...) VALUES (?, ?, ...)
     */
    public boolean inserir(OrdemProducao ordem) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "INSERT INTO ordem_producao (codigo, cliente_id, data_emissao, data_prevista, status, prioridade, descricao) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ordem.getCodigo());
            stmt.setLong(2, ordem.getClienteId());
            stmt.setDate(3, Date.valueOf(ordem.getDataEmissao()));
            stmt.setDate(4, Date.valueOf(ordem.getDataPrevista()));
            stmt.setString(5, ordem.getStatus());
            stmt.setString(6, ordem.getPrioridade());
            stmt.setString(7, ordem.getDescricao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Ordem inserida com sucesso!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao inserir ordem!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * ATUALIZAR ORDEM
     *
     * SQL: UPDATE ordem_producao SET codigo = ?, ... WHERE id = ?
     */
    public boolean atualizar(OrdemProducao ordem) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "UPDATE ordem_producao SET codigo = ?, cliente_id = ?, data_emissao = ?, " +
                        "data_prevista = ?, status = ?, prioridade = ?, descricao = ? WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ordem.getCodigo());
            stmt.setLong(2, ordem.getClienteId());
            stmt.setDate(3, Date.valueOf(ordem.getDataEmissao()));
            stmt.setDate(4, Date.valueOf(ordem.getDataPrevista()));
            stmt.setString(5, ordem.getStatus());
            stmt.setString(6, ordem.getPrioridade());
            stmt.setString(7, ordem.getDescricao());
            stmt.setLong(8, ordem.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Ordem atualizada!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao atualizar ordem!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * DELETAR ORDEM
     *
     * SQL: DELETE FROM ordem_producao WHERE id = ?
     */
    public boolean deletar(Long id) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "DELETE FROM ordem_producao WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Ordem deletada!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao deletar ordem!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }
}
