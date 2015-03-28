package fr.cp.reseau.message.complex;

import akka.actor.ActorRef;
import fr.cp.message.core.Sender;

public class SenderReseau implements Sender {

	private ActorRef actorRef;

	public SenderReseau(ActorRef actorRef) {
		super();
		this.actorRef = actorRef;
	}


	public ActorRef getActorRef() {
		return actorRef;
	}	
	
}
