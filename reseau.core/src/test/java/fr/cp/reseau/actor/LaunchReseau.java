package fr.cp.reseau.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import fr.cp.reseau.api.GetReseau;

public class LaunchReseau {

	final static ActorSystem SYSTEM = ActorSystem.create("reseauSystem");

	private final static Logger LOG = LoggerFactory
			.getLogger(LaunchReseau.class);	
	
	public static void main(String[] args) throws Exception {		
		//final ExecutionContext ex = SYSTEM.dispatchers().lookup("my-dispatcher2");
		LOG.info("" + Thread.currentThread());			
		
//		ActorRef listener = SYSTEM.actorOf(Props.create(ListenerReseau.class),
//				"listener");
		
		ActorRef listener = null;
		
		SYSTEM.actorOf(
				Props.create(ReseauActor.class, listener), "reseau");			
		
		ActorRef act = Await.result(SYSTEM.actorSelection("user/reseau").resolveOne(Timeout.intToTimeout(1000)), Duration.Inf());
				
		//act.tell(new GetReseau(1, 1), ActorRef.noSender());	
		
		LOG.info(act.toString());	
			
	}

}
