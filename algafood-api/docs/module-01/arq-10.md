# Configuração do Projeto com o application.properties

O arquivo de **application.properties** auxilia na configuração do projeto Spring. Nele 
adicionamos chaves e valores de varias configurações. 
Link da documentação Spring com catalogo de todas as configurações possíveis de se realizar:
 https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

 ## Especificar parâmetros na inicialização do JAR
 Com o projeto ja builded é possível adicionar configurações do application.properties.
 Dando assim prioridade para a informação passada por linha de comando 
Exemplo:

~~~ bash
java -jar /target/projeto.jar -server.port=8081
~~~

Neste exemplo mesmo que no application.properties do projeto esteja configurado uma porta 
diferente sera priorizado a porta passada pelo comando.

## Especificar parâmetros por variáveis de ambiente

Também é possível especificar configuração do projeto com variáveis de ambientes.
Exemplo

~~~ bash
$ export SERVER_PORT=8082
$ echo $SERVER_PORT
$ > 8083
~~~

Então assim que rodar a plicação ela ira subir com a porta especificada na variável de ambiente. 

## Adicionando configurações customizadas
É possível criar suas próprias configurações customizadas no arquivo de application.properties
Para as vincular com variáveis no projeto utilizamos a anotação @Value("${nome.property}")  
Exemplo
~~~ property
configuration.nome.empresa=company_X
~~~

com a property customizada criada no application.properties utilizamos a anotação para atribuir
seu valor. Exemplo:

~~~ java

public class ClassBean{

    @Value("${configuration.nome.empresa}")
    public String nomeEmpresa;

    public ClassBean(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
        System.out.print(this.nomeEmpresa);
    }
}
~~~
Assim que o Bean for inicializado no Container ao subir o projeto a Saida será: 

~~~ bash
$ company_X
~~~

E caso alterar o valor em properties assim que reiniciar o servidor o valor sera alterado.

Uma forma de separar as properties customizadas em grupos é Utilizando o @ConfigurationProperties
É criada um classe que representa aquele conjunto de properties  . Segue o exemplo da arquitetura
do uso do @ConfigurationProperties:
~~~ java
@Component
@ConfigurationProperties("configuration") //referencia ao prefixo no application.properties
public class ClassProperties{ 
    private String nomeEmpresa;  //caso for definido um valor, ele será default. Não existindo no
                                 //application.properties é usado esse.

    //getters and setters
}

public class ClassBean{

    @Autowired    //injeção da classe ClassProperties
    private ClassProperties classProperties
    public String nomeEmpresa;

    public ClassBean(String nomeEmpresa){
        this.nomeEmpresa = classProperties.nomeEmpresa;
        System.out.print(this.nomeEmpresa);
    }
}
~~~

Podemos criar arquivos properties personalizados para separar configurações de acordo com a 
necessidade do projeto. Podemos ter:

application-dev.properties
application-prod.properties

application.properties

no application.properties informamos qual properties sera usado como configuração complementar.

~~~ property
spring.profiles.active=dev|prod
~~~

Essa arquitetura é muito util para guardar configurações para ambientes diferentes. o mais 
usado é para distinguir ambiente local para o de produção. Onde varias configuração são distintas,
como por exemplo configuração de banco de dados, url de serviços WEB entre outros.
É possível trocar de profile active por linha de comando em um projeto ja builded:

~~~ bash
java -jar /projeto.jar --spring.profiles.active=prod
~~~
Também tem a possibilidade de informar o mesmo por variável de ambiente. 
