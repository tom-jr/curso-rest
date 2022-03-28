## Flyaway

Com o Flyaway podemos gerenciar migrações do banco de dados. Assim toda e 
qualquer alteração fica a cargo do Flyaway. E não mais pelo auto geração do JPA.

## Configurar o Flyaway

1. removemos as configurações de geração automática de DDL do arquivo 
***application.properties***
~~~
#spring.jpa.generate-ddl=true
#spring.jpa.hibernate.ddl-auto=create
~~~

2. Deletar as tabelas que ja foram criadas automaticamente do banco de dados. Caso
existir imports sql na pasta resource, as mesma serão ignoradas a partir da adição
do Flyaway.

3. Adição das dependências do Flyaway no pom.xml
~~~ xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
    <version>8.5.3</version>
</dependency>

~~~

4. Adicionar o diretório db/migration no sistema. Que será para adicionar os 
arquivos de migrations

5. adicionar os arquivos de migration conform a evolução da API. 
Padrão de nomenclatura do arquivo deve ser com ***'V'*** maniáculo. Caso contrario
não se é lido.

## Gerar DDL com Spring

No ***application.properties*** podemos adicionar a configuração
***spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create***
***spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=src/main/resources/db/ddl-jpa/ddl.sql***


A primeira configuration ativa a criação do DDL que é criado pelas anotações
do JPA. A segunda informa o caminho onde salvar o DDL.
Caso a configuração permanecer toda vez que se subir a aplicação sera gerando o DDL.

## Configuration do Flyaway
  O arquivo deve se chamar flyway.properties

~~~ properties
flyway.url=  #o value é a url de conexão ao banco de dados
flyway.user= #username banco de dados
flyway.password= #password do username do banco de dados
~~~
Comando para rodar um repair do flyway. Para remover scripts que não rodaram com sucesso na migração

~~~ bash
./mvn flyway:repair -Dflyway.configFiles={dir-do-profile/flyway.properties}
~~~