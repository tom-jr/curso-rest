
Quando as configurações de validações estão funcionando correntemente.
E dispara por exemplo, um erro de validação provido da captura de um
campo que não pode ser null. A nossa classe de tratamento de erro pode capturar
a e fazer um handle do erro que é disparado.

Na nossa class de APIExceptionHandle que criamos e estendemos de ResponseEntityExceptionHandler.
nos podemos sobrescrever os métodos de ResponseEntityExceptionHandler, certo?

Então no próprio console da API mostra o método que dispara ao dar o erro de validação, o nome do método
é MethodArgumentNotValid. E o ResponseEntityExceptionHandler captura e lança seu método  handleMethodArgumentNotValid
e nos sobrescrevemos ele para customizarmos.

A estrutura do método para sobrescrever é essa.

~~~ java
  @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
~~~

Dentro deste método adicionamos todos criaremos o corpo do retorno:

~~~ java
@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

	    ProblemType problemType = ProblemType.DADOS_INVALIDO;  // declaramos um enum do tipo de problema
	    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."; //detalhe sobre o problema
	    
      // O erro vem com campo chamado bindResult neles iremos encontrar o atributo de fieldErros. Que é um array de descrição
      // de cada erro que disparou na tentativa da validação
      // E esse field é passado para o Field erro da classe que recebe as informações dos erros por meio de um stream.
      
	    BindingResult bindingResult = ex.getBindingResult();
	    
	    List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
	    		.map(fieldError -> Problem.Field.builder()
	    				.name(fieldError.getField())
	    				.userMessage(fieldError.getDefaultMessage())
	    				.build())
	    		.collect(Collectors.toList());
	    
	    Problem problem = createProblemBuilder(status, problemType, detail)
	        .userMessage(detail)
	        .fields(problemFields)
	        .build();
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
~~~

então o método retornará um handleExceptionInternal que também foi sobrescrito para passar a nossa
classe que padroniza os erros.