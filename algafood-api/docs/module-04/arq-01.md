# JPQL

## Consultas simples

Consulta Básica de Seleção
 ~~~sql
 SELECT p FROM Player p

 ~~~
  **Dados Recuperados**: todos os Players
  **Descrição**: A cláusula FROM declara uma variável de identificação p, omitindo a palavra
chave AS. Caso não fosse omitida seria:

~~~sql
    SELECT p FROM Player AS p
 ~~~

## Eliminando valores duplicados
 ~~~sql
 SELECT DISTINCT p FROM Player WHERE p.position = ?1
 ~~~
**Dados Recuperados**: Jogadores com posição especificada pelo parâmetro da consulta.
**Descrição**: A palavra-chave DISTINCT elimina os valores duplicados

 A cláusula **WHERE** restringe os Jogadores recuperados verificando sua posição, o elemento 
 **?1** deNOTa o parâmetro de entrada de parâmetro da consulta.


### Usando parâmetros nomeados
 ~~~sql
 SELECT DISTINCT p FROM Player p WHERE P.position = :position AND p.name = :name
 
 ~~~
**Dados recuperados:** Os Players que tem especificada posição e nome;
**Descrição:** Os elementos position e name são campos persitentes da entidade Player, a cláusula WHERE compara os valores com os valores nomeados da consulta, definidos usando a
função Query.___setNamedParameter___ usando ':' seguido por um identificador


# Consultas que navegam para entidades relacionadas 
 Na linguagem de consulta, uma expressão pode percorrer para entidades relacionadas. Essas 
 expressões são as principaIS diferenças entre a linguagem de consulta Java Persistence e a 
linguagem SQL. As consultas navegam  para entidades relacionadas, enquanto o SQL une as tabelas.
 
## Uma consulta simples com relacionamento.
 ~~~sql
 SELECT DISTINCT p FROM Player p, IN(p.teams) t
 
 ~~~
  __Dados recuperados:__ todos os Player que pertence a uma teams
  __Descrição:__ a cláusula FROM declara  duas variáveIS de identificação 'p' e 't', variável 
  p->Player, e t--> Team. A palavra-chave 'IN' significa que o teams é uma coleção de entidades
relacionadas, A expressão p.teams navega de um jogador para sua equipe relacionada. A expressão 
p.teams é o operador de navegação

 Também pode ser usada a expressão __JOIN__ para fazer a mesma consulta 
 
 ~~~sql
 SELECT DISTINCT p FROM Player p JOIN p.teams t
 ~~~
 Esta query pode ser rescrita como :
 ~~~sql
 SELECT DISTINCT p FROM Player p WHERE p.teams IS NOT EMPTY
 ~~~


## Navegando para relacionamento de valor unico F.
 Use a cláusula ___JOIN___ para navegar até um campo de relacionamento de valor único.
~~~sql
  SELECT t FROM Team t JOIN t.league l WHERE l.sport = 'soccer' or l.sport = 'football'
 ~~~

  Neste exemplo a consulta ira voltar todos os times que estão em ligas de soccer e football.

## Atravessando relacionamentos com parametro de entradas
 ~~~sql
 SELECT DISTINCT p FROM Player p, in(p.teams) as t WHERE t.city = :city
 SELECT DISTINCT p FROM Player p JOIN p.teams as t WHERE t.city = :city
 ~~~
__Dados recuperados:__ Players cujo o team pertence a cidade especificada.
__Descrição:__ semelhante a consulta anterior, porém acrescenta um parâmetro de entrada. A
palavra-chave 'AS' é opcional na cláusula FROM. Na cláusula WHERE, o período anterior à 
variável persistente city é um delimitador, não um operador de navegação 
   
## Atravessando varios relacionamentos
 ~~~sql
 SELECT DISTINCT p FROM Player p, in(p.teams) t WHERE t.league.sport = :sport 
 
 ~~~
__Dados recuperados:__ Os Players que participam do sport especificada 
__Descrição:__ O campo sport pertence a entidade league. Para chegar ao campo do sport, a
consulta deve passar pelos campos
Player --> Team --> League-->sport


## Consultas com outras expressões condicionais
   ### ___Like___ expressão
~~~sql
 
 SELECT p FROM Player p WHERE p.name LIKE 'name%'
 ~~~
__Dados recuperados:__ Todos Players que o nome inicia com 'name'
__Descrição:__ A expressão LIKE usa caracteres coringa para sua pesquisa. No casa utiliza o **'%'**


### Expressão IS NULL
~~~sql
 SELECT p FROM Player p WHERE p.teams IS EMPTY
 ~~~
__Dados recuperados:__ Todos os Players que tem o campo team NULL

### Expressão BETWEEN
~~~sql
 
 SELECT DISTINCT p FROM Player p WHERE p.salary BETWEEN :lowerSalary AND higherSalary
 ~~~
__Dados recuperados:__ Players cujo os salarios estão dentro do intervalo especificado
__Descrição:__ A seguinte expressão é equivalente ao BETWEEN

~~~sql
SELECT DISTINCT p FROM Player p WHERE p.salary >= lowerSalary AND p.salarios <= higherSalary
 ~~~

### Operadores de comparação
~~~sql
 SELECT DISTINCT p1 FROM Player p1, Player p2 WHERE p1.salary > p2.salary AND p2.name = :name
 ~~~
__Dados recuperados:__ Todos os Players cujo o salario é maior que o salario do Player com nome
 especificado


## UPDATES AND DELETE

### UPDATE Query
~~~sql
 UPDATE Player p SET p.status = 'inactive' WHERE p.lastPlayed > :inactiveThresholdDate
 ~~~
__Descrição:__ Atualiza o status do Player cujo a data do ultimo game é maior que o tempo para
inativação


### DELETE Query
~~~sql
 DELETE FROM Player p WHERE p.status = 'inactive' AND p.teams IS EMPTY
 ~~~
__Descrição:__ Deleta os Player cujo não fazem parte de time e o status é inativo 