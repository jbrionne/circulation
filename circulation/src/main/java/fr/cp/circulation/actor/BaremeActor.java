package fr.cp.circulation.actor;

import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import fr.cp.bareme.api.Bareme;
import fr.cp.bareme.call.BaremeService;
import fr.cp.bareme.call.CallForBareme;
import fr.cp.circulation.message.GetBareme;

public class BaremeActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory
			.getLogger(BaremeActor.class);

	private static BaremeService baremeService = new CallForBareme();

	public void onReceive(Object message) {
		if (message instanceof GetBareme) {

			final ExecutionContext ex = getContext().system().dispatchers()
					.lookup("my-dispatcher");
			LOG.info("" + Thread.currentThread());
			final ActorRef send = getSender();
			final ActorRef self = getSelf();
			final GetBareme getBareme = (GetBareme) message;

			Future<Void> f = future(new Callable<Void>() {
				public Void call() {
					LOG.info("GetBareme");
					LOG.info("" + Thread.currentThread());
					Bareme bareme = baremeService.getBareme(getBareme.getId());
					send.tell(bareme, self);
					LOG.info("GetBareme ok");
					return null;
				}
			}, ex);
		} else {
			unhandled(message);
		}
	}

}
