package fr.cp.circulation.message.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import fr.cp.circulation.message.ResultCalculate;
import fr.cp.message.core.Message;
import fr.cp.message.core.Sender;
import fr.cp.translate.api.Translate;



public class MessageCirculationAndTranslate<T> extends Message<T> {
	
	private final static Logger LOG = LoggerFactory
			.getLogger(MessageCirculationAndTranslate.class);
	
	public MessageCirculationAndTranslate(String id) {
		super(id);
	}

	public void checkResponse(){
		
		boolean res = isAllComplete();
		LOG.info("checkResponse " + res);
		if(res) {			
			ResultCalculate resultResultCalculate = null;
			Translate resultTranslate = null;
			
			for(Message subMessage : getSubMessages()){
				if(subMessage.getResponse() instanceof ResultCalculate) {
					resultResultCalculate = (ResultCalculate) subMessage.getResponse();
				} else if(subMessage.getResponse() instanceof Translate) {
					resultTranslate = (Translate) subMessage.getResponse();
				}
			}
			
			ResultCalculate resultCalculate = new ResultCalculate(resultResultCalculate.getScore() + " " + resultTranslate.getComment());
			
			setComplete(true);
			setResponse((T) resultCalculate);
			for(Message parent : getParents()){				
				parent.checkResponse();			
			}	
			
			LOG.info("getSenders().size " + getSenders().size());
			for(Sender sender : getSenders()){
				SenderCirculation senderCir = (SenderCirculation) sender;
				senderCir.getActorRef().tell(resultCalculate, ActorRef.noSender());				
			}			
		}
	}
	
}
