package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

public class Resultat {
	private EResultat typeResultat;
	private int penalite;
	
	public Resultat() {
		typeResultat=EResultat.Plouf;
		penalite=0;
	}
	
	public EResultat getTypeResultat() {
		return typeResultat;
	}
	public void setTypeResultat(EResultat typeResultat) {
		this.typeResultat = typeResultat;
	}
	public int getPenalite() {
		return penalite;
	}
	public void setPenalite(int penalite) {
		this.penalite = penalite;
	}

	
}
