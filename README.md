# loan-service

## Setup
Para configurar e subir essa api é necessário possuir Java 17 ou 8. Você poderá instalar o JDK a partir do [SDKman](https://sdkman.io).

Observação: Embora a persistência de dados através do spring data jpa esteja configurada, não houve a necessidade, em um primeiro momento, de a utilizarmos. Por essa razão, não será necessário instalar banco de dados.
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

Essa api foi documentada com a ferramenta API Blueprint. 
Para acessar a documentação, utilize a plataforma [apiary](https://apiary.io), faca o login, e utilize o script constante no arquivo LoanSimulationApi.apib

Observação: Não foi possível completar a implementação do swagger nesta api.


## Boas práticas:
- Implementação de interface (Princípio da segregação de interface - SOLID)
- Injeção de dependência por construtor (TaxCalculator e LoanCalculator)
- Constantes e precisão do Bigdecimal
- Responsabilidade única e métodos privados
- Validação de entrada (validateRequest)
- Método assíncrono para múltiplas funções (completableFuture e supplyAssinc)
- Princípio DRY(Don't Repeat Yourself) - O cálculo de valores como interestRateMonthly,
  monthlyInstallment, amountPaidTotal, e interestPaidTotal é realizado de forma modular,
  com operações definidas em uma sequência clara, sem repetir códigos desnecessários, 
 o que favorece a manutenção e legibilidade.
- Definição de precisão com setScale
- Princípio da Responsabilidade única - Solid



  