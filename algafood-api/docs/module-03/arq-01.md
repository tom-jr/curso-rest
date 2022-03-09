# Content negotiation para Json ou XML
Quando desejamos consumir uma API podemos especifica o tipo de media que desejamos que nos
retorne. No header da requisição tem um atributo chamado ***Content-Type*** nele informamos se
desejamos respostas do tipo **application/json** ou application/xml e entre vários outros.

Caso especificarmos em uma requisição o tipo de dados e ele for diferente do tipo default da API
o gerenciador da API deve adicionar as dependências que capacitam a API para responder aquele 
tipo de formato.

É possível configurar o tipo de retorno de um método de um controle com o atributo ***produces***.
Assim o método só dará resposta a sua requisição com o media type que lhe foi configurado.
Obs: É possível passar vários tipos com procedures, bastando separar com vírgula.  

~~~ java
@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public T method(){
    return t;
}
~~~