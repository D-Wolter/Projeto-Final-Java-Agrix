# Documentação do Projeto - Agrix (Java - Spring)


## Visão Geral
Este projeto, denominado "Agrix," representa uma aplicação Java baseada no ecossistema Spring que aborda vários aspectos fundamentais do desenvolvimento de software, incluindo o design de APIs RESTful, gerenciamento de dependências, persistência de dados, manipulação de erros, Dockerização e segurança. A seguir, destacamos os principais aspectos deste projeto e suas realizações:

## Conquistas no Projeto
### Neste projeto, tive a oportunidade de demonstrar uma série de habilidades essenciais, incluindo:

### Desenvolvimento com Spring
Utilizei o ecossistema Spring para criar rotas eficientes e seguras para nossa API.

Implementei a injeção de dependência para conectar as camadas de controle, serviço e persistência, promovendo um código limpo e modular.

Utilizei o Spring Data JPA para definir entidades e repositórios, simplificando a interação com o banco de dados.

Criei buscas personalizadas usando o Spring Data JPA para aprimorar a eficiência e flexibilidade da persistência de dados.

Utilizei campos de data nas rotas da API e no banco de dados para suportar funcionalidades relacionadas a datas.

###  Testes Unitários e Qualidade de Código

Desenvolvi testes unitários abrangentes para garantir a qualidade e o funcionamento correto da implementação.

Alcancei uma cobertura de código adequada, garantindo que os testes abranjam todos os cenários críticos.

### Gerenciamento de Erros

Implementei uma estratégia de gerenciamento de erros sólida no Spring Web, proporcionando respostas adequadas a exceções inesperadas.

### Dockerização

Criei um Dockerfile personalizado para facilitar a implantação e execução da aplicação em contêineres Docker, melhorando a portabilidade e escalabilidade da aplicação.

### Segurança

Utilizei o Spring Security para adicionar autenticação ao projeto, protegendo nossos recursos de forma eficaz.

Garanti que diferentes rotas atendam a regras específicas de autorização, aumentando a segurança da aplicação.

### Detalhes das Entidades

#### Neste projeto, trabalhamos com as seguintes entidades:

Fazenda (farm): Representa uma fazenda.

Plantação (crop): Representa uma plantação e mantém um relacionamento "muitos para um" com a tabela de fazendas (farm). Além disso, essa entidade inclui campos de datas cruciais para o nosso projeto.

Fertilizante (fertilizer): Esta nova tabela representa um fertilizante e está relacionada de forma "muitos para muitos" com a tabela de plantações (crop) por meio da tabela crop_fertilizer.


## Requisitos

### 1. Crie uma API para controle de fazendas com a rota POST /farms

<details>
  <summary>Crie sua aplicação com uma API para gerenciamento de fazendas que inclua a rota POST</summary><br />

Neste requisito, você deverá criar a base para gerenciamento de fazendas da sua API, utilizando
Spring, Spring Boot, Spring Web e Spring Data. Lembre-se que, para isso, você deve criar e configurar sua aplicação apropriadamente, incluindo as dependências e quaisquer classes/camadas que julgar necessárias.

Neste requisito, além de criar a base da sua aplicação, você deverá criar também a primeira rota:

- `/farms` (`POST`)
    - deve receber via corpo do POST os dados de uma fazenda (veja abaixo para os
      dados de requisição e resposta)
    - deve salvar uma nova fazenda a partir dos dados recebidos
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da fazenda criada. O `id` da fazenda deve estar incluso na resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:
```json
{
  "name": "Fazendinha",
  "size": 5
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```
</details>

</details>


### 2. Crie a rota GET /farms

<details>
  <summary>Crie a rota GET /farms da sua API, para retornar todas as fazendas cadastradas </summary><br />

Neste requisito, você deverá criar a rota:

