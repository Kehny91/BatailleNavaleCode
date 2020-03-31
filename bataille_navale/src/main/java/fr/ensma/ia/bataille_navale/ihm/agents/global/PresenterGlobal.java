package fr.ensma.ia.bataille_navale.ihm.agents.global;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.ihm.agents.ExceptionNoVueSet;
import fr.ensma.ia.bataille_navale.ihm.agents.action.PresenterAction;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.texte.PresenterTexte;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import javafx.application.Platform;

public class PresenterGlobal implements IAsker{
	private PresenterTexte presTexteJoueur,presTexteConsigne,presTexteAide;
	private PresenterGrille presGrilleMyBoats,presGrilleEnnemy;
	private PresenterAction presAction;
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
	
	public PresenterAction getPresAction() {
		return presAction;
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
		presAction.setVue(vue.getVueAction());
	}

	public PresenterGlobal(IJoueur looker, IJoueur ennemy) {
		this.looker = looker;
		presTexteJoueur = new PresenterTexte();
		presTexteConsigne = new PresenterTexte();
		presTexteAide = new PresenterTexte();
		presGrilleMyBoats = new PresenterGrille(looker.getGrille(), looker, looker);
		presGrilleEnnemy = new PresenterGrille(ennemy.getGrille(), looker, ennemy);
		presAction = new PresenterAction();
	}

	@Override
	public Case demandeUneCase(String string, Grille grille) throws ExceptionBadInput, InterruptedException {
		Case out = null;
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					presTexteConsigne.setText(string);
					if (grille == looker.getGrille()) 
						presTexteAide.setText("Pour cela, veuillez cliquer sur l'une de vos case");
					else 
						presTexteAide.setText("Pour cela, veuillez cliquer sur l'une des cases adverses");
				} catch (ExceptionNoVueSet e) {
					e.printStackTrace();
				}
				
			}
		});
		
		if (grille == looker.getGrille()) 
			out = presGrilleMyBoats.demandeUneCase();
		else 
			out = presGrilleEnnemy.demandeUneCase();
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					presTexteConsigne.clean();
					presTexteAide.clean();
				} catch (ExceptionNoVueSet e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
			
		return out;
	}

	@Override
	public BateauAbs demandeUnBateau(String string, Grille grille) throws ExceptionBadInput, InterruptedException {
		BateauAbs out = null;
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					presTexteConsigne.setText(string);
					presTexteAide.setText("Pour cela, veuillez cliquer sur l'un de vos bateau");
				} catch (ExceptionNoVueSet e) {
					e.printStackTrace();
				}
				
			}
		});
		
		out = presGrilleMyBoats.demandeUnBateau();
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					presTexteConsigne.clean();
					presTexteAide.clean();
				} catch (ExceptionNoVueSet e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		for (ElementBateau el : out.getElementsBateau()) {
			presGrilleMyBoats.select(el.getCase());
		}
		
		return out;
	}

	@Override
	public void clean() {
		presGrilleEnnemy.clean();
		presGrilleMyBoats.clean();
	}

	@Override
	public EAction demandeAction() throws InterruptedException {
		return presAction.demandeAction();
	}
}
