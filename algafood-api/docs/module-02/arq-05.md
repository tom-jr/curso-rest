# Realizando CRUD com EntityManager

## Read Lendo entidades do banco de dados
 ~~~ java
@Component
public class EntityRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Entity> findEntity(){
        String  jpql = "SELECT e FROM Entity e";

        TypedQuery<Entity> query = entityManager.createQuery(jpql,Entity.class);
        return query.getResultList();
    }
}
 ~~~

 1. Inicia com uma Bean que sera o responsável pelo método de consulta da entidade.
 2. Cria uma instancia de EntityManager, que é um class de gerenciamento de entidades do JPA.
 Neste caso o mesmo esta sendo injetado com uma anotação voltada para um contexto de 
 persistência o **@PersistenceContext**
 3. Cria um método que retorna uma lista da Entidade que sera consultada no banco.
 4. com a instancia de **entityManager** chamamos o método createQuery que recebe como parâmetro
 uma string que representa o comando em **JPQL** (JAVA PERSISTENCE QUERY LANGUAGE ) e uma
 referência a Entidade consultada.
 5. O retorno do método é do tipo TypedQuery o qual tem o retorno da consulta, utilizando o 
 **.getResultList()** podemos retorna os intens da consulta.

 ## Buscar Entidade pela chave primaria

 ~~~ java
@Component
public class EntityRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public Entity findEntityById(Long id){

        return entityManager.find(Entity.class, id);
    }
}
 ~~~


## Create, adicionando entidades
s
 ~~~ java
@Component
public class EntityRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Entity createEntity(Entity entity){

        return entityManager.merge(entity);
    }
}
 ~~~

 É adicionado uma anotação de **@Transactional** , pois para transações de inserção e exclusão 
 é regra que sejam feitas e contexto de transação.

## Atualizar uma Entidade 

Para atualizar utilizamos o mesmo metodo do entityManager o **merge(T t)**.
Caso existir uma entidade com um id passado ele atualiza com as informações,
caso o contraio ele cria um novo.  

## Removendo uma Entidade do banco de dados

 ~~~ java
@Component
public class EntityRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Entity createEntity(Entity entity){
        entity = entityManager.find(Entity.class, entity.getId())
        return entityManager.remove(entity);
    }
}
 ~~~

 Na  exclusão de uma entidade é preciso que ele esteja em um estado gerenciável.
 Por isso fazemos uma busca pelo id da entidade antes de executart o delete de 
 fato. E deixar a obbservação que o **remove()** também se trata de um método
 Transactional.