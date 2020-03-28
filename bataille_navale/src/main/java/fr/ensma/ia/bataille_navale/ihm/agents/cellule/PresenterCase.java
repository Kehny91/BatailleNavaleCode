package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.ihm.agents.ExceptionNoVueSet;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;
import fr.ensma.ia.bataille_navale.observation.IObservable;
import fr.ensma.ia.bataille_navale.observation.IObservateur;
/*
 * La presentation observe une case.
 * Lorsque cette case est modifiée (un bateau vient de quitter/rejoindre la case, ou le bateau a été touché),
 * une notification est emise pour actualiser le rendu
 */
public class PresenterCase implements IObservateur{
	private ModeleCase model;
	private IVueCase vue;
	private int xCase,yCase;
	private IJoueur looker;
	public IObservable onMaClicke;
	
	public void handleClick()
	{
		System.out.println("Click sur une case du joueur " + looker.toString());
		onMaClicke.notifyObservateurs();
	}
	
	public void updateView() throws ExceptionNoVueSet
	{
		if (vue == null)
			throw new ExceptionNoVueSet();
		vue.clean();
		if (model.hasAShip() && model.isVisible())
		{
			try {
				vue.blitBateau(model.getNiveauDef0(), model.getNiveauDef(), model.connect(EDirection.Nord), model.connect(EDirection.Sud), model.connect(EDirection.Ouest), model.connect(EDirection.Est));
				if (model.isHead())
					vue.blitHead(model.getCap());
			} catch (ExceptionPasDeBateauIci e) {
				// Inexplicable
				e.printStackTrace();
			}
		}
	}
	

	public IVueCase getVue() {
		return vue;
	}

	public void setVue(IVueCase vue) {
		this.vue = vue;
		doOnNotification();
	}
	
	public PresenterCase(final Case cellule, final IJoueur looker)
	{
		this.looker = looker;
		xCase = cellule.getX();
		yCase = cellule.getY();
		model = new ModeleCase(cellule,looker);
		onMaClicke = new GenericObservable();
		cellule.somethingChanged.addObservateur(this);
	}
	
	public int getCaseX() {return xCase;}
	public int getCaseY() {return yCase;}

	@Override
	public void doOnNotification() {
		try {
			updateView();
		} catch (ExceptionNoVueSet e) {
			e.printStackTrace();
		}
	}
}

