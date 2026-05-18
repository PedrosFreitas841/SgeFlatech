# 📖 GUIA MVC TRADICIONAL - PASSO A PASSO

Sistema **SUPER SIMPLIFICADO** usando MVC tradicional (DAO + JDBC).

---

## 🎯 ESTRUTURA DO PROJETO (MVC TRADICIONAL)

```
sge-flatech-simples/
├── src/main/java/com/flatech/sge/
│   ├── SgeApplication.java       ← MAIN (inicia tudo)
│   │
│   ├── 📁 conexao/                ← CONEXÃO COM BANCO
│   │   └── ConexaoDB.java        ← Conecta no MySQL (JDBC)
│   │
│   ├── 📁 model/                  ← MODEL (classes de dados)
│   │   ├── Cliente.java          ← Representa tabela 'cliente'
│   │   ├── Componente.java
│   │   └── OrdemProducao.java
│   │
│   ├── 📁 dao/                    ← DAO (acessa banco)
│   │   ├── ClienteDAO.java       ← INSERT, SELECT, UPDATE, DELETE
│   │   ├── ComponenteDAO.java
│   │   └── OrdemDAO.java
│   │
│   └── 📁 controller/             ← CONTROLLER (rotas web)
│       ├── ClienteController.java ← Recebe requisições HTTP
│       ├── ComponenteController.java
│       └── OrdemController.java
│
├── src/main/resources/
│   ├── application.properties     ← CONFIGURAR SENHA AQUI!
│   ├── templates/                 ← Páginas HTML
│   │   ├── pagina-clientes.html
│   │   ├── pagina-componentes.html
│   │   └── pagina-dashboard.html
│   └── static/css/
│       └── estilo.css
│
└── pom.xml
```

---

## 🔄 COMO FUNCIONA (FLUXO COMPLETO)

### Exemplo: Listar Clientes

```
1. USUÁRIO acessa:
   http://localhost:8080/clientes

2. CONTROLLER recebe a requisição:
   ClienteController.listar()

3. CONTROLLER chama o DAO:
   ClienteDAO.listarTodos()

4. DAO conecta no banco:
   ConexaoDB.getConexao()

5. DAO executa SQL:
   SELECT * FROM cliente

6. DAO retorna lista de clientes

7. CONTROLLER envia para HTML:
   model.addAttribute("clientes", clientes)

8. HTML exibe na tela:
   pagina-clientes.html
```

---

## 📂 1. CONEXÃO (ConexaoDB.java)

**Localização:** `src/main/java/com/flatech/sge/conexao/ConexaoDB.java`

### O que faz?
Conecta no MySQL usando JDBC puro (sem JPA).

### Código explicado:

```java
public class ConexaoDB {
    // Dados do banco
    private static final String URL = "jdbc:mysql://localhost:3306/sge_flatech";
    private static final String USUARIO = "root";
    private static final String SENHA = "SUA_SENHA_AQUI";  // ← ALTERE AQUI!
    
    // Conectar no banco
    public static Connection getConexao() {
        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        return conexao;
    }
    
    // Fechar conexão
    public static void fecharConexao(Connection conexao) {
        conexao.close();
    }
}
```

### Como usar:
```java
// Abrir conexão
Connection conexao = ConexaoDB.getConexao();

// Usar a conexão aqui...

// Fechar conexão
ConexaoDB.fecharConexao(conexao);
```

---

## 📦 2. MODEL (Cliente.java)

**Localização:** `src/main/java/com/flatech/sge/model/Cliente.java`

### O que faz?
Representa uma linha da tabela `cliente`.

### Código:

```java
public class Cliente {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String cidade;
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    // ... resto dos getters/setters
}
```

### Como usar:
```java
// Criar cliente
Cliente cliente = new Cliente();
cliente.setNome("TechCorp LTDA");
cliente.setCpfCnpj("12.345.678/0001-90");
```

---

## 🗄️ 3. DAO (ClienteDAO.java)

**Localização:** `src/main/java/com/flatech/sge/dao/ClienteDAO.java`

### O que faz?
Acessa o banco de dados. Executa SQL.

### Métodos:

```java
public class ClienteDAO {
    
    // LISTAR TODOS
    public List<Cliente> listarTodos() {
        // 1. Conectar
        Connection conexao = ConexaoDB.getConexao();
        
        // 2. SQL
        String sql = "SELECT * FROM cliente";
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        // 3. Percorrer resultados
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setId(rs.getLong("id"));
            c.setNome(rs.getString("nome"));
            clientes.add(c);
        }
        
        // 4. Fechar
        ConexaoDB.fecharConexao(conexao);
        
        return clientes;
    }
    
    // INSERIR
    public boolean inserir(Cliente cliente) {
        Connection conexao = ConexaoDB.getConexao();
        
        String sql = "INSERT INTO cliente (nome, cpf_cnpj, telefone, email, cidade) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpfCnpj());
        stmt.setString(3, cliente.getTelefone());
        stmt.setString(4, cliente.getEmail());
        stmt.setString(5, cliente.getCidade());
        
        int linhas = stmt.executeUpdate();
        
        ConexaoDB.fecharConexao(conexao);
        
        return linhas > 0;
    }
    
    // DELETAR
    public boolean deletar(Long id) {
        Connection conexao = ConexaoDB.getConexao();
        
        String sql = "DELETE FROM cliente WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setLong(1, id);
        
        int linhas = stmt.executeUpdate();
        
        ConexaoDB.fecharConexao(conexao);
        
        return linhas > 0;
    }
}
```

