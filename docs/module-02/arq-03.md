# Mapeando Entidade com JPA

~~~ java
@Entity
@Table(name = "class_entidade")
public class ClassEntidade{

    @Id
    @GeneratedValue(strategy = GeneratedType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
~~~

Para que o JPA faça o mapping de uma classe Java para modelo relacional Utilizamos anotação do
package **javax.persistence**
 * @**Entity**: Anotação responsável pelo mapeamento principal. Diz para o compilador que a 
 classe se trata de um objeto persistente.

 * @**Table:** Anotação para adicionar configurações da tabela. A classe sera convertida em
tabela no banco de dados. No exemplo é adicionado um parâmetro name, que configura o nome que
a tabela receberá ao ser criada. Caso não for informado o JPA adiciona automaticamente o nome
da classe.

 * @**Column:** Semelhante ao @Table auxilia na configuração da coluna. Um atributo é convertido
em Coluna de uma tabela.

 * @**Id:** É uma anotação para definir a chave primaria da tabela. 

 As praticas Adicionada no desenvolvimento da classe é criar os atributos privados, gerar getters
 e setters dos atributos e equals e hash code com o atributo id.

 