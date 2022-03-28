# Lombok

 Um plugin que auxilia na abstração de vários códigos para aumenta a produtividade do 
desenvolvimento.

 Para Utilizar o Lombok é preciso adicionar o plugin na IDE que estiver utilizando e 
 adicionar sua dependência no projeto pelo ***pom.xml***

 ~~~ xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.22</version>
    <scope>provided</scope>
</dependency>

 ~~~

 Uma class que voce antes tinha na mesma os seus getters e setters adicionado. Estaram de 
 forma oculta usando o Lombok. Ele tem anotações para geração de getters, setters, equals, 
 hashCode, construtor default, serialize e entre outros.
 O padrão que iremos usar:

 ~~~ java
 @Entity
 @Data   //lombok
 @EqualsAndHashCode(onlyExplicitlyIncluded = true) //lombok
 public class Entity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Long id;
 }
 ~~~
 Então a classe fica dessa forma, sem a necessidade de descrever os códigos  abstraídos.


 