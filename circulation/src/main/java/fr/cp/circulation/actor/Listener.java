package fr.cp.circulation.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.UntypedActor;
import fr.cp.circulation.message.ResultCalculate;

public class Listener extends UntypedActor {
	
	private final static Logger LOG = LoggerFactory
			.getLogger(Listener.class);

	
	public void onReceive(Object message) {		
		if (message instanceof ResultCalculate) {			
			ResultCalculate resultCalculate = (ResultCalculate) message;
				LOG.info("OK " + resultCalculate
						.getScore());
		} else {
			LOG.info("unhandled " + message);
			unhandled(message);
		}
		
	}
	
}
