package fr.ensma.ia.bataille_navale.ihm.agents.global;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.ihm.agents.ExceptionNoVueSet;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.texte.PresenterTexte;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class PresenterGlobal implements IAsker{
	private PresenterTexte presTexteJoueur,presTexteConsigne,presTexteAide;
	private PresenterGrille presGrilleMyBoats,presGrilleEnnemy;
	private IVueGlobal vue;
	private IJoueur looker;

	public PresenterTexte getPresTexteJoueur() {
		return presTexteJoueur;
	}

	public PresenterTexte getPresTexteConsigne() {
		return presTexteConsigne;
	}

	public PresenterTexte getPresTexteAide() {
		return presTexteAide;
	}

	public PresenterGrille getPresGrilleMyBoats() {
		return presGrilleMyBoats;
	}

	public PresenterGrille getPresGrilleEnnemy() {
		return presGrilleEnnemy;
	}
	
	public IVueGlobal getVue() {
		return vue;
	}

	public void setVue(IVueGlobal vue) {
		this.vue = vue;
		presTexteJoueur.setVue(vue.getVueTexteJoueur());
		presTexteConsigne.setVue(vue.getVueTexteConsigne());
		presTexteAide.setVue(vue.getVueTexteAide());
		presGrilleMyBoats.setVue(vue.getVueGrilleMyBoats());
		presGrilleEnnemy.setVue(vue.getVueGrilleEnnemy());
	}

	public PresenterGlobal(IJoueur looker, IJoueur ennemy) {
		this.looker = looker;
		presTexteJoueur = new PresenterTexte();
		presTexteConsigne = new PresenterTexte();
		presTexteAide = new PresenterTexte();
		presGrilleMyBoats = new PresenterGrille(looker.getGrille(), looker);
		presGrilleEnnemy = new PresenterGrille(ennemy.getGrille(), looker);
	}

	@Override
	public Case demandeUneCase(String string, Grille grille) throws ExceptionBadInput {
		try {
			presTexteConsigne.setText(string);
			if (grille == looker.getGrille()) {
				presTexteAide.setText("Pour cela, veuillez cliquer sur l'une de vos case");
				Case out = presGrilleMyBoats.demandeUneCase(null, grille);
				presTexteConsigne.clean();
				presTexteAide.clean();
				return out;
			}
			else {
				presTexteAide.setText("Pour cela, veuillez cliquer sur l'une des cases adverses");
				Case out = presGrilleEnnemy.demandeUneCase(null, grille);
				presTexteConsigne.clean();
				presTexteAide.clean();
				return out;
			}
		} catch (ExceptionNoVueSet e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BateauAbs demandeUnBateau() {
		// TODO Auto-generated method stub
		return null;
	}
}
