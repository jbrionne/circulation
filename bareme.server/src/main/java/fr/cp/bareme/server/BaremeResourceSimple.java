package fr.cp.bareme.server;

import javax.inject.Inject;

import fr.cp.bareme.api.Bareme;
import fr.cp.bareme.api.BaremeResource;

public class BaremeResourceSimple implements BaremeResource {

    @Inject
    private BaremeProvider provider;
 
	@Override
	public Bareme getBareme(long id) {
		return provider.getBareme(id);
	}
}