- `/farms` (`GET`)
    - deve retornar uma lista de todas as fazendas. O `id` da fazenda deve estar
      incluso na resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "name": "Fazendinha",
    "size": 5.0
  },
  {
    "id": 2,
    "name": "Fazenda do Júlio",
    "size": 2.5
  }
]
```

</details>

</details>

### 3. Crie a rota GET /farms/{id}

<details>
  <summary>Crie a rota GET /farms da sua API, para retornar as informações de uma fazenda</summary><br />

Neste requisito, você deverá criar a rota:

- `/farms/{id}` (`GET`):
    - deve receber um `id` pelo caminho da rota e retornar a fazenda com esse `id`. O `id` da
      fazenda deve estar incluso na resposta.
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta. Você pode definir a estrutura da
      resposta como preferir, desde que contenha a mensagem.
        - Dica: pense desde já em como você vai fazer o gerenciamento de erros, pois isso afetará o
          restante da aplicação.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

```json
{
  "id": 3,
  "name": "My Cabbages!",
  "size": 3.49
}
```

</details>

</details>

### 4. Crie a rota POST /farms/{farmId}/crops

<details>
  <summary>Continue a implementação da sua aplicação, agora criando a rota POST /farms/{farmId}/crops para criação de plantações</summary><br />

Neste requisito, você deverá criar a rota criação de plantações. Lembre-se que as plantações estão em um relacionamento `n:1` com as fazendas, então considere isso na hora de implementar sua solução deste e dos próximos requisitos.

A rota a ser criada é:
- `/farms/{farmId}/crops` (`POST`)
    - deve receber o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - deve receber via corpo do POST os dados da plantação (veja abaixo para os dados de requisição
      e resposta)
    - deve salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da plantação criada. A resposta deve incluir o `id` da plantação e
          o `id` da fazenda, mas não deve incluir os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.
    - 
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

</details>

### 5. Crie a rota GET /farms/{farmId}/crops

<details>
  <summary>Crie a rota GET /farms/{farmId}/crops para listar as plantações de uma fazenda</summary><br />

Neste requisito, você deverá criar a rota para listar as plantações de uma fazenda. A rota a ser criada é:
- `/farms/{farmId}/crops` (`GET`):
    - deve receber o `id` de uma fazenda pelo caminho
    - deve retornar uma lista com todas as plantações associadas à fazenda
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  }
]
```

</details>

</details>

### 6. Crie a rota GET /crops

<details>
  <summary>Crie a rota GET /crops para listar todas as plantações cadastradas</summary><br />

A rota a ser criada é:
- `/crops` (`GET`)
    - deve retornar uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "farmId": 2
  }
]
```

</details>

</details>

### 7. Crie a rota GET /crops/{id}

<details>
  <summary>Crie a rota GET /crops/{id} para retornar as informações de uma fazenda</summary><br />

A rota a ser criada é:
- `/crops/{id}` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho da rota
    - caso exista a plantação com o `id` recebido, deve retornar os dados da plantação. A resposta
      deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os
      dados da fazenda.
    - caso não exista uma plantação com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "farmId": 2
}
```

</details>

</details>

### 8. Crie um Dockerfile para sua aplicação

<details>
  <summary>Crie um Dockerfile multi-estágio configurando a imagem Docker da sua aplicação</summary><br />

Finalmente, você deve construir um `Dockerfile` para rodar a sua aplicação no Docker.

Seu `Dockerfile`:

- Deve ser multi-estágio
- O primeiro estágio deve se chamar `build-image` e deve ser utilizado para a construção do pacote da sua aplicação, contendo:
    - Um diretório de trabalho (workdir) chamado `/to-build-app`
    - A cópia dos arquivos necessários
    - A instalação das dependências utilizando Maven
        - Aqui, se quiser você pode utilizar o goal `dependency:go-offline` do Maven, que vai baixar todas as dependências e pode ajudar o Docker a criar um cache que agilize o processo de re-criação da imagem.
    - A construção do pacote JAR utilizando Maven com o goal `package`
- O segundo estágio deve ser utilizado para a construção da imagem final, contendo:
    - Um diretório de trabalho (workdir) chamado `/app`
    - A cópia dos arquivos necessários a partir da imagem do primeiro estágio
    - A exposição da porta `8080`
    - Um ponto de entrada (entrypoint) executando o pacote da aplicação

