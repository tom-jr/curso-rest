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


