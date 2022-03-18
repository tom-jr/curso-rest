#  Exceções ResponseStatusException
 Uma exception base lançada sem precisar cria-la. Pode substituir as Exceptions que criamos 
e estendemos de RuntimeException

As classes deixam de serem usadas e instanciamos o ResponseStatusException com parâmetro
de status e mensagem.

~~~ java
public void delete(Long entityId) {
        try {
            this.entityRepository.deleteById(entityId);
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, StringUtils.entityNotExist(entityId, entity.class.getSimpleName()));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, StringUtils.entityLinked(entity.class.getSimpleName()));
        }
    }
~~~

É possível também fazer com a classe de exception personalizada estenda 
o ResponseStatusException e implementar o construtor. Assim a exception
Personalizada não precisa mais usar a anotação de ResponseStatus, pois 
o mesmo sera informado no construtor. Podendo haver um construtor onde
se não informar o status ele passa o status padrão da exception.

~~~ java
public class EntityNotFoundException extends ResponseStatusException{

    public EntityNotFoundException(HttpStatus status,String message) {
        super(status,message);
    }

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND,message);
    }
} 


public class EntidadeInUseException extends ResponseStatusException {

     public EntityNotFoundException(HttpStatus status,String message) {
        super(status,message);
    }

    public EntityNotFoundException(String message) {
        super(HttpStatus.CONFLICT,message);
    } 
}
~~~