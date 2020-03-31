package fr.ensma.ia.bataille_navale.noyau.actions.deplacements;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public class Translation extends DeplacementAbs{
	private int nbDeCase;

	public int getNbDeCase() {
		return nbDeCase;
	}


	public void setNbDeCase(int nbDeCase) {
		this.nbDeCase = nbDeCase;
	}



	@Override
	public Resultat doAction() throws ExceptionBadInput{
		EDirection sensDeplacement;
		if (nbDeCase>=0)
			sensDeplacement = bateauADeplacer.getCap();
		else
			sensDeplacement = EDirection.reverse(bateauADeplacer.getCap());
		
		
		for (int i=0;i<Math.max(nbDeCase,-1*nbDeCase) ; i++) {
			if ((nbDeCase>=0 && !bateauADeplacer.peutAvancer()) || (nbDeCase<0 && !bateauADeplacer.peutReculer())) {
				Resultat out = new Resultat();
				out.setPenalite(1);
				return out;
			}
			for (ElementBateau el : bateauADeplacer.getElementsBateau()) {
				//On deplace chaque element d'une case
				//Certaine case vont se superposer, ce n'est pas grave car c'est temporaire
				Case depart = el.getCase();
				depart.voisin(sensDeplacement).addPlacable(el);
				depart.removePlacable(el);
				el.setCase(depart.voisin(sensDeplacement));
			}
			bateauADeplacer.triggerWholeUpdate();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
				//Peu probable
			}
		}
		
		Resultat out = new Resultat();
		out.setPenalite(1);
		return out;
	}

}
