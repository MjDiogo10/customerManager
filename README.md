      
# Custumer Manager

## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Configurar o Ambiente](#como-configurar-o-ambiente)

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

## Como Configurar o Ambiente

1.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO_GIT]
    cd projectCustomerManager
    ```

2.  **Execute o docker-compose:**
    ```bash
    docker-compose up
    ```