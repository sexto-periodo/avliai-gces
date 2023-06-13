# AVALIAÍ

**Gabriel Victor Couto Martins de Paula, gabriel.paula.1265840@sga.pucminas.br**

**João Victor Guerra Prata Lima, joao.lima.1322940@sga.pucminas.br**

**Luís Antônio de Souza e Sousa, lassousa@sga.pucminas.br**

**Luiz Gustavo Santos Soares, luiz.soares.1310151@sga.pucminas.br**

**Pedro Henrique Fernandes Machado, pedro.machado.1277720@sga.pucminas.br**

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

É muito comum em cursos de graduação que seus alunos não saibam sua grade por completo e, como consequência, não estejam preparados para estas, ou ainda pior, nem sabem o que esperar de cada matéria que está cursando e isto pode levar os alunos a se dedicarem pouco a uma matéria de extrema importância ao curso. Para mais, alunos também precisam decidir sobre matérias optativas e, atualmente, não existe nenhum meio facilitador para conseguir informações a respeito destas matérias e suas respectivas relevância ao curso.


## 1.1. Problema

O principal detrator da situação atual é a falta de um local adequado para busca de informações, além da falta de contato com colegas da universidade ou até mesmo do próprio curso. Além disso, não existe qualquer tipo de registro histórico para acesso dos alunos, deste modo é difícil saber se a ementa da matéria foi alterada antes que realmente comece a cursar. 

Em adição, o atual formato de avaliação de disciplinas, ou seja, a CPA, não é de agrado dos alunos, tanto pelo seu valor institucional, dando uma sensação de obrigatoriedade, quanto pela quantidade exaustiva de perguntas. Sendo assim, os dados coletados através da CPA perdem parte do valor, já que são escassos e, possivelmente, respondidos de maneira leviana, no intuito de finalizar rapidamente a avaliação.


## 1.2. Objetivos do trabalho

Para o desenvolvimento da aplicação teremos como objetivo geral desenvolver um aplicativo mobile e uma página web que auxilia alunos e universidades a terem melhor compreensão dos cursos e disciplinas fornecidos.

A partir de avaliações e comentários de alunos que já cursaram, além de descrição da ementa, carga horária, período, entre outras informações da disciplina; um aluno será capaz de julgar seu curso e de se organizar para uma experiência acadêmica otimizada. Enquanto as universidades poderiam usar os dados das avaliações para agregar valor às decisões sobre seus cursos ofertados.

Como objetivos específicos temos os seguintes:
- Criar base de dados de disciplinas, cursos e universidades
- Disponibilizar comentários sobre disciplinas
- Permitir que os usuários avaliem comentários de outros usuários
- Disponibilizar inserção de novas disciplinas


## 1.3. Definições e Abreviaturas
- CPA: Comissão Própria de Avaliação

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
| RF003  | O usuário deve conseguir aprovar ou desaprovar a avaliação de outros usuários através de um sistema de upvote e downvote | Desejável |
| RF004  | O usuário deve ser capaz de visualizar a lista de diciplinas ofertadas pelo curso de engenharia de software.| Essencial |
| RF005  | O usuário deve ser capaz de pesquisar determinada disciplina por seu nome no sistema.| Essencial|
| RF006  | O usuário deve ser capaz de filtrar disciplinas por período no sistema.| Desejável |
| RF007  | O usuário deve visualizar dados das disciplinas, como carga horária, modalidade(síncrona)| Essencial |
| RF008  | O usuário deve possuir anonimato quanto a sua identidade | Desejável |
| RF009  | O usuário deve ser capaz de criar a conta com email puc | Essencial |
| RF010  | O usuário deve ser capaz de denunciar comentários.| Desejável |
| RF011  | O usuário preenche formulários de requisição para adicionar disciplinas. | Desejável |
| RF012  | O moderador valida o banimento de um usuário. | Desejável |
| RF013  | O moderador valida a adição de uma disciplina. | Desejável |

