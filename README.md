# api-ecommerce-serratec

![GitHub repo size](https://img.shields.io/github/repo-size/iuricode/README-template?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/iuricode/README-template?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/iuricode/README-template?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/iuricode/README-template?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/iuricode/README-template?style=for-the-badge)


 Bem vindo(a) ao projeto final da disciplina de Desenvolvimento de Api Restful da Residência de TIC Desenvolvimento de Software do Serratec.
 A Ecommerce Serratec é uma Api Rest desenvolvida com a linguagem Java e o Framework Springboot.

## Pré-requisitos

- Versão Java JDK 17: https://www.oracle.com/br/java/technologies/downloads/#java17
- Maven: https://maven.apache.org/download.cgi
- Framework Spring: https://spring.io/tools
- Banco de dados PostgreSQL: https://www.postgresql.org/download/ > Certifique-se de gravas a senha criada na instalação do Postgres
- Gercenciador de banco de dados Dbeaver: https://dbeaver.io/download/

## Instalando o Projeto

- Clonar o projeto: https://github.com/matheusdalbone/api-ecommerce-serratec

Após a instalação de todos os pré-requisitos abra o Springtools e na Package Explorer, clique com o botão direito e selecione Import, expanda a pasta Maven e clique duas vezes em Existing Maven Projects, selecione o Browse e selecione a pasta do projeto que você clonou. Verifique se o checkbox do /pom.xml está selecionada e clique em Finish.

## Executando o Projeto

Antes de executar o projeto no SpringTools crie um banco de dados chamado ecommerce no Dbeaver. <br>
Após a criação do banco de dados, certifique-se de copiar as informações abaixo e colar no seu application.properties no caminho src/main/resources/application.properties

> Nome da aplicação
spring.application.name=ecommerce <br>

> Propriedades do banco de dados: 
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto			= update
spring.jpa.hibernate.show-sql			= true
spring.datasource.url					= jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username				= postgres
spring.datasource.password				= *Seu password do Postgres*
logging.level.org.hibernate.SQL			= INFO

>Prorpriedades do mailtrap
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=*Seu username do mailtrap*
spring.mail.password=*Seu password do mailtrap*
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.enable=false

Após configurar o application.properties, rode o projeto, verifique se as tabelas foram criadas no banco de dados corretamente e realize as requisições Http desejadas.

## Requisições Http
- Postman API: https://www.postman.com/downloads/
- Swagger UI: http://localhost:8080/swagger-ui/index.html#

## Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

- Artur Foster de Souza <br>
    Github: https://github.com/ArturFoster <br>
    LinkedIn: https://www.linkedin.com/in/artur-foster/ <br>
- Leonardo de Andrada Esplinio <br>
    Github: https://github.com/LeoEsplinio <br>
    LinkedIn: https://www.linkedin.com/in/leoesplinio/ <br>
- Matheus Augusto Dalbone Gusmão <br>
    Github: https://github.com/matheusdalbone <br>
    LinkedIn: https://www.linkedin.com/in/matheus-augusto-dalbone-gusm%C3%A3o-8ab128266/ <br>
- Miguel Caldas Coutinho <br>
    Github: https://github.com/Biguelisto <br>
    LinkedIn: https://www.linkedin.com/in/miguel-caldas-0359802a5/ <br>
- Raphael Vivarini Damico <br>
    Github: https://github.com/RaphaelDamico <br>
    LinkedIn: https://www.linkedin.com/in/raphaelvivarinidamico/ <br>


