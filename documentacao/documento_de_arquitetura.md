# TITULO DO PROJETO

**Gabriel Victor Couto Martins de Paula, email do aluno 1**

**João Victor Guerra Prata Lima, jvgplhg@@gmail.com 2**

**Luís Antônio de Souza e Sousa, email do aluno 3**

**Luiz Gustavo Santos Soares, email do aluno 4**

**Pedro Henrique Fernandes Machado, email do aluno 5**

---

Professores:

**Aline Noberta de Brito**

**Cleiton Silva Tavares**


---

_Curso de Engenharia de Software, Unidade Praça da Liberdade_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---

_**Resumo**. Escrever aqui o resumo. O resumo deve contextualizar rapidamente o trabalho, descrever seu objetivo e, ao final, 
mostrar algum resultado relevante do trabalho (até 10 linhas)._

---

## Histórico de Revisões

| **Data** | **Autor** | **Descrição** | **Versão** |
| --- | --- | --- | --- |
| **[dd/mm/aaaa]** | [Nome do autor] | [Descrever as principais alterações realizadas no documento, evidenciando as seções ou capítulos alterados] | [X] |
| | | | |
| | | | |

## SUMÁRIO

1. [Apresentação](#apresentacao "Apresentação") <br />
	1.1. Problema <br />
	1.2. Objetivos do trabalho <br />
	1.3. Definições e Abreviaturas <br />

2. [Requisitos](#requisitos "Requisitos") <br />
'	2.1. Requisitos Funcionais <br />
	2.2. Requisitos Não-Funcionais <br />
	2.3. Restrições Arquiteturais <br />
	2.4. Mecanismos Arquiteturais <br />

3. [Modelagem](#modelagem "Modelagem e projeto arquitetural") <br />
	3.1. Visão de Negócio <br />
	3.2. Visão Lógica <br />
	3.3. Modelo de dados (opcional) <br />

4. [Avaliação](#avaliacao "Avaliação da Arquitetura") <br />
	4.1. Cenários <br />
	4.2. Avaliação <br />

5. [Referências](#referencias "REFERÊNCIAS")<br />

6. [Apêndices](#apendices "APÊNDICES")<br />


<a name="apresentacao"></a>
# 1. Apresentação

_Faça uma introdução ao projeto, apresentando o contexto onde o projeto se situa. É importante deixar claro para o leitor os aspectos que configuram o problema que será apresentado na sequência. Apresente, se possível, números reais que demonstram a relevância do problema apresentado._


## 1.1. Problema

_Nesse momento você deve apresentar o problema que a sua aplicação deve resolver. No entanto, não é a hora de comentar sobre a aplicação._

## 1.2. Objetivos do trabalho

_Aqui você deve descrever os objetivos do trabalho indicando que o objetivo geral é apresentar a descrição do projeto arquitetural da aplicação escolhida. Apresente também os objetivos específicos do projeto, descrevendo onde você vai concentrar sua atenção na descrição arquitetural, ou seja, os pontos onde você vai aprofundar no seu trabalho._

## 1.3. Definições e Abreviaturas

Coloque aqui as definições, siglas e abreviaturas utilizadas no trabalho._

<a name="requisitos"></a>
# 2. Requisitos

_Esta seção descreve os requisitos comtemplados nesta descrição arquitetural, divididos em dois grupos: funcionais e não funcionais._

## 2.1. Requisitos Funcionais

_Enumere os requisitos funcionais previstos para a sua aplicação. Concentre-se nos requisitos funcionais que sejam críticos para a definição arquitetural. Lembre-se de listar todos os requisitos que são necessários para garantir cobertura arquitetural. Esta seção deve conter uma lista de requisitos ainda sem modelagem. Na coluna Prioridade utilize uma escala (do mais prioritário para o menos): Essencial, Desejável, Opcional._

| **ID** | **Descrição** | **Prioridade** |
| --- | --- | --- |
| RF001  | O usuário deve conseguir avaliar uma determinada disciplina com uma nota de 1 a 5             | Essencial |
| RF002  | O Usuário pode incluir em sua avaliação um texto para justificar a nota daquela matéria.      | Essencial |
| RF003  | O usuário deve conseguir aprovar ou desaprovar a avaliação de outros usuários através de um sistema de upvote e downvote | Essencial |
| RF004  | O sistema deve ser capaz de apresentar a lista de diciplinas ofertadas pelo curso de engenharia de software.| Essencial |
| RF005  | O usuário deve ser capaz de pesquisar determinada disciplina por seu nome no sistema.| Essencial|
| RF006  | O usuário deve ser capaz de filtrar disciplinas por período no sistema.| Desejável |
| RF007  | Ter dados das disciplinas, como carga horária, modalidade(síncrona)| Essencial |
| RF008  | O sistema deve manter anonimato do usuário | Essencial |
| RF009  | O sistema deve apenas permitir a criação de conta com email puc | Essencial |
| RF010  | O sistema deve possuir pontuação baseada em like/dislike dos comentários. | Essencial |
| RF011  | O sistema deve permitir denúncia de comentário, restrição de comentar e dar nota.| Essencial |
| RF012  | O sistema deve possuir formulário de requisição para adicionar disciplinas. | Essencial |
| RF013  | O sistema deve permitir preenchimento de formulário de requisição para adicionar disciplinas | Essencial |

Obs: acrescente mais linhas, se necessário.

## 2.2. Requisitos Não-Funcionais

_Enumere os requisitos não-funcionais previstos para a sua aplicação. Entre os requisitos não funcionais, inclua todos os requisitos que julgar importante do ponto de vista arquitetural ou seja os requisitos que terão impacto na definição da arquitetura. Os requisitos devem ser descritos de forma completa e preferencialmente quantitativa._

| **ID** | **Descrição** |
| --- | --- |
| RNF001 | O sistema deve prover serviços no ambiente web e móvel. |
| RNF002 | O sistema deve possuir uma implementação do front-end em tecnologias móveis e/ou híbridas (multiplataforma). |
| RNF003 | O sistema deve permitir que múltiplos clientes se conectem a um único servidor e realizem operações de forma concorrente. |
| RNF004 | O sistema deve tratar erros de cliente e de servidor corretamente. Por exemplo: falhas de comunicação, servidor indisponível, timeouts etc. Mostre como esses erros são tratados com estratégias tais como reenvio de mensagens. |
| RNF005 | O sistema deve possuir implementação de estratégias de testes. |

Obs: acrescente mais linhas, se necessário.

## 2.3. Restrições Arquiteturais

_Enumere as restrições arquiteturais. Lembre-se de que as restrições arquiteturais geralmente não são consideradas requisitos uma vez que limitam a solução candidata. Os requisitos não impõem restrição, mas precisam ser satisfeitos._

As restrições impostas ao projeto que afetam sua arquitetura são (por exemplo):

- O sistema deve utilizar um modelo baseado em Web Service.
- O sistema deve fazer uso de middlewares e tecnologias associadas no contexto de processamento de mensagens em tempo real (mensageria).
- O sistema deve possuir ou utilizar serviços em nuvem.
- A comunicação da API deve seguir o padrão RESTful.

## 2.4. Mecanismos Arquiteturais

_Visão geral dos mecanismos que compõem a arquitetura do sosftware baseando-se em três estados: (1) análise, (2) design e (3) implementação. Em termos de Análise devem ser listados os aspectos gerais que compõem a arquitetura do software como: persistência, integração com sistemas legados, geração de logs do sistema, ambiente de front end, tratamento de exceções, formato dos testes, formato de distribuição/implantação (deploy), entre outros. Em Design deve-se identificar o padrão tecnológico a seguir para cada mecanismo identificado na análise. Em Implementação, deve-se identificar o produto a ser utilizado na solução.
 Ex: Análise (Persistência), Design (ORM), Implementação (Hibernate)._

| **Análise** | **Design** | **Implementação** |
| --- | --- | --- |
| Persistência |Banco de dados não relacional |Firebase |
| Front end |Interface de comunicação com o usuário web | |
| Back end | Plataforma de desenvolvimento orientada a objeto | Spring boot |
| Integração | | |
| Log do sistema | | |
| Teste de Software |Camada para tratar as exceções criando interações diferentes para usuários | |
| Deploy | Configuração da IDE de deploy  | Visual Studio |

<a name="modelagem"></a>

# 3. Modelagem e projeto arquitetural

A arquitetura do sistema Avaliaí utiliza o protocolo de rede HTTP para se comunicar com outros serviços e ferramentas. A requisição do usuário passa pelo RabbitMQ, um serviço de mensagens aberto que notificará o restante da aplicação, modelada em arquitetura MVC (Model, View, Controller). A primeira camada, view, composta pelos frameworks Next.JS (web) e Flutter (mobile), se comunica com o controller, composto pelo serviço Firebase e o framework Spring. Esse último  se comunica com a camada de dados, em PostgreSQL. Todo esse processo é disponibilizado em containers, tarefa realizada pelo Docker.

![Arquitetura Avaliaí drawio (1)](https://user-images.githubusercontent.com/84593164/223147012-2e294e31-d566-48c8-9f06-fa60ceb1a019.png)


**Figura 1 - Visão Geral da Solução**


## 3.1. Visão de Negócio (Funcionalidades)

1. O sistema deve possibilitar avaliação de disciplinas.
2. O sistema deve permitir textos justificando a avaliação
3. O sistema deve possibilitar Like e dislike do comentário/avaliação.
4. O sistema deve possibilitar listar todas as disciplinas.
5. Deve ser possível pesquisar disciplinas pelo nome
6. Deve ser possível pesquisar disciplinas pelo período.
7. O sistema deve poder apresentar dados da disciplina.
8. O sistema deve manter o anonimato do usuário.
9. O sistema deve permitir a criação de contas apenas com o puc mail.
10. O sistema deve garantir um sistema de pontuação baseado em likes/dislikes.
11. O sistema deve apresentar moderação e diretrizes.
12. O sistema deve permitir denúncia de comentários e restrições.
13. O usuário deve possuir um nome gerado aleatoriamente.
14. O usuário deve possuir uma foto gerada aleatoriamente.
15. O sistema deve disponibilizar um formulário para requisição de disciplinas.


### Descrição resumida dos Casos de Uso

Exemplos de resumo de Casos de Uso:

#### UC01 – AVALIAR DISCIPLINAS

| **Descrição** | Permitir ao usuário avaliar as disciplinas da universidade  |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Alta |
| **Requisitos associados** | RF 1, RF 2,  |
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas, juntamente com um campo de busca
			2. O usuário escolhe a disciplina que deseja avaliar
			3. A partir desse contexto, é solicitado que o usuário atribua uma nota de 1 a 5 à disciplina e redija um comentário a respeito  |

#### UC02 – AVALIAR COMENTÁRIOS EM DISCIPLINA

| **Descrição** | O usuário deve ser capaz de avaliar - com likes ou dislikes - outros comentários na página da disciplina |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Média |
| **Requisitos associados** | 3 |
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas, juntamente com um campo de busca
			2. O usuário escolhe a disciplina que deseja avaliar
			3. Os comentários dos usuários são mostrados na página da disciplina, permitindo que o usuário atribua um "Like" ou "Dislike" aos mesmos |

#### UC03 – LISTAR DISCIPLINAS

| **Descrição** | O sistema deve ser capaz de listar todas as disciplinas cadastradas |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Alta |
| **Requisitos associados** | 4 |
| **Fluxo Principal** | 1. O usuário entra na página inicial e encontra uma lista de disciplinas já cadastradas no sistema |
			

#### UC04 – PESQUISAR DISCIPLINAS 

| **Descrição** | O usuário deve ser capaz de pesquisar disciplinas por nome ou período |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Baixa |
| **Requisitos associados** | 4, 5 |
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas, juntamente com um campo de busca
			2. O usuário seleciona o campo de busca na página e insere o nome ou período da disciplina |

#### UC05 – CRIAR CONTA COM PUCMAIL 

| **Descrição** | O sistema deve permitir que o usuário se cadastre na plataforma com seu pucmail (@sga) |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Alta |
| **Requisitos associados** | 9 |
| **Fluxo Principal** | 1. O sistema apresenta a opção de *Cadastro* e *Login*
			2. Selecionando a opção de cadastro, o usuário informa seu e-mail da instituição PUC Minas |

#### UC06 – DENUNCIAR COMENTÁRIOS

| **Descrição** | O sistema deve permitir que o usuário denuncie outros comentários publicados em disciplinas |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Média |
| **Requisitos associados** | 12 |
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas, juntamente com um campo de busca
			2. O usuário escolhe a disciplina que deseja avaliar
			3. Os comentários dos usuários são mostrados na página da disciplina, permitindo que o usuário denuncie os mesmos e atribua uma justificativa |


#### UC07 – MOSTRAR DADOS DA DISCIPLINA

| **Descrição** | O sistema deve mostrar os dados da disciplina selecionada (período, carga horária, ementa, etc) |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Alta |
| **Requisitos associados** | 7 |
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas, juntamente com um campo de busca
			2. O usuário escolhe a disciplina que deseja avaliar
			3. Os dados da disciplina são mostrados na página |


#### UC08 – REQUISITAR DISCIPLINA

| **Descrição** | O sistema deve disponibilizar um formulário para requisição de disciplinas |
| --- | --- |
| **Atores** | Usuário |
| **Prioridade** | Alta |
| **Requisitos associados** | 15 |
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas e um botão "Requisitar Disciplina"
			2. O usuário seleciona essa opção
			3. O usuário é direcionado para uma página de cadastro de disciplina, devendo preencher todos os campos requisitados
			4. Após preenchidas as informações, o usuário deve selecionar a opção "solicitar requisição" |
			
			
#### UC09 – GERAR IDENTIDADE VISUAL

| **Descrição** | O sistema deve gerar um nome e foto aleatórios para o usuário recém cadastrado  |
| --- | --- |
| **Atores** | Sistema |
| **Prioridade** | Média |
| **Requisitos associados** | 13, 14 |
| **Fluxo Principal** | 1. O usuário se cadastra no sistema
			2. Um nome aleatório e uma foto de perfil são geradas pelo sistema |

## 3.2. Visão Lógica

_Apresente os artefatos que serão utilizados descrevendo em linhas gerais as motivações que levaram a equipe a utilizar estes diagramas._

### Diagrama de Classes

![Diagrama de Classes - Avaliaí drawio](https://user-images.githubusercontent.com/84593164/223796282-dbb51f04-e9c7-452d-926f-0d91893eba92.png)



**Figura 2 – Diagrama de classes (exemplo). Fonte: o próprio autor.**

Obs: Acrescente uma breve descrição sobre o diagrama apresentado na Figura 3.

### Diagrama de componentes

_Apresente o diagrama de componentes da aplicação, indicando, os elementos da arquitetura e as interfaces entre eles. Liste os estilos/padrões arquiteturais utilizados e faça uma descrição sucinta dos componentes indicando o papel de cada um deles dentro da arquitetura/estilo/padrão arquitetural. Indique também quais componentes serão reutilizados (navegadores, SGBDs, middlewares, etc), quais componentes serão adquiridos por serem proprietários e quais componentes precisam ser desenvolvidos._

![Diagrama de Componentes - Avaliaí drawio](https://user-images.githubusercontent.com/84593164/223796339-0d652d01-5473-47f5-a5f6-837eb3a25c62.png)


**Figura 3 – Diagrama de Componentes (exemplo). Fonte: o próprio autor.**

_Apresente uma descrição detalhada dos artefatos que constituem o diagrama de implantação._

Ex: conforme diagrama apresentado na Figura X, as entidades participantes da solução são:

- **Componente 1** - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras nunc magna, accumsan eget porta a, tincidunt sed mauris. Suspendisse orci nulla, sagittis a lorem laoreet, tincidunt imperdiet ipsum. Morbi malesuada pretium suscipit.
- **Componente 2** - Praesent nec nisi hendrerit, ullamcorper tortor non, rutrum sem. In non lectus tortor. Nulla vel tincidunt eros.

## 3.3. Modelo de dados (opcional)

_Caso julgue necessário para explicar a arquitetura, apresente o diagrama de classes ou diagrama de Entidade/Relacionamentos ou tabelas do banco de dados. Este modelo pode ser essencial caso a arquitetura utilize uma solução de banco de dados distribuídos ou um banco NoSQL._

![Diagrama de Entidade Relacionamento (ER) ](imagens/der.png "Diagrama de Entidade Relacionamento (ER) ")

**Figura 4 – Diagrama de Entidade Relacionamento (ER) - exemplo. Fonte: o próprio autor.**

Obs: Acrescente uma breve descrição sobre o diagrama apresentado na Figura 3.

<a name="avaliacao"></a>
# 4. Avaliação da Arquitetura

_Esta seção descreve a avaliação da arquitetura apresentada, baseada no método ATAM._

## 4.1. Cenários

_Apresente os cenários de testes utilizados na realização dos testes da sua aplicação. Escolha cenários de testes que demonstrem os requisitos não funcionais sendo satisfeitos. Os requisitos a seguir são apenas exemplos de possíveis requisitos, devendo ser revistos, adequados a cada projeto e complementados de forma a terem uma especificação completa e auto-explicativa._

**Cenário 1 - Acessibilidade:** Suspendisse consequat consectetur velit. Sed sem risus, dictum dictum facilisis vitae, commodo quis leo. Vivamus nulla sem, cursus a mollis quis, interdum at nulla. Nullam dictum congue mauris. Praesent nec nisi hendrerit, ullamcorper tortor non, rutrum sem. In non lectus tortor. Nulla vel tincidunt eros.

**Cenário 2 - Interoperabilidade:** Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce ut accumsan erat. Pellentesque in enim tempus, iaculis sem in, semper arcu.

**Cenário 3 - Manutenibilidade:** Phasellus magna tellus, consectetur quis scelerisque eget, ultricies eu ligula. Sed rhoncus fermentum nisi, a ullamcorper leo fringilla id. Nulla lacinia sem vel magna ornare, non tincidunt ipsum rhoncus. Nam euismod semper ante id tristique. Mauris vel elit augue.

**Cenário 4 - Segurança:** Suspendisse consectetur porta tortor non convallis. Sed lobortis erat sed dignissim dignissim. Nunc eleifend elit et aliquet imperdiet. Ut eu quam at lacus tincidunt fringilla eget maximus metus. Praesent finibus, sapien eget molestie porta, neque turpis congue risus, vel porttitor sapien tortor ac nulla. Aliquam erat volutpat.

## 4.2. Avaliação

_Apresente as medidas registradas na coleta de dados. O que não for possível quantificar apresente uma justificativa baseada em evidências qualitativas que suportam o atendimento do requisito não-funcional. Apresente uma avaliação geral da arquitetura indicando os pontos fortes e as limitações da arquitetura proposta._

| **Atributo de Qualidade:** | Segurança |
| --- | --- |
| **Requisito de Qualidade** | Acesso aos recursos restritos deve ser controlado |
| **Preocupação:** | Os acessos de usuários devem ser controlados de forma que cada um tenha acesso apenas aos recursos condizentes as suas credenciais. |
| **Cenários(s):** | Cenário 4 |
| **Ambiente:** | Sistema em operação normal |
| **Estímulo:** | Acesso do administrador do sistema as funcionalidades de cadastro de novos produtos e exclusão de produtos. |
| **Mecanismo:** | O servidor de aplicação (Rails) gera um _token_ de acesso para o usuário que se autentica no sistema. Este _token_ é transferido para a camada de visualização (Angular) após a autenticação e o tratamento visual das funcionalidades podem ser tratados neste nível. |
| **Medida de Resposta:** | As áreas restritas do sistema devem ser disponibilizadas apenas quando há o acesso de usuários credenciados. |

**Considerações sobre a arquitetura:**

| **Riscos:** | Não existe |
| --- | --- |
| **Pontos de Sensibilidade:** | Não existe |
| _ **Tradeoff** _ **:** | Não existe |

Evidências dos testes realizados

_Apresente imagens, descreva os testes de tal forma que se comprove a realização da avaliação._

<a name="referencias"></a>
# 5. REFERÊNCIAS

_Como um projeto da arquitetura de uma aplicação não requer revisão bibliográfica, a inclusão das referências não é obrigatória. No entanto, caso você deseje incluir referências relacionadas às tecnologias, padrões, ou metodologias que serão usadas no seu trabalho, relacione-as de acordo com a ABNT._

Verifique no link abaixo como devem ser as referências no padrão ABNT:

http://www.pucminas.br/imagedb/documento/DOC\_DSC\_NOME\_ARQUI20160217102425.pdf


**[1]** - _ELMASRI, Ramez; NAVATHE, Sham. **Sistemas de banco de dados**. 7. ed. São Paulo: Pearson, c2019. E-book. ISBN 9788543025001._

**[2]** - _COPPIN, Ben. **Inteligência artificial**. Rio de Janeiro, RJ: LTC, c2010. E-book. ISBN 978-85-216-2936-8._

**[3]** - _CORMEN, Thomas H. et al. **Algoritmos: teoria e prática**. Rio de Janeiro, RJ: Elsevier, Campus, c2012. xvi, 926 p. ISBN 9788535236996._

**[4]** - _SUTHERLAND, Jeffrey Victor. **Scrum: a arte de fazer o dobro do trabalho na metade do tempo**. 2. ed. rev. São Paulo, SP: Leya, 2016. 236, [4] p. ISBN 9788544104514._

**[5]** - _RUSSELL, Stuart J.; NORVIG, Peter. **Inteligência artificial**. Rio de Janeiro: Elsevier, c2013. xxi, 988 p. ISBN 9788535237016._


<a name="apendices"></a>
# 6. APÊNDICES

_Inclua o URL do repositório (Github, Bitbucket, etc) onde você armazenou o código da sua prova de conceito/protótipo arquitetural da aplicação como anexos. A inclusão da URL desse repositório de código servirá como base para garantir a autenticidade dos trabalhos._