Notas:
1. Você pode usar as imagens de base que preferir para cada estágio. Uma possibilidade é utilizar a `maven:3-openjdk-17` para o estágio de construção, pois já traz o Maven instalado. Já para o estágio final você pode usar uma imagem de tamanho reduzido, como a `eclipse-temurin:17-jre-alpine`, por exemplo.
2. Apesar de o Maven já instalar as dependências na construção do pacote, como mencionado é útil termos uma execução da instalação separada da construção no primeiro estágio, para termos os benefícios de cache do Docker e reduzir o tempo de reconstrução.
3. Quando for testar sua imagem, lembre-se que a exposição da porta no Dockerfile não faz o mapeamento automaticamente (diferente do `docker-compose`). Nesse caso, é necessário passar o mapeamento por parâmetro para o docker na hora da execução da imagem.

</details>

### 9. Escreva testes com cobertura mínima de 80% das linhas da classe PersonService

<details>
  <summary>Escreva testes com cobertura mínima de 80% das linhas da classe PersonService</summary><br />

A Fase A do projeto Agrix deu tão certo que as pessoas inverstidoras decidiram comprar uma base de código existente de outra empresa. Infelizmente, esse código não incluia testes unitários, e você ficou responsável por escrever testes para uma das classes.

O código adquirido está no pacote `com.betrybe.agrix.ebytr.staff`. Por enquanto o código não será refatorado ou integrado à aplicação, então tome cuidado para não alterar ou apagar nada nesse pacote.

A classe que você deverá testar é a `PersonService`, dentro do subpacote `service`. Você deverá garantir uma cobertura dos testes de no mínimo **80%** das linhas dessa classe. Crie seus testes no pacote `com.betrybe.agrix.solution`.

**_Atenção_**: Você pode utilizar as funcionalidades de cobertura de código da sua IDE para te ajudar a identificar o que falta testar. No entanto, lembre-se de que a cobertura que será considerada é a dada pelos testes oficiais do projeto.

</details>

### 10. Ajuste (ou crie) a rota POST /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajuste ou crie a rota POST /farms/{farmId}/crops para utilizar campos com datas</summary><br />

Neste requisito, você deverá garantir que a rota para criação de plantações tenha os campos com data definidos abaixo. 

Caso você já tenha implementado esta rota durante a Fase A do projeto, você precisa ajustá-la para incluir os novos campos. Caso contrário, você precisará implementar a rota completa, incluindo os campos antigos e os novos.

A definição original da rota é:
- `/farms/{farmId}/crops` (`POST`)
    - deve receber o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - deve receber via corpo do POST os dados da plantação (veja abaixo para os dados de requisição
      e resposta)
    - deve salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da plantação criada. A resposta deve incluir o `id` da plantação e
          o `id` da fazenda, mas não deve incluir os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Você precisará incluir dois atributos novos (descritos no diagrama atualizado das tabelas):
- `plantedDate`, representando a data em que a plantação foi semeada
- `harvestDate`, representando a data em qua a plantação foi ou está prevista para ser colhida

As datas devem ser recebidas e retornadas no formato ISO (`YYYY-MM-DD`). Sugerimos que você use o tipo `LocalDate`.

Nota: dependendo de como você fez sua implementação, é possível que ao resolver este requisito você também resolva automaticamente os próximos requisitos relacionados a plantações. Caso isso aconteça, não se assuste :)

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08",
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

</details>

### 11. Ajuste (ou crie) a rota GET /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajuste ou crie a rota GET /farms/{farmId}/crops para utilizar campos com datas</summary><br />

Da mesma forma que no requisito 2, você deve incluir os campos com datas na resposta deste requisito.

A definição original da rota é:
- `/farms/{farmId}/crops` (`GET`):
    - deve receber o `id` de uma fazenda pelo caminho
    - deve retornar uma lista com todas as plantações associadas à fazenda
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-12-05",
    "harvestDate": "2023-06-08",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  }
]
```

</details>

</details>

### 12. Ajuste (ou crie) a rota GET /crops para utilizar datas

<details>
  <summary>Ajuste ou crie a rota GET /crops para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops` (`GET`)
    - deve retornar uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>

