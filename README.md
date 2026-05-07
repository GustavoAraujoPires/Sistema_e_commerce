# 🛒 Projeto E-commerce API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0-green?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql" />
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven" />
</p>

---

# 📚 Sobre o Projeto

Este projeto é uma API REST de E-commerce desenvolvida utilizando:

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- MySQL
- Maven
- Lombok
- MapStruct
- Validações com Jakarta Validation
- Tratamento Global de Exceções

O objetivo principal da aplicação é simular o funcionamento básico de um sistema de e-commerce, permitindo:

✅ Cadastro de clientes  
✅ Cadastro de produtos  
✅ Criação de pedidos  
✅ Pagamento de pedidos  
✅ Entrega de pedidos  
✅ Cancelamento de pedidos  
✅ Tratamento de erros personalizados  

---

# 🎯 Objetivo do Projeto

Este projeto foi criado com foco em aprendizado de:

- Arquitetura em camadas
- APIs REST
- Boas práticas com Spring Boot
- Organização de código
- Tratamento de exceções
- DTOs
- Mapeamento com MapStruct
- Persistência de dados com JPA
- Integração com banco MySQL

---

# 🧱 Estrutura do Projeto

```bash
src/main/java/com/github/GustavoAraujoPires/Projeto/e_commerce
│
├── controller
│   ├── ClienteController
│   ├── ProdutosController
│   ├── PedidosController
│   └── common
│       └── GlobalExceptionHandler
│
├── controller/dto
│   ├── ClienteDTO
│   ├── ProdutoDTO
│   ├── PedidoRequestDTO
│   ├── PedidoResponseDTO
│   ├── ErroCampo
│   └── ErroResposta
│
├── controller/mappers
│   ├── ClienteMapper
│
├── model
│   ├── Cliente
│   ├── Produto
│   └── Pedido
│
├── repository
│   ├── ClienteRepository
│   ├── ProdutoRepository
│   └── PedidoRepository
│
├── service
│   ├── ClienteService
│   ├── ProdutoService
│   └── PedidoService
│
├── exception
│   ├── ClienteInvalidoException
│   ├── IdNaoEncontradoException
│   ├── PagamentoInvalidoException
│   ├── PedidoEntregueException
│   ├── PedidoInvalidoException
│   └── ProdutoInvalidoException

```

---

# 🧠 Explicação da Arquitetura

A aplicação segue uma arquitetura em camadas.

## 📌 Controller

Responsável por receber as requisições HTTP.

Exemplo:

```java
@PostMapping
public ResponseEntity<ClienteDTO> salvar(@RequestBody @Valid ClienteDTO dto)
```

Aqui a API recebe os dados enviados pelo usuário.

---

## 📌 Service

Responsável pelas regras de negócio.

Exemplo:

- Validar cliente
- Validar pagamento
- Verificar status do pedido
- Cancelar pedidos
- Validar estoque de produtos

A camada Service evita que a regra de negócio fique dentro do controller.

---

## 📌 Repository

Responsável pela comunicação com o banco de dados.

Utiliza Spring Data JPA.

Exemplo:

```java
public interface ClienteRepository extends JpaRepository<Cliente, Long>
```

---

## 📌 DTO

DTO significa:

> Data Transfer Object

Serve para transferir dados entre cliente e servidor.

Isso evita expor diretamente as entidades do banco.

Exemplo:

```java
public class ProdutoDTO {
    private String nome;
    private BigDecimal preco;
}
```

---

## 📌 Mapper

O MapStruct é utilizado para converter DTO → Entidade.

Exemplo:

```java
Produto toEntity(ProdutoDTO dto);
```

---

## 📌 Exception Handler

Centraliza os erros da aplicação.

Classe:

```java
GlobalExceptionHandler
```

Ela captura exceções personalizadas e retorna mensagens amigáveis.

Exemplo:

```json
{
  "status": 404,
  "mensagem": "Produto não encontrado"
}
```

---

# ⚙️ Dependências Utilizadas

## 📦 Spring Web MVC

Responsável pela criação da API REST.

---

## 📦 Spring Data JPA

Responsável pela persistência de dados.

---

## 📦 MySQL Connector

Conexão com banco de dados MySQL.

---

## 📦 Lombok

Reduz código repetitivo.

Exemplo:

```java
@Data
@RequiredArgsConstructor
```

---

## 📦 MapStruct

Automatiza o mapeamento entre DTOs e entidades.

---

# 🚀 Como Rodar o Projeto

# 1️⃣ Clonar o Repositório

```bash
git clone URL_DO_REPOSITORIO
```

---

# 2️⃣ Entrar na Pasta

```bash
cd Projeto-e-commerce
```

---

# 3️⃣ Configurar o Banco MySQL

Crie um banco no MySQL:

```sql
CREATE DATABASE ecommerce;
```

---

# 4️⃣ Configurar o application.yaml

Local:

```bash
src/main/resources/application.yaml
```

Exemplo:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce
    username: root
    password: sua_senha

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

# 5️⃣ Rodar a Aplicação

## Pelo IntelliJ

Execute a classe principal do Spring Boot.

---

## Pelo Terminal

### Linux / Mac

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

---

# 🌐 Porta da Aplicação

