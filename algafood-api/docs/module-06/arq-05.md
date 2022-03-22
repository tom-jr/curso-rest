
## Exemplo de corpo de resposta com erro, usando Problem Details for HTTP APIs

~~~ json
{
    "status":value,
    "type":value,
    "title":value,
    "detail":value,
    "instance":value
}
~~~

- **Status:** Indica o código de status gerado. Deve ser o mesmo status
vindo no cabeçalho da requisição
- **Type:** URI que identifica o tipo de problema: URI que pode ser adicionado
para explorar as causas dos problemas.
 - **Title:** curto titulo sobre o erro.
 - **Detail:** Espaço para detalhar o tipo de erro
 - **Instance:**  Um campo opcional para adicionar a URI do especifica do erro 
 Novas propriedades podem ser adicionada estendendo esse padrão

Na arquitetura que estamos trabalhando no momento o corpo onde retorna os nossos
erros tem apenas os campos de dataTime e message. 
Assim vamos utilizar o os campos da especificação.

Refatoração dos métodos

~~~ java

  @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalExceptionHandlerMethod(GeneralException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        EnumErrorType errorType = EnumErrorType.GENERAL_ERROR;
        String detail = e.getMessage();

        StandardError error = this.factoryErrorStandard(status, errorType, detail).build();
        return this.handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        if (body == null) {
            body = StandardError.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = StandardError.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }

            private StandardError.StandardErrorBuilder factoryErrorStandard(HttpStatus status, EnumErrorType errorType,
            String detail) {
        return StandardError.builder()
                .status(status.value())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }
~~~

Tratando erro no formato de um tipo. Bad Request
~~~ java
@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
~~~

Tratamento para que não aceite propriedades que não existam na entidade ou qu estão ignoradas
pela anotação de JsonIgnore.

Adicionando em application.properties as configurações
~~~ properties
spring.jackson.deserialization.fail-on-unknown-properties=true
spring.jackson.deserialization.fail-on-ignored-properties=true
~~~