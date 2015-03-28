package fr.cp.circulation.actor;


import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import fr.cp.circulation.message.GetClient;
import fr.cp.client.api.Client;
import fr.cp.client.call.CallForClient;
import fr.cp.client.call.ClientService;

public class ClientActor extends UntypedActor {

	private final static Logger LOG = LoggerFactory.getLogger(ClientActor.class);		
	
	private static 	ClientService clientService = new CallForClient();				
	
	public void onReceive(Object message) {		
		if (message instanceof GetClient) {
			final ExecutionContext ex = getContext().system().dispatchers()
					.lookup("my-dispatcher");
			LOG.info("" + Thread.currentThread());
			final ActorRef send = getSender();
			final ActorRef self = getSelf();
			final GetClient getClient = (GetClient) message;
			
			Future<Void> f = future(new Callable<Void>() {
				public Void call() {
					LOG.info("GetClient");
					LOG.info("" + Thread.currentThread());						
					clientService.getClient(
					new InvocationCallback<Response>() {
						@Override
						public void completed(Response response) {
							Client client = null;
							try {
								client = response.readEntity(Client.class);
							} catch (Exception e) {
								LOG.error("onReceive", e);
							}							
							send.tell(client, self);
							LOG.info("GetClient ok");						
						}

						@Override
						public void failed(Throwable throwable) {
							LOG.error("Invocation failed.", throwable);
						}
					}, getClient.getId());
					return null;
				}
			}, ex);			
		} else {
			unhandled(message);
		}
	}


}
