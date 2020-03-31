package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

public class AttaqueNormale extends AttaqueAbs {

	@Override
	public Resultat doAction() {
		Resultat r = tirSimpleSurCase(cible, puissance);
		r.setPenalite(r.getPenalite()+1);
		return r;
	}

}
