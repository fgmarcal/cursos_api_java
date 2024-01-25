
# # Desafio 01 - Rocketseat

## Sobre o desafio

"Desenvolver uma API fictícia para uma empresa de cursos de programação, onde em um primeiro momento, deve-se utilizar o CRUD, para criação de cursos."

A API contém as seguintes funcionalidades:

- Criação de um novo curso
- Listagem de todos os cursos
- Atualização de um curso pelo `id`
- Remover um curso pelo `id`

### Rotas e regras de negócio

- `id` - Identificador único de cada curso
- `name` - Nome do curso
- `category` - Categoria do curso
- `Active` - Define se o curso está ativo ou não
- `created_at` - Data de quando o curso foi criado.
- `updated_at` - Deve ser sempre alterado para a data de quando o curso for atualizada.

Rotas:

- `POST - /cursos`
    
  Criar um curso no banco de dados, enviando os campos `name` e `category` por meio do `body` da requisição.
    
    Ao criar um curso, os campos: `id`, `created_at`   e `updated_at` são preenchidos automaticamente, conforme a orientação das propriedades acima.

- `GET - /cursos`
    
    É possível listar todas os cursos salvos no banco de dados.
    
    Também é possível realizar uma busca, filtrando os cursos pelo `name` e `category`

- `PUT - /cursos/:id`
    
    É possível atualizar um curso pelo `id`.
    
    No `body` da requisição, recebe-se somente o `name` e/ou `category` para serem atualizados.
    
    Se for enviado somente o `name`, significa que o `category` não será atualizado, mantendo o valor anterior.

- `DELETE - /cursos/:id`
    
    Para remover um curso pelo `id`.

- `PATCH - /cursos/:id/complete`

    Atualiza o status do curso para INACTIVE, indicando que o curso foi finalizado.
