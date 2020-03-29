package fr.ensma.ia.bataille_navale.ihm.agents.grille;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.ihm.agents.ExceptionNoVueSet;
import fr.ensma.ia.bataille_navale.ihm.agents.cellule.IVueCase;
import fr.ensma.ia.bataille_navale.ihm.agents.cellule.PresenterCase;
import fr.ensma.ia.bataille_navale.ihm.agents.cellule.VueCase;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.observation.IObservateur;

public class PresenterGrille implements IAsker{
	private IVueGrille vue;
	private List<PresenterCase> presenters;
	private List<IObservateur> observateurs; //Observe si les cases sont cliquées
	private final Grille grille;
	private IJoueur looker;
	private Case lastCelluleClicked;
	private List<PresenterCase> caseSelectionnee;
	
	public PresenterGrille(final Grille grille, IJoueur looker, IJoueur owner)
	{
		presenters = new ArrayList<PresenterCase>();
		observateurs = new ArrayList<IObservateur>(); // Un observateur par case (monitor les clicks)
		caseSelectionnee = new ArrayList<PresenterCase>();
		this.looker = looker;
		this.grille = grille;
		PresenterCase currentPresentationCase;
		for (int x=0;x<Parametres.largeur;x++) {
			for (int y=0;y<Parametres.hauteur;y++) {
				try {
					Case currentCase = grille.getCase(x, y);
					currentPresentationCase = new PresenterCase(currentCase, looker, owner);
					presenters.add(currentPresentationCase);
					IObservateur observateurDeCetteCase = new IObservateur() {
						
						final Case maCase = currentCase;
						
						@Override
						public void doOnNotification() {
							lastCelluleClicked = maCase;
						}
					};
					currentPresentationCase.onMaClicke.addObservateur(observateurDeCetteCase);
					observateurs.add(observateurDeCetteCase);
					
				} catch (ExceptionBadInput e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateAll() {
		for (PresenterCase pres : presenters) {
			try {
				pres.updateView();
			} catch (ExceptionNoVueSet e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Quand on set la vue de la grille,
	 * on cree les vues des cases et fait le lien avec les presenters existant
	 */
	public void setVue(IVueGrille vue)
	{
		this.vue = vue;
		IVueCase currentCaseVue;
		for (PresenterCase pres : presenters)
		{
			currentCaseVue = new VueCase(pres);
			pres.setVue(currentCaseVue);
			vue.ajouteVueCase(currentCaseVue, pres.getCaseX(), pres.getCaseY());
		}
	}
	
	public IVueGrille getVue() {return vue;}

	@Override
	public Case demandeUneCase(String string, Grille grille) throws ExceptionBadInput {
		if (string!=null)
			System.out.println(string);
		lastCelluleClicked = null;
		while (lastCelluleClicked == null)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		findPresenter(lastCelluleClicked).select();
		caseSelectionnee.add(findPresenter(lastCelluleClicked));
		return lastCelluleClicked;
	}

	@Override
	public BateauAbs demandeUnBateau() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PresenterCase findPresenter(Case cellule) {
		for (PresenterCase pres : presenters) {
			if(pres.getCaseX() == cellule.getX() && pres.getCaseY() == cellule.getY())
				return pres;
		}
		return null;
	}

	@Override
	public void clean() {
		for (PresenterCase pres : caseSelectionnee) {
			pres.unselect();
		}
		caseSelectionnee.clear();
	}
}
