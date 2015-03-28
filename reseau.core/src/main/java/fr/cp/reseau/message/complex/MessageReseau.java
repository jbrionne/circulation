package fr.cp.reseau.message.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import fr.cp.message.core.Message;
import fr.cp.message.core.Sender;
import fr.cp.rail.api.Rail;
import fr.cp.reseau.api.ResultGetReseau;
import fr.cp.train.api.Train;



public class MessageReseau<T> extends Message<T> {
	
	private final static Logger LOG = LoggerFactory
			.getLogger(MessageReseau.class);
	
	public MessageReseau(String id) {
		super(id);
	}

	public void checkResponse(){
		boolean res = isAllComplete();
		if(res) {			
			Train resultTrain = null;
			Rail resultRail = null;
			
			for(Message subMessage : getSubMessages()){
				if(subMessage.getResponse() instanceof Train) {
					resultTrain = (Train) subMessage.getResponse();
				} else if(subMessage.getResponse() instanceof Rail) {
					resultRail = (Rail) subMessage.getResponse();
				} 
			}
			
			//We can do the calcul
			ResultGetReseau resultCalculate = new ResultGetReseau(resultRail.getId() + "_"
					+ resultTrain.getId(),
					resultRail.getName() + " "
							+ resultTrain.getName());
			
			setComplete(true);
			setResponse((T) resultCalculate);
			for(Message parent : getParents()){				
				parent.checkResponse();			
			}	
			
			LOG.info("getSenders().size " + getSenders().size());
			for(Sender sender : getSenders()){
				SenderReseau senderCir = (SenderReseau) sender;
				senderCir.getActorRef().tell(resultCalculate, ActorRef.noSender());				
			}			
		}
	}
	
}
