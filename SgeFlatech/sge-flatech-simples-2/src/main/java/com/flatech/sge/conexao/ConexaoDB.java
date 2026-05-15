package com.flatech.sge.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CONEXÃO COM O BANCO DE DADOS
 *
 * Esta classe conecta no MySQL usando JDBC puro (sem JPA).
 * É o jeito mais simples e direto de conectar no banco.
 */
public class ConexaoDB {

    // CONFIGURAÇÕES DO BANCO (ALTERE AQUI SE NECESSÁRIO)
    private static final String URL = "jdbc:mysql://localhost:3306/sge_flatech";
    private static final String USUARIO = "root";
    private static final String SENHA = "SUA_SENHA_AQUI";  // <<<< ALTERE AQUI

    /**
     * CONECTAR NO BANCO
     *
     * Retorna uma conexão com o MySQL.
     * Use esta conexão para executar queries SQL.
     */
    public static Connection getConexao() {
        try {
            // Carregar driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar no banco
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.println("✓ Conectado ao banco de dados!");
            return conexao;

        } catch (ClassNotFoundException e) {
            System.err.println("✗ Erro: Driver MySQL não encontrado!");
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            System.err.println("✗ Erro ao conectar no banco de dados!");
            System.err.println("Verifique: URL, usuário e senha");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * FECHAR CONEXÃO
     *
     * Fecha a conexão com o banco.
     * IMPORTANTE: Sempre feche a conexão após usar!
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("✓ Conexão fechada!");
            } catch (SQLException e) {
                System.err.println("✗ Erro ao fechar conexão!");
                e.printStackTrace();
            }
        }
    }

    /**
     * TESTAR CONEXÃO
     *
     * Use este método para verificar se está conectando corretamente.
     */
    public static void testarConexao() {
        Connection conexao = getConexao();

        if (conexao != null) {
            System.out.println("✓✓✓ TESTE DE CONEXÃO: SUCESSO! ✓✓✓");
            fecharConexao(conexao);
        } else {
            System.err.println("✗✗✗ TESTE DE CONEXÃO: FALHOU! ✗✗✗");
        }
    }
}
