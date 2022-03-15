# Multiplicidade nas Relações com Entidades
Multiplicidades são dos seguintes tipos.

**Um para um**: Cada instância da entidade está relacionada a uma única instância
 de outra entidade. Por exemplo, para modelar um armazém físico no qual cada
  caixa de armazenamento contém um único widget, e teria um relacionamento 
  um-para-um. Relacionamentos um-para-um usam a anotação na propriedade ou 
 javax.persistence.OneToOne


**Um a muitos**: Uma instância de entidade pode estar relacionada a múltiplas 
instâncias das outras entidades. Uma ordem de vendas, por exemplo, pode ter
 vários itens de linha. Na aplicação do pedido, teria uma relação de um a 
 muitos com . Relacionamentos únicos usam a anotação na propriedade ou campo 
 persistente javax.persistence.OneToMany


**Muitos para um**: Várias instâncias de uma entidade podem estar relacionadas a 
uma única instância da outra entidade. Essa multiplicidade é o oposto de uma 
relação de um a muitos. No exemplo apenas mencionado, a relação com a 
perspectiva de são muitos para um. Relacionamentos muitos para um usam a 
anotação na propriedade ou campo persistente correspondente.
javax.persistence.ManyToOne

**Muitos para Muitos**: As instâncias da entidade podem estar relacionadas a múltiplas instâncias umas das outras. Por exemplo, cada curso universitário 
tem muitosalunos, e cada aluno pode fazer vários cursos. Portanto, em um 
pedido de matrícula, e teria um relacionamento de muitos para muitos. Muitas 
relações usam a anotação na propriedade ou campo persistente correspondente.
javax.persistence.ManyToMany

## Operações e Relacionamentos em Cascata

 Entidades que utilizam relações muitas vezes têm dependências da existência
da outra entidade na relação. Por exemplo, um item de linha faz parte de um
pedido; se o pedido for excluído, o item da linha também deve ser excluído. 
Isso é chamado de relação de exclusão em cascata.

 Operação | Descrição 
 -|-
 ALL|Todas as operações em cascata serão aplicadas à entidade relacionada à entidade-mãe. é equivalente a especificar Allcascade={DETACH, MERGE, PERSIST, REFRESH, REMOVE}
 DETACH| Se a entidade-mãe for desvinculada do contexto de persistência, a entidade relacionada também será destacada.
MERGE|Se a entidade-mãe for incorporada ao contexto de persistência, a entidade relacionada também será fundida.
PERSIST|Se a entidade-mãe persistir no contexto de persistência, a entidade relacionada também será persistiu.
REFRESH|Se a entidade-mãe for atualizada no contexto atual de persistência, a entidade relacionada também será atualizada.
REMOVE|Se a entidade-mãe for removida do contexto atual de persistência, a entidade relacionada também será removida.

## ManyToMany

Sera gerado uma tabela extra com o relacionamento no banco de dados

entity_entity2|
-|-

entity_id|entity2_id
-|-
|
|
|
~~~ java
@Entity
public class Entity{
    \\code
    @ManyToMany
    @JoinTable(name = "entity_entity2",
    joinColumns = @JoinColumn(name = "entity_id"),
    inverseJoinCOlumn = @JoinColumn(name = "entity2_id"))
    private List<Entity2> entities =  new ArrayList<>();
    \\code
}
~~~

## Incorporação
 É possível adicionar classe que se tornam complementos de outros com o @Embedded. 
Assim a classe que é incorporada não é uma entidade(não cria uma tabela no banco)
Mas cria os campos referente a class na tabela da entidade na qual a mesma foi 
incorporada.

~~~ java
@Data
@Embeddable
public class EntityEmbeddable {

    private Type attribute1;
    private Type attribute4;
    private Type attribute3;
}

public class Entity {


@Embedded
private EntityEmbeddable entityEmbeddable;
}


~~~

## Data

Uma explicação breve sobre como adicionar
dataCriação e Atualização
~~~java

 @Column(nullable = false)
    @CreationTimestamp //adiciona a data automaticamente ao criar entidade
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @UpdateTimestamp // sempre que passar por uma atualização na persistência atualiza a data
    private LocalDateTime dataAtualizacao;
~~~