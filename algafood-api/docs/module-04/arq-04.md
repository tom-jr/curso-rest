# Query methods
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



## Usando queries JPQL customizadas com @Query

caso queiramos fazer métodos customizados  para atender algum padrão diferente
dos disponíveis bo Spring podemos utilizar o ***@Query*** para passarmos 
JPQL na consulta e fazer parse dos parâmetros necessários

Exemplo:
~~~ java
@Query("SELECT e FROM Entidade AS e WHERE e.nome like :nome AND e.status = :status")
List<Entidade> buscarEntidadePorNomeEStatus(@Param("nome") String nome, @Param("status") String status){
}
~~~


 Outra forma possível é adicionando um arquivo ORM para "instanciar o  método"

resource/META_INF

orm.xml
~~~ xml
<?xml version="1.0" encoding="UTF-8"?>
<entity-mappings
    xmlns="http://xmlns.jcp.org/xml/ns/persistence/orm"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/orm_2_2.xsd"
    version="2.2">

	<named-query name="Entidade.buscarEntidadePorNomeEStatus">
		<query>
		FROM Entidade
		where nome like concat('%', :nome, '%')
		and id = :id
		</query>
	</named-query>

</entity-mappings>
~~~