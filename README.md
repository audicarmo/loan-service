# loan-service

## Utilizando Docker
Para rodar a aplicação via Docker, siga os passos abaixo:

### 1. Instale o Docker
Baixe e instale o Docker a partir do [site oficial](https://www.docker.com/):
- [Download para Windows](https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe)
- [Download para macOS](https://desktop.docker.com/mac/stable/Docker.dmg)
- [Guia de Instalação para Linux](https://docs.docker.com/engine/install/)

### 2. Comandos Docker

#### Construir a imagem Docker:
```bash
docker-compose build
```

#### Subir os contêineres:
```bash
docker-compose up
```

#### Subir os contêineres em segundo plano (modo detached):
```bash
docker-compose up -d
```

#### Parar os contêineres:
```bash
docker-compose down
```


## REQUISIÇÕES via Postman:

POST - Taxa de juros de acordo com a idade:
http://localhost:8080/api/v1/simulations/simulation

Body:

{
"loanAmount": 10000,
"dateOfBirthClient": "1995-06-15",
"paymentPeriodMonths": 24
}

POST - Múltiplas requisições:
http://localhost:8080/api/v1/simulations/simulations

Body:

[
{
"loanAmount":10000,
"dateOfBirthClient":"1990-01-01",
"paymentPeriodMonths":24
},
{
"loanAmount":5000,
"dateOfBirthClient":"1985-05-15",
"paymentPeriodMonths":12
},
{
"loanAmount":20000,
"dateOfBirthClient":"1992-08-22",
"paymentPeriodMonths":36
}
]


## Documentação:

Essa API foi documentada com a ferramenta API Blueprint.
Para acessar a documentação, utilize a plataforma [apiary](https://apiary.io), faça o login, e utilize o script constante no arquivo `LoanSimulationApi.apib`.
