## Relacionamento ManyToOne

 Quando entidades tem relacionamentos entre si, para que seja representado essas relações no
banco de dados utilizamos as anotações do javax.persistence para definir os tipos de 
relacionamentos e assim traspor em DDL para o banco de dados relacional

O  primeiro relacionamento que vamos tratar é o ManyToOne. Quado muitos de um tipo de entidade
se relaciona com uma unidade de outra entidade.

Exemplo gráfico de relacionamento:

 ![](/algafood-api/docs/resources/img/ex_relacionamento_01.png)

 Nesta situação cada entidade1 pode se relacionar com apenas um entidade2. E entidade2 pode 
 ter vinculo com varias entidade1.

 O mapeamento em java fica dessa forma:

 ~~~ java
 @Entity
 public class Entidade1{
//..
    @ManyToOne
    private Entidade2 2ntidade2;
    //..

 }
 ~~~

 Com esse mapeamento o banco de dados criara um campo na tabela Entidade1 com o nome default
 entidade2_id que sera uma ***chave estrangeira*** que representa o relacionamento dessas duas 
 entidades.


Opcionalmente se pode usar o ***@JoinColumn(name ="name_column_custom")*** para adicionar um nome distinto do default para a nova
coluna que ira ser criada.

 No JoinColumn é possível adicionar o nullable para definir se a coluna pode ou não ser nula,
 o mesmo pode ser usado para a anotação ***@Column***

 ~~~java
 @Column(name="name", nullable = true)
 @JoinColumn(name="name", nullable = false)
~~~
