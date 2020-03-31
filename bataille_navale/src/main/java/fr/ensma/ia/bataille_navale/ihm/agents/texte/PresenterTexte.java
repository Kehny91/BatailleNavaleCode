package fr.ensma.ia.bataille_navale.ihm.agents.texte;

import fr.ensma.ia.bataille_navale.ihm.agents.ExceptionNoVueSet;
import javafx.application.Platform;

public class PresenterTexte {
	private IVueTexte vue;
	private ModeleTexte model;
	
	public void setVue(IVueTexte vue){
		this.vue = vue;
	}
	
	public IVueTexte getVue() {
		return vue;
	}
	
	public PresenterTexte() {
		model = new ModeleTexte();
	}
	
	public void setText(String s) throws ExceptionNoVueSet {
		if (vue==null)
			throw new ExceptionNoVueSet();
		model.setText(s);
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				vue.afficheText(s);
			}
		});
		
	}
	
	public void clean() throws ExceptionNoVueSet {
		if (vue==null)
			throw new ExceptionNoVueSet();
		model.setText("");
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				vue.clean();
			}
		});
		
	}
}
