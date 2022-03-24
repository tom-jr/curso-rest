# Agrupamento de Validação

Para que uma validação de um determinado fluxo não 
atrapalhe a outra existe os Groups, voce pode validar
atributos baseado em seu Group assim tal validação so
acontecera caso ao invés de esta o @Valid, esteja o 
@Validate(Group.Nome)

Ex:
~~~ java

public interface Groups {
    
    public interface PostEntity;
}
~~~
~~~ java
@Entity
public class Entity2{

@Id
@NotNull(groups= Groups.PostEntity.class)
private Long id;
}
~~~
Funciona igualmente para Validações cascata.

Podemos também trabalhar apenas o group com a entidade
que tem relacionamento. Assim não precisando adicionar group
em todos os atributos de validação

~~~ java
public class Entity{

    //relations
    @ManyToOne
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.Entity2Id.class)
    @NotNull
    private Entity2 entity2;
}

@Entity
public class Entity2{

@Id
@NotNull
private Long id;
}
~~~