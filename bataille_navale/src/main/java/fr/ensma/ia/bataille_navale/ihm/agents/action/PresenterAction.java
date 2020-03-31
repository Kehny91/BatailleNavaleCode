package fr.ensma.ia.bataille_navale.ihm.agents.action;

import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import fr.ensma.ia.bataille_navale.outilsMultithread.MDD;

public class PresenterAction {
	MDD<EAction> actionChoisie;
	ModeleAction model;
	IVueAction vue;
	
	public PresenterAction() {
		actionChoisie = new MDD<EAction>();
		model = new ModeleAction();
	}

	public void handleChoix(EAction action) {
		actionChoisie.pushValue(action);
	}
	
	public EAction demandeAction() throws InterruptedException {
		return actionChoisie.waitNewValue();
	}
	
	public void setAvailable(EAction action, boolean available) {
		model.setAvailable(action, available);
		if (vue != null)
			vue.setAvailable(action, available);
	}
	
	/*
	 * Donne une base des availabilités
	 */
	public void setAvailable(List<EAction> available) {
		//Par défaut, tout le monde est disable sauf fin de tour
		for (EAction action : EAction.values()) {
			if (action != EAction.Explosion)
			{
				setAvailable(action, false);
				if (action == EAction.FinDeTour)
					setAvailable(action,true);
			}
		}
		
		for (EAction action : available) {
			setAvailable(action,true);
		}
	}
	
	public void updateView() {
		for (EAction action : EAction.values()) {
			if (action != EAction.Explosion) {
				vue.setAvailable(action, model.isAvaiblable(action));
			}
		}
	}
	
	public void setVue(IVueAction vue) {
		this.vue = vue;
		updateView();
	}

}
