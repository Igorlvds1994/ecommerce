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
   git clone https://github.com/Igorlvds1994/ecommerce.git
   cd ecommerce
Configure o banco Oracle:

Crie um usuário com permissões de CRUD. No exemplo abaixo, utilizamos o nome crud_user, mas você pode usar o nome que desejar, como ecommerce, que é o nome real usado neste projeto:

sql

CREATE USER ecommerce IDENTIFIED BY sua_senha;
GRANT CONNECT, RESOURCE TO ecommerce;
No arquivo src/main/resources/application.yml, ajuste as credenciais do banco de dados Oracle:

yaml

spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521/XEPDB1
    username: ecommerce
    password: sua_senha
Rode o projeto com Maven:

bash
mvn spring-boot:run
Acesse a aplicação via Postman:

Base URL: http://localhost:8080/clientes

Exemplos de Endpoints
POST /clientes → Criar cliente:

json

{
  "nome": "João da Silva",
  "email": "joao@email.com",
  "cpf": "12345678901"
}
GET /clientes → Listar todos os clientes

GET /clientes?nome=João → Buscar cliente por nome

PUT /clientes/{id} → Atualizar cliente

DELETE /clientes/{id} → Deletar cliente

🧪 Testes com Postman
O projeto foi testado via Postman com todos os endpoints validados. Você pode testar manualmente ou importar uma coleção.

👨‍💻 Autor
Desenvolvido por Igor Luiz

📧 igor@email.com
🔗 LinkedIn
🐙 GitHub
