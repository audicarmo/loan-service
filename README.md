# loan-service


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


## Swagger:

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/swagger-ui.html
