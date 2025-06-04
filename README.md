      
# Customer Manager

## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Configurar o Ambiente](#como-executar-o-projeto)
- [Documentação via Swagger](#documentação-via-swagger)

## Descrição

Este projeto é uma API RESTful para gerenciamento de clientes (CRUD - Create, Read, Update, Delete), desenvolvido como parte de um desafio técnico para TOTVS. A aplicação permite criar, listar, visualizar, atualizar e remover clientes no sistema.

## Funcionalidades

-   [X] Criar um novo cliente.
-   [X] Listar todos os clientes cadastrados.
-   [X] Obter os detalhes de um cliente específico por ID.
-   [X] Atualizar os dados de um cliente existente.
-   [X] Remover um cliente do sistema.
-   [X] Gerenciar telefones do cliente.
-   [X] Gerenciar endenreços do cliente.


## Tecnologias Utilizadas

-   **Linguagem:** Java, TypeScript, Html e Css
-   **Framework/Biblioteca Principal:** Spring Boot, Angular, PO UI
-   **Banco de Dados:** PostgreSQL
-   **Testes:** JUnit
-   **Outras Ferramentas:** Docker, Swagger/OpenAPI para documentação

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

Se Windows:
- WSL
- Docker
- Docker-compose

Se Linux/Mac
- Docker
- Docker-compose

## Como Executar o Projeto

- Se estiver usando Windows/Mac, inicialize o docker desktop e aguarde a engine do docker iniciar.

1.  **Crie a pasta do projeto.**


2.  **Acesse a pasta no terminal:**


3.  **Inicialize o git:**
    ```bash
    git init
    ```

3.  **Vincular o repositório:**
    ```bash
    git remote add origin https://github.com/MjDiogo10/customerManager.git
    ```

4.  **Puxar arquivos do projeto:**
    ```bash
    git pull origin master
    ```

5.  **Subir o docker:**
    ```bash
    docker-compose up
    ```

6. **Acessar a aplicação no navegador:**
    - Acesse no navegador a url: http://localhost:4200/customers


## Documentação via Swagger

- Acesse no navegador a url: http://localhost:8080/api/swagger-ui/index.html#/