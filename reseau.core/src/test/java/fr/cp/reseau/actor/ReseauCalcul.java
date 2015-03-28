package fr.cp.reseau.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fr.cp.reseau.api.GetReseau;

public class ReseauCalcul {

	private final static ActorSystem SYSTEM = ActorSystem
			.create("reseauSystem");

	private final static Logger LOG = LoggerFactory
			.getLogger(ReseauCalcul.class);

	private static final long NUMBERCYCLE = 20;

	public static void main(String[] args) throws InterruptedException {

		LOG.info("" + Thread.currentThread());

		System.gc();
		System.gc();
		System.gc();
		System.gc();
		Thread.sleep(2000);

		final ActorRef listener = SYSTEM.actorOf(
				Props.create(ListenerReseau.class), "listener");

		final ActorRef reseauActorRef = SYSTEM.actorOf(
				Props.create(ReseauActor.class, listener), "reseau");

		for (int i = 0; i < NUMBERCYCLE; i++) {
			reseauActorRef.tell(new GetReseau(1, 1), ActorRef.noSender());
		}

		LOG.info("end main -- continue");
		LOG.info("do something else");
	}

}
