package fr.cp.reseau.actor;


import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import fr.cp.rail.api.Rail;
import fr.cp.rail.call.CallForRail;
import fr.cp.rail.call.RailService;
import fr.cp.reseau.message.GetRail;


public class RailActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory.getLogger(RailActor.class);		
	
	private static 	RailService railService = new CallForRail();	
	
	public RailActor(){
		LOG.info("RailActor " + Thread.currentThread());		
	}
	
	
	public void onReceive(Object message) {		
		if (message instanceof GetRail) {
			final ExecutionContext ex = getContext().system().dispatchers()
					.lookup("my-dispatcher2");
			LOG.info("" + Thread.currentThread());
			final ActorRef send = getSender();
			final ActorRef self = getSelf();
			final GetRail getRail = (GetRail) message;
			
			Future<Void> f = future(new Callable<Void>() {
				public Void call() {
					LOG.info("GetRail");
					LOG.info("" + Thread.currentThread());						
					Rail rail = railService.getRail(getRail.getId());	
					send.tell(rail, self);
					LOG.info("GetRail ok");
					return null;
				}
			}, ex);			
		} else {
			unhandled(message);
		}
	}


}
