# 📘 GUIA DO MVC SIMPLES - SGE FLATECH

## 🎯 O QUE É ESTE PROJETO?

Este é o **SGE Flatech Simples** - Sistema de Gestão Eletrônica usando o **padrão MVC tradicional** com **DAO + JDBC**.

**Este projeto NÃO usa:**
- ❌ Spring Data JPA
- ❌ Repository interfaces  
- ❌ Service layer
- ❌ @Entity annotations
- ❌ Nenhuma "mágica" do Spring

**Este projeto USA apenas:**
- ✅ JDBC puro (java.sql)
- ✅ SQL escrito à mão
- ✅ Classes DAO tradicionais
- ✅ POJOs simples (Model)
- ✅ Controllers do Spring MVC

---

## 🏗️ ESTRUTURA DO PROJETO

```
sge-flatech-simples/
│
├── src/main/java/com/flatech/sge/
│   │
│   ├── conexao/           ← CONEXÃO COM BANCO
│   │   └── ConexaoDB.java          (JDBC puro)
│   │
│   ├── model/             ← CLASSES SIMPLES (POJOs)
│   │   ├── Cliente.java
│   │   ├── Componente.java
│   │   ├── OrdemProducao.java
│   │   ├── Producao.java
│   │   └── Financeiro.java
│   │
│   ├── dao/               ← ACESSO AO BANCO (SQL à mão)
│   │   ├── ClienteDAO.java
│   │   ├── ComponenteDAO.java
│   │   ├── OrdemProducaoDAO.java
│   │   ├── ProducaoDAO.java
│   │   └── FinanceiroDAO.java
│   │
│   ├── controller/        ← RECEBE REQUISIÇÕES HTTP
│   │   ├── ClienteController.java
│   │   ├── ComponenteController.java
│   │   ├── OrdemProducaoController.java
│   │   ├── ProducaoController.java
│   │   └── FinanceiroController.java
│   │
│   └── SgeApplication.java    ← INICIA O SPRING BOOT
│
└── src/main/resources/
    └── templates/         ← PÁGINAS HTML (Thymeleaf)
        ├── pagina-clientes.html
        ├── pagina-componentes.html
        ├── pagina-ordens.html
        ├── pagina-producao.html
        └── pagina-financeiro.html
```

---

## 🔄 FLUXO DO MVC TRADICIONAL

```
┌─────────┐       ┌────────────┐       ┌─────────┐       ┌──────────┐       ┌──────────┐
│ USUÁRIO │ ----> │ CONTROLLER │ ----> │   DAO   │ ----> │   JDBC   │ ----> │  MYSQL   │
└─────────┘       └────────────┘       └─────────┘       └──────────┘       └──────────┘
    ^                    |                                                         |
    |                    v                                                         |
    |               ┌────────┐                                                     |
    └-------------- │  HTML  │ <---------------------------------------------------┘
                    └────────┘
                    (Thymeleaf)
```

### **Exemplo prático: Listar Clientes**

1. **Usuário** acessa: `http://localhost:8080/clientes`
2. **Controller** recebe a requisição
3. **Controller** chama o **DAO**: `clienteDAO.listarTodos()`
4. **DAO** abre conexão JDBC e executa SQL: `SELECT * FROM cliente`
5. **DAO** converte ResultSet em List<Cliente>
6. **Controller** recebe a lista e envia para o **HTML**
7. **HTML (Thymeleaf)** renderiza a tabela
8. **Usuário** vê a página

---

## 📂 COMPARAÇÃO: COMPLEXO vs SIMPLES

### ❌ VERSÃO COMPLEXA (que você NÃO quer)

```
Controller → Service → Repository → JPA → Hibernate → MySQL
```

**Problema:** Muitas camadas, muita "mágica", difícil de entender!

### ✅ VERSÃO SIMPLES (este projeto)

```
Controller → DAO → JDBC → MySQL
```

**Vantagem:** Direto, sem mágica, você vê o SQL!

---

## 💻 EXEMPLO COMPLETO: MÓDULO CLIENTE

### **1. Model (Cliente.java)**

```java
package com.flatech.sge.model;

public class Cliente {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String cidade;

    // Construtor vazio
    public Cliente() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... outros getters/setters
}
```

**O que é?** Uma classe simples que representa um cliente. Apenas getters e setters, nada mais!

---

### **2. DAO (ClienteDAO.java)**

```java
package com.flatech.sge.dao;

import com.flatech.sge.conexao.ConexaoDB;
import com.flatech.sge.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // LISTAR TODOS
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        Connection conexao = null;

        try {
            // 1. Conectar no banco
            conexao = ConexaoDB.getConexao();

            // 2. Preparar SQL
            String sql = "SELECT * FROM cliente ORDER BY nome";
            Statement stmt = conexao.createStatement();

            // 3. Executar query
            ResultSet rs = stmt.executeQuery(sql);

            // 4. Converter resultados em objetos
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

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes!");
            e.printStackTrace();
        } finally {
            // 5. Fechar conexão
            ConexaoDB.fecharConexao(conexao);
        }

        return clientes;
    }

    // INSERIR
    public boolean inserir(Cliente cliente) {
        Connection conexao = null;
        try {
            conexao = ConexaoDB.getConexao();
            String sql = "INSERT INTO cliente (nome, cpf_cnpj, telefone, email, cidade) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getCidade());
            
            int linhas = stmt.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }
    }

    // DELETAR
    public boolean deletar(Long id) {
        Connection conexao = null;
        try {
            conexao = ConexaoDB.getConexao();
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            
            int linhas = stmt.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexaoDB.fecharConexao(conexao);
        }
    }
}
```