### Como usar:
```java
ClienteDAO dao = new ClienteDAO();

// Listar
List<Cliente> clientes = dao.listarTodos();

// Inserir
Cliente novo = new Cliente();
novo.setNome("Empresa ABC");
dao.inserir(novo);

// Deletar
dao.deletar(1L);
```

---

## 🌐 4. CONTROLLER (ClienteController.java)

**Localização:** `src/main/java/com/flatech/sge/controller/ClienteController.java`

### O que faz?
Recebe requisições HTTP e retorna páginas.

### Código:

```java
@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    // GET /clientes (exibe página)
    @GetMapping
    public String listar(Model model) {
        // 1. Buscar no banco
        List<Cliente> clientes = clienteDAO.listarTodos();
        
        // 2. Enviar para HTML
        model.addAttribute("clientes", clientes);
        
        // 3. Nome da página
        return "pagina-clientes";
    }
    
    // POST /clientes/salvar (recebe formulário)
    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                        @RequestParam String cpfCnpj) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpfCnpj(cpfCnpj);
        
        clienteDAO.inserir(cliente);
        
        return "redirect:/clientes";
    }
    
    // GET /clientes/deletar/123
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        clienteDAO.deletar(id);
        return "redirect:/clientes";
    }
}
```

---

## 🎨 5. VIEW (HTML)

**Localização:** `src/main/resources/templates/pagina-clientes.html`

### Código:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Clientes</title>
    <link rel="stylesheet" th:href="@{/css/estilo.css}">
</head>
<body>
    <h1>Lista de Clientes</h1>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF/CNPJ</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop: para cada cliente -->
            <tr th:each="cliente : ${clientes}">
                <td th:text="${cliente.id}">1</td>
                <td th:text="${cliente.nome}">Nome</td>
                <td th:text="${cliente.cpfCnpj}">000.000.000-00</td>
                <td>
                    <a th:href="@{/clientes/deletar/{id}(id=${cliente.id})}">
                        Deletar
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
    
    <!-- Formulário para adicionar -->
    <h2>Novo Cliente</h2>
    <form method="post" th:action="@{/clientes/salvar}">
        <input type="text" name="nome" placeholder="Nome" required>
        <input type="text" name="cpfCnpj" placeholder="CPF/CNPJ">
        <input type="text" name="telefone" placeholder="Telefone">
        <input type="text" name="email" placeholder="Email">
        <input type="text" name="cidade" placeholder="Cidade">
        <button type="submit">Salvar</button>
    </form>
</body>
</html>
```

---

## ⚙️ CONFIGURAR E EXECUTAR

### PASSO 1: Configurar senha do MySQL

**Arquivo:** `src/main/resources/application.properties`

```properties
db.url=jdbc:mysql://localhost:3306/sge_flatech
db.username=root
db.password=SUA_SENHA_AQUI  ← ALTERE AQUI!
```

**Arquivo:** `src/main/java/com/flatech/sge/conexao/ConexaoDB.java`

```java
private static final String SENHA = "SUA_SENHA_AQUI";  ← ALTERE AQUI!
```

### PASSO 2: Criar banco de dados

```bash
mysql -u root -p
```

```sql
CREATE DATABASE sge_flatech;
USE sge_flatech;
SOURCE /caminho/para/database-schema.sql;
```

### PASSO 3: Compilar e executar

```bash
cd sge-flatech-simples
mvn clean install
mvn spring-boot:run
```

### PASSO 4: Acessar

```
http://localhost:8080/clientes
```

---

## 🔍 RESUMO DO PADRÃO MVC

```
┌─────────────────────────────────────────────────────────┐
│                                                         │
│  USUÁRIO                                                │
│    ↓                                                    │
│  CONTROLLER (recebe requisição HTTP)                    │
│    ↓                                                    │
│  DAO (acessa banco de dados)                            │
│    ↓                                                    │
│  CONEXÃO (conecta no MySQL)                             │
│    ↓                                                    │
│  BANCO DE DADOS (executa SQL)                           │
│    ↓                                                    │
│  DAO (retorna dados)                                    │
│    ↓                                                    │
│  CONTROLLER (envia para HTML)                           │
│    ↓                                                    │
│  VIEW (exibe na tela)                                   │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

**CAMADAS:**
1. **VIEW** (HTML) - Interface visual
2. **CONTROLLER** - Recebe requisições
3. **DAO** - Acessa banco de dados
4. **MODEL** - Representa dados
5. **CONEXÃO** - Conecta no MySQL

**SEM:**
- ❌ Repository
- ❌ Service
- ❌ JPA/Hibernate
- ❌ Anotações @Entity

**APENAS:**
- ✅ JDBC puro
- ✅ SQL direto
- ✅ DAO simples

---

## ✅ DIFERENÇAS

### Versão Complexa (Spring Data JPA):
```
Controller → Service → Repository → JPA → Banco
```

### Versão Simples (JDBC):
```
Controller → DAO → JDBC → Banco
```

**Muito mais fácil de entender!**

---

**✅ PRONTO!** Agora você tem MVC tradicional simples! 🎉
