package com.flatech.sge.dao;

import com.flatech.sge.conexao.ConexaoDB;
import com.flatech.sge.model.Financeiro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: FINANCEIRO
 *
 * Data Access Object - Acessa o banco de dados.
 * Todas as operações com a tabela 'financeiro' ficam aqui.
 *
 * MÉTODOS:
 * - listarTodos() - SELECT * FROM financeiro
 * - buscarPorId(id) - SELECT * FROM financeiro WHERE id = ?
 * - inserir(financeiro) - INSERT INTO financeiro
 * - atualizar(financeiro) - UPDATE financeiro
 * - deletar(id) - DELETE FROM financeiro WHERE id = ?
 */
public class FinanceiroDAO {

    /**
     * LISTAR TODOS OS REGISTROS FINANCEIROS
     *
     * SQL: SELECT * FROM financeiro
     */
    public List<Financeiro> listarTodos() {
        List<Financeiro> registros = new ArrayList<>();
        Connection conexao = null;

        try {
            // 1. Conectar no banco
            conexao = ConexaoDB.getConexao();

            // 2. Preparar o SQL
            String sql = "SELECT * FROM financeiro ORDER BY data_movimento DESC";
            Statement stmt = conexao.createStatement();

            // 3. Executar a query
            ResultSet rs = stmt.executeQuery(sql);

            // 4. Percorrer os resultados
            while (rs.next()) {
                Financeiro financeiro = new Financeiro();
                financeiro.setId(rs.getLong("id"));
                financeiro.setTipo(rs.getString("tipo"));
                financeiro.setCategoria(rs.getString("categoria"));
                financeiro.setValor(rs.getDouble("valor"));

                // Converter Date para LocalDate
                Date dataMovimento = rs.getDate("data_movimento");
                if (dataMovimento != null) {
                    financeiro.setDataMovimento(dataMovimento.toLocalDate());
                }

                financeiro.setDescricao(rs.getString("descricao"));

                // ordem_producao_id pode ser null
                Long ordemId = rs.getLong("ordem_producao_id");
                if (!rs.wasNull()) {
                    financeiro.setOrdemProducaoId(ordemId);
                }

                registros.add(financeiro);
            }

            System.out.println("✓ " + registros.size() + " registros financeiros encontrados");

        } catch (SQLException e) {
            System.err.println("✗ Erro ao listar registros financeiros!");
            e.printStackTrace();

        } finally {
            // 5. Fechar conexão
            ConexaoDB.fecharConexao(conexao);
        }

        return registros;
    }

    /**
     * BUSCAR REGISTRO FINANCEIRO POR ID
     *
     * SQL: SELECT * FROM financeiro WHERE id = ?
     */
    public Financeiro buscarPorId(Long id) {
        Financeiro financeiro = null;
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "SELECT * FROM financeiro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                financeiro = new Financeiro();
                financeiro.setId(rs.getLong("id"));
                financeiro.setTipo(rs.getString("tipo"));
                financeiro.setCategoria(rs.getString("categoria"));
                financeiro.setValor(rs.getDouble("valor"));

                Date dataMovimento = rs.getDate("data_movimento");
                if (dataMovimento != null) {
                    financeiro.setDataMovimento(dataMovimento.toLocalDate());
                }

                financeiro.setDescricao(rs.getString("descricao"));

                Long ordemId = rs.getLong("ordem_producao_id");
                if (!rs.wasNull()) {
                    financeiro.setOrdemProducaoId(ordemId);
                }
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar registro financeiro!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return financeiro;
    }

    /**
     * INSERIR NOVO REGISTRO FINANCEIRO
     *
     * SQL: INSERT INTO financeiro (tipo, categoria, ...) VALUES (?, ?, ...)
     */
    public boolean inserir(Financeiro financeiro) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "INSERT INTO financeiro (tipo, categoria, valor, data_movimento, descricao, ordem_producao_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, financeiro.getTipo());
            stmt.setString(2, financeiro.getCategoria());
            stmt.setDouble(3, financeiro.getValor());
            stmt.setDate(4, Date.valueOf(financeiro.getDataMovimento()));
            stmt.setString(5, financeiro.getDescricao());

            // ordem_producao_id pode ser null
            if (financeiro.getOrdemProducaoId() != null) {
                stmt.setLong(6, financeiro.getOrdemProducaoId());
            } else {
                stmt.setNull(6, Types.BIGINT);
            }

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Registro financeiro inserido com sucesso!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao inserir registro financeiro!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * ATUALIZAR REGISTRO FINANCEIRO
     *
     * SQL: UPDATE financeiro SET tipo = ?, ... WHERE id = ?
     */
    public boolean atualizar(Financeiro financeiro) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "UPDATE financeiro SET tipo = ?, categoria = ?, valor = ?, " +
                        "data_movimento = ?, descricao = ?, ordem_producao_id = ? WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, financeiro.getTipo());
            stmt.setString(2, financeiro.getCategoria());
            stmt.setDouble(3, financeiro.getValor());
            stmt.setDate(4, Date.valueOf(financeiro.getDataMovimento()));
            stmt.setString(5, financeiro.getDescricao());

            if (financeiro.getOrdemProducaoId() != null) {
                stmt.setLong(6, financeiro.getOrdemProducaoId());
            } else {
                stmt.setNull(6, Types.BIGINT);
            }

            stmt.setLong(7, financeiro.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Registro financeiro atualizado!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao atualizar registro financeiro!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }

    /**
     * DELETAR REGISTRO FINANCEIRO
     *
     * SQL: DELETE FROM financeiro WHERE id = ?
     */
    public boolean deletar(Long id) {
        Connection conexao = null;

        try {
            conexao = ConexaoDB.getConexao();

            String sql = "DELETE FROM financeiro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Registro financeiro deletado!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("✗ Erro ao deletar registro financeiro!");
            e.printStackTrace();
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }

        return false;
    }
}
