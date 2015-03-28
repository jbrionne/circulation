package fr.cp.circulation.actor;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.util.Timeout;
import fr.cp.bareme.api.Bareme;
import fr.cp.circulation.message.Calculate;
import fr.cp.circulation.message.GetBareme;
import fr.cp.circulation.message.GetClient;
import fr.cp.circulation.message.GetTranslate;
import fr.cp.circulation.message.ResultCalculate;
import fr.cp.circulation.message.complex.MessageCirculation;
import fr.cp.circulation.message.complex.MessageCirculationAndTranslate;
import fr.cp.circulation.message.complex.SenderCirculation;
import fr.cp.client.api.Client;
import fr.cp.message.core.Message;
import fr.cp.message.core.Sender;
import fr.cp.reseau.api.GetReseau;
import fr.cp.reseau.api.Reseau;
import fr.cp.reseau.api.ResultGetReseau;
import fr.cp.translate.api.Translate;

public class CalculatorActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory
			.getLogger(CalculatorActor.class);
	
	private ActorRef baremeActor = getContext().actorOf(
			Props.create(BaremeActor.class), "bareme");		
	private ActorRef clientActor = getContext().actorOf(
			Props.create(ClientActor.class), "client");
	private ActorRef translateActor = getContext().actorOf(
			Props.create(TranslateActor.class), "translate");
	
	private static Cache cacheMessageCalculate = CacheManager.getInstance().getCache(
			"Calculate");

	private static Cache cacheMessageBareme = CacheManager.getInstance().getCache(
			"Bareme");
	private static Cache cacheMessageReseau = CacheManager.getInstance().getCache(
			"Reseau");
	private static Cache cacheMessageClient = CacheManager.getInstance().getCache(
			"Client");
	private static Cache cacheMessageTranslate = CacheManager.getInstance().getCache(
			"Translate");
	
	private ActorRef listener;
	
	private long idComment;
	private long idCirculationAndTranslate;


	public CalculatorActor(ActorRef listener) {
		this.listener = listener;
	}

	public void onReceive(Object message) {
		LOG.info("CalculatorActor onReceive");
		LOG.info("" + Thread.currentThread());

		if (message instanceof Calculate) {
			LOG.info("Calculate");

			Calculate calcul = (Calculate) message;
			
			Element eltCalculate = cacheMessageCalculate.get(getCalculateId(calcul));
			if (eltCalculate == null) {	
				LOG.info("Create calculate element");
				
				Element eltBareme = cacheMessageBareme.get(String.valueOf(calcul.getIdBareme()));
				
				if (eltBareme == null) {
					Message<Bareme> messageBareme = new Message<>(String.valueOf(calcul.getIdBareme()));
					eltBareme = new Element(messageBareme.getId(), messageBareme);				
					cacheMessageBareme.put(eltBareme);
					baremeActor.tell(new GetBareme(calcul.getIdBareme()), getSelf());				
				}
				
				Element eltClient = cacheMessageClient.get(String.valueOf(calcul.getIdClient()));	
				if (eltClient == null) {				
					Message<Client> messageClient = new Message<>(String.valueOf(calcul.getIdClient()));
					eltClient = new Element(messageClient.getId(), messageClient);				
					cacheMessageClient.put(eltClient);				
					clientActor
							.tell(new GetClient(calcul.getIdClient()), getSelf());
				}
	
				Element eltReseau = cacheMessageReseau.get(String.valueOf(calcul.getIdReseau()));
				if (eltReseau == null) {				
					Message<Reseau> messageReseau = new Message<>(String.valueOf(calcul.getIdReseau()));
														
					eltReseau = new Element(messageReseau.getId(), messageReseau);				
					cacheMessageReseau.put(eltReseau);	
					
					LOG.info("CALL RESEAU");
					String actor = "akka.tcp://reseauSystem@127.0.0.1:2552/user/reseau";
					
					ActorRef actS = null;
					try {
						actS = Await.result(getContext().actorSelection(actor).resolveOne(Timeout.intToTimeout(1000)), Duration.Inf());
					} catch (Exception e) {
						LOG.info("ActorRef " + actor, e);
					}
					
					if(actS == null) {
						LOG.error(actor + " is null");
						throw new NullPointerException(actor + " is null");
					}
					LOG.info("GetReseau {}", calcul.getIdReseau() + " " + actS);
					String[] s = calcul.getIdReseau().split("_");
					actS.tell(new GetReseau(Long.valueOf(s[0]), Long.valueOf(s[1])), getSelf());					
					
					LOG.info("END TELL CALL RESEAU");
				}
				
				
				long nextId = idComment++;
				Message<Translate> messageTranslate = new Message<>(String.valueOf(nextId));
				Element eltTranslate = new Element(messageTranslate.getId(), messageTranslate);				
				cacheMessageTranslate.put(eltTranslate);				
				translateActor
				.tell(new GetTranslate(nextId, calcul.getComment()), getSelf());			
				
				MessageCirculationAndTranslate<ResultCalculate> messageCirculationCalculate = new MessageCirculationAndTranslate<>(String.valueOf(idCirculationAndTranslate++));
				
				MessageCirculation<ResultCalculate> messageCalculate = new MessageCirculation<>(getCalculateId(calcul));
				messageCalculate.addsubMessages((Message<Bareme>) eltBareme.getObjectValue());
				messageCalculate.addsubMessages((Message<Client>) eltClient.getObjectValue());
				messageCalculate.addsubMessages((Message<ResultGetReseau>) eltReseau.getObjectValue());
				
				messageCirculationCalculate.addsubMessages(messageCalculate);
				messageCirculationCalculate.addsubMessages(messageTranslate);				
				
				
				Sender senderCirc = new SenderCirculation(listener);				
				messageCirculationCalculate.addSender(senderCirc);
				
				eltCalculate = new Element(messageCalculate.getId(), messageCalculate);				
				cacheMessageCalculate.put(eltCalculate);	
			} else {
				
				LOG.info("calculate element already exist");
				
				//We have already the request..and may be the answer
				MessageCirculation<ResultCalculate> messageCalculate = (MessageCirculation<ResultCalculate>) eltCalculate.getObjectValue();
				
				long nextId = idComment++;
				Message<Translate> messageTranslate = new Message<>(String.valueOf(nextId));
				Element eltTranslate = new Element(messageTranslate.getId(), messageTranslate);				
				cacheMessageTranslate.put(eltTranslate);				
				translateActor
				.tell(new GetTranslate(nextId, calcul.getComment()), getSelf());
				
				MessageCirculationAndTranslate<ResultCalculate> messageCirculationCalculate = new MessageCirculationAndTranslate<>(String.valueOf(idCirculationAndTranslate++));
				messageCirculationCalculate.addsubMessages(messageCalculate);
				messageCirculationCalculate.addsubMessages(messageTranslate);
				
				Sender senderCirc = new SenderCirculation(listener);				
				messageCirculationCalculate.addSender(senderCirc);			

			}	
			
			LOG.info("continue .. CalculatorActor onReceive");
		} else if (message instanceof ResultGetReseau) {	
			LOG.info("*********ResultGetReseau");
			ResultGetReseau reseau = (ResultGetReseau) message;
			
					
			Element eltReseau = cacheMessageReseau.get(String.valueOf(reseau.getId()));
			if(eltReseau == null) {
				throw new AssertionError("There must be a message reseau with id " + reseau.getId());
			}
			
			Message<ResultGetReseau> messageReseau = (Message<ResultGetReseau>) eltReseau.getObjectValue();
			messageReseau.giveResponse(reseau);				
		} 
		
		else if (message instanceof Bareme) {

			LOG.info("Bareme");
			Bareme bareme = (Bareme) message;			
			Element eltBareme = cacheMessageBareme.get(String.valueOf(bareme.getId()));
			if(eltBareme == null) {
				throw new AssertionError("There must be a message bareme with id " + bareme.getId());
			}
			
			Message<Bareme> messageBareme = (Message<Bareme>) eltBareme.getObjectValue();
			messageBareme.giveResponse(bareme);			

		} else if (message instanceof Client) {

			LOG.info("Client");
			Client client = (Client) message;			
			Element eltClient = cacheMessageClient.get(String.valueOf(client.getId()));
			if(eltClient == null) {
				throw new AssertionError("There must be a message client with id " + client.getId());
			}
			
			Message<Client> messageClient = (Message<Client>) eltClient.getObjectValue();
			messageClient.giveResponse(client);				
		

		} else if (message instanceof Translate) {
			
			LOG.info("Translate");
			Translate translate = (Translate) message;			
			Element eltTranslate = cacheMessageTranslate.get(String.valueOf(translate.getId()));
			if(eltTranslate == null) {
				throw new AssertionError("There must be a message translate with id " + translate.getId());
			}
			
			Message<Translate> messageTranslate = (Message<Translate>) eltTranslate.getObjectValue();
			messageTranslate.giveResponse(translate);
			
			cacheMessageTranslate.remove(translate.getId());

		} else {
			unhandled(message);
		}

	}
	
	private String getCalculateId(Calculate calcul){
		return calcul.getIdBareme() + "_" + calcul.getIdReseau() + "_" + calcul.getIdClient();
	}

}
