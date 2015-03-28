package fr.cp.message.core;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Message<T> {
	
	private final static Logger log = LoggerFactory
			.getLogger(Message.class);

	private boolean complete = false;;	
	
	private String id;
	
	private Set<Message> parents = new HashSet<>();	
	private Set<Message> subMessages = new HashSet<>();	
	private Set<Sender> senders = new HashSet<>();
	
	private T response;
	
	
	public Message(String id){
		this.id = id;
	}

	
	public void addsubMessages(Message message){
		message.getParents().add(this);
		subMessages.add(message);
	}
	
	
	public boolean addSender(Sender sender){
		return senders.add(sender);
	}
	
	public void checkResponse(){
		log.error("ERROR : checkResponse");
		throw new AssertionError("checkResponse");
	}
	
	public boolean isAllComplete(){
		for(Message subMessage : subMessages){
			if(!(subMessage.isComplete())) {
				return false; 
			} 
		}
		return true;
	}
	
	public void giveResponse(T response){
		this.response = response;
		this.complete = true;
		for(Message parent : parents){				
			parent.checkResponse();			
		}		
	}
	

	public String getId() {
		return id;
	}	


	public Set<Message> getParents() {
		return parents;
	}


	public T getResponse() {
		return response;
	}

	public boolean isComplete() {
		return complete;
	}


	public Set<Message> getSubMessages() {
		return subMessages;
	}


	public void setComplete(boolean complete) {
		this.complete = complete;
	}


	public void setParents(Set<Message> parents) {
		this.parents = parents;
	}


	public void setResponse(T response) {
		this.response = response;
	}


	public Set<Sender> getSenders() {
		return senders;
	}
	
}
