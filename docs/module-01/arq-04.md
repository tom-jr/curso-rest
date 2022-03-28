# Pontos de injeção

 Os pontos de injeções são os meios que se pode adicionar a injeção de um Bean a uma classe que
 deseja utilizar seus recurso. Pontos de injeções
  * Constructor: como visto antes o construtor é um dos meios de se injetar um Bean. Adicionan-
do o mesmo como um atributo do construtor, a injeção é realizada. Exemplo da injeção pelo cons-
trutor:

~~~ java

@Component
public ClassBean{

}

public class GenericClass(){

    private ClassBean classBean;

    public GenericClass(ClassBean classBean){
        this.classBean = classBean;
    }

}

~~~
  Caso exista mais construtores na classe que injeta, pode-se adicionar a annotation @Autowired
para especificar o construtor que sera utilizado para declarar a class. Assim o construtor com
a injeção pode ser preservado.

 * Set: um meio que se é valida a injeção é pelo método set do atributo que é adicionado como 
parâmetro na injeção pelo construtor. Utilizando a notação @Autowired no set a injeção é 
realizada. Exemplo:

~~~ java

@Component
public ClassBean{

}

public class GenericClass(){

    private ClassBean classBean;

    public GenericClass(){
    }

    //getters and setters

    @Autowired
    public void setClassBean(ClassBean classBean){
        this.classBean = classBean;
    }

}

~~~

 * Atributo uma bem comum de se encontrar é a injeção pelo atributo. Onde na declaração do
atributo se adiciona a anotação @Autowired


~~~ java

@Component
public ClassBean{

}

public class GenericClass(){

    @Autowired
    private ClassBean classBean;

    public GenericClass(){
    }
}

~~~

O tipo de injeção ideal é pelo constructo. Pois gerencia melhor as dependências que a classe
injetora ira utilizar.