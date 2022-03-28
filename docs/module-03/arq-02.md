
## @Path variable

 Quando desejamos realizar um requisição que retorna uma entidade única costuma-se realizar essa
 requisição enviando o valor de chave única da entidade que queremos como respostas 

Essa URL representa uma requisição para retorna a entidade que possua a chave numero 1.
 ~~~ http
 GET: http://localhost:8080/entidades/1
 ~~~
 Para que um método rest possa receber esse valor de chave e retorna o desejado é necessário
 usar uma anotação chamada ***@PathVariable***. Ela tem a responsabilidade de recupera a chave
 passada pela URL. EX:

 ~~~ java

@GetMapping(value = "/entidades/{id}")
public Entidade findEntidadeById(@PathVariable Long id){

    //code
    return entidade;
}

 ~~~

 Dessa maneira o **@PathVariable** pode converter a chave para a variável **Long id** e o método
 realizará a busca de acordo com esse parâmetro

## Response Entity
O Response entity é uma class builder que utilizamos para adicionar informações do protocolo
http junto com a resposta de uma entidade. Ao invés de um método rest retorna diretamente uma
entidade ele envia a mesma encapsulada em um ResponseEntity onde po esta adicionando status e 
outras informações que auxiliam na compreensão da transação. Exemplo:

~~~ java


@GetMapping(value = "/entidades/{id}")
public ResponseEntity<Entidade> findEntidadeById(@PathVariable Long id){

    //code
    return ResponseEntity.status(HttpStatus.OK).body(entidade);
    //ou apenas return ResponseEntity.ok(entidade);
}

~~~
A vantagem de se usar um ResponseEntity é que voce pode customizar o retorno de acordo com o
status do processo do método.

***ResponseEntity.NotFound().build();*** usamos quando queremos enviar um status 404 de NotFound

## Método rest de adição de  nova entidade

Quando se deseja salvar uma nova entidade utilizamos o verbo HTTP Post. 
a requisição fica dessa forma:
~~~ json
POST: http://localhost:8080/entidaedes
payload
{
    "atributo": "valor"
}
~~~

 No código Java utilizamos o @RequestBody para receber o body que vem do payload da requisição
assim podemos deserializer o objeto e salvar no banco de dados.

~~~java
@PostMapping
public void save(@RequestBody Entidade entidade){
    //code
}
~~~

Para realizar uma atualização utilizamos a chave para buscar a entidade e então atualiza-la.

~~~java
@PutMapping(value ="/entidades/{id}")
public void save(@RequestBody Entidade entidade, @PathVariable Long idEntidade){
    //code
}
~~~

Para realizar uma exclusão utilizamos a chave para buscar a entidade e então exclui-la.

~~~java
@DeleteMapping(value ="/entidades/{id}")
public void remove(@RequestBody Entidade entidade, @PathVariable Long idEntidade){
    //code
}
~~~
s