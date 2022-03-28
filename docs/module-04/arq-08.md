# Specifications Predicate
Criaremos specifications de EntityRepository para que possam ser adicionados **predicates**
 em métodos do CustomJpaRepository.
~~~ java
public interface EntityRepository extends CustomJpaRepository,EntityRepositoryCustom,JpaSpecificationExecutor<Entity>{

} 
~~~
~~~ java
public class EntitySpecs {

    public static Specification<Restaurante> withFreeShip() {
        return (root, query, builder) -> builder.equal(root.get("attribute"), BigDecimal.ZERO);

    }
}
~~~

~~~ java
@GetMapping(value = "/entities")
public ResponseEntity<?> findAllWithPredicate(){

    return ResponseEntity.ok(this.entityRepository.findAll(EntitySpecs.withFreeShip()));
}
~~~

# Lazy
 Para que a responsabilidade sai do Controller adicionamos o método como uma query personalizada
e implementamos. Utilizamos o ***@Lazy*** para evitar ***referencia circular*** na injeções.

~~~ java
@GetMapping(value = "/entities")
public ResponseEntity<?> findAllWithPredicate(){

    return ResponseEntity.ok(this.entityRepository.findAllWithFreeShip());
}
~~~
~~~ java
public interface EntityRepository extends CustomJpaRepository,EntityRepositoryCustom,JpaSpecificationExecutor<Entity>{
    List<Entity> findAllWithFreeShip();
} 
~~~ 

~~~ java
public interface EntityRepositoryCustom{
    List<Entity> findAllWithFreeShip();
}
~~~ 

~~~ java
public class EntityRepositoryImpl implements EntityRepositoryCustom {

    @PersistenceContext
    private EntityManege entityManege;

    @Autowired
    @Lazy
    private EntityRepository entityRepository;

    @Override
    List<Entity> findAllWithFreeShip(){
        return entityRepository.findAll(EntitySpecs.withFreeShip());
    }
}
~~~ 

~~~ java
~~~ 

