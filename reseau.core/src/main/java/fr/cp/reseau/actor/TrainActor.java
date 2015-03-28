package fr.cp.reseau.actor;


import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import fr.cp.reseau.message.GetTrain;
import fr.cp.train.api.Train;
import fr.cp.train.call.CallForTrain;
import fr.cp.train.call.TrainService;


public class TrainActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory.getLogger(TrainActor.class);		
	
	private static 	TrainService trainService = new CallForTrain();				
	
	public TrainActor(){
		LOG.info("TrainActor " + Thread.currentThread());		
	}
	
	public void onReceive(Object message) {		
		if (message instanceof GetTrain) {
			final ExecutionContext ex = getContext().system().dispatchers()
					.lookup("my-dispatcher2");
			LOG.info("" + Thread.currentThread());
			final ActorRef send = getSender();
			final ActorRef self = getSelf();
			final GetTrain getTrain = (GetTrain) message;
			
			Future<Void> f = future(new Callable<Void>() {
				public Void call() {
					LOG.info("GetTrain");
					LOG.info("" + Thread.currentThread());						
					Train train = trainService.getTrain(getTrain.getId());	
					send.tell(train, self);
					LOG.info("GetTrain ok");
					return null;
				}
			}, ex);			
		} else {
			unhandled(message);
		}
	}


}
