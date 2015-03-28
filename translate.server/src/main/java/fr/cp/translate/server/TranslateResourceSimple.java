package fr.cp.translate.server;

import javax.inject.Inject;

import fr.cp.translate.api.Translate;
import fr.cp.translate.api.TranslateResource;

public class TranslateResourceSimple implements TranslateResource {

    @Inject
    private TranslateProvider provider;
 
	@Override
	public Translate getTranslate(String comment) {
		return provider.getTranslate(comment);
	}
}