Obs: acrescente mais linhas, se necessário.

## 2.2. Requisitos Não-Funcionais

_Enumere os requisitos não-funcionais previstos para a sua aplicação. Entre os requisitos não funcionais, inclua todos os requisitos que julgar importante do ponto de vista arquitetural ou seja os requisitos que terão impacto na definição da arquitetura. Os requisitos devem ser descritos de forma completa e preferencialmente quantitativa._

| **ID** | **Descrição** |
| --- | --- |
| RNF001 | O sistema deve ser implementado em tecnologias móveis e/ou híbridas, suportando duas plataformas, disponibilidade de 95%. |
| RNF002 | O sistema deve permitir pelo menos 50 clientes conectados ao mesmo tempo. |
| RNF003 | O tempo médio de resposta do servidor deve ser inferior a 500 ms. |
| RNF004 | A cobertura de testes deve ser de pelo menos 60% das funcionalidades e requisitos. |
| RNF005 | O sistema deve utilizar Spring Security com JWT para garantir criptografia dos dados dos clientes. |


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

| **Análise** | **Design**                                                                 | **Implementação** |
| --- |----------------------------------------------------------------------------|-------------------|
| Persistência | Banco de dados  relacional                                              | PostgreSQL          |
| Front end | Interface de comunicação com o usuário web                                 | TypesCript, React, Sass                  |
| Back end | Plataforma de desenvolvimento orientada a objeto                           | Spring boot       |
| Integração |                                                                            |                   |
| Log do sistema | Implementação dos recursos de log do componente de persistência            | Rest APIs         | |
| Teste de Software | Camada para tratar as exceções criando interações diferentes para usuários | IntelliJ     |
| Deploy | Configuração da IDE de deploy                                              | IntelliJ     |

<a name="modelagem"></a>

# 3. Modelagem e projeto arquitetural

A arquitetura do sistema Avaliaí utiliza o protocolo de rede HTTP para se comunicar com outros serviços e ferramentas. A requisição do usuário passa pelo RabbitMQ, um serviço de mensagens aberto que notificará o restante da aplicação, modelada em arquitetura MVC (Model, View, Controller). A primeira camada, view, composta pelos frameworks Next.JS (web) e Flutter (mobile), se comunica com o controller, composto pelo serviço Firebase e o framework Spring. Esse último  se comunica com a camada de dados, em PostgreSQL. Todo esse processo é disponibilizado em containers, tarefa realizada pelo Docker.

![AvaliAi Architecture-1-1](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2023-1-ti5-5104100-avaliai/assets/84593164/9087c626-9407-434d-923e-2bd1c180a8a3)

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
| **Fluxo Principal** | 1. O sistema apresenta uma lista de disciplinas, juntamente com um campo de busca                                                                                       2. O usuário escolhe a disciplina que deseja avaliar                                                                                                                   3. Os comentários dos usuários são mostrados na página da disciplina, permitindo que o usuário atribua um "Like" ou "Dislike" aos mesmos |

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

### Diagrama de Classes

O diagrama de classes foi utilizado para modelar a aplicação do ponto de vista do backend, tendo como referência a linguagem de programação Java, visto que o grupo optou pelo uso do framework Spring. 

![Diagrama de classes - AvaliAí](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2023-1-ti5-5104100-avaliai/assets/72470621/c5d35134-538c-468e-9437-bd10b6d3ce24)

As classes identificadas para cumprir com o propósito e os requisitos do sistema foram:
1. **User** - Referente ao usuário, sua conta e perfil.
2. **Moderator** - Um tipo de usuário com permissões especiais, capaz de banir temporariamente ou intermitentemente usuários e apagar comentários.
3. **Comment** - Se trata dos comentários postados pelos usuários, apresentando quantidade de likes e dislikes
4. **University** - Referente à universidade, que contém cursos e um CNPJ associado.
5. **Course** - Abstração dos cursos ofertados pelas universidades; os cursos possuem um conjunto de disciplinas.
6. **Subject** - Referente às disciplinas ofertadas pelos cursos. É o foco das avaliações dos usuários. 

