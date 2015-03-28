package fr.cp.circulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fr.cp.circulation.actor.CalculatorActor;
import fr.cp.circulation.actor.Listener;
import fr.cp.circulation.message.Calculate;

public class Circulation {

	private final static ActorSystem SYSTEM = ActorSystem.create("circulation");

	private final static Logger LOG = LoggerFactory
			.getLogger(Circulation.class);

	private static final long NUMBERCYCLE = 100;

	public static void main(String[] args) throws InterruptedException {
		LOG.info("" + Thread.currentThread());

		System.gc();
		System.gc();
		System.gc();
		System.gc();
		Thread.sleep(2000);

		final ActorRef listener = SYSTEM.actorOf(Props.create(Listener.class),
				"listener");

		final ActorRef circulationActorRef = SYSTEM.actorOf(
				Props.create(CalculatorActor.class, listener), "calculator");

		for (int i = 0; i < NUMBERCYCLE; i++) {
			circulationActorRef.tell(new Calculate(1, "1_1", 1 , "comment" + i),
					ActorRef.noSender());
		}

		LOG.info("end main -- continue");
		LOG.info("do something else");
	}

}
