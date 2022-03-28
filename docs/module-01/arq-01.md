# Um pouco sobre o Maven
 Logo apos criar o projeto uma estrutura de diretórios é criada na raiz, pode se
usar o arquivo mvn para rodar os comando maven de empacotamento, build e etc.

  comando_ 
~~~bash 
  ./mvn {comand_maven}

~~~
  impacota o projeto em um jar do diretorio_ [target]
    
~~~ bash
    ./mvn package
~~~  

  Apos a geração do Jar pode-se rodar o mesmo com o seguinte comando

~~~ bash
java -jar diretorio/arquivo.jar}
~~~

  Limpa os builds Jars que existiren em target

~~~ bash
./mvn clean
~~~