### Diagrama de componentes

![Diagrama de Componentes - Avaliaí drawio](https://user-images.githubusercontent.com/84593164/223796339-0d652d01-5473-47f5-a5f6-837eb3a25c62.png)


**Figura 3 – Diagrama de Componentes.**

O grupo optou por modelar o diagrama de componentes, subdividindo e agrupando as classes identificadas no diagrama de classes em componentes.

Conforme diagrama apresentado na Figura X, as entidades participantes da solução são:

- **People** - O componente people compreende as classes User e Moderator, referentes aos cargos do sistema e às suas respectivas permissões.
- **Institutional** - O componente institutional, composto pelas classes University, Course e Subject agrupa todos os módulos institucionais.
- **Interaction** - O componente interaction compreende a classe Comment, referente à comunicação entre os usuários.


**Figura 4 – Diagrama de Entidade Relacionamento (ER) - exemplo. Fonte: o próprio autor.**

Obs: Acrescente uma breve descrição sobre o diagrama apresentado na Figura 3.

<a name="avaliacao"></a>
# 4. Avaliação da Arquitetura

_Esta seção descreve a avaliação da arquitetura apresentada, baseada no método ATAM._

## 4.1. Cenários

_Apresente os cenários de testes utilizados na realização dos testes da sua aplicação. Escolha cenários de testes que demonstrem os requisitos não funcionais sendo satisfeitos. Os requisitos a seguir são apenas exemplos de possíveis requisitos, devendo ser revistos, adequados a cada projeto e complementados de forma a terem uma especificação completa e auto-explicativa._

**Cenário 1 - Disponibilidade:** O sistema deve ser implementado em tecnologias móveis e/ou híbridas, suportando duas plataformas, disponibilidade de 95%.

**Cenário 2 - Desempenho:** O sistema deve permitir pelo menos 50 clientes conectados ao mesmo tempo.

**Cenário 3 - Desempenho:** O tempo médio de resposta do servidor deve ser inferior a 500 ms.

**Cenário 4 - Testabilidade:** A cobertura de testes deve ser de pelo menos 60% das funcionalidades e requisitos.

**Cenário 5 - Segurança:** O sistema deve utilizar Spring Security com JWT para garantir criptografia dos dados dos clientes.

## 4.2. Avaliação

_Apresente as medidas registradas na coleta de dados. O que não for possível quantificar apresente uma justificativa baseada em evidências qualitativas que suportam o atendimento do requisito não-funcional. Apresente uma avaliação geral da arquitetura indicando os pontos fortes e as limitações da arquitetura proposta._

| **Atributo de Qualidade:** | Disponibilidade |
| --- | --- |
| **Requisito de Qualidade** | O sistema deve ser implementado em tecnologias móveis e/ou híbridas, suportando duas plataformas, disponibilidade de 95%. |
| **Preocupação:** | O sistema deve estar disponível para acesso imediato de usuários em 95% do tempo |
| **Cenários(s):** | Cenário 1 |
| **Ambiente:** | Sistema em operação normal |
| **Estímulo:** | Disponibilidade e tempo de ativo |
| **Mecanismo:** | ... |
| **Medida de Resposta:** | Disponibilidade |

**Considerações sobre a arquitetura:**

| **Riscos:** | Não existe |
| --- | --- |
| **Pontos de Sensibilidade:** | Não existe |
| _ **Tradeoff** _ **:** | Não existe |

Evidências dos testes realizados

Imagem aqui

| **Atributo de Qualidade:** | Desempenho |
| --- | --- |
| **Requisito de Qualidade** | O sistema deve permitir pelo menos 50 clientes conectados ao mesmo tempo. |
| **Preocupação:** | O sistema deve suportar uma quantidade razoável de usuários simultaneamente |
| **Cenários(s):** | Cenário 2 |
| **Ambiente:** | Sistema em operação normal |
| **Estímulo:** | Desempenho do sistema e usuários online |
| **Mecanismo:** | ... |
| **Medida de Resposta:** | Desempenho |

**Considerações sobre a arquitetura:**

| **Riscos:** | Não existe |
| --- | --- |
| **Pontos de Sensibilidade:** | Não existe |
| _ **Tradeoff** _ **:** | Não existe |

Evidências dos testes realizados

Imagem aqui

| **Atributo de Qualidade:** | Desempenho |
| --- | --- |
| **Requisito de Qualidade** | O tempo médio de resposta do servidor deve ser inferior a 500 ms. |
| **Preocupação:** | O servidor e as APIs devem ter um tempo de resposta menor que 500 ms para garantir o desempenho da aplicação |
| **Cenários(s):** | Cenário 3 |
| **Ambiente:** | Sistema em operação normal |
| **Estímulo:** | Desempenho do sistema e requisição de APIs |
| **Mecanismo:** | ... |
| **Medida de Resposta:** | Desempenho |

**Considerações sobre a arquitetura:**

| **Riscos:** | Não existe |
| --- | --- |
| **Pontos de Sensibilidade:** | Não existe |
| _ **Tradeoff** _ **:** | Não existe |

Evidências dos testes realizados

Imagem aqui

| **Atributo de Qualidade:** | Testabilidade |
| --- | --- |
| **Requisito de Qualidade** | A cobertura de testes deve ser de pelo menos 60% das funcionalidades e requisitos. |
| **Preocupação:** | A cobertura de testes do sistema deve exceder 60% para garantir que a aplicação funciona corretamente, sem bugs ou falhas críticos. |
| **Cenários(s):** | Cenário 4 |
| **Ambiente:** | Sistema em operação normal |
| **Estímulo:** | Cobertura do sistema e sua extensão de testes |
| **Mecanismo:** | Através da ferramenta Jacoco, foram geradas informações a respeito da cobertura de testes do sistema. É possível identificar que a cobertura (coverage) excede os 60% determinados|
| **Medida de Resposta:** | Testabilidade e cobertura de testes. |

**Considerações sobre a arquitetura:**

| **Riscos:** | Não existe |
| --- | --- |
| **Pontos de Sensibilidade:** | Não existe |
| _ **Tradeoff** _ **:** | Muito tempo foi gasto para a preparação e implementação dos testes, mas com o fim de garantir que o sistema estivesse funcionando corretamente |

Evidências dos testes realizados

![jacoco](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2023-1-ti5-5104100-avaliai/assets/84593164/d374a7c8-2ff3-46dd-9907-ade1f49db731)

| **Atributo de Qualidade:** | Segurança |
| --- | --- |
| **Requisito de Qualidade** | O sistema deve utilizar Spring Security com JWT para garantir criptografia dos dados dos clientes. | 
| **Preocupação:** | O usuário deve ter dados como a senha protegidos por criptografia para garantir sua segurança. |
| **Cenários(s):** | Cenário 5 |
| **Ambiente:** | Sistema em operação normal |
| **Estímulo:** | Segurança do usuário |
| **Mecanismo:** | Utilizando o Spring Security em conjunto com o JWT, juntamente com funções nativas do Spring Boot e do Java, foi possível criptografar a senha do usuário. |
| **Medida de Resposta:** | Segurança. |

**Considerações sobre a arquitetura:**

| **Riscos:** | Não existe |
| --- | --- |
| **Pontos de Sensibilidade:** | Não existe |
| _ **Tradeoff** _ **:** | Para garantir a segurança, o sistema abdica um pouco do desempenho |

Evidências dos testes realizados

Imagem aqui

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

