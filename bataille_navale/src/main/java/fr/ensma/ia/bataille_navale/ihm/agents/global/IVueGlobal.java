package fr.ensma.ia.bataille_navale.ihm.agents.global;

import fr.ensma.ia.bataille_navale.ihm.agents.grille.IVueGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.texte.IVueTexte;

public interface IVueGlobal {
	IVueGrille getVueGrilleMyBoats();
	IVueGrille getVueGrilleEnnemy();
	IVueTexte getVueTexteJoueur();
	IVueTexte getVueTexteConsigne();
	IVueTexte getVueTexteAide();
}
