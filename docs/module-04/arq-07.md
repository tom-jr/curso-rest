## Estendendo o JpaRepository

Quando desejamos criar consultas que possam ser utilizadas por todos os 
Repository do projeto nos cria mos uma camada amais de abstração.
 Uma interface EntidadeRepository extend de JPARepository e quando queremos
desenvolver consultas personalizadas as implementações são individuais sendo
que cada Entidade tem os seus repository customs.
 Uma forma de se criar métodos que sejam usados por qualquer repositório
do sistema é criando uma interface que extend JPARepository e que todas 
os repositórios estenda dessa interface e criamos uma class que implementa 
os métodos dessa interface.


A interface que era assim:
~~~ java
public interface EntidadeRepository extends JpaRepository<Entidade, Long>{

}
~~~

Passa a ser assim:
~~~ java
public interface EntidadeRepository extends CustomJpaRepository<Entidade, Long>{

}
~~~

Assim que extend a JpaRepository é a interface Customizada
~~~ java
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID>{

// adiciona abstração dos metodos que serão usados para todos os Repository
}
~~~

A implementação deve seguir esse padrão:
~~~ java
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        // TODO Auto-generated constructor stub
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        // TODO Auto-generated method stub
        String jpql = String.format("FROM %s", this.getDomainClass().getName());
        T t = entityManager.createQuery(jpql, this.getDomainClass())
                .setMaxResults(1)
                .getSingleResult();
        return Optional.ofNullable(t);
    }

}
~~~