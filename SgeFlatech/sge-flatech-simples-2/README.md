# 🏭 SGE FLATECH - Sistema de Gestão Eletrônica (Versão Simples)

## 📌 Sobre o Projeto

O **SGE Flatech Simples** é um sistema de gestão para empresas de produção eletrônica, desenvolvido usando o **padrão MVC tradicional** com **DAO + JDBC**.

Este projeto foi criado especificamente para ser **fácil de entender e manter**, sem usar JPA, Repository ou outras abstrações complexas do Spring.

---

## ✨ Características

- ✅ **MVC Tradicional**: Controller → DAO → JDBC → MySQL
- ✅ **JDBC Puro**: Todo SQL escrito à mão, sem "mágica"
- ✅ **POJOs Simples**: Classes Model sem anotações JPA
- ✅ **DAO Pattern**: Lógica de banco de dados centralizada
- ✅ **Spring Boot**: Apenas para MVC e servidor web
- ✅ **Thymeleaf**: Templates HTML dinâmicos

---

## 🗂️ Módulos do Sistema

O sistema possui 5 módulos principais:

| Módulo | Rota | Descrição |
|--------|------|-----------|
| **Clientes** | `/clientes` | Cadastro de clientes da empresa |
| **Componentes** | `/componentes` | Controle de estoque de componentes eletrônicos |
| **Ordens de Produção** | `/ordens` | Gerenciamento de ordens de produção |
| **Produção** | `/producao` | Registro de etapas de produção |
| **Financeiro** | `/financeiro` | Controle de movimentações financeiras |

---

## 🚀 Início Rápido

### 1️⃣ **Pré-requisitos**

- Java 17+
- MySQL 8.0+
- Maven 3.6+
- IntelliJ IDEA (recomendado)

### 2️⃣ **Configurar o banco**

```sql
CREATE DATABASE sge_flatech;
-- Execute seu script SQL para criar as tabelas
```

### 3️⃣ **Alterar a senha**

Edite: `src/main/java/com/flatech/sge/conexao/ConexaoDB.java`

```java
private static final String SENHA = "SUA_SENHA_AQUI";  // <<<< ALTERE AQUI
```

### 4️⃣ **Executar o projeto**

No IntelliJ:
1. Abra a pasta `sge-flatech-simples`
2. Execute: `SgeApplication.java`

Ou no terminal:
```bash
mvn spring-boot:run
```

### 5️⃣ **Acessar no navegador**

```
http://localhost:8080/clientes
http://localhost:8080/componentes
http://localhost:8080/ordens
http://localhost:8080/producao
http://localhost:8080/financeiro
```

---

## 📚 Documentação

Leia os guias na pasta `docs/`:

- **[INICIO-RAPIDO.md](docs/INICIO-RAPIDO.md)** - Como configurar e rodar o projeto
- **[GUIA-MVC-SIMPLES.md](docs/GUIA-MVC-SIMPLES.md)** - Entenda o padrão MVC usado

---

## 🏗️ Estrutura do Projeto

```
sge-flatech-simples/
├── src/main/java/com/flatech/sge/
│   ├── conexao/          ← Conexão JDBC
│   ├── model/            ← POJOs simples
│   ├── dao/              ← SQL à mão
│   ├── controller/       ← Spring MVC
│   └── SgeApplication.java
│
├── src/main/resources/
│   ├── application.properties
│   └── templates/        ← HTML (Thymeleaf)
│
├── pom.xml
└── docs/                 ← LEIA ESTES GUIAS!
```

---

## 💻 Tecnologias Usadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Spring Boot | 3.2.5 | Framework web |
| Thymeleaf | 3.1.x | Templates HTML |
| MySQL | 8.0+ | Banco de dados |
| JDBC | Nativo | Conexão com banco |
| Maven | 3.6+ | Gerenciador de dependências |
| Java | 17+ | Linguagem |

---

## 🎯 Por que "Simples"?

Este projeto **NÃO usa**:
- ❌ Spring Data JPA
- ❌ Hibernate
- ❌ Repository interfaces
- ❌ Service layer
- ❌ @Entity annotations

Este projeto **USA**:
- ✅ JDBC puro (`java.sql`)
- ✅ SQL escrito à mão
- ✅ DAO tradicional
- ✅ POJOs sem anotações
- ✅ MVC direto: Controller → DAO → JDBC

**Vantagem:** Se você conhece SQL e Java básico, você consegue entender e modificar este código!

---

## 📊 Banco de Dados

O sistema usa as seguintes tabelas MySQL:

- `cliente` - Dados dos clientes
- `componente` - Estoque de componentes
- `ordem_producao` - Ordens de produção
- `producao` - Etapas de produção
- `financeiro` - Movimentações financeiras
- `log_alerta` - Log de alertas (trigger)

---

## 🤝 Contribuindo

Este projeto foi criado para fins educacionais e como base para customizações.

Sinta-se livre para:
- Adicionar novos módulos seguindo o mesmo padrão
- Criar páginas HTML mais elaboradas
- Adicionar validações
- Melhorar o CSS

---

## ⚠️ Observações Importantes

1. **Sempre feche as conexões** no bloco `finally`
2. **Use PreparedStatement** para evitar SQL Injection
3. **Altere a senha** em `ConexaoDB.java` antes de rodar
4. Este projeto é **simples de propósito** - não tem todas as "boas práticas" modernas, mas é **fácil de entender**

---

## 🐛 Problemas Comuns

| Erro | Solução |
|------|---------|
| "Access denied" | Altere a senha em `ConexaoDB.java` |
| "Table doesn't exist" | Execute o script SQL para criar as tabelas |
| "Connection refused" | Verifique se o MySQL está rodando |
| "Driver not found" | Execute `mvn clean install` |

---

## 📞 Suporte

Se tiver dúvidas:
1. Leia os guias em `docs/`
2. Verifique os comentários no código
3. Consulte a documentação do Spring Boot: https://spring.io/projects/spring-boot

---

## 📝 Licença

Este projeto é livre para uso educacional e comercial.

---

**Desenvolvido para facilitar o entendimento do padrão MVC tradicional! 🚀**
