# Validando em cascade

Para realiza a validação em cascata realizamos o procedimento
de anotar o @NotNull no relacionamento e o @Valid para que
o Spring entenda que é para verificar a validade dos atributos

Ex:
~~~ java
public class Entity{

    //relations
    @ManyToOne
    @Valid
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

Dessa forma no momento em que a Entity for validada
ela validara além do campo Entity2 se esta null, também
verificar as anotações de seus atributos.
