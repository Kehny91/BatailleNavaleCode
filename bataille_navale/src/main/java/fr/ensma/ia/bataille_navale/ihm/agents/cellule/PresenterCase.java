package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.observation.IObservateur;

public class PresenterCase {
	private ModeleCase model;
	private IVueCase vue;
	
	private IObservateur observeShip; //Lorsqu'on l'appelle , ça veut dire qu'un bateau doit etre mis a jour (apparition/disparition/def)
	
	
	public void handleClick()
	{
		//TODO sachant qu'il faut recoller avec un IAsker
	}
	
	public void updateView(IJoueur forUser)
	{
		vue.clean();
		if (model.getHasAShip() && (model.getOwner()==forUser || model.getVisibleByEnnemyForNTours()>0)) //Il y a un bateau a afficher
		{
			vue.blitBateau(model.getIniDef());
			if (model.getDef()<model.getIniDef())
				vue.blitTouche(model.getDef());
		}
	}
	

	public IVueCase getVue() {
		return vue;
	}

	public void setVue(IVueCase vue) {
		this.vue = vue;
	}
	
	public PresenterCase(final Case cellule)
	{
		model = new ModeleCase();
		observeShip = new IObservateur() {
			private Case cell = cellule;
			
			@Override
			public void doOnNotification() {
				if(cell.getPlacables().size()==0)
				{
					model.setHasAShip(false);
					model.setIniDef(-1);
					model.setDef(-1);
					model.setOwner(null);
				}
				else
				{
					ElementBateau elementBateau = (ElementBateau) cell.getPlacables().get(0);
					model.setHasAShip(true);
					model.setIniDef(elementBateau.getNiveauDef0());
					model.setDef(elementBateau.getNiveauDef());
					model.setOwner( elementBateau.getBateauAbs().getOwner());
				}
			}
		};
		
		observeShip.doOnNotification();
	}
}

