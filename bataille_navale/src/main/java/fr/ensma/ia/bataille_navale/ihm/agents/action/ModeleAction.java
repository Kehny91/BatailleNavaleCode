package fr.ensma.ia.bataille_navale.ihm.agents.action;

import java.util.EnumMap;

import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;

public class ModeleAction {
	private EnumMap<EAction, Boolean> avaiblables;
	
	
	public ModeleAction() {
		avaiblables = new EnumMap<EAction, Boolean>(EAction.class);
		for (EAction action : EAction.values()) {
			if (action!=EAction.Explosion) {
				avaiblables.put(action, false);
			}
		}
	}
	
	public Boolean isAvaiblable(EAction action) {
		return avaiblables.get(action);
	}
	
	public void setAvailable(EAction action, Boolean available) {
		avaiblables.replace(action, available);
	}
}
