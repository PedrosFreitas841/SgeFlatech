# 📦 COMO INSTALAR O MAVEN

## ⚠️ IMPORTANTE: Você NÃO precisa do Spring Initializr!

O **Spring Initializr** (https://start.spring.io/) é usado para **CRIAR** novos projetos.

**O seu projeto JÁ ESTÁ PRONTO!** Você só precisa instalar o Maven no seu computador.

---

## 🤔 O QUE É O MAVEN?

Maven é uma ferramenta que:
- 📥 Baixa as dependências do projeto (bibliotecas)
- 🔨 Compila o código Java
- ▶️ Executa a aplicação
- 📦 Cria o arquivo .jar final

É como o `npm` para Node.js ou `pip` para Python!

---

## ✅ VERIFICAR SE JÁ TEM MAVEN

Antes de instalar, veja se já tem:

```bash
mvn -version
```

**Se aparecer algo assim, você JÁ TEM Maven instalado!**
```
Apache Maven 3.9.6
Maven home: C:\Program Files\Apache\maven
Java version: 21.0.1
```

✅ **Pule para a seção "EXECUTAR O PROJETO"** no final!

**Se aparecer erro "mvn não encontrado", continue lendo!**

---

## 📥 INSTALAR MAVEN

Escolha seu sistema operacional:

---

### 🪟 WINDOWS

#### **Opção 1: Instalar com Chocolatey (RECOMENDADO)**

1. Abra o **PowerShell como Administrador**

2. Instale o Chocolatey (se não tiver):
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

3. Instale o Maven:
```powershell
choco install maven
```

4. Verifique:
```bash
mvn -version
```

---

#### **Opção 2: Instalar Manualmente**

1. **Baixar Maven:**
   - Acesse: https://maven.apache.org/download.cgi
   - Baixe: `apache-maven-3.9.6-bin.zip` (ou versão mais recente)

2. **Extrair:**
   - Extraia para: `C:\Program Files\Apache\maven`

3. **Configurar Variáveis de Ambiente:**
   
   a) Abra: `Painel de Controle → Sistema → Configurações Avançadas do Sistema`
   
   b) Clique em: `Variáveis de Ambiente`
   
   c) Em "Variáveis do Sistema", clique em `Novo`:
      - Nome: `MAVEN_HOME`
      - Valor: `C:\Program Files\Apache\maven`
   
   d) Edite a variável `Path`:
      - Clique em `Editar`
      - Clique em `Novo`
      - Adicione: `%MAVEN_HOME%\bin`
   
   e) Clique em `OK` em tudo

4. **Reinicie o terminal** e teste:
```bash
mvn -version
```

---

### 🐧 LINUX (Ubuntu/Debian)

```bash
# Atualizar pacotes
sudo apt update

# Instalar Maven
sudo apt install maven -y

# Verificar
mvn -version
```

---

### 🍎 MAC

#### **Opção 1: Com Homebrew (RECOMENDADO)**

```bash
# Instalar Homebrew (se não tiver)
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Instalar Maven
brew install maven

# Verificar
mvn -version
```

#### **Opção 2: Manual**

1. Baixe de: https://maven.apache.org/download.cgi
2. Extraia para `/opt/maven`
3. Adicione ao `~/.bash_profile` ou `~/.zshrc`:
```bash
export MAVEN_HOME=/opt/maven
export PATH=$MAVEN_HOME/bin:$PATH
```
4. Recarregue:
```bash
source ~/.bash_profile  # ou source ~/.zshrc
mvn -version
```

---

## 🎓 USANDO O INTELLIJ (NÃO PRECISA INSTALAR MAVEN!)

Se você usa o **IntelliJ IDEA**, ele já vem com Maven embutido!

### **Não precisa instalar nada!**

Basta:
1. Abrir o projeto no IntelliJ
2. Esperar ele baixar as dependências (barra inferior)
3. Executar `SgeApplication.java`

**✅ Pronto! O IntelliJ faz tudo sozinho!**

---

## ▶️ EXECUTAR O PROJETO COM MAVEN

Depois de instalar o Maven:

### **1. Ir para a pasta do projeto:**
```bash
cd sge-flatech-simples
```

### **2. Baixar dependências e compilar:**
```bash
mvn clean install
```

**Isso vai:**
- ✅ Baixar todas as bibliotecas (Spring, MySQL, Thymeleaf, etc)
- ✅ Compilar o código Java
- ✅ Criar o arquivo .jar

**Aguarde!** Na primeira vez pode demorar 2-5 minutos!

