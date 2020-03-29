package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

public class AttaqueNormale extends AttaqueAbs {

	@Override
	public int doAction() {
		Resultat r = tirSimpleSurCase(cible, puissance);
		return 1 + r.getPenalite();
	}

}
