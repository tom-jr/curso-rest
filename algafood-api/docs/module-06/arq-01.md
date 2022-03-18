
# Response Status
 Com o Response Status podemos adicionar o status de uma requisição por meio de anotação.
 Abaixo estamos anotando a class e informando que se essa exception for lançada o retorno
 da requisição retornará com o status informado no value da annotation.
Abaixo uma class de Exception para ser disparada caso uma entidade solicitada pela persistência
não exista.
~~~ java
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
} 


@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeInUseException extends RuntimeException {

    public EntidadeInUseException(String message) {
        super(message);
    }   
}
~~~

Abaixo um controller que tem o ResponseStatus com retorno de NO_CONTENT
um padrão para quando o delete ocorrer normalmente.
~~~ java
    @DeleteMapping(value = "/{entityId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long entityId) {
      
        this.entityService.delete(entityId);
    }
~~~

O métod do service Chamado onde esta a logica que dispara os erros caso
não satisfaça a função desejada.
No exemplo, dependendo de qual erro disparar o status de retorno da requisição 
sera distinta
caso deletar: ***NO_NO_CONTENT***
caso não existir a entidade no banco: ***NOT_NOT_FOUND***
caso a entidade tenha vinculo com outra tabela : ***CONFLICT***
~~~ java
public void delete(Long entityId) {
        try {
            this.entityRepository.deleteById(entityId);
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            throw new EntidadeNaoEncontradaException(
                    StringUtils.entityNotExist(entityId, entity.class.getSimpleName()));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(StringUtils.entityLinked(entity.class.getSimpleName()));
        }
    }
~~~