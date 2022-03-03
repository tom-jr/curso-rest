# Injeção de dependências

## Spring IoC Container
  É a implementação de injeção de dependência do framework Spring.

## Beans (dependecies)
 São os objetos gerenciados pelo container Spring. Eles podem ser injetados uns nos outros de acordo com suas necessidades.

## Definindo Bean com a annotation @Component
  Quando utilizamos a annotation @Component na assinatura de uma class dizemos ao Spring que se trata de um Bean gerenciado.
Ele é instanciado pelo Spring na inicialização do servidor e fica pronto para ser utilizado em class que o injetar.

 O @Controller e @RestController também são Beans gerenciados que herdam a annotation de @Component. Logo também são 
 carregadas para o container Spring.

## Formas de se injetar

 * Pelo Construtor: Umas classe pode injetar um Bean gerenciado pelo container Spring pelo seu construtor. Assim poderá 
 utilizar dos recurso do Bean injetado. Exemplo:

 ~~~Java

        @Component
        class BeanSpring{

            public methods(){}
        }


        class Generic{
            private BeanSpring beanSpring;

            public Generic(BeanSpring beanSpring){
                this.beanSpring = beanSpring;
            }

        }
~~~

 Caso a injeção for de uma interface que esta sendo monitorada pelo container Spring. O mesmo, automaticamente utiliza a 
 implementação da interface como Bean. Porém, isso ocorre apenas no caso de uma única implementação existir. Caso contrario 
 é necessário configurar qual implementação sera utilizada na injeção da interface.

![Exemplo](/docs/resources/img/injecao_iterface.png)

            
## @Configuration e @ Bean

 Uma outra forma de gerenciar Beans é com uma class de configuration. que ira  Instanciar a interface/classes 
 como um Bean em sua classe. A classe de configuration é anotada com o 
 @Configuration e instancia as classes/interfaces que antes estavam anotados como @Component.
 As instancias dessas classes agora recebem a anotação de @Bean que fara com que o container 
 Spring as monitorem.
 Essa arquitetura tem a vantagem de se poder configurar parâmetros que entram no construtor dos Beans.
Assim tem uma pre-set. Segue o exemplo de uma classe de configuração  

~~~java
@Configuration
public class ClassConfigBeans{

    @Bean
    public ClassBean beanClass(){
        return new ClassBean();
    }
}
~~~

 Assim a injeção do Bean, que antes era um Component continuara funcionando normalmente.


 

































