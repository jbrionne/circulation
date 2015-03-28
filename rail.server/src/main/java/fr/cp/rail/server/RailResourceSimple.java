package fr.cp.rail.server;

import javax.inject.Inject;

import fr.cp.rail.api.Rail;
import fr.cp.rail.api.RailResource;

public class RailResourceSimple implements RailResource {

    @Inject
    private RailProvider provider;
 
	@Override
	public Rail getRail(long id) {
		return provider.getRail(id);
	}
}