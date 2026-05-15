# 🎯 COMO RODAR NO INTELLIJ (SUPER FÁCIL!)

## 📍 PASSO A PASSO COM IMAGENS (SEM ERRO!)

### 1️⃣ ABRIR O PROJETO

1. Abra o **IntelliJ IDEA**
2. Clique em: **File → Open**
3. Navegue até a pasta: `sge-flatech-simples`
4. Selecione a pasta (a que tem o arquivo `pom.xml` dentro)
5. Clique em **OK**

**AGUARDE!** O IntelliJ vai:
- 🔄 Detectar que é um projeto Maven
- 📥 Baixar todas as dependências
- ⚙️ Configurar tudo automaticamente

Você vai ver no canto inferior direito:
```
Importing Maven project...
Downloading dependencies...
```

**AGUARDE TERMINAR!** (pode demorar 2-5 minutos na primeira vez)

---

### 2️⃣ CONFIGURAR O SDK (Java 21)

Se aparecer erro de SDK:

1. Clique em: **File → Project Structure**
2. Em **Project Settings → Project**:
   - **SDK:** Escolha Java 21 (ou 17)
   - Se não aparecer, clique em **Add SDK → Download JDK**
3. Em **Project Settings → Modules**:
   - Selecione **sge-simples**
   - **Language level:** 21 (ou 17)
4. Clique em **OK**

---

### 3️⃣ LOCALIZAR O ARQUIVO PRINCIPAL

No painel esquerdo (Project):

```
sge-flatech-simples
└── src
    └── main
        └── java
            └── com.flatech.sge
                └── SgeApplication.java  ← ESTE É O ARQUIVO!
```

**Ou use o atalho:**
- Pressione: `Ctrl + N` (Windows/Linux) ou `Cmd + O` (Mac)
- Digite: `SgeApplication`
- Pressione Enter

---

### 4️⃣ EXECUTAR O PROJETO

**Opção A: Botão Verde ▶️**

1. Abra o arquivo: `SgeApplication.java`
2. Você vai ver um **triângulo verde ▶️** ao lado da linha do `main`
3. Clique no **triângulo verde ▶️**
4. Selecione: **Run 'SgeApplication'**

**Opção B: Clique Direito**

1. Clique com o **botão direito** em `SgeApplication.java`
2. Selecione: **Run 'SgeApplication.main()'**

**Opção C: Menu Superior**

1. Menu: **Run → Run...**
2. Selecione: **SgeApplication**

---

### 5️⃣ VERIFICAR SE ESTÁ RODANDO

No console do IntelliJ (parte inferior), você vai ver:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.5)

✓ Conectado ao banco de dados!
✓ Conexão fechada!

=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

**✅ SE VIU ISSO, FUNCIONOU!**

---

### 6️⃣ ABRIR NO NAVEGADOR

No IntelliJ, você vai ver no console:
```
Acesse: http://localhost:8080
```

**Você pode:**
- **Clicar no link** (IntelliJ abre automaticamente)
- **Ou copiar e colar** no navegador

---

## 🐛 PROBLEMAS COMUNS NO INTELLIJ

### ❌ Problema: "Cannot resolve symbol 'SpringApplication'"

**Causa:** Dependências não foram baixadas

**Solução:**
1. Clique com botão direito no arquivo `pom.xml`
2. Selecione: **Maven → Reload Project**
3. Aguarde terminar o download

---

### ❌ Problema: "Project JDK is not defined"

**Solução:**
1. **File → Project Structure → Project**
2. **SDK:** Selecione Java 21 (ou baixe)
3. Clique em **OK**

---

### ❌ Problema: "Access denied for user 'root'"

**Causa:** Senha do MySQL está errada!

**Solução:**
1. Abra: `src/main/java/com/flatech/sge/conexao/ConexaoDB.java`
2. Linha 18: Altere a senha:
   ```java
   private static final String SENHA = "SUA_SENHA_AQUI";
   ```
3. **Salve o arquivo** (Ctrl+S)
4. **Pare o servidor** (quadrado vermelho ⏹️)
5. **Execute novamente** (triângulo verde ▶️)

---

### ❌ Problema: "Table 'sge_flatech.cliente' doesn't exist"

**Causa:** Banco de dados não foi criado!

**Solução:**
1. Abra o MySQL
2. Execute:
   ```sql
   CREATE DATABASE sge_flatech;
   USE sge_flatech;
   
   -- Coloque aqui seu script SQL de criação das tabelas
   ```
3. **Reinicie a aplicação**

---

### ❌ Problema: "Port 8080 already in use"

**Causa:** Já tem algo rodando na porta 8080

**Solução 1:** Parar processos Java antigos
- No IntelliJ, clique no **quadrado vermelho ⏹️** para parar

**Solução 2:** Mudar a porta
- Edite: `src/main/resources/application.properties`
- Adicione: `server.port=8081`
- Acesse: http://localhost:8081

---

## 🎯 ATALHOS ÚTEIS NO INTELLIJ

```
Ctrl + N (Cmd + O no Mac)  → Buscar classe
Shift + F10                → Executar
Shift + F9                 → Debug
Ctrl + F5                  → Reiniciar aplicação
Shift + F5                 → Parar aplicação
```

---

## ✅ QUANDO DÁ TUDO CERTO

Você vai ver:

**No Console do IntelliJ:**
```
✓ Conectado ao banco de dados!
=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

**No Navegador (http://localhost:8080):**
- 🏠 Dashboard bonito com 5 cards
- 📋 Menu de navegação
- 🎨 Design com CSS colorido

**Clique nos cards** para testar cada módulo!

---

## 💡 DICA PRO

Depois que funcionar uma vez, você pode:

1. Salvar a configuração de execução:
   - **Run → Edit Configurations**
   - Verifique se tem **SgeApplication**
   - Clique em **OK**

2. Da próxima vez, basta clicar no **botão verde ▶️** na barra superior!

---

## 🎉 PRONTO!

Agora você sabe rodar o projeto no IntelliJ!

É muito mais fácil do que pelo terminal, não é? 😊
