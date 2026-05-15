# 🚀 INÍCIO RÁPIDO - SGE FLATECH SIMPLES

## 📋 PRÉ-REQUISITOS

Antes de começar, você precisa ter instalado:

- ☕ **Java 17** ou superior
- 🗄️ **MySQL 8.0** ou superior
- 🛠️ **Maven 3.6** ou superior
- 💻 **IntelliJ IDEA** (recomendado) ou outra IDE Java

---

## 📥 PASSO 1: BAIXAR O PROJETO

1. Baixe todos os arquivos do projeto
2. Coloque tudo na pasta: `sge-flatech-simples/`

**Estrutura esperada:**
```
sge-flatech-simples/
├── src/
├── pom.xml
└── docs/
```

---

## 🗄️ PASSO 2: CONFIGURAR O BANCO DE DADOS

### **2.1 - Criar o banco de dados**

Abra o MySQL e execute:

```sql
CREATE DATABASE sge_flatech;
USE sge_flatech;
```

### **2.2 - Criar as tabelas**

Execute o script SQL completo que você já tem. As tabelas principais são:

- `cliente`
- `componente`
- `ordem_producao`
- `producao`
- `financeiro`
- `log_alerta` (para o trigger)

### **2.3 - Configurar a senha**

Abra o arquivo: `src/main/java/com/flatech/sge/conexao/ConexaoDB.java`

Altere a linha 18:
```java
private static final String SENHA = "SUA_SENHA_AQUI";  // <<<< ALTERE AQUI
```

Coloque a senha do seu MySQL!

---

## 🛠️ PASSO 3: ABRIR NO INTELLIJ

### **3.1 - Importar o projeto**

1. Abra o IntelliJ IDEA
2. Clique em: `File → Open`
3. Navegue até a pasta `sge-flatech-simples/`
4. Selecione a pasta e clique em `OK`
5. O IntelliJ vai detectar que é um projeto Maven

### **3.2 - Importar dependências Maven**

O IntelliJ vai mostrar um popup no canto inferior direito:

```
Maven projects need to be imported
```

Clique em: **Import Changes** ou **Enable Auto-Import**

Aguarde o IntelliJ baixar todas as dependências (pode levar alguns minutos).

---

## ▶️ PASSO 4: EXECUTAR O PROJETO

### **Opção 1: Executar pela IDE**

1. Abra o arquivo: `src/main/java/com/flatech/sge/SgeApplication.java`
2. Clique com o botão direito no arquivo
3. Selecione: `Run 'SgeApplication'`

### **Opção 2: Executar pelo terminal**

Dentro da pasta do projeto, execute:

```bash
mvn spring-boot:run
```

---

## 🌐 PASSO 5: ACESSAR NO NAVEGADOR

Se tudo deu certo, você verá no console:

```
=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

Abra seu navegador e acesse:

- **Clientes:** http://localhost:8080/clientes
- **Componentes:** http://localhost:8080/componentes
- **Ordens:** http://localhost:8080/ordens
- **Produção:** http://localhost:8080/producao
- **Financeiro:** http://localhost:8080/financeiro

---

## ✅ TESTANDO O SISTEMA

### **Teste 1: Listar clientes**

1. Acesse: http://localhost:8080/clientes
2. Você deve ver uma página (mesmo que vazia se não tiver clientes)

### **Teste 2: Verificar conexão no console**

No console do IntelliJ, você deve ver:

```
✓ Conectado ao banco de dados!
✓ X clientes encontrados
✓ Conexão fechada!
```

### **Teste 3: Inserir um cliente**

Se você tiver um formulário HTML, tente inserir um cliente e veja se aparece no banco!

---

## 🐛 PROBLEMAS COMUNS

### ❌ Erro: "Access denied for user 'root'"

**Problema:** Senha do MySQL incorreta

**Solução:**
1. Abra `ConexaoDB.java`
2. Altere a senha na linha 18
3. Reinicie o projeto

---

### ❌ Erro: "Table 'sge_flatech.cliente' doesn't exist"

**Problema:** Tabelas não foram criadas no banco

**Solução:**
1. Abra o MySQL
2. Execute: `USE sge_flatech;`
3. Execute: `SHOW TABLES;`
4. Se não aparecer nada, execute seu script SQL completo

---

### ❌ Erro: "Connection refused"

**Problema:** MySQL não está rodando

**Solução:**
1. Verifique se o MySQL está ativo
2. Tente conectar com outro cliente (MySQL Workbench)
3. Verifique se está na porta 3306

---

### ❌ Erro: "Driver not found"

**Problema:** Dependência do MySQL não foi baixada

**Solução:**
1. Verifique se tem internet
2. No IntelliJ: `View → Tool Windows → Maven`
3. Clique em: `Reload All Maven Projects` (ícone de refresh)

---

## 📂 ESTRUTURA DO PROJETO

```
sge-flatech-simples/
│
├── src/main/java/com/flatech/sge/
│   ├── conexao/
│   │   └── ConexaoDB.java          ← ALTERE A SENHA AQUI!
│   │
│   ├── model/                       ← Classes simples (POJOs)
│   │   ├── Cliente.java
│   │   ├── Componente.java
│   │   ├── OrdemProducao.java
│   │   ├── Producao.java
│   │   └── Financeiro.java
│   │
│   ├── dao/                         ← Operações no banco (SQL)
│   │   ├── ClienteDAO.java
│   │   ├── ComponenteDAO.java
│   │   ├── OrdemProducaoDAO.java
│   │   ├── ProducaoDAO.java
│   │   └── FinanceiroDAO.java
│   │
│   ├── controller/                  ← Recebe requisições HTTP
│   │   ├── ClienteController.java
│   │   ├── ComponenteController.java
│   │   ├── OrdemProducaoController.java
│   │   ├── ProducaoController.java
│   │   └── FinanceiroController.java
│   │
│   └── SgeApplication.java          ← EXECUTE ESTE ARQUIVO!
│
├── src/main/resources/
│   ├── application.properties       ← Configurações do Spring
│   └── templates/                   ← Páginas HTML
│       ├── pagina-clientes.html
│       ├── pagina-componentes.html
│       ├── pagina-ordens.html
│       ├── pagina-producao.html
│       └── pagina-financeiro.html
│
├── pom.xml                          ← Dependências Maven
│
└── docs/                            ← LEIA ESTES GUIAS!
    ├── INICIO-RAPIDO.md             ← Você está aqui
    └── GUIA-MVC-SIMPLES.md          ← Entenda o padrão MVC
```

---

## 🎯 PRÓXIMOS PASSOS

Agora que o projeto está rodando:

1. ✅ Leia o guia: `docs/GUIA-MVC-SIMPLES.md`
2. ✅ Entenda como funciona o padrão DAO
3. ✅ Teste cada módulo do sistema
4. ✅ Crie as páginas HTML para os formulários
5. ✅ Adicione mais funcionalidades!

---

## 📞 PRECISA DE AJUDA?

Se algo não funcionou:

1. Verifique se seguiu TODOS os passos
2. Leia a seção "Problemas Comuns" acima
3. Verifique os logs no console do IntelliJ
4. Leia os comentários no código - eles explicam tudo!

---

**Boa sorte com o SGE Flatech! 🚀**
