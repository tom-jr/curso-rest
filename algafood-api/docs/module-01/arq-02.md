# Criando um controller

 Criando um diretório com nome de controllers adicionamos uma class com nome de PrimeiroController.java

   ~~~java
 @RestController
    @RequestMapping(value = "/uri_controller" )
    public class PrimeiroController{

        @GetMapping(value = "/sub_uri")
        public String helloWorld(){

        return "Hello World!!!";
        }
    }
   ~~~

 Quando se tem um @ o que segue é denotado como uma annotation. neste exemplo, tres annotations são 
 utilizadas:

 * @RestController: informa ao compilador que essa classe s trata de um controlador REST. Ques estará
recebendo as requisições que se direcionam para as
URIs dos controladores declarados.
        OBS: Todo e qualquer class RestController precisa ter sua URI declara para que assim possa
receber requisições WEB. Essas URI é como um diretório na classe.Temos a URI pai, que vem informado na
declaração da class com a annotation.
 

 * @RequestMapping(value = "/URI"): O requestMapping é usado para mapear uma class ou método. Assim pode-
 do ser mapeado como um componente WEB pelo Spring.


 * GettMapping: serve tambem para realizar mapeamento, mas apenas de métodos. Existe alguns tipos de
mapeamentos de métodos, de acordo com a ação que se deseja realizar nos dados trabalhados pela requisição.    


  Quando desejarmos fazer uma requisição para um método do do Controller utilizamos a URI filho precedido pela URI do pai. EX:

  ~~~java
  @RestCOntroller
  @RequestMapping(value = "/uri-pai")
  public class ClassController{

      @GetMapping(value= "/uri-filho")      //Para recuperar o retorno do método testeRequest
      public String testeRequest(){         //é preciso especificar a uri: uri-pai/uri-filho
          return "OKAY!";                   //http://localhost:8080/uri-pai//uri-filho
      }
  }
  ~~~
  
    
  



  