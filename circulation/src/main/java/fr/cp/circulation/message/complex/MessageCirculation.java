package fr.cp.circulation.message.complex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.cp.bareme.api.Bareme;
import fr.cp.circulation.message.ResultCalculate;
import fr.cp.client.api.Client;
import fr.cp.message.core.Message;
import fr.cp.reseau.api.Reseau;
import fr.cp.reseau.api.ResultGetReseau;



public class MessageCirculation<T> extends Message<T> {
	
	private final static Logger LOG = LoggerFactory
			.getLogger(MessageCirculation.class);
	
	public MessageCirculation(String id) {
		super(id);
	}

	public void checkResponse(){
		boolean res = isAllComplete();
		LOG.info("MessageCirculation checkResponse " + res);
		if(res) {	
			LOG.info("*************** " + res);
			Bareme resultBareme = null;
			Client resultClient = null;
			ResultGetReseau resultReseau = null;
			
			for(Message subMessage : getSubMessages()){
				if(subMessage.getResponse() instanceof Bareme) {
					resultBareme = (Bareme) subMessage.getResponse();
				} else if(subMessage.getResponse() instanceof Client) {
					resultClient = (Client) subMessage.getResponse();
				} else if(subMessage.getResponse() instanceof ResultGetReseau) {
					resultReseau = (ResultGetReseau) subMessage.getResponse();
				}
			}
			
			ResultCalculate resultCalculate = new ResultCalculate(
					resultClient.getFirstname() + " "
							+ resultBareme.getCost() + " "
							+ resultReseau.getScore());
			
			setComplete(true);
			setResponse((T) resultCalculate);
			for(Message parent : getParents()){				
				parent.checkResponse();			
			}		
		}
	}
	
}
