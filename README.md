# api-ecommerce-serratec


 Bem vindo(a) ao projeto final da disciplina de Desenvolvimento de Api Restful da Residência de TIC Desenvolvimento de Software do Serratec.
 A Ecommerce Serratec é uma Api Rest desenvolvida com a linguagem Java e o Framework Springboot.

## Pré-requisitos

- JDK 17: https://www.oracle.com/br/java/technologies/downloads/#java17
- Maven: https://maven.apache.org/download.cgi
- Framework Spring: https://spring.io/tools
- Banco de dados PostgreSQL: https://www.postgresql.org/download/ (Certifique-se de gravar a senha criada na instalação do Postgres)
- Gercenciador de banco de dados Dbeaver: https://dbeaver.io/download/

## Instalando o Projeto

- Clonar o projeto: https://github.com/matheusdalbone/api-ecommerce-serratec

Após a instalação de todos os pré-requisitos abra o Springtools e na Package Explorer, clique com o botão direito e selecione Import, expanda a pasta Maven e clique duas vezes em Existing Maven Projects, selecione o Browse e selecione a pasta do projeto que você clonou. Verifique se o checkbox do /pom.xml está selecionada e clique em Finish.

## Executando o Projeto

Antes de executar o projeto no SpringTools crie um banco de dados chamado ecommerce no Dbeaver. <br>
Após a criação do banco de dados, certifique-se de copiar as informações abaixo e colar no seu application.properties no caminho src/main/resources/application.properties

> Nome da aplicação <br>
spring.application.name=ecommerce <br>

> Propriedades do banco de dados: <br>
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect <br>
spring.jpa.hibernate.ddl-auto			= update <br>
spring.jpa.hibernate.show-sql			= true <br>
spring.datasource.url					= jdbc:postgresql://localhost:5432/ecommerce <br>
spring.datasource.username				= postgres <br>
spring.datasource.password				= *Seu password do Postgres* <br>
logging.level.org.hibernate.SQL			= INFO <br>

>Prorpriedades do mailtrap <br>
spring.mail.host=sandbox.smtp.mailtrap.io <br>
spring.mail.port=2525 <br>
spring.mail.username=*Seu username do mailtrap* <br>
spring.mail.password=*Seu password do mailtrap* <br>
spring.mail.properties.mail.smtp.auth=true <br>
spring.mail.properties.mail.smtp.starttls.enable=true <br>
spring.mail.properties.mail.smtp.starttls.required=true <br>
spring.mail.properties.mail.smtp.ssl.enable=false <br>

Após configurar o application.properties, rode o projeto, verifique se as tabelas foram criadas no banco de dados corretamente e realize as requisições Http desejadas e após atualizar o pedido, verifique na sua mailbox através do link https://mailtrap.io/.

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


