# Agrupamento de Validação

Para que uma validação de um determinado fluxo não 
atrapalhe a outra existe os Groups, voce pode validar
atributos baseado em seu Group, assim tal validação so
acontecera caso ao invés de esta o @Valid, esteja o 
@Validate(Group.Nome)

Ex:
criamos uma interface Groups e criamos um Group para quando realizamos um POST
~~~ java

public interface Groups {
    
    public interface PostEntity;
}
~~~
Então, quando chamar a validação no método post, ao invés de especificar a anotação
***@Valid***, adicionamos a anotação ***@Validate(Group.Nome)***
assim a validação vai passar na classe verificando as validações que pertencem a esse group. Então 
na associação da class, adicionamos o @Valid e e dentro da class que é associada adicionamos as validações
do grupo. 
Assim a validação vai ser realizada na class e na class associada sem influenciar outras validações.
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
em todos os atributos de validação.

Neste caso. usaremos o group apenas na class associada em sua atributo
que precisamos validar, assim continua sendo uma validação referente apenas aquele group.
 Para que a validação seja default em uma class e passe a verificar o group quando for
validar a class associada utilizamos a anotação ***@ConvertGroup(from = Default.class, to = Groups.Entity2Id.class)***

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
@NotNull(group = Groups.Entity2Id.class)
private Long id;
}
~~~

É possível adicionar nas Anotações das validações o valor de 
message para que o retorno do erro de validação apresente a mensagem
especificada na anotação
Ex:
~~~ java 
@NotNull(message = "O campo não pode ser null")
@NotBlank(message = "O campo não pode ser vazio")

~~~

Outra forma de se realizar a personalização da mensagem é criando um arquivo
de properties.
No diretório de resources criamos um arquivo com o mome especificadamente de
***message.properties***

Nele informamos o  nome da anotação de validação e passamos a mensagem  
~~~ properties
NotBlank={0} é obrigatório  # o parâmetro 0 é o nome da propriedade que a NotBlank foi definida
nome = Nome # substituindo o valor do nome da propriedade
propriedade.nome= Nome da propriedade # caso exista uma property menos abstrata(mais complexa). O Spring usará a mais complexa
~~~
 Para que funcione. No método onde adicionamos os fields errors ao invés de adicionarmos default
 Criamos uma string que recebe o retorno de messageSource.getMessage que tem de parâmetro o 
 fieldErro e o Locale da lingua. Assim o field é substituído pelo definido na ***messages.properties***
~~~ java
 String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

~~~