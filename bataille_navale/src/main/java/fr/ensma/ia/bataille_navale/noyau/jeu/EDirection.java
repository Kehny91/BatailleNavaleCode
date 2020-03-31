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

	public static EDirection rotate(EDirection dir, boolean sensTrigo) {
		if (sensTrigo)
		{
			switch (dir) {
				case Nord:
					return Ouest;
				case Sud:
					return Est;
				case Est:
					return Nord;
				case Ouest:
					return Sud;
				default:
					return null;
			}
		}else {
			switch (dir) {
			case Nord:
				return Est;
			case Sud:
				return Ouest;
			case Est:
				return Sud;
			case Ouest:
				return Nord;
			default:
				return null;
		}
		}
	}
}
