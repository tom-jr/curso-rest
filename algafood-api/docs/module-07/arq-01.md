
Para utilizar o Bean Validation não sera necessário adicionar nenhuma
dependência extra, como temos a dependência **spring-boot-start-web** 
ja adicionada ela traz em sua arvore a dependência validation API da 
Implementação de Validation do Hibernate.
Porem,  a partir da versão 2.3 do Spring não é mais adicionada na three do
**spring-boot-start-web** . Sendo assim, devemos adicionar ao pom a dependência
~~~xml
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.1.5.Final</version>
		</dependency>

~~~ 

 Para que possamos validar alguns campos e disparar exceptions, adicionamos
anotações de constraints.

## Constraints

### **@NotNull**
O NotNull adicionamos no campo da entity que gostaríamos de validar antes de qualquer
transação na API.
package:import javax.validation.constraints.NotNull;
Ex:

~~~ java
@Entity
@Table(name = "entity")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Entity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column(name = "name")
    @NotNull
    private String name;

    
}
~~~
No método que recebe o @Request body informamos com a anotação 
**@Valid** para que possa se executar a validação no Objeto que
recebe a serialization

Ex:
~~~ java
@PostMapping(value="/entities")
public Entity add(@RequestBody @Valid EntityDTO dto){
    //code
}
~~~ 
