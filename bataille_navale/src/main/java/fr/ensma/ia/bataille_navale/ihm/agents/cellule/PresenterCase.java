package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.observation.IObservateur;
/*
 * La presentation observe une case.
 * Lorsque cette case est modifiée (un bateau vient de quitter/rejoindre la case, ou le bateau a été touché),
 * une notification est emise pour actualiser le rendu
 */
public class PresenterCase {
	private ModeleCase model;
	private IVueCase vue;
	
	private IObservateur observeShip; //Lorsqu'on l'appelle , ça veut dire qu'un bateau doit etre mis a jour (apparition/disparition/def)
	
	
	public void handleClick()
	{
		//TODO sachant qu'il faut recoller avec un IAsker
	}
	
	public void updateView()
	{
		vue.clean();
		if (model.hasAShip() && model.isVisible())
		{
			ElementBateau e = null;
			try {
				e = model.getElementBateau();
			} catch (ExceptionPasDeBateauIci e1) {
				e1.printStackTrace(); //inexplicable
			}
			vue.blitBateau(e.getNiveauDef0(), e.getNiveauDef(), model.connect(EDirection.Nord), model.connect(EDirection.Sud), model.connect(EDirection.Ouest), model.connect(EDirection.Est));
		}
	}
	

	public IVueCase getVue() {
		return vue;
	}

	public void setVue(IVueCase vue) {
		this.vue = vue;
	}
	
	public PresenterCase(final Case cellule, final IJoueur looker)
	{
		model = new ModeleCase(cellule,looker);
		observeShip = new IObservateur() {
			
			@Override
			public void doOnNotification() {
				updateView();
			}
		};
		
		observeShip.doOnNotification();
	}
}

