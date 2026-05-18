# 🔧 RESOLVER PROBLEMA DO LOCALHOST

## 🚨 SE O LOCALHOST NÃO ESTÁ FUNCIONANDO

Siga estes passos na ordem:

---

## 1️⃣ VERIFICAR JAVA

```bash
java -version
```

**Deve mostrar:** Java 21 (ou superior)

Se não mostrar Java 21:
- Instale o Java 21 JDK
- Configure a variável JAVA_HOME
- No IntelliJ: File → Project Structure → SDK → Java 21

---

## 2️⃣ VERIFICAR MAVEN

```bash
mvn -version
```

**Deve funcionar!**

Se não funcionar:
- Instale o Maven 3.6+
- Ou use o Maven que vem com o IntelliJ

---

## 3️⃣ VERIFICAR MYSQL

```bash
# No terminal MySQL
mysql -u root -p

# Dentro do MySQL:
SHOW DATABASES;
```

**Deve aparecer:** `sge_flatech` na lista

Se não aparecer:
```sql
CREATE DATABASE sge_flatech;
```

---

## 4️⃣ TESTAR CONEXÃO COM BANCO

No arquivo `ConexaoDB.java`, adicione no método `main`:

```java
public static void main(String[] args) {
    ConexaoDB.testarConexao();
}
```

Execute essa classe diretamente!

**Se der erro "Access denied":**
- Senha está errada na linha 18!

**Se der erro "Unknown database":**
- Banco não foi criado! Execute `CREATE DATABASE sge_flatech;`

---

## 5️⃣ LIMPAR E RECONSTRUIR

```bash
cd sge-flatech-simples
mvn clean install -U
```

**Aguarde baixar tudo!** Isso pode demorar na primeira vez.

---

## 6️⃣ EXECUTAR O PROJETO

### **Opção A: IntelliJ**

1. Botão direito em `SgeApplication.java`
2. Run 'SgeApplication'
3. **AGUARDE** aparecer no console:
   ```
   =================================
   SGE Flatech rodando!
   Acesse: http://localhost:8080
   =================================
   ```

### **Opção B: Terminal**

```bash
cd sge-flatech-simples
mvn spring-boot:run
```

---

## 7️⃣ VERIFICAR NO NAVEGADOR

Abra: **http://localhost:8080**

**Se aparecer "Cannot connect":**
- O servidor não está rodando
- Veja o console se tem erros

**Se aparecer "Whitelabel Error Page":**
- O servidor está rodando!
- Mas falta alguma configuração

---

## 🐛 ERROS COMUNS E SOLUÇÕES

### ❌ "Port 8080 already in use"

**Solução 1:** Matar o processo na porta 8080
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <numero> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Solução 2:** Mudar a porta
No `application.properties`:
```properties
server.port=8081
```
Acesse: http://localhost:8081

---

### ❌ "Failed to configure a DataSource"

**Causa:** Spring está tentando configurar banco automaticamente

**Solução:** No `application.properties`, adicione:
```properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

---

### ❌ "Driver not found: com.mysql.cj.jdbc.Driver"

**Solução:** Rebuild do Maven
```bash
mvn clean install -U
```

---

### ❌ Página em branco ou CSS não carrega

**Solução:** Verificar estrutura de pastas
```
src/main/resources/
├── static/
│   └── css/
│       └── style.css
└── templates/
    ├── index.html
    └── pagina-*.html
```

Se estiver errado, reorganize!

---

### ❌ "Whitelabel Error Page"

**Solução:** Verifique os Controllers

No `HomeController.java`, deve ter:
```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
```

E o arquivo `index.html` deve existir em `templates/`!

---

## 🧪 TESTE SIMPLES

### 1. Criar um Controller de Teste

Crie: `src/main/java/com/flatech/sge/controller/TesteController.java`

```java
package com.flatech.sge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste")
    public String teste() {
        return "FUNCIONOU! O servidor está rodando!";
    }
}
```

### 2. Executar e acessar

```
http://localhost:8080/teste
```

**Se aparecer:** "FUNCIONOU! O servidor está rodando!"
- ✅ O Spring Boot está funcionando!
- ✅ Problema está só nas páginas HTML

**Se NÃO aparecer:**
- ❌ Servidor não está rodando corretamente

---

## 📋 CHECKLIST COMPLETO

Marque conforme for testando:

```
□ Java 21 instalado e configurado
□ Maven instalado
□ MySQL rodando
□ Banco sge_flatech criado
□ Tabelas criadas no banco
□ Senha alterada em ConexaoDB.java (linha 18)
□ mvn clean install executado com sucesso
□ Nenhum erro no console ao executar
□ Porta 8080 disponível (não está em uso)
□ Arquivos HTML existem em src/main/resources/templates/
□ Arquivo CSS existe em src/main/resources/static/css/
□ Navegador consegue acessar localhost:8080
```

Se TODOS os itens estiverem marcados, **TEM QUE FUNCIONAR!**

---

## 🆘 AINDA NÃO FUNCIONA?

### Execute este comando e me envie a saída:

```bash
cd sge-flatech-simples
mvn clean install
mvn spring-boot:run 2>&1 | tee log.txt
```

Isso vai criar um arquivo `log.txt` com todos os erros!

Leia o arquivo `log.txt` e procure por:
- "ERROR"
- "Exception"
- "Failed"

---

## 💡 DICAS FINAIS

1. **Sempre leia o console!** Os erros aparecem lá
2. **Google o erro** se não entender
3. **Teste passo a passo** - não pule etapas
4. **Reinicie o servidor** após mudanças
5. **Limpe o cache do navegador** (Ctrl+F5)

---

## ✅ QUANDO DÁ TUDO CERTO

Você vai ver:

**No Console:**
```
✓ Conectado ao banco de dados!
✓ Conexão fechada!

=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

**No Navegador:**
- Dashboard bonito com 5 cards
- Menu de navegação no topo
- Design com cores e CSS

---

**SE FUNCIONOU, PARABÉNS! 🎉**

Se não funcionou, revise TODOS os passos acima!
