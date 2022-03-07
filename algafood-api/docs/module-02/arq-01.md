# O que é JPA e Hibernate

## Persistência com banco de dados usando Java

    |código Java| --> |Driver JDBC|---|Qualquer Banco|

## Mapeamento Objeto-Relacional

 Model Relacional| Modelo Orientado a Objetos
 ----------------|---------------------------
Tabela           | Classe
Linha            | Objeto
Coluna           | Atributo
|                | Método
Chave estrangeira| Associação 

![mapeamento-class](/algafood-api/docs/resources/img/ex_mapeamento_OR.png)


## Persistência com ORM

    |Código Java | --> |ORM| --> |Driver JDBC| --> |Qualquer Banco|



## O que é Java Persistence API (JPA)?

É uma especificação que define a forma de mapeamento Objeto relacional. Especificação descreve
como algo deve funcionar.

## O que é Hibernate?

Hibernate é uma implementação do JPA. Implementa as especificações do JPA e assim pode se inte-
grar como um ORM
