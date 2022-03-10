
Quando se usa uma interface que extends JpaRepository o repository consegue 
utilizar vários métodos de consulta padrão que herda de JpaRepository e suas
heranças. Mas também é possível acionar na interface métodos abstratos que 
serão implementados automaticamente pelo Spring Data. Mas para que o Spring 
Data consiga interpretar o método, ele precisa esta padronizado com uma 
"sintaxe" de escrita. 
 Caso voce tenha um repository de uma Entidade_X e queira realizar uma busca
especifica que não se encontra entre os métodos fornecido pelo JpaRepository
você pode criar um método abstrato com o nome do atributo pelo qual voce
quer determinar o retorno, e então o Spring Data implementa automaticamente
e retorna a consulta com base no atributo. Exemplo

~~~ java
public interface Entidade_XRepository extends JpaRepository<Entidade_X, Long>{

    public List<Entidade_X> atributo1();
}
~~~

No caso acima o Spring faz a busca pelo atributo1 da Entidade_X.
É possível adicionar keywords na assinatura do método para realizar buscas 
ainda mais avançadas. Devendo manter dentro do padrão de aceitação do Spring.

formas de reescrever o metodo **atributo1()**
~~~ java

    public List<Entidade_X> atributo1();
    public List<Entidade_X> findByAtributo1();
    public List<Entidade_X> findEntidade_XByAtributo1();

}
~~~
Todas essas formas o Spring consegue implementar.

documentação com todas keywords possíveis para métodos. [doc Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)