### **3. Executar a aplicação:**
```bash
mvn spring-boot:run
```

**Você verá no console:**
```
=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

### **4. Acessar no navegador:**
```
http://localhost:8080
```

---

## 🎯 COMANDOS MAVEN ÚTEIS

```bash
# Baixar dependências e compilar
mvn clean install

# Executar a aplicação
mvn spring-boot:run

# Compilar sem executar testes
mvn clean install -DskipTests

# Limpar build anterior
mvn clean

# Ver dependências do projeto
mvn dependency:tree

# Criar arquivo JAR
mvn package
```

---

## 🐛 PROBLEMAS COMUNS

### ❌ "mvn não é reconhecido como comando"

**Causa:** Maven não está no PATH

**Solução Windows:**
1. Verifique se `MAVEN_HOME` existe nas variáveis de ambiente
2. Verifique se `%MAVEN_HOME%\bin` está no PATH
3. Reinicie o terminal/cmd

**Solução Linux/Mac:**
```bash
# Adicione ao ~/.bashrc ou ~/.zshrc
export PATH=/caminho/do/maven/bin:$PATH
source ~/.bashrc
```

---

### ❌ "JAVA_HOME is not set"

**Causa:** Maven precisa saber onde está o Java

**Solução:**

**Windows:**
1. Variáveis de Ambiente → Novo
2. Nome: `JAVA_HOME`
3. Valor: `C:\Program Files\Java\jdk-21` (ou onde instalou)

**Linux/Mac:**
```bash
# Adicione ao ~/.bashrc ou ~/.zshrc
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export PATH=$JAVA_HOME/bin:$PATH
source ~/.bashrc
```

---

### ❌ "Failed to connect to repository"

**Causa:** Sem internet ou proxy bloqueando

**Solução:**
1. Verifique sua conexão com a internet
2. Se usar proxy corporativo, configure o Maven

Crie/edite: `~/.m2/settings.xml` (ou `C:\Users\SeuUsuario\.m2\settings.xml`)
```xml
<settings>
  <proxies>
    <proxy>
      <active>true</active>
      <protocol>http</protocol>
      <host>seu.proxy.com</host>
      <port>8080</port>
    </proxy>
  </proxies>
</settings>
```

---

### ❌ "Failed to execute goal"

**Causa:** Versão do Java incompatível

**Solução:**
1. Verifique a versão do Java:
```bash
java -version
```

2. Deve ser Java 21!

3. Se não for, instale o Java 21 e configure o `JAVA_HOME`

---

## 🔄 ALTERNATIVA: MAVEN WRAPPER (mvnw)

Muitos projetos Spring Boot vêm com o **Maven Wrapper** (mvnw), que funciona sem instalar Maven!

Procure no seu projeto:
- `mvnw` (Linux/Mac)
- `mvnw.cmd` (Windows)

Se existir, use assim:

**Windows:**
```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw clean install
./mvnw spring-boot:run
```

**Vantagem:** Não precisa instalar Maven no sistema!

---

## 📚 ORDEM DE EXECUÇÃO COMPLETA

Seguindo tudo certinho:

```bash
# 1. Verificar Java
java -version
# Deve mostrar: Java 21

# 2. Verificar Maven
mvn -version
# Deve mostrar: Apache Maven 3.x

# 3. Ir para o projeto
cd sge-flatech-simples

# 4. Baixar dependências
mvn clean install
# Aguarde 2-5 minutos na primeira vez

# 5. Executar
mvn spring-boot:run
# Aguarde aparecer "SGE Flatech rodando!"

# 6. Abrir navegador
# http://localhost:8080
```

---

## ✅ QUANDO DEU CERTO

Você verá no terminal:

```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

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

E no navegador em `http://localhost:8080` você verá o dashboard bonito!

---

## 🎓 RESUMO

1. ✅ Você **NÃO precisa** do Spring Initializr (start.spring.io)
2. ✅ O projeto **JÁ ESTÁ PRONTO**
3. ✅ Você só precisa **instalar o Maven** (ou usar o IntelliJ)
4. ✅ Execute: `mvn clean install` e depois `mvn spring-boot:run`
5. ✅ Acesse: http://localhost:8080

---

## 💡 DICA FINAL

**Se você usa IntelliJ IDEA:**
- Não precisa instalar Maven!
- Abra o projeto no IntelliJ
- Execute direto pelo botão verde ▶️

**É muito mais fácil!**

---

**Qualquer dúvida, volte aqui! 🚀**
