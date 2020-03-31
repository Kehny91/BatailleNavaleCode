package fr.ensma.ia.bataille_navale.noyau.actions.deplacements;


import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public class Rotation extends DeplacementAbs{
	private Case pivot;
	private boolean sensTrigo;
	
	@Override
	public Resultat doAction() throws ExceptionBadInput {
		for (ElementBateau el : bateauADeplacer.getElementsBateau()) {
			Case depart = el.getCase();
			depart.rotate(pivot, sensTrigo).addPlacable(el);
			depart.removePlacable(el);
			el.setCase(depart.rotate(pivot, sensTrigo));
		}
		bateauADeplacer.setCap(EDirection.rotate(bateauADeplacer.getCap(), sensTrigo));
		bateauADeplacer.triggerWholeUpdate();
		Resultat out = new Resultat();
		out.setPenalite(1);
		return out;
	}

	public Case getPivot() {
		return pivot;
	}

	public void setPivot(Case pivot) {
		this.pivot = pivot;
	}

	public boolean isSensTrigo() {
		return sensTrigo;
	}

	public void setSensTrigo(boolean sensTrigo) {
		this.sensTrigo = sensTrigo;
	}
	
	
	
}