Por padrão:

```bash
http://localhost:8080
```

---

# 📬 Endpoints da API

# 👤 Clientes

## ➕ Criar Cliente

```http
POST /clientes
```

### Body

```json
{
  "nome": "Gustavo",
  "email": "gustavo@email.com"
}
```

---

## 📋 Buscar Todos Clientes

```http
GET /clientes
```

---

## 🔎 Buscar Cliente por ID

```http
GET /clientes/{id}
```

---

## ❌ Deletar Cliente

```http
DELETE /clientes/{id}
```

---

# 📦 Produtos

## ➕ Criar Produto

```http
POST /produtos
```

### Body

```json
{
  "nome": "Notebook Gamer",
  "descricao": "Notebook com RTX 4060",
  "preco": 5500.00,
  "estoque": 10
}
```

---

## 📋 Buscar Todos Produtos

```http
GET /produtos
```

---

## 🔎 Buscar Produto por ID

```http
GET /produtos/{id}
```

---

## ✏️ Atualizar Produto

```http
PUT /produtos/{id}
```

### Body

```json
{
  "nome": "Notebook Atualizado",
  "descricao": "Nova descrição",
  "preco": 6000.00,
  "estoque": 8
}
```

---

## ❌ Deletar Produto

```http
DELETE /produtos/{id}
```

---

# 🛒 Pedidos

## ➕ Criar Pedido

```http
POST /pedidos
```

### Body

```json
{
  "clienteId": 1,
  "listaPedidoIds": [1, 2]
}
```

---

## 📋 Buscar Todos Pedidos

```http
GET /pedidos
```

---

## 🔎 Buscar Pedido por ID

```http
GET /pedidos/{id}
```

---

## 💳 Pagar Pedido

```http
PATCH /pedidos/{id}/pagar
```

---

## 🚚 Entregar Pedido

```http
PATCH /pedidos/{id}/entregar
```

---

## ❌ Cancelar Pedido

```http
PATCH /pedidos/{id}/cancelar
```

---

## 🗑️ Deletar Pedido

```http
DELETE /pedidos/{id}
```

---

# 🛡️ Tratamento de Exceções

A aplicação possui tratamento global de erros.

## Exemplos:

### Cliente não encontrado

```json
{
  "status": 404,
  "mensagem": "Cliente não encontrado",
  "erros": []
}
```

---

### Produto não encontrado

```json
{
  "status": 404,
  "mensagem": "Produto não encontrado",
  "erros": []
}
```

---

### Erro de validação

```json
{
  "status": 422,
  "mensagem": "Erro de validação",
  "erros": [
    {
      "campo": "email",
      "erro": "must be a well-formed email address"
    }
  ]
}
```

---

# 🔍 Explicando o Fluxo da Aplicação

# 🧠 Fluxo de Cadastro de Cliente

```text
Cliente envia requisição
        ↓
Controller recebe
        ↓
DTO valida dados
        ↓
Mapper converte DTO → Entidade
        ↓
Service executa regra de negócio
        ↓
Repository salva no banco
        ↓
Resposta retorna ao usuário
```

---

# 🧠 Fluxo de Cadastro de Produto

```text
Usuário envia dados do produto
        ↓
Controller recebe requisição
        ↓
DTO valida dados
        ↓
Mapper converte DTO → Entidade
        ↓
Service aplica regras de negócio
        ↓
Repository salva no banco
        ↓
Resposta enviada ao usuário
```

---

# 🧠 Fluxo de Criação de Pedido

```text
Usuário envia clienteId e produtos
        ↓
Controller recebe requisição
        ↓
Service valida cliente
        ↓
Service busca produtos
        ↓
Pedido é criado
        ↓
Valor total é calculado
        ↓
Pedido salvo no banco
        ↓
Resposta enviada
```

---

# 📚 Conceitos Aplicados no Projeto

| Conceito | Aplicação |
|---|---|
| REST API | Comunicação HTTP |
| DTO | Transferência de dados |
| JPA | Persistência |
| Repository Pattern | Acesso ao banco |
| Exception Handler | Tratamento de erros |
| Validation | Validação de entrada |
| MapStruct | Conversão DTO ↔ Entidade |
| Lombok | Redução de código |

---

# 🧪 Ferramentas Recomendadas para Testar

## ✅ Postman

Ideal para testar endpoints.

---

## ✅ Insomnia

Alternativa ao Postman para testes de API.

---

# 💻 Tecnologias Utilizadas

- Java 21
- Spring Boot 4
- MySQL
- Maven
- JPA / Hibernate
- Lombok
- MapStruct
- Jakarta Validation

---

# 👨‍💻 Autor

Desenvolvido por Gustavo Araujo Pires.

Projeto desenvolvido com foco em aprendizado de desenvolvimento backend com Java e Spring Boot.

---

# ❤️ Considerações Finais

Este projeto foi muito importante para consolidar conhecimentos em:

- APIs REST
- Spring Boot
- Arquitetura em camadas
- Tratamento de exceções
- Persistência com JPA
- Organização de código
- DTOs e Mappers
- Desenvolvimento de APIs profissionais

Além disso, ele representa uma evolução prática no desenvolvimento backend utilizando Java.
