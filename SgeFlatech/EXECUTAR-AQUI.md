# 🎯 COMO EXECUTAR O PROJETO - VISUAL

## ✅ SEU MÉTODO MAIN ESTÁ AQUI:

```
📁 sge-flatech-simples/
└── 📁 src/
    └── 📁 main/
        └── 📁 java/
            └── 📁 com/flatech/sge/
                └── 📄 SgeApplication.java  ← ESTE É O ARQUIVO!!!
                    └── ✨ linha 12: public static void main(String[] args)
```

---

## 🚀 JEITO 1: RODAR NO INTELLIJ (RECOMENDADO!)

### 1. Abrir o Projeto
```
IntelliJ → File → Open → sge-flatech-simples → OK
```

### 2. Aguardar Download
```
⏳ Aguarde o IntelliJ baixar dependências (2-5 min)
Você vai ver: "Importing Maven project..." no canto inferior
```

### 3. Localizar o Main
```
Painel Esquerdo (Project):
sge-flatech-simples
└── src
    └── main
        └── java
            └── com.flatech.sge
                └── SgeApplication.java  ← ABRA ESTE!
```

### 4. Executar
```
Opção A: Clique no triângulo verde ▶️ ao lado da linha 12
Opção B: Botão direito no arquivo → Run 'SgeApplication'
Opção C: Menu Run → Run 'SgeApplication'
```

### 5. Ver Rodando
```
Console (parte inferior) vai mostrar:

=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

### 6. Acessar
```
Clique no link: http://localhost:8080
OU digite no navegador
```

**✅ PRONTO! Dashboard vai aparecer!**

---

## 🚀 JEITO 2: RODAR PELO TERMINAL (Maven)

### 1. Ir para a pasta do projeto
```bash
cd sge-flatech-simples
```

### 2. Baixar dependências (primeira vez)
```bash
mvn clean install
```
⏳ **AGUARDE 2-5 MINUTOS!** Está baixando bibliotecas

### 3. Executar
```bash
mvn spring-boot:run
```

### 4. Ver rodando
```
Você vai ver:

=================================
SGE Flatech rodando!
Acesse: http://localhost:8080
=================================
```

### 5. Acessar
```
Abra navegador: http://localhost:8080
```

**✅ PRONTO! Dashboard vai aparecer!**

---

## 🚀 JEITO 3: EXECUTAR O .JAVA DIRETO (Se tiver Java configurado)

```bash
cd sge-flatech-simples/src/main/java
javac com/flatech/sge/SgeApplication.java
java com.flatech.sge.SgeApplication
```

**Mas esse jeito é mais complicado! Use o IntelliJ!**

---

## 📋 CHECKLIST ANTES DE EXECUTAR

```
✅ Java 21 instalado?
   → java -version

✅ Senha do MySQL alterada?
   → src/main/java/com/flatech/sge/conexao/ConexaoDB.java (linha 18)

✅ Banco de dados criado?
   → CREATE DATABASE sge_flatech;

✅ Tabelas criadas?
   → Execute seu script SQL

✅ Maven instalado? (se for usar terminal)
   → mvn -version

✅ IntelliJ aberto? (se for usar IntelliJ)
   → Use esse jeito que é mais fácil!
```

---

## 🎯 LOCALIZAÇÃO EXATA DO MAIN

**Caminho completo:**
```
/workspaces/default/code/sge-flatech-simples/src/main/java/com/flatech/sge/SgeApplication.java
```

**Linha:** 12

**Código:**
```java
public static void main(String[] args) {
    SpringApplication.run(SgeApplication.class, args);
    System.out.println("=================================");
    System.out.println("SGE Flatech rodando!");
    System.out.println("Acesse: http://localhost:8080");
    System.out.println("=================================");
}
```

---

## 🐛 SE DER ERRO

### "Cannot find or load main class"
→ Use o IntelliJ ou Maven! Não execute o .java direto!

### "Access denied for user root"
→ Senha errada! Mude na linha 18 do ConexaoDB.java

### "Port 8080 already in use"
→ Mude para 8081 no application.properties

### "Driver not found"
→ Execute: mvn clean install -U

---

## ✅ RESUMO SUPER RÁPIDO

**JEITO MAIS FÁCIL:**

1. Abra o IntelliJ
2. File → Open → sge-flatech-simples
3. Aguarde download das dependências
4. Abra: src/main/java/com/flatech/sge/SgeApplication.java
5. Clique no triângulo verde ▶️
6. Acesse: http://localhost:8080

**FIM! É SÓ ISSO!**

---

## 🎉 QUANDO FUNCIONAR

Você vai ver no navegador:

```
╔═══════════════════════════════╗
║     🏭 SGE FLATECH            ║
║  Sistema de Gestão Eletrônica ║
╚═══════════════════════════════╝

📊 Dashboard

[👥 Clientes]  [🔧 Componentes]  [📦 Ordens]

[🏭 Produção]  [💰 Financeiro]
```

**Clique nos cards para testar cada módulo!**

---

**SEU MAIN EXISTE SIM! SÓ SEGUIR OS PASSOS! 🚀**
