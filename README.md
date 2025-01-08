# Desafio Backend

Este projeto é um sistema backend desenvolvido como parte de um desafio técnico, com o objetivo de gerenciar informações de clientes, incluindo nome, CPF, endereços, e-mails e telefones, seguindo padrões de segurança e boas práticas de desenvolvimento.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.4**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Boot DevTools
  - Validation
- **H2 Database** (em memória)
- **Maven** (para gerenciamento de dependências)

## Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── com.cooperativa.desafiobackend
│   │       ├── config
│   │       │   ├── CorsConfig.java
│   │       │   └── SecurityConfig.java
│   │       ├── controller
│   │       │   └── ClienteController.java
│   │       ├── exception
│   │       │   └── ClienteNaoEncontrado.java
│   │       ├── model
│   │       │   ├── Cliente.java
│   │       │   ├── Emails.java
│   │       │   ├── Endereco.java
│   │       │   └── Telefone.java
│   │       ├── repository
│   │       │   ├── ClienteRepository.java
│   │       │   ├── EmailsRepository.java
│   │       │   ├── EnderecoRepository.java
│   │       │   └── TelefoneRepository.java
│   │       └── service
│   │           └── ClienteService.java
│   ├── resources
│   │   ├── application.properties
│   │   ├── static
│   │   └── templates
├── test
└── pom.xml
```

## Configuração Inicial

### Requisitos

- JDK 17 ou superior.
- Maven instalado.
- IntelliJ IDEA (ou outro IDE de sua preferência).

### Configuração do Banco de Dados

O projeto utiliza o **H2 Database** em memória, configurado no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:desafio_db;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=algumasenhaai
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

O console do H2 pode ser acessado em: `http://localhost:8080/h2-console`.

### Configuração do Spring Security

Dois usuários foram configurados com diferentes níveis de permissão:

- **Admin**: 
  - Usuário: `admin`
  - Senha: `123qwe!@#`
  - Permissão: Administração total (CRUD).

- **Usuário Padrão**:
  - Usuário: `user`
  - Senha: `123qwe123`
  - Permissão: Apenas leitura.

### Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/MarcosCast/desafio-backend.git
   cd desafio-backend
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Inicie o servidor:
   ```bash
   mvn spring-boot:run
   ```

4. O backend estará disponível em: `http://localhost:8080`.

## Endpoints Disponíveis

### Clientes

#### **GET /api/clientes**
Retorna todos os clientes cadastrados.

#### **GET /api/clientes/{id}**
Retorna os detalhes de um cliente pelo ID.

#### **POST /api/clientes**
Cria um novo cliente. (*Apenas admin pode acessar este endpoint.*)

- Exemplo de payload:
```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "telefones": [
    {
      "tipo": "celular",
      "numero": "11987654321"
    }
  ],
  "emails": [
    {
      "enderecoEmail": "joao.silva@email.com"
    }
  ],
  "enderecos": [
    {
      "cep": "01001000",
      "logradouro": "Praça da Sé",
      "bairro": "Sé",
      "cidade": "São Paulo",
      "uf": "SP"
    }
  ]
}
```

#### **PUT /api/clientes/{id}**
Atualiza os dados de um cliente existente. (*Apenas admin pode acessar este endpoint.*)

#### **DELETE /api/clientes/{id}**
Remove um cliente pelo ID. (*Apenas admin pode acessar este endpoint.*)

## Deploy

O projeto foi implantado no **Railway** e pode ser acessado no seguinte link:

[Desafio Backend - Railway](https://desafio-backend-production-bcb7.up.railway.app)

## Melhorias Futuras

- Implementar autenticação JWT para integração com o frontend.
  - **Motivo da não implementação nesta versão**: A integração com JWT apresentou erros durante o desenvolvimento e, devido ao tempo limitado para o desafio, foi decidido manter a autenticação com Basic Auth como solução provisória.
- Adicionar testes de integração e unitários mais abrangentes.
- Internacionalização (i18n).

## Licença

Este projeto foi desenvolvido como parte de um desafio técnico e não possui licença especificada.
