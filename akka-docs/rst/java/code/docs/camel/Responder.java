package docs.camel;
//#CustomRoute
import akka.actor.UntypedActor;
import akka.camel.CamelMessage;
import akka.dispatch.Mapper;
import akka.japi.Function;

public class Responder extends UntypedActor{

  public void onReceive(Object message) {
    if (message instanceof CamelMessage) {
      CamelMessage camelMessage = (CamelMessage) message;
      getSender().tell(createResponse(camelMessage), getSelf());
    } else
      unhandled(message);
  }

  private CamelMessage createResponse(CamelMessage msg) {
    return msg.mapBody(new Mapper<String,String>() {
      @Override
      public String apply(String body) {
        return String.format("received %s", body);
      }
    });
  }
}
//#CustomRoute