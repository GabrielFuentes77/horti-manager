🌿 Horti-Manager - Sistema de Gestão de Paisagismo
O Horti-Manager é um sistema de gestão desenvolvido para otimizar o controle de clientes, fornecedores e estoque de uma loja de paisagismo e horticultura. Este projeto foi concebido como parte de um estudo de caso prático, integrando regras de negócio reais com tecnologias modernas de desenvolvimento.

🚀 Tecnologias Utilizadas
Java 17 (Amazon Corretto): Linguagem base para o desenvolvimento do ecossistema Spring.

Spring Boot 3: Framework para criação de APIs RESTful robustas.

PostgreSQL: Banco de dados relacional para persistência de informações.

Spring Data JPA / Hibernate: Para mapeamento objeto-relacional (ORM).

Lombok: Para redução de código boilerplate (Getters/Setters).

Git: Controle de versão e gestão de portfólio no GitHub.

🛠️ Funcionalidades Implementadas
CRUD de Pessoas: Gerenciamento completo de Clientes e Fornecedores.

API de Integração: Busca inteligente por CPF ou CNPJ com tratamento automático de caracteres especiais (limpeza de máscaras).

Gestão de Cidades: Estrutura independente para organização regional (ex: Assis e Bauru).

Controle de Produtos: Cadastro de plantas e insumos com atributos técnicos como luminosidade e classificação pet friendly.

📦 Como Rodar o Projeto
Clone o repositório: git clone https://github.com/GabrielFuentes77/horti-manager.git

Configure o banco de dados PostgreSQL (nome da base: horti_manager).

Renomeie o arquivo src/main/resources/application.properties.example para application.properties e insira suas credenciais locais.

Execute a aplicação via IntelliJ ou terminal: ./mvnw spring-boot:run

🛡️ Segurança e Boas Práticas
Este projeto segue padrões de segurança, como o uso de .gitignore para proteção de credenciais sensíveis e a normalização de dados antes da persistência no banco.