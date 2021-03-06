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

A class advice também pode extender de **ResponseEntityExceptionHandler** o mesmo captura
alguns erros corriqueiros em um sistema. Podemos também sobrescrever os métodos para 
customizar ainda mais as respostas.
todos os métodosHandler criados podem retorna um handleExceptionInternal, que é um método
reescrito do **ResponseEntityExceptionHandler**

A class abstrata ResponseEntityExceptionHandler ela retorna ResponseEntity de varios tipos de erros
entre ele são:

- HttpRequestMethodNotSupportedException
- HttpMediaTypeNotSupportedException
- HttpMediaTypeNotAcceptableException
- MissingPathVariableException
- MissingServletRequestParameterExceptio,
- ServletRequestBindingException
- ConversionNotSupportedException
- TypeMismatchException
- HttpMessageNotReadableException
- HttpMessageNotWritableException
- MethodArgumentNotValidException
- MissingServletRequestPartException
- BindException
- NoHandlerFoundException
- AsyncRequestTimeoutExceptio

Todos esses erros estão em tratativa em um método que faz uso da anotação exceptionHandle
Dependendo do instanceOf de cada error chama sua tratativa e statusHttp referente. Mas o que
nos iremos utilizar desata class abstrata é o seu método chamado ***handleExceptionInternal***
esse método é o retorno para qualquer um desses erros. eles são desviados para suas tratativas especificas
e depois chama esse método para retorna como resposta de error da request.
O esquema do fluxo desses erros

![](/docs/resources/img/error_001.png)


O **handleExceptionInternal** não retorna um body para o ResponseEntity. No nosso caso estamos utilizando corpo
que é o StandardError. Então ao sobrescrever o método trataremos de adicionar corpo para todos os erros que tratarmos
em nossa classe de controller advice.
~~~java
 @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandlerMethod(EntityNotFoundException e, WebRequest request) {
        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<?> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        if (body == null) {
            body = StandardError.builder()
                    .dateTime(LocalDateTime.now())
                    .message(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = StandardError.builder()
                    .dateTime(LocalDateTime.now())
                    .message((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
~~~