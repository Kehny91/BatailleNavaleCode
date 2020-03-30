package fr.ensma.ia.bataille_navale.noyau.jeu;

public enum EDirection {
	Nord,
	Sud,
	Est,
	Ouest;

	public static EDirection reverse(EDirection dir) {
		switch (dir) {
			case Nord:
				return Sud;
			case Sud:
				return Nord;
			case Est:
				return Ouest;
			case Ouest:
				return Est;
			default:
				return null;
		}
	}
}
