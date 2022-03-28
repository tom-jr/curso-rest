# Pool

 Normalmente em uma requisição Rest a conexão do banco de dados é aberta para
 cada requisição feita e logo apos ser realizada a mesma conexão se fecha.
Sendo assim a cada nova requisição é realizada uma nova conexão que se encerra
apos a transação
 O Pool trabalha de tal modo que para evitar que a conexão seja aberta cada
vez que se recebe uma requisição. Assim ele tem uma tarefa de manter conexões
ja abertas em um estado de ***listener*** para que economize recursos precisos
que fazem toda a diferença em um sistema Web de grande escala.

 O Pool pode gerenciar quantidades de conexões maxima no sistema, quantidade de
listener conectado e o tempo que novas conexões podem ficar como apoio aos 
listeners ja existentes

## Hikari

Hikari é uma dependência que é adicionada no pacote de dependências do started
JPA. Ele faz as conexões defaults do Pool para a API.

### Como verificar as conexões ativas pelo Hikari no Pgadmin4

1. Abrimos o pgadmin4 e selecionamos o banco de dados da API

![pg1](/algafood-api/docs/resources/img/ex_pgadmin_01.png)

2. O dashboard é apresentado assim que clicamos então na sessão de Server 
activity na aba Sessions vemos todas as conexões abertas pelo Hikari.
Notar que apenas uma esta em uso, que se trata da sessão do pgadmin4. As demais
estão em status de ***idle***, que significa que estão em espera para serem 
aproveitada para qualquer requisição que vinhe.

![pg1](/algafood-api/docs/resources/img/ex_pgadmin_02.png)


 Podemos utilizar o comando do apache serve para realizar múltiplas requisições
 ~~~ bash
 ab -n 6000 -c 2 localhost:8080/restaurantes
 ~~~
 Assim vemos as conexões mudar de ***idle*** para idle-in-transaction

![pg1](/algafood-api/docs/resources/img/ex_pgadmin_03.png)



### Configurar o Hikari 

No arquivo de configuração ***application.properties*** adicionamos as 
configurações do Hikari

~~~ profiles
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=3
spring.datasource.hikari.idle-timeout=10000
~~~

- ***maximum-pool-size:*** máximo de conexões que permitirá, caso não adicionar o min
o Hikari configura o valor do máximo também como o mínimo. O default do máximo é 10.
- ***minimum-pool-size:*** mínimo de pools inicial.
- ***idle-timeout:*** tempo que os pools adicionais ficam ainda ativo apos o fim de 
seu uso.
