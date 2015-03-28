package fr.cp.circulation.actor;

import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import fr.cp.circulation.message.GetTranslate;
import fr.cp.translate.api.Translate;
import fr.cp.translate.call.CallForTranslate;
import fr.cp.translate.call.TranslateService;

public class TranslateActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory.getLogger(TranslateActor.class);
	
	private static TranslateService translateService = new CallForTranslate();

	public void onReceive(Object message) {
		if (message instanceof GetTranslate) {

			final ExecutionContext ex = getContext().system().dispatchers()
					.lookup("my-dispatcher");
			LOG.info("" + Thread.currentThread());
			final ActorRef send = getSender();
			final ActorRef self = getSelf();				
			final GetTranslate getTranslate = (GetTranslate) message;
			
			final long getTranslateId = getTranslate.getId();
			
			Future<Void> f = future(new Callable<Void>() {
				public Void call() {
					LOG.info("GetTranslate");
					LOG.info("" + Thread.currentThread());
					Translate translate = translateService.getTranslate(getTranslate.getComment());
					translate.setId(getTranslateId);
					send.tell(translate, self);
					LOG.info("GetTranslate ok");
					return null;					
				}
			}, ex);				
		} else {
			unhandled(message);
		}
	}

	

}
