# AvaliAí

O aplicativo busca dar aos alunos, inicialmente, do curso de engenharia de software uma maneira de avaliar, discutir sobre e criticar matérias disponibilizadas pelo curso. Visando promover um maior entendimento sobre as disciplinas existentes, principalmente para optativas, das quais muitas não têm relação direta com nossa área. Além disso, os dados acumulados poderiam, posteriormente, serem disponibilizados para uso da instituição que poderia utilizar para quaisquer fins desejados.

## Integrantes

* Gabriel Victor Couto Martins de Paula
* João Victor Guerra Prata Lima
* Luís Antônio de Souza e Sousa
* Luiz Gustavo Santos Soares
* Pedro Henrique Fernandes Machado

## Orientadores

* Aline Noberta de Brito
* Cleiton Silva Tavares

## Instruções de utilização

Esta documentação fornece uma visão geral dos requisitos e dependências necessários para executar o projeto localmente. Descreveremos os serviços incluídos no projeto, bem como as etapas para executar o Docker Compose e acessar os diferentes componentes do sistema.

* Requisitos e Dependências

Antes de executar o projeto localmente, certifique-se de ter as seguintes dependências instaladas em seu ambiente de desenvolvimento:

- Docker: [Instalação do Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Instalação do Docker Compose](https://docs.docker.com/compose/install/)

* Serviços do Projeto

O projeto consiste em quatro serviços principais, cada um executado em seu próprio contêiner Docker. Aqui estão os detalhes sobre cada serviço:

1. Frontend (Next.js)
   - Porta: 3000
   - Dependências: Node.js 18

2. Backend (Spring)
   - Porta: 8080
   - Caminho de contexto: /avaliai
   - Dependências: Java 17

3. Banco de Dados (PostgreSQL)
   - Porta: 5432

4. Sistema de Fila (RabbitMQ)
   - Porta: 5672

* Executando o Projeto Localmente

Siga as etapas abaixo para executar o projeto localmente:

1. Certifique-se de que as dependências do Docker e Docker Compose estejam instaladas corretamente em seu sistema.

2. Abra o terminal ou prompt de comando e navegue até o diretório raiz do projeto, onde está localizado o arquivo `docker-compose.yml`.

3. Execute o seguinte comando para iniciar os contêineres usando o Docker Compose:

   ```
   docker-compose up --build
   ```

   Isso criará e iniciará todos os contêineres especificados no arquivo `docker-compose.yml`. O parâmetro `--build` garante que as imagens dos contêineres sejam reconstruídas, se necessário.

4. Aguarde até que todos os serviços sejam inicializados corretamente. Você verá a saída do Docker Compose indicando a criação e execução dos contêineres.

5. Após a inicialização bem-sucedida, você poderá acessar os diferentes componentes do sistema usando as seguintes URLs:

   - Frontend (Next.js): [http://localhost:3000](http://localhost:3000)
   - Backend (Spring): [http://localhost:8080/avaliai](http://localhost:8080/avaliai)
   - Banco de Dados (PostgreSQL): [localhost:5432](localhost:5432)
   - Sistema de Fila (RabbitMQ): [localhost:5672](localhost:5672)

   **Observação:** É importante garantir que o Banco de Dados e o Sistema de Fila estejam em execução antes de iniciar o Backend. Caso contrário, o Backend pode falhar ao iniciar devido às dependências ausentes.

6. O Swagger do Backend pode ser acessado através da seguinte URL:
   - [http://localhost:8080/avaliai/swagger-ui.html](http://localhost:8080/avaliai/swagger-ui.html)

* Encerrando a Execução

Para encerrar a execução do projeto e parar todos os contêineres, você pode pressionar `Ctrl + C` no terminal ou prompt de comando em

 que o Docker Compose está sendo executado. Isso encerrará os contêineres e desligará o sistema localmente.

Esperamos que essas instruções sejam úteis para configurar e executar o projeto localmente. Se você tiver alguma dúvida adicional, não hesite em entrar em contato com a equipe de desenvolvimento.


## Histórico de versões

* 0.1.1
    * CHANGE: Atualização das documentacoes. Código permaneceu inalterado.
* 0.1.0
    * Implementação da funcionalidade X pertencente ao processo P.
* 0.0.1
    * Trabalhando na modelagem do processo de negócios.

