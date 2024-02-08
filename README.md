
# Certificação de Alunos

O projeto é um sistema de cadastro de alunos que permite aos usuários se registrarem utilizando seus endereços de e-mail. Após o registro, os alunos têm a opção de participar de uma prova para obter um certificado. O sistema garante que cada aluno só possa fazer a prova uma vez, impedindo que façam novamente caso já tenham concluído anteriormente. Para implementar essa funcionalidade, o projeto utiliza o framework Spring, aproveitando seus recursos para desenvolvimento de aplicativos web de forma eficiente e escalável. O backend do sistema é desenvolvido em Java, com o Spring Framework fornecendo suporte para a criação de endpoints RESTful para manipulação de dados de alunos, gerenciamento de sessões de usuários e validação de acesso à prova.

## Documentação da API

### Verifica se o usuário já realizou a prova

```http
  POST /students/verify
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `String` | O email do usuário a ser consultado |
| `tech` | `String` | A tecnologia da prova a ser consultada |

#### Exemplo de Requisição
```json
POST /students/verify
Content-Type: application/json

{
  "email": "exemplo@email.com",
  "tech": "JAVA"
}
```

### Retorna as questões da prova

```http
  GET /questions/techs/JAVA
```

### Verifica as questões, se acertou ou errou

```http
  POST /students/certification/answer
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `String` | O email do usuário |
| `tech` | `String` | A tecnologia da prova |
| `questionsAnswers` | `Object` | A lista com os IDs das perguntas e respostas |

#### Exemplo de Requisição
```json
POST /students/certification/answer
Content-Type: application/json

{
	"email": "exemplo@email.com",
	"tech": "JAVA",
	"questionsAnswers": [
		{
			"questionID": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
			"alternativeID": "993a7d37-62a0-4040-810d-d667e3f7a891"
		},
		{
			"questionID": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
			"alternativeID": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
		},
		{
			"questionID": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
			"alternativeID": "d3e51a56-9b97-4bb8-9827-8bcf89f4b276"
		}
	]
}
```

### Retorna um ranking com as 10 melhores notas

```http
  GET /ranking/top-10
```

## Aprendizados

Durante a configuração de uma imagem de banco de dados PostgreSQL no Docker, enfrentei vários problemas relacionados à recursividade de arquivos JSON. Esses problemas dificultaram a correta configuração e execução do ambiente de banco de dados.

Para superar esses desafios tive que analisar detalhadamente as mensagens de erro e os logs do Docker para compreender os problemas específicos que estavam ocorrendo, revisei os arquivos JSON envolvidos na configuração em busca de erros de sintaxe, referências cruzadas incorretas ou problemas de configuração e garanti que todas as dependências e versões do PostgreSQL, Docker e outras ferramentas estivessem atualizadas para evitar problemas de compatibilidade.