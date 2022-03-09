# Full Query Language Syntax

Esta seção discute a sintaxe da linguagem de consulta conforme definido na especificação
Java Persistence API 2.0 

## Symbolos

 Symbol |  Descrição
--|-- 
 ::=      | O elemento a esquerda do símbolo e definido pelas construções á direita
\*        |  A construção anterior pode ocorrer zero ou mais vezes
 {...}    | As construções dentro das chaves são agrupadas   
 ...    | As construções dentro do colchetes são opcionais
 |   \|   | An exclusive OR


## Clásula FROM
  Cláusula ___FROM___ define o domínio da consulta, declarando variáveis de identificação.
  
 __Identifiers__
  Sequencia de um ou mais caracteres. O primeiro deve ser um carácter valido (letra,$,_) em um
identificador da linguagem Java
um identificador não pode ser o mesmo que uma palavra **chave**. Lista de palavras-chaves

 - ABS
 - ALL
 - AND
 - ANY
 - AS
 - ASC
 - AVG
 - BETWEEN
 - BIT_LENGTH
 - BOTH
 - BY
 - CASE
 - CHAR_LENGTH
 - CHARACTER_LENGTH
 - CLASS
 - COALESCE
 - CONCAT
 - COUNT
 - CURRENT_DATE
 - CURRENT_TIMESTAMP
 - DELETE
 - DESC
 - DISTINCT
 - ELSE
 - EMPTY
 - END
 - ENTRY
 - ESCAPE
 - EXISTS
 - FALSE
 - FETCH
 - FROM
 - GROUP
 - HAVING
 - IN
 - INDEX
 - INNER
 - IS
 - JOIN
 - KEY
 - LEADING
 - LEFT
 - LENGTH
 - LIKE
 - LOCATE
 - LOWER
 - MAX
 - MEMBER
 - MIN
 - MOD
 - NEW
 - NOT
 - NULL
 - NULLIF
 - OBJECT
 - OF
 - OR
 - ORDER
 - OUTER
 - POSITION
 - SELECT
 - SET
 - SIZE
 - SOME
 - SQRT
 - SUBSTRING
 - SUM
 - THEN
 - TRAILING
 - TRIM
 - TRUE
 - TYPE
 - UNKNOWN
 - UPDATE
 - UPPER
 - VALUE
 - WHEN
 - WHERE


 ## Variavel de identificador
 Uma variavel de identificação é um identificador declarado na cláusula ***FROM***
 . Embora possam referenciar as variáveis de identificação, as cláusulas
**SELECT** e **WHERE** não pode declara-las.
 As variáveis de identificação tem as mesma restrições de nomeação que os identificadores. 

##   JOIN
 O operador JOIN é usado para percorrer relacionamentos entre entidades e é funcionalmente semelhante ao operador **IN** 
 ~~~sql
 SELECT c FROM  Customer c JOIN c.orders o WHERE c.status = 1 AND o.totalPrice > 1000 
 ~~~
 A keyword **INNER** é opcional

~~~sql
 SELECT c FROM Customer c INNER JOIN c.orders o WHERE c.status = 1 AND o.totalPrice > 1000
 ~~~

 Exemplo é equivalente a:
 ~~~sql
        SELECT c FROM Customer c, IN (c.orders) o WHERE c.status = 1 AND o.totalPrice > 1000
 ~~~

 Também pode ingressar em relacionamento de valor único:
~~~sql
 SELECT t FROM Team t JOIN t.league l WHERE l.sport = :sport 
 ~~~


 O operador **JOIN** é usado para percorrer relacionamento entre entidades e
é funcionalmente semelhante ao IN.
 ~~~sql
 SELECT c FROM Costumer c JOIN c.orders o where c.status = 1 AND o.totPrice > 10000
 ~~~
 
 A chave INNER se torna opcional nessa query
 ~~~sql
 SELECT c FROM Costumer c INNER JOIN c.orders o where c.status = 1 AND o.totalPrice > 10000
 ~~~

 Os exemplos são equivalentes a seguinte query utilizando o IN
 ~~~sql
 SELECT c FROM Costumer c, IN(c.orders) c WHERE c.status = 1 AND o.totalPrice > 10000
 ~~~

Um **LEFT JOIN** ou *LEFT OUTER JOIN* recupera um conjunto de entidades em
que os valores correspondentes na condição de junção podem estar ausentes. A
 palavra chave **OUTER** é opcional.
 ~~~sql
 SELECT c.name, o.totalPrice FROM CostumerOrder o LEFT JOIN o.customer c 
 ~~~

 Um **FETCH JOIN** é uma operação de junção que retorna entidades associadas
  como um efeito colateral da execução da consulta. No exemplo a seguir, a consulta 
 retorna um conjunto de departamentos e, como efeito colateral, os 
 funcionários associados dos departamentos, mesmo que os funcionários não 
 tenham sido recuperados explicitamente pela cláusula **SELECT** 
 ~~~sql
 SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.deptno = 1
 ~~~