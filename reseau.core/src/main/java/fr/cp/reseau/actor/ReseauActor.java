package fr.cp.reseau.actor;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import fr.cp.message.core.Message;
import fr.cp.message.core.Sender;
import fr.cp.rail.api.Rail;
import fr.cp.reseau.api.GetReseau;
import fr.cp.reseau.api.ResultGetReseau;
import fr.cp.reseau.message.GetRail;
import fr.cp.reseau.message.GetTrain;
import fr.cp.reseau.message.complex.MessageReseau;
import fr.cp.reseau.message.complex.SenderReseau;
import fr.cp.train.api.Train;

public class ReseauActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory
			.getLogger(ReseauActor.class);

	private ActorRef trainActor = getContext().actorOf(
			Props.create(TrainActor.class), "train");
	private ActorRef railActor = getContext().actorOf(
			Props.create(RailActor.class), "rail");

	private static Cache cacheMessageGetReseau = CacheManager.getInstance()
			.getCache("GetReseau");

	private static Cache cacheMessageTrain = CacheManager.getInstance()
			.getCache("Train");
	private static Cache cacheMessageRail = CacheManager.getInstance()
			.getCache("Rail");

	private ActorRef listener;

	public ReseauActor(ActorRef listener) {
		LOG.info("ReseauActor " + Thread.currentThread());
		this.listener = listener;
	}

	public void onReceive(Object message) {

		LOG.info("ReseauActor onReceive");
		LOG.info("" + Thread.currentThread());
		LOG.info("" + self().path());

		if (message instanceof GetReseau) {
			LOG.info("GetReseau");

			GetReseau calcul = (GetReseau) message;

			Element eltCalculate = cacheMessageGetReseau
					.get(getReseauId(calcul));
			if (eltCalculate == null) {
				LOG.info("Create calculate GetReseau element");

				Element eltTrain = cacheMessageTrain.get(calcul.getIdTrain());
				if (eltTrain == null) {
					Message<Train> messageTrain = new Message<>(
							String.valueOf(calcul.getIdTrain()));
					eltTrain = new Element(messageTrain.getId(), messageTrain);
					cacheMessageTrain.put(eltTrain);
					trainActor.tell(new GetTrain(calcul.getIdTrain()),
							getSelf());
				}

				Element eltRail = cacheMessageRail.get(calcul.getIdRail());
				if (eltRail == null) {
					Message<Rail> messageRail = new Message<>(
							String.valueOf(calcul.getIdRail()));
					eltRail = new Element(messageRail.getId(), messageRail);
					cacheMessageRail.put(eltRail);
					railActor.tell(new GetRail(calcul.getIdRail()), getSelf());
				}

				MessageReseau<ResultGetReseau> messageCalculate = new MessageReseau<>(
						getReseauId(calcul));

				messageCalculate.addsubMessages((Message<Train>) eltTrain
						.getObjectValue());
				messageCalculate.addsubMessages((Message<Rail>) eltRail
						.getObjectValue());

				
				Sender senderCirc = null;
				if (listener == null) {
					senderCirc = new SenderReseau(getSender());
				} else {
					senderCirc = new SenderReseau(listener);
				}
				messageCalculate.addSender(senderCirc);

				eltCalculate = new Element(messageCalculate.getId(),
						messageCalculate);
				cacheMessageGetReseau.put(eltCalculate);
			} else {

				LOG.info("calculate reseau element already exist");

				// We have already the request..and may be the answer
				MessageReseau<ResultGetReseau> messageCalculate = (MessageReseau<ResultGetReseau>) eltCalculate
						.getObjectValue();

				if (messageCalculate.isComplete()) {
					LOG.info("calculate reseau isCompleted()");

					if (listener == null) {
						getSender().tell(messageCalculate.getResponse(),
								getSelf());
					} else {
						listener.tell(messageCalculate.getResponse(), getSelf());
					}
				} else {
					LOG.info("calculate reseau not isCompleted()");
					Sender senderCirc = null;
					if (listener == null) {
						senderCirc = new SenderReseau(getSender());
					} else {
						senderCirc = new SenderReseau(listener);
					}

					messageCalculate.addSender(senderCirc);
				}
			}

			LOG.info("continue .. ReseauActor onReceive");
		} else if (message instanceof Train) {

			LOG.info("Train");
			Train train = (Train) message;
			Element eltTrain = cacheMessageTrain.get(String.valueOf(train
					.getId()));
			if (eltTrain == null) {
				throw new AssertionError(
						"There must be a message train with id "
								+ train.getId());
			}

			Message<Train> messageTrain = (Message<Train>) eltTrain
					.getObjectValue();
			messageTrain.giveResponse(train);

		} else if (message instanceof Rail) {

			LOG.info("Rail");
			Rail rail = (Rail) message;
			Element eltRail = cacheMessageRail
					.get(String.valueOf(rail.getId()));
			if (eltRail == null) {
				throw new AssertionError(
						"There must be a message rail with id " + rail.getId());
			}

			Message<Rail> messageRail = (Message<Rail>) eltRail
					.getObjectValue();
			messageRail.giveResponse(rail);

		} else {
			unhandled(message);
		}

	}

	private String getReseauId(GetReseau calcul) {
		return calcul.getIdTrain() + "_" + calcul.getIdRail();
	}

}