**O que é?** A classe que faz as operações no banco. Todo SQL é escrito à mão aqui!

---

### **3. Controller (ClienteController.java)**

```java
package com.flatech.sge.controller;

import com.flatech.sge.dao.ClienteDAO;
import com.flatech.sge.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteDAO clienteDAO = new ClienteDAO();

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        List<Cliente> clientes = clienteDAO.listarTodos();
        model.addAttribute("clientes", clientes);
        return "pagina-clientes";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                        @RequestParam String cpfCnpj,
                        @RequestParam String telefone,
                        @RequestParam String email,
                        @RequestParam String cidade) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpfCnpj(cpfCnpj);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setCidade(cidade);
        
        clienteDAO.inserir(cliente);
        return "redirect:/clientes";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        clienteDAO.deletar(id);
        return "redirect:/clientes";
    }
}
```

**O que é?** Recebe as requisições HTTP e chama o DAO diretamente!

---

## 🗂️ TODOS OS MÓDULOS DO SISTEMA

### **1. Cliente**
- **Rota:** `/clientes`
- **Arquivos:** `Cliente.java`, `ClienteDAO.java`, `ClienteController.java`
- **Tabela:** `cliente`
- **Função:** Cadastro de clientes da empresa

### **2. Componente**
- **Rota:** `/componentes`
- **Arquivos:** `Componente.java`, `ComponenteDAO.java`, `ComponenteController.java`
- **Tabela:** `componente`
- **Função:** Controle de estoque de componentes eletrônicos

### **3. Ordem de Produção**
- **Rota:** `/ordens`
- **Arquivos:** `OrdemProducao.java`, `OrdemProducaoDAO.java`, `OrdemProducaoController.java`
- **Tabela:** `ordem_producao`
- **Função:** Gerenciamento de ordens de produção

### **4. Produção**
- **Rota:** `/producao`
- **Arquivos:** `Producao.java`, `ProducaoDAO.java`, `ProducaoController.java`
- **Tabela:** `producao`
- **Função:** Registro de etapas de produção

### **5. Financeiro**
- **Rota:** `/financeiro`
- **Arquivos:** `Financeiro.java`, `FinanceiroDAO.java`, `FinanceiroController.java`
- **Tabela:** `financeiro`
- **Função:** Controle de movimentações financeiras

---

## 🔌 CONEXÃO COM O BANCO (ConexaoDB.java)

```java
package com.flatech.sge.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3306/sge_flatech";
    private static final String USUARIO = "root";
    private static final String SENHA = "SUA_SENHA_AQUI";

    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✓ Conectado ao banco!");
            return conexao;
        } catch (Exception e) {
            System.err.println("✗ Erro ao conectar!");
            e.printStackTrace();
            return null;
        }
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("✓ Conexão fechada!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

**IMPORTANTE:** Altere a senha no arquivo!

---

## 🚀 COMO USAR

### **1. Configurar o banco de dados**
```sql
-- Altere a senha em ConexaoDB.java
-- Execute o script SQL do banco (criar tabelas)
```

### **2. Executar o projeto**
```bash
mvn spring-boot:run
```

### **3. Acessar no navegador**
```
http://localhost:8080/clientes
http://localhost:8080/componentes
http://localhost:8080/ordens
http://localhost:8080/producao
http://localhost:8080/financeiro
```

---

## 📝 REGRAS IMPORTANTES

### ✅ O QUE FAZER

1. **Sempre fechar a conexão** no bloco `finally`
2. **Usar PreparedStatement** para evitar SQL Injection
3. **Tratar exceções SQLException**
4. **Escrever SQL claro e legível**
5. **Seguir o padrão MVC**: Controller → DAO → JDBC

### ❌ O QUE NÃO FAZER

1. **NÃO** deixar conexões abertas
2. **NÃO** concatenar SQL com strings (use PreparedStatement!)
3. **NÃO** colocar lógica de negócio no Controller
4. **NÃO** misturar SQL com HTML
5. **NÃO** esquecer de alterar a senha do banco

---

## 🆘 PROBLEMAS COMUNS

### **Erro: "Access denied for user 'root'"**
- **Solução:** Altere a senha em `ConexaoDB.java`

### **Erro: "Table 'cliente' doesn't exist"**
- **Solução:** Execute o script SQL para criar as tabelas

### **Erro: "Connection refused"**
- **Solução:** Verifique se o MySQL está rodando

### **Erro: "Driver not found"**
- **Solução:** Verifique se o driver MySQL está no `pom.xml`

---

## 📚 PRÓXIMOS PASSOS

1. ✅ Configurar o banco de dados MySQL
2. ✅ Alterar senha em `ConexaoDB.java`
3. ✅ Executar o projeto
4. ✅ Testar cada módulo no navegador
5. 🔄 Criar as páginas HTML com Thymeleaf
6. 🔄 Adicionar validação de dados
7. 🔄 Melhorar o layout com CSS

---

## 💡 DICA FINAL

**Este projeto é SIMPLES de propósito!**

Ele não tem todas as "boas práticas" modernas (Service layer, DTOs, etc), mas é **fácil de entender e modificar**.

Se você conhece SQL e Java básico, você consegue manter este código!

---

**Dúvidas?** Leia este guia novamente ou procure pelos comentários no código! 😊
