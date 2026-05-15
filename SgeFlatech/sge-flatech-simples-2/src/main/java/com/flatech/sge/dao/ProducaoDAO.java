package com.flatech.sge.dao;

import com.flatech.sge.conexao.ConexaoDB;
import com.flatech.sge.model.Producao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: PRODUCAO
 *
 * Data Access Object - Acessa o banco de dados.
 * Todas as operações com a tabela 'producao' ficam aqui.
 *
 * MÉTODOS:
 * - listarTodos() - SELECT * FROM producao
 * - buscarPorId(id) - SELECT * FROM producao WHERE id = ?
 * - inserir(producao) - INSERT INTO producao
 * - atualizar(producao) - UPDATE producao
 * - deletar(id) - DELETE FROM producao WHERE id = ?
 */
public class ProducaoDAO {

    /**
     * LISTAR TODAS AS PRODUCOES
     *
     * SQL: SELECT * FROM producao
     */
    public List<Producao> listarTodos() {
        List<Producao> producoes = new ArrayList<>();
        Connection conexao = null;

        try {
            // 1. Conectar no banco
            conexao = ConexaoDB.getConexao();

            // 2. Preparar o SQL
            String sql = "SELECT * FROM producao ORDER BY data_inicio DESC";
            Statement stmt = conexao.createStatement();

            // 3. Executar a query
            ResultSet rs = stmt.executeQuery(sql);

            // 4. Percorrer os resultados
            while (rs.next()) {
                Producao producao = new Producao();
                producao.setId(rs.getLong("id"));
                producao.setOrdemProducaoId(rs.getLong("ordem_producao_id"));
                producao.setEtapa(rs.getString("etapa"));
                producao.setOperador(rs.getString("operador"));

                // Converter Timestamp para LocalDateTime
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                if (dataInicio != null) {
                    producao.setDataInicio(dataInicio.toLocalDateTime());
                }

                Timestamp dataFim = rs.getTimestamp("data_fim");
                if (dataFim != null) {
                    producao.setDataFim(dataFim.toLocalDateTime());
                }

                producao.setStatus(rs.getString("status"));
                producao.setObservacoes(rs.getString("observacoes"));

                producoes.add(producao);
            }

            System.out.println("✓ " + producoes.size() + " produções encontradas");

        } catch (SQLException e) {
            System.err.println("✗ Erro ao listar produções!");
            e.printStackTrace();

        } finally {
            // 5. Fechar conexão
            ConexaoDB.fecharConexao(conexao);
        }

        return producoes;
    }

    /**
     * BUSCAR PRODUCAO POR ID
     *
     * SQL: SELECT * FROM producao WHERE id = ?
     */
    public Producao buscarPorId(Long id) {
        Producao producao = null;
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "SELECT * FROM producao WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                producao = new Producao();
                producao.setId(rs.getLong("id"));
                producao.setOrdemProducaoId(rs.getLong("ordem_producao_id"));
                producao.setEtapa(rs.getString("etapa"));
                producao.setOperador(rs.getString("operador"));

                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                if (dataInicio != null) {
                    producao.setDataInicio(dataInicio.toLocalDateTime());
                }

                Timestamp dataFim = rs.getTimestamp("data_fim");
                if (dataFim != null) {
                    producao.setDataFim(dataFim.toLocalDateTime());
                }

                producao.setStatus(rs.getString("status"));
                producao.setObservacoes(rs.getString("observacoes"));
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar produção!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return producao;
    }

    /**
     * INSERIR NOVA PRODUCAO
     *
     * SQL: INSERT INTO producao (ordem_producao_id, etapa, ...) VALUES (?, ?, ...)
     */
    public boolean inserir(Producao producao) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "INSERT INTO producao (ordem_producao_id, etapa, operador, data_inicio, data_fim, status, observacoes) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, producao.getOrdemProducaoId());
            stmt.setString(2, producao.getEtapa());
            stmt.setString(3, producao.getOperador());
            stmt.setTimestamp(4, Timestamp.valueOf(producao.getDataInicio()));

            if (producao.getDataFim() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(producao.getDataFim()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            stmt.setString(6, producao.getStatus());
            stmt.setString(7, producao.getObservacoes());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Produção inserida com sucesso!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao inserir produção!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * ATUALIZAR PRODUCAO
     *
     * SQL: UPDATE producao SET ordem_producao_id = ?, ... WHERE id = ?
     */
    public boolean atualizar(Producao producao) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "UPDATE producao SET ordem_producao_id = ?, etapa = ?, operador = ?, " +
                        "data_inicio = ?, data_fim = ?, status = ?, observacoes = ? WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, producao.getOrdemProducaoId());
            stmt.setString(2, producao.getEtapa());
            stmt.setString(3, producao.getOperador());
            stmt.setTimestamp(4, Timestamp.valueOf(producao.getDataInicio()));

            if (producao.getDataFim() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(producao.getDataFim()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            stmt.setString(6, producao.getStatus());
            stmt.setString(7, producao.getObservacoes());
            stmt.setLong(8, producao.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Produção atualizada!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao atualizar produção!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * DELETAR PRODUCAO
     *
     * SQL: DELETE FROM producao WHERE id = ?
     */
    public boolean deletar(Long id) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "DELETE FROM producao WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Produção deletada!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao deletar produção!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }
}