### 13. Ajuste (ou crie) a rota GET /crops/{id} para utilizar datas

<details>
  <summary>Ajuste ou crie a rota GET /crops/{id} para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops/{id}` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho da rota
    - caso exista a plantação com o `id` recebido, deve retornar os dados da plantação. A resposta
      deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os
      dados da fazenda.
    - caso não exista uma plantação com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "plantedDate": "2023-05-22",
  "harvestDate": "2024-01-10",
  "farmId": 2
}
```

</details>

</details>


### 14. Crie a rota GET /crops/search para busca de plantações

<details>
  <summary>Crie a rota GET /crops/search para busca de plantações a partir da data de colheita</summary><br />

A rota a ser criada é:
- `/crops/search` (`GET`)
  - deve receber dois parâmetros por query string para busca:
    - `start`: data de início
    - `end`: data de fim
  - deve retornar uma lista com as plantações nas quais o campo `harvestDate` esteja entre as data de início e de fim.
    - a comparação das datas deve ser inclusiva (ou seja, deve incluir datas que sejam iguais à de início ou à de fim)
  - a resposta deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>


### 15. Crie a rota POST /fertilizers

<details>
  <summary>Crie a rota POST /fertilizers para criação de um novo fertilizante</summary><br />

Neste requisito, você deverá criar a primeira rota para gerenciamento de fertilizantes. 

Lembre-se que os fertilizantes estão em um relacionamento `n:n` com plantações, então considere isso na hora de implementar sua solução deste e dos próximos requisitos.

A rota a ser criada é:
- `/fertilizers` (`POST`)
    - deve receber via corpo do POST os dados de um fertilizante
    - deve salvar um novo fertilizante a partir dos dados recebidos
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados do fertilizante criado, incluindo seu `id`

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:

```json
{
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

</details>

</details>


### 16. Crie a rota GET /fertilizers

<details>
  <summary>Crie a rota GET /fertilizers para listar todos os fertilizantes cadastrados</summary><br />

Neste requisito, você deverá criar a rota para listar todos os fertilizantes cadastrados. A rota a ser criada é:
- `/fertilizers` (`GET`):
    - deve retornar uma lista de todos os fertilizantes cadastrados, incluindo o `id` de cada.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Compostagem",
    "brand": "Feita em casa",
    "composition": "Restos de alimentos"
  },
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```
</details>

</details>


### 17. Crie a rota GET /fertilizers/{id}

<details>
  <summary>Crie a rota GET /fertilizers/{id} para pegar as informações de um fertilizante</summary><br />

Neste requisito, você deverá criar a rota para pegar as informações de um fertilizante. A rota a ser criada é:
- `/fertilizers/{fertilizerId}` (`GET`):
    - deve receber o `id` de um fertilizante pelo caminho da rota
    - caso exista o fertilizante com o `id` recebido, deve retornar seus dados, incluindo seu `id`
    - caso não exista um fertilizante com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

```json
{
  "id": 3,
  "name": "Adubo",
  "brand": "Feito pelas vaquinhas",
  "composition": "Esterco"
}
```

</details>

</details>


### 18. Crie a rota POST /crops/{cropId}/fertilizers/{fertilizerId}

<details>
  <summary>Crie a rota POST /crops/{cropId}/fertilizers/{fertilizerId} associar uma plantação com um fertilizante</summary><br />

Neste requisito, você deverá criar a rota para criar a associação entre uma plantação e um fertilizante. A rota a ser criada é:
- `/crops/{cropId}/fertilizers/{fertilizerId}` (`POST`)
    - deve receber tanto o `id` da plantação quanto o `id` do fertilizante pelo caminho da rota
    - o corpo da requisição será vazio
    - deve fazer a associação entre o fertilizante e a plantação
    - em caso de sucesso, deve retornar o status HTTP 201 (CREATED) com a mensagem `Fertilizante e plantação associados com sucesso!` no corpo da resposta
    - caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.
    - caso não exista um fertilizante com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de resposta para a rota `/crops/1/fertilizers/2` (supondo que exista uma plantação com `id = 1` e um fertilizante com `id = 2`):

