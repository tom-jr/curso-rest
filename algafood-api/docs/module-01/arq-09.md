# Eventos customizados

Um designer pattern que ajuda a deixar o acoplamento mais baixo entre as classes. Chamado de 
EventHandler.

Com a instancia de ApplicationEventPublisher publicamos um evento ja instanciando a classe que 
recebe os argumentos deste evento e trata de acordo com sua logica.
E apos o evento ser publicado a classe ouvinte dispara sua logica, pois estava como ouvinte 
aguardando que esse evento ser publicado.

~~~java
@Component
public class ClassBean{

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public voi AnyMethod(){
        //code
        this.eventPublisher.publishEvent(new EventCustom(argsFromAnymethod));
    }
}
    public class EventCustom{
        public EventCustom(ArgsFromAnymethod argsFromAnymethod){
            //code
        }
    }


@Component
    public class ListenerOfEvent(){

        @EventListener
        public void methodListener(EventCustom eventCustom){
            //code
        }
    }
~~~