package fr.cp.reseau.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.UntypedActor;
import fr.cp.reseau.api.ResultGetReseau;

public class ListenerReseau extends UntypedActor {
	
	private final static Logger LOG = LoggerFactory
			.getLogger(ListenerReseau.class);

	
	public void onReceive(Object message) {		
		if (message instanceof ResultGetReseau) {			
			ResultGetReseau resultCalculate = (ResultGetReseau) message;
			if (!"rail 1 train 1".equals(resultCalculate
					.getScore())) {
				LOG.info("Error : score invalid "
						+ resultCalculate.getScore());
				LOG.info("END ERROR");
			} else {
				LOG.info("OK " + resultCalculate
						.getScore());
			}
		} else {
			unhandled(message);
		}
		
	}
	
}
