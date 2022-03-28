# Adição de dados para teste
 Com o flyaway podemos adicionar dados para que sejam adicionado ao subir o 
 banco de dados.
 ## Passos com PostgreSQL
 1. criamos um arquivo com nome de **afterMigrate.sql** na pasta de 
 db/migration ou adicionamos uma nova pasta e referenciamos 
 em properties com o ***spring.flyway.locations***. Nesse arquivo adicionamos
as inserções no banco.

2. Para que a inserção funcione corretamente devemos seguir em Postgres a sequencia:
- desabilitando os triggers para não gerar erro ao deleter registros devido as FK

~~~ sql
ALTER TABLE name_table DISABLE TRIGGER ALL;
~~~
- Reset das sequences das tabelas para que as associações funcionem normalmente

~~~ sql
ALTER SEQUENCE name_table RESTART WITH 1;
~~~
- Delete das tables para sempre reiniciar com dados limpos
~~~ sql
delete from name_table;
~~~
- Ativa todos o triggers novamente
~~~ sql
ALTER TABLE name_table ENABLE TRIGGER ALL;
~~~
- Inserir os valores
~~~ sql
insert into name_table () values ();
~~~

