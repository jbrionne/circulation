package fr.cp.translate.server;

import javax.inject.Singleton;

import fr.cp.translate.api.Translate;

@Singleton
public class TranslateProvider {

	public Translate getTranslate(String comment){
		return 	new Translate("Tr:" + comment);
	}
}
