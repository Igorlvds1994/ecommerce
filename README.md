# 🛒 Projeto E-commerce API - Spring Boot + Oracle

Este é um projeto simples de uma API REST de e-commerce desenvolvida com **Spring Boot**, **Java 21**, e banco de dados **Oracle**. A aplicação realiza operações básicas de CRUD para clientes.

## 🚀 Tecnologias utilizadas

- Java 21 (Preview)
- Spring Boot 3.2.x
- Spring Web
- Spring Data JPA
- Oracle Database 21c (XEPDB1)
- Maven
- Postman (para testes de API)

## 📦 Funcionalidades implementadas

- ✅ Criar cliente
- ✅ Listar todos os clientes
- ✅ Buscar cliente por nome
- ✅ Atualizar cliente
- ✅ Deletar cliente

Cada cliente possui:
- `id` (UUID)
- `nome`
- `email`
- `cpf`

## ⚙️ Como executar o projeto

### Pré-requisitos
- Java 21 instalado
- Oracle Database rodando (XEPDB1)
- Maven instalado

### Passos para rodar localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   cd nome-do-repositorio
Configure o banco Oracle:

Crie um usuário chamado crud_user com permissões para CRUD.

No arquivo src/main/resources/application.yml, ajuste as credenciais:

yaml
Copiar
Editar
spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521/XEPDB1
    username: crud_user
    password: sua_senha
Rode o projeto com Maven:

bash
Copiar
Editar
mvn spring-boot:run
Acesse a aplicação via Postman:

Base URL: http://localhost:8080/clientes

Exemplos de endpoints (JSON)
POST /clientes → Criar cliente:

json
Copiar
Editar
{
  "nome": "João da Silva",
  "email": "joao@email.com",
  "cpf": "12345678901"
}
GET /clientes → Listar todos

GET /clientes?nome=João → Buscar por nome

PUT /clientes/{id} → Atualizar cliente

DELETE /clientes/{id} → Deletar cliente

🧪 Testes com Postman
O projeto foi testado via Postman com todos os endpoints validados. Você pode importar uma coleção manualmente ou testar os endpoints acima.

👨‍💻 Autor
Desenvolvido por Igor Luiz
📧 igor@email.com
🔗 LinkedIn: https://www.linkedin.com/in/igor-videira-0b7889106/ | GitHub : https://github.com/Igorlvds1994
