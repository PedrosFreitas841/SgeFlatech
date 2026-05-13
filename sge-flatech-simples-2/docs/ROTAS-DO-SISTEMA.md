# 🌐 ROTAS DO SISTEMA - SGE FLATECH

Este documento lista todas as rotas (URLs) disponíveis no sistema.

---

## 📋 ÍNDICE

1. [Módulo Clientes](#-módulo-clientes)
2. [Módulo Componentes](#-módulo-componentes)
3. [Módulo Ordens de Produção](#-módulo-ordens-de-produção)
4. [Módulo Produção](#-módulo-produção)
5. [Módulo Financeiro](#-módulo-financeiro)

---

## 👥 MÓDULO CLIENTES

**Base URL:** `/clientes`

### **GET** /clientes
- **Descrição:** Lista todos os clientes cadastrados
- **Controller:** `ClienteController.listar()`
- **DAO:** `ClienteDAO.listarTodos()`
- **SQL:** `SELECT * FROM cliente ORDER BY nome`
- **Retorna:** `pagina-clientes.html`
- **Exemplo:** http://localhost:8080/clientes

### **POST** /clientes/salvar
- **Descrição:** Salva um novo cliente
- **Controller:** `ClienteController.salvar()`
- **DAO:** `ClienteDAO.inserir()`
- **SQL:** `INSERT INTO cliente (...) VALUES (...)`
- **Parâmetros:**
  - `nome` (String)
  - `cpfCnpj` (String)
  - `telefone` (String)
  - `email` (String)
  - `cidade` (String)
- **Redireciona:** `/clientes`

### **GET** /clientes/deletar/{id}
- **Descrição:** Deleta um cliente pelo ID
- **Controller:** `ClienteController.deletar()`
- **DAO:** `ClienteDAO.deletar()`
- **SQL:** `DELETE FROM cliente WHERE id = ?`
- **Parâmetros:** `id` (Long) - no path
- **Redireciona:** `/clientes`
- **Exemplo:** http://localhost:8080/clientes/deletar/1

---

## 🔧 MÓDULO COMPONENTES

**Base URL:** `/componentes`

### **GET** /componentes
- **Descrição:** Lista todos os componentes do estoque
- **Controller:** `ComponenteController.listar()`
- **DAO:** `ComponenteDAO.listarTodos()`
- **SQL:** `SELECT * FROM componente ORDER BY nome`
- **Retorna:** `pagina-componentes.html`
- **Exemplo:** http://localhost:8080/componentes

### **POST** /componentes/salvar
- **Descrição:** Salva um novo componente
- **Controller:** `ComponenteController.salvar()`
- **DAO:** `ComponenteDAO.inserir()`
- **SQL:** `INSERT INTO componente (...) VALUES (...)`
- **Parâmetros:**
  - `nome` (String)
  - `tipo` (String)
  - `quantidade` (Integer)
  - `valorUnitario` (Double)
  - `fornecedor` (String)
- **Redireciona:** `/componentes`

### **GET** /componentes/deletar/{id}
- **Descrição:** Deleta um componente pelo ID
- **Controller:** `ComponenteController.deletar()`
- **DAO:** `ComponenteDAO.deletar()`
- **SQL:** `DELETE FROM componente WHERE id = ?`
- **Parâmetros:** `id` (Long) - no path
- **Redireciona:** `/componentes`
- **Exemplo:** http://localhost:8080/componentes/deletar/5

---

## 📦 MÓDULO ORDENS DE PRODUÇÃO

**Base URL:** `/ordens`

### **GET** /ordens
- **Descrição:** Lista todas as ordens de produção
- **Controller:** `OrdemProducaoController.listar()`
- **DAO:** `OrdemProducaoDAO.listarTodos()`
- **SQL:** `SELECT * FROM ordem_producao ORDER BY data_emissao DESC`
- **Retorna:** `pagina-ordens.html`
- **Exemplo:** http://localhost:8080/ordens

### **POST** /ordens/salvar
- **Descrição:** Salva uma nova ordem de produção
- **Controller:** `OrdemProducaoController.salvar()`
- **DAO:** `OrdemProducaoDAO.inserir()`
- **SQL:** `INSERT INTO ordem_producao (...) VALUES (...)`
- **Parâmetros:**
  - `codigo` (String)
  - `clienteId` (Long)
  - `dataEmissao` (LocalDate) - formato: YYYY-MM-DD
  - `dataPrevista` (LocalDate) - formato: YYYY-MM-DD
  - `status` (String) - ex: "pendente", "em_producao", "concluida"
  - `prioridade` (String) - ex: "baixa", "media", "alta"
  - `descricao` (String)
- **Redireciona:** `/ordens`

### **GET** /ordens/deletar/{id}
- **Descrição:** Deleta uma ordem de produção pelo ID
- **Controller:** `OrdemProducaoController.deletar()`
- **DAO:** `OrdemProducaoDAO.deletar()`
- **SQL:** `DELETE FROM ordem_producao WHERE id = ?`
- **Parâmetros:** `id` (Long) - no path
- **Redireciona:** `/ordens`
- **Exemplo:** http://localhost:8080/ordens/deletar/3

---

## 🏭 MÓDULO PRODUÇÃO

**Base URL:** `/producao`

### **GET** /producao
- **Descrição:** Lista todos os registros de produção
- **Controller:** `ProducaoController.listar()`
- **DAO:** `ProducaoDAO.listarTodos()`
- **SQL:** `SELECT * FROM producao ORDER BY data_inicio DESC`
- **Retorna:** `pagina-producao.html`
- **Exemplo:** http://localhost:8080/producao

### **POST** /producao/salvar
- **Descrição:** Salva um novo registro de produção
- **Controller:** `ProducaoController.salvar()`
- **DAO:** `ProducaoDAO.inserir()`
- **SQL:** `INSERT INTO producao (...) VALUES (...)`
- **Parâmetros:**
  - `ordemProducaoId` (Long)
  - `etapa` (String) - ex: "Montagem", "Soldagem", "Teste"
  - `operador` (String)
  - `dataInicio` (LocalDateTime) - formato: YYYY-MM-DDTHH:MM:SS
  - `dataFim` (LocalDateTime, opcional) - formato: YYYY-MM-DDTHH:MM:SS
  - `status` (String) - ex: "em_andamento", "concluida"
  - `observacoes` (String)
- **Redireciona:** `/producao`

### **GET** /producao/deletar/{id}
- **Descrição:** Deleta um registro de produção pelo ID
- **Controller:** `ProducaoController.deletar()`
- **DAO:** `ProducaoDAO.deletar()`
- **SQL:** `DELETE FROM producao WHERE id = ?`
- **Parâmetros:** `id` (Long) - no path
- **Redireciona:** `/producao`
- **Exemplo:** http://localhost:8080/producao/deletar/7

---

## 💰 MÓDULO FINANCEIRO

**Base URL:** `/financeiro`

### **GET** /financeiro
- **Descrição:** Lista todos os registros financeiros
- **Controller:** `FinanceiroController.listar()`
- **DAO:** `FinanceiroDAO.listarTodos()`
- **SQL:** `SELECT * FROM financeiro ORDER BY data_movimento DESC`
- **Retorna:** `pagina-financeiro.html`
- **Exemplo:** http://localhost:8080/financeiro

### **POST** /financeiro/salvar
- **Descrição:** Salva um novo registro financeiro
- **Controller:** `FinanceiroController.salvar()`
- **DAO:** `FinanceiroDAO.inserir()`
- **SQL:** `INSERT INTO financeiro (...) VALUES (...)`
- **Parâmetros:**
  - `tipo` (String) - "entrada" ou "saida"
  - `categoria` (String) - ex: "Venda", "Compra", "Salário"
  - `valor` (Double)
  - `dataMovimento` (LocalDate) - formato: YYYY-MM-DD
  - `descricao` (String)
  - `ordemProducaoId` (Long, opcional) - pode ser null
- **Redireciona:** `/financeiro`

### **GET** /financeiro/deletar/{id}
- **Descrição:** Deleta um registro financeiro pelo ID
- **Controller:** `FinanceiroController.deletar()`
- **DAO:** `FinanceiroDAO.deletar()`
- **SQL:** `DELETE FROM financeiro WHERE id = ?`
- **Parâmetros:** `id` (Long) - no path
- **Redireciona:** `/financeiro`
- **Exemplo:** http://localhost:8080/financeiro/deletar/10

---

## 📝 NOTAS IMPORTANTES

### **Formato de Datas**

- **LocalDate:** `YYYY-MM-DD` (ex: 2024-05-13)
- **LocalDateTime:** `YYYY-MM-DDTHH:MM:SS` (ex: 2024-05-13T14:30:00)

### **Status Comuns**

**Ordem de Produção:**
- `pendente` - Aguardando início
- `em_producao` - Em andamento
- `concluida` - Finalizada
- `cancelada` - Cancelada

**Produção:**
- `em_andamento` - Etapa em execução
- `concluida` - Etapa finalizada
- `pausada` - Temporariamente pausada

**Prioridade:**
- `baixa` - Prioridade baixa
- `media` - Prioridade média
- `alta` - Prioridade alta
- `urgente` - Urgente

### **Tipo Financeiro**
- `entrada` - Receita/Entrada de dinheiro
- `saida` - Despesa/Saída de dinheiro

---

## 🧪 TESTANDO AS ROTAS

### **Usando o navegador (GET)**

Basta acessar a URL:
```
http://localhost:8080/clientes
```

### **Usando formulário HTML (POST)**

Crie um formulário na página HTML:

```html
<form action="/clientes/salvar" method="POST">
    <input type="text" name="nome" placeholder="Nome" required>
    <input type="text" name="cpfCnpj" placeholder="CPF/CNPJ" required>
    <input type="text" name="telefone" placeholder="Telefone">
    <input type="email" name="email" placeholder="Email">
    <input type="text" name="cidade" placeholder="Cidade">
    <button type="submit">Salvar</button>
</form>
```

### **Usando Postman/Insomnia**

**POST** http://localhost:8080/clientes/salvar

Body (x-www-form-urlencoded):
```
nome: João Silva
cpfCnpj: 123.456.789-00
telefone: (11) 98765-4321
email: joao@email.com
cidade: São Paulo
```

---

## 🔍 PADRÃO DAS ROTAS

Todas as rotas seguem este padrão:

```
GET  /{modulo}              → Listar todos
POST /{modulo}/salvar       → Inserir novo
GET  /{modulo}/deletar/{id} → Deletar por ID
```

**Futuras expansões possíveis:**
```
GET  /{modulo}/editar/{id}  → Formulário de edição
POST /{modulo}/atualizar    → Atualizar existente
GET  /{modulo}/{id}         → Buscar por ID (detalhe)
```

---

## 📊 MAPA VISUAL

```
SGE FLATECH
│
├── /clientes
│   ├── GET  /                    (Listar)
│   ├── POST /salvar              (Inserir)
│   └── GET  /deletar/{id}        (Deletar)
│
├── /componentes
│   ├── GET  /                    (Listar)
│   ├── POST /salvar              (Inserir)
│   └── GET  /deletar/{id}        (Deletar)
│
├── /ordens
│   ├── GET  /                    (Listar)
│   ├── POST /salvar              (Inserir)
│   └── GET  /deletar/{id}        (Deletar)
│
├── /producao
│   ├── GET  /                    (Listar)
│   ├── POST /salvar              (Inserir)
│   └── GET  /deletar/{id}        (Deletar)
│
└── /financeiro
    ├── GET  /                    (Listar)
    ├── POST /salvar              (Inserir)
    └── GET  /deletar/{id}        (Deletar)
```

---

**Pronto! Agora você conhece todas as rotas do sistema! 🚀**
