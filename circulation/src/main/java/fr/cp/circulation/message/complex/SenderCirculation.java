package fr.cp.circulation.message.complex;

import akka.actor.ActorRef;
import fr.cp.message.core.Sender;

public class SenderCirculation implements Sender {

	private ActorRef actorRef;

	public SenderCirculation(ActorRef actorRef) {
		super();
		this.actorRef = actorRef;
	}


	public ActorRef getActorRef() {
		return actorRef;
	}	
	
}
