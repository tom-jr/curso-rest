# Spring Profiles
 É uma for de separar componentes da aplicação que serão disponibilizados em certos ambientes. 
Exemplo na pratica:

~~~ java

@Profile("desenvolvimento")
@Component
public class EnviarEmailTeste(){

//métodos de envio de email fake, apenas simula o envio.
}



@Profile("producao")
@Component
public class EnviarEmail(){

//métodos que de fato enviam emails
}
~~~
No exemplo fora criado dois componentes para envio de email, um é de simulação o outro é de 
fato funcional. Quando desejamos realizar testes com o EnviarEmailTeste basta especifica no 
arquivo application.properties qual profile desejamos subir no servidor Spring.
[profile](algafood-api/src/main/resources/application.properties) - **/src/main/resources/application.properties**

no arquivo application.properties set o profile ativo.
~~~ profile
spring.profiles.active=producao
~~~

Assim o component que ira para o Container Spring é o com value de produção.

## Substituir profile em um projeto ja builded pelo maven

Na execução do jar do projeto passe junto ao comando

~~~ bash
java -jar project_name.jar -Dspring.profiles.active=producao
~~~