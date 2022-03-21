##

### Refatorando uma class de busca

Esse método do controller não esta lançando exception alguma.
esta enviado status e mensagem diferente baseado no Optional
esta ou não com uma entidade buscada na persistência 
~~~ java
  @GetMapping(value = "/{entityId}")
    public ResponseEntity<?> findById(@PathVariable Long entityId) {
        Optional<entity> entity = this.entityService.getRepository().findById(entityId);
        if (entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(entityId, entity.class.getSimpleName()));
        }
        return ResponseEntity.ok(entity);
    }
~~~

Podemos refatorar de modo que deixa mais simples e também usa 
a exception criada para tal situação.

~~~ java
  @GetMapping(value = "/{entityId}")
    public Entity findById(@PathVariable Long entityId) {
        return entityService.findById(entityId);
    }
~~~
No EntityService
~~~ java
public Entity findById(Long entityId) {
        return this.getRepository().findById(entityId).orElseThrow(() -> new EntityNotFoundException(
                StringUtils.entityNotExist(entityId, Cozinha.class.getSimpleName())));
    }
~~~