```text
Fertilizante e plantação associados com sucesso!
```

</details>

</details>


### 19. Crie a rota GET /crops/{cropId}/fertilizers

<details>
  <summary>Crie a rota GET /crops/{cropId}/fertilizers para listar os fertilizante associados a uma plantação</summary><br />

Neste requisito, você deverá criar a rota para listar os fertilizante associados a uma plantação. A rota a ser criada é:
- `/crops/{cropId}/fertilizers` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho
    - deve retornar uma lista com todas os fertilizantes associados à plantação
    - caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/2/fertilizers` (supondo que exista uma plantação com `id = 2`):

```json
[
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```

</details>

</details>



### 20. Crie a rota POST /persons

<details>
  <summary>Crie a rota POST /persons para salvar novas pessoas no banco</summary><br />

Neste requisito você vai criar uma rota para integrar a API com o código que foi adquirido e testado na fase anterior, localizado no pacote `com.betrybe.agrix.ebytr.staff`.

Se quiser, nesta fase você já pode refatorar o código desse pacote e mover ele para seguir a organização do restante da sua aplicação.

A definição da rota é:
- `/persons` (`POST`)
    - deve receber no corpo da requisição:
      - `username`
      - `password`
      - `roles` (conforme definido no enum `Role`, disponibilizado com o código)
    - deve criar a pessoa com os dados passados
    - deve responder com os campos `id`, `username` e `role` (mas não `password`)

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/persons`:

```json
{
  "username": "zerocool",
  "password": "senhasecreta",
  "role": "ADMIN"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "username": "zerocool",
  "role": "ADMIN"
}
```

</details>

</details>

### 21. Adicione autenticação no projeto

<details>
  <summary>Adicione autenticação no projeto, incluindo uma rota para login que retorna um JWT</summary><br />

Neste requisito você deverá configurar o Spring Security e implementar no seu projeto a autenticação por usuário e senha.

Você deverá:
1. Garantir acesso público (ou seja, desprotegido) aos endpoints:
    - POST `/persons` (criado acima, para permitir cadastro de novas pessoas)
    - POST `/auth/login` (será criado abaixo, para permitir login) 
2. Criar a rota POST `/auth/login`:
    - deve receber o `username` e `password` no corpo da requisição
    - deve validar os dados passados utilizando as pessoas que foram criadas pela rota `/persons`
    - caso os dados estejam incorretos, deve retornar status 403
    - caso os dados estejam corretos, deve retornar um campo `token` contendo um JWT gerado

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota POST `/auth/login` (suppondo que os dados estejam corretos):

```json
{
  "username": "zerocool",
  "password": "senhasecreta"
}
```

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZ3JpeCIsInN1YiI6Im1ycm9ib3QiLCJleHAiOjE2ODk5ODY2NTN9.lyha4rMcMhFd_ij-farGCXuJy-1Tun1IpJd5Ot6z_5w"
}
```

</details>

</details>

### 22. Limitar acesso à rota GET /farms

<details>
  <summary>Limitar acesso à rota GET /farms para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/farms` para que apenas uma pessoa autenticada com role `USER`, `MANAGER` ou `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

### 23. Limitar acesso à rota GET /crops

<details>
  <summary>Limitar acesso à rota GET /crops para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/crops` para que apenas uma pessoa autenticada com role `MANAGER` ou `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

### 24. Limitar acesso à rota GET /fertilizers

<details>
  <summary>Limitar acesso à rota GET /fertilizers para pessoa autenticada com role correto</summary><br />

Neste requisito você deve limitar o acesso à rota GET `/fertilizers` para que apenas uma pessoa autenticada com role `ADMIN` possa acessar.

Você deve retornar status 403 caso a pessoa não tenha permissões corretas. Do contrário, a rota deve retornar a resposta usual.

</details>

---


