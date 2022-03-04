#Ambiguidade de beans e injeção de lista de beans
  Quando um Bean é uma interface e existe duas ou mais instanciações a injeção da interface em
qualquer class não saberá qual das implementações do Bean utilizar em sua injeção esta aconteci-
mento se trata de uma ambiguidade e gera um erro de compilação ao subir o serviço WEB. 

![alt](/algafood-api/docs/resources/img/ex_ambiguidade_01.png)


Formas de se tratar a ambiguidade:

* Transformando o atributo do Bean em uma List. Assim, quando a injeção for realizada trabalha-
remos com uma lista de objetos que implementam a interface Bean. Então adicionando a regra que
desejar.

![alt](/algafood-api/docs/resources/img/ex_ambiguidade_02.png)

* Desambiguizada com a annotation **@Primary**. 
Com o primary na class que implementa o Bean adicionando a annotation @Primary o Spring 
container dará prioridade em instanciar esse implementação sempre.

![alt](/algafood-api/docs/resources/img/ex_ambiguidade_03.png)


* Desambiguação com @Qualifier
 Com o qualifier é possível declarar um alias --> Qualifier("aliasBean"). Assim, quando for rea-
 lizar uma injeção da interface basta anotar o atributo com o @Qualifier com o Alias que deseja
 utilizar na injeção.

 ![alt](/algafood-api/docs/resources/img/ex_ambiguidade_04.png)

 Outra forma de se utilizar o Qualifier é usando anotação customizada.
 Criando uma Enumeração com enums que representam a implementação a ser selecionada
 Criando uma annotation que tera um objeto do Enum

 ~~~ java

/*
* Enum que representa as class que implementa a interfaceBean 
*/
public enum EnumImpl{

    IMPL_OO1,
    IMPL_OO2
}


/*
*Annotation que carregara a seleção da implementação
* antaremos a annotation com Qualifier e Retention(RetentionPolicy.RUNTIME)
*/
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Implementation{

    EnumImpl value();
}



 ~~~
Então nas class de implementações que carregavam a annotation @Qualifier(""). Poderá ser subs-
tituida pela annotation costumada que também é um qualifier devido termos anotado a mesmo 
como um Qualifier. Comportamento semelhante a uma herança. 

~~~ java
@Component
@Implementation(EnumImpl.IMPL_OO1)
public class BeanInterfaceImpl_01 implements BeanInterface{

}

@Component
@Implementation(EnumImpl.IMPL_OO2)
public class BeanInterfaceImpl_02 implements BeanInterface{

}
~~~


Logo, na injeção, usamos a annotation customizada para selecionar a implementação desejada.

~~~ java
public class Generic{

    @Autowired
    @Implementation(EnumImpl.IMPL_OO2)
    private BeanInterface beanInterface;


}
~~~

Diagrama da estrutura:

![](/algafood-api/docs/resources/img/ex_ambiguidade_05.png)
