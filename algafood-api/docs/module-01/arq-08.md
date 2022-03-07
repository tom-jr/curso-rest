# Fases de vida de um Bean

O ciclo de vida de um Bean tem duas fases
 * Inicialização
 * Destruição

 Nestes ciclos de vida podemos implementar métodos de **callback**. Que são métodos chamados
 pelo Spring ao passar por alguma das fases de vida do Bean.

Quando se deseja adicionar um método na fase de inicialização usamos a anotação @PostConstructor
 Exemplo:
~~~ java
@Component
public class ClassBean{

    public ClassBean(){}

    @PostConstructor
    public void init(){
        System.out.println("Hello World!");
    }
}
~~~
O método sera executado exatamente apos o constructor do Bean for inicializado.

Quando se deseja adicionar um método na fase de destruição do Bean se utiliza a anotação 
@PreDestroy
Exemplo:

~~~ java
@Component
public class ClassBean{

    public ClassBean(){}

    @PostConstructor
    public void init(){
        System.out.println("Hello World!");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("Goodby World!");
    }
}
~~~
O método destroy será executado momento antes do Bean ser destruído.

Caso estiver utilizando Bean por meio de um configuration utilizamos o seguinte padrão.

~~~ java
@Configuration
public class ClassConfigBean{

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ClassBean classBean(){

    }
}
~~~
Como parâmetros da anotação que define aquela instancia como um Bean é adicionada o initMethod e
destroyMethod que como value são informados os métodos que exerceram os papeis de métodos das
fases de inicialização e destruição. 