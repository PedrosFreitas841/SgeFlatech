# 🚀 COMO EXECUTAR O SGE FLATECH

## ✅ CHECKLIST RÁPIDO

Antes de rodar, verifique se tem:
- ✅ Java 21 instalado
- ✅ Maven instalado
- ✅ MySQL rodando
- ✅ Banco `sge_flatech` criado

---

## 📝 PASSO 1: CONFIGURAR SENHA

Abra o arquivo:
```
src/main/java/com/flatech/sge/conexao/ConexaoDB.java
```

Linha 18 - ALTERE:
```java
private static final String SENHA = "SUA_SENHA_AQUI";
```

Coloque a senha do seu MySQL!

---

## 🗄️ PASSO 2: CRIAR BANCO DE DADOS

Abra o MySQL e execute:

```sql
CREATE DATABASE sge_flatech;
USE sge_flatech;

-- Crie as tabelas do seu script SQL aqui
-- Tabelas necessárias:
-- - cliente
-- - componente
-- - ordem_producao
-- - producao
-- - financeiro
```

---

## ▶️ PASSO 3: EXECUTAR O PROJETO

### **Opção 1: Pelo IntelliJ**

1. Abra a pasta `sge-flatech-simples` no IntelliJ
2. Aguarde o Maven baixar as dependências
3. Localize o arquivo: `src/main/java/com/flatech/sge/SgeApplication.java`
4. Clique com botão direito → `Run 'SgeApplication'`

### **Opção 2: Pelo Terminal**

```bash
cd sge-flatech-simples
mvn clean install
mvn spring-boot:run
```

---

## 🌐 PASSO 4: ACESSAR NO NAVEGADOR

Se tudo deu certo, você verá no console:

```
=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

Abra o navegador em:

### **🏠 Página Inicial (Dashboard)**
```
http://localhost:8080
```

### **📋 Módulos do Sistema**
```
http://localhost:8080/clientes
http://localhost:8080/componentes
http://localhost:8080/ordens
http://localhost:8080/producao
http://localhost:8080/financeiro
```

---

## ✅ TESTANDO O SISTEMA

### **Teste 1: Ver a página inicial**
1. Acesse: http://localhost:8080
2. Você deve ver um dashboard com 5 cards clicáveis

### **Teste 2: Adicionar um cliente**
1. Acesse: http://localhost:8080/clientes
2. Preencha o formulário "Novo Cliente"
3. Clique em "✓ Salvar Cliente"
4. O cliente deve aparecer na lista abaixo!

### **Teste 3: Ver no banco de dados**
No MySQL, execute:
```sql
SELECT * FROM cliente;
```

Você deve ver o cliente que acabou de cadastrar!

---

## 🐛 PROBLEMAS COMUNS

### ❌ Erro: "Access denied for user 'root'"
**Causa:** Senha incorreta no `ConexaoDB.java`
**Solução:** Altere a senha na linha 18 e reinicie

### ❌ Erro: "Table 'sge_flatech.cliente' doesn't exist"
**Causa:** Tabelas não foram criadas
**Solução:** Execute o script SQL completo no MySQL

### ❌ Erro: "Port 8080 already in use"
**Causa:** Outra aplicação usando a porta 8080
**Solução:** Pare a outra aplicação ou mude a porta em `application.properties`:
```properties
server.port=8081
```

### ❌ Erro: "Cannot resolve symbol 'Cliente'"
**Causa:** IntelliJ não atualizou o Maven
**Solução:** 
1. Clique com botão direito em `pom.xml`
2. Maven → Reload Project

### ❌ Erro: "Driver not found"
**Causa:** Dependência MySQL não foi baixada
**Solução:**
```bash
mvn clean install -U
```

---

## 🎯 FLUXO COMPLETO DE TESTE

Vamos testar criando um fluxo completo:

### **1. Criar um Cliente**
```
http://localhost:8080/clientes
Nome: Empresa Teste LTDA
CPF/CNPJ: 12.345.678/0001-00
Telefone: (11) 98765-4321
Email: contato@empresateste.com
Cidade: São Paulo - SP
```
Clique em "Salvar" → Cliente deve aparecer na lista!

### **2. Adicionar um Componente**
```
http://localhost:8080/componentes
Nome: Resistor 10K
Tipo: Resistor
Quantidade: 100
Valor Unitário: 0.50
Fornecedor: ElectroShop
```
Clique em "Salvar" → Componente deve aparecer no estoque!

### **3. Criar uma Ordem de Produção**
```
http://localhost:8080/ordens
Código: OP-001
Cliente ID: 1 (o ID do cliente que você criou)
Data Emissão: (data de hoje)
Data Prevista: (data futura)
Status: Pendente
Prioridade: Alta
Descrição: Primeira ordem de teste
```
Clique em "Criar Ordem" → Ordem deve aparecer na lista!

### **4. Registrar Produção**
```
http://localhost:8080/producao
Ordem ID: 1
Etapa: Montagem
Operador: João Silva
Data Início: (agora)
Status: Em Andamento
```
Clique em "Registrar" → Registro deve aparecer!

### **5. Adicionar Movimentação Financeira**
```
http://localhost:8080/financeiro
Tipo: Entrada
Categoria: Venda
Valor: 1500.00
Data: (hoje)
Descrição: Pagamento da Ordem OP-001
```
Clique em "Registrar" → Movimentação deve aparecer!

---

## 📊 O QUE VOCÊ DEVE VER

### **Console (IntelliJ/Terminal)**
```
✓ Conectado ao banco de dados!
✓ 1 clientes encontrados
✓ Conexão fechada!
✓ Cliente inserido com sucesso!
```

### **Navegador**
- ✅ Dashboard com 5 módulos clicáveis
- ✅ Formulários funcionando
- ✅ Dados aparecendo nas tabelas
- ✅ Botões de deletar funcionando
- ✅ Design bonito com CSS

### **Banco de Dados**
```sql
SELECT * FROM cliente;        -- Deve ter dados
SELECT * FROM componente;     -- Deve ter dados
SELECT * FROM ordem_producao; -- Deve ter dados
SELECT * FROM producao;       -- Deve ter dados
SELECT * FROM financeiro;     -- Deve ter dados
```

---

## 🎉 PRONTO!

Se você conseguiu:
- ✅ Ver o dashboard em http://localhost:8080
- ✅ Cadastrar um cliente
- ✅ Ver o cliente na lista
- ✅ Ver os dados no MySQL

**Parabéns! O SGE Flatech está funcionando 100%!**

---

## 📞 PRECISA DE AJUDA?

1. Leia os guias na pasta `docs/`
2. Verifique os comentários no código
3. Teste cada módulo separadamente
4. Confira se o MySQL está rodando
5. Verifique se a senha está correta

---

## 🔥 DICAS EXTRAS

### **Ver logs detalhados**
No `application.properties`, adicione:
```properties
logging.level.com.flatech.sge=DEBUG
```

### **Testar conexão com banco**
No `ConexaoDB.java`, execute o método `testarConexao()` direto

### **Limpar e reconstruir**
```bash
mvn clean install -U
mvn spring-boot:run
```

### **Ver versão do Java**
```bash
java -version
```
Deve mostrar Java 21!

---

**Última atualização:** 13/05/2026  
**Projeto:** SGE Flatech Simples v1.0
