# ExceptionHandler
Criamos uma class que sera um controller advice.
Nela ficara métodos que tratam as Exceptions criadas
e customiza um retorno do tipo ResponseEntity com um 
body padrão que também criaremos

Primeiro criaremos a class StandardError
Ela servira como body de retorno de uma ResponseEntity
toda Exception que tratarmos encapsularemos informações
no corpo da Class StandardError
~~~java
@Getter
@Builder
public class StandardError {
    
    private LocalDateTime dateTime;

    private String message;
}
~~~

Então criamos uma class para adicionar os métodos que capturam os erros Customizados.
A class recebe uma anotação de ControllerAdvice que é um tipo de component especial.
o exemplo se trata de uma tratativa a classe EntityNotFoundException ela é explicita
pela anotação @ExceptionHandler para que  o Spring saiba a classException que se trata
Então sempre que essa exception for lançada no sistema o controle advice ira alimentar
um StandardError com as informações da exception e retorna para o consumidor da API
~~~java
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandlerMethod(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build());
    }
}
~~~

~~~java
~~~