# Adicionando JPA e configurando o Data Source

## Adicionando Spring JPA no pom.xml

 Para que o Spring Started JPA rode no projeto Basta adicionar suas dependências no pom.xml
    
~~~ xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

~~~

Adicionada a dependência o projeto subirá apenas apos a configuração de um banco de dados.

Adicionamos as configurações de conexão com banco de dados no arquivo de application.properties

~~~ properties
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:postgresql://localhost:5432/database-name
spring.datasource.username=user
spring.datasource.password=password

spring.jpa.database-platform = org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

~~~

documentação do [postgresql](https://jdbc.postgresql.org/documentation/80/connect.html) de conexão ao banco


Adição do Drive PostgreSQL no **pom.xml**
~~~ xml
<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.3.3</version>
</dependency>
~~~

Então basta criar o banco de dados com o nome referente ao da aplicação no caso do PostgreSQL.
E estará pronto para subir a aplicação.