package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.KernelThread;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.EResultat;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Explosion;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.Bombe;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;
import fr.ensma.ia.bataille_navale.noyau.element.Plaisance;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;
import fr.ensma.ia.bataille_navale.observation.IObservateur;

public class Case implements IObservateur{
	private int x,y;
	private List<IPlacable> placables;
	private Grille myGrille;
	private EResultat ennemyHint = EResultat.None;
	private int nbDeTourVisible;
	
	public GenericObservable somethingChanged;
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addPlacable(IPlacable pl)
	{
		placables.add(pl);
		somethingChanged.notifyObservateurs();
	}
	
	public void removePlacable(IPlacable pl)
	{
		if (placables.remove(pl))
			somethingChanged.notifyObservateurs();
	}
	
	public ElementBateau getElementBateauSaufBombe() throws ExceptionPasDeBateauIci
	{
		if (placables.size()==0)
			throw new ExceptionPasDeBateauIci();
		else
		{
			for (IPlacable pl : placables)
			{
				if (pl.getClass()==ElementBateau.class && ((ElementBateau)pl).getBateauAbs().getClass()!=Bombe.class)
				{
					return ((ElementBateau)pl);
				}
			}
			throw new ExceptionPasDeBateauIci();
		}	
	}
	
	public ElementBateau getElementBateau() throws ExceptionPasDeBateauIci
	{
		if (placables.size()==0)
			throw new ExceptionPasDeBateauIci();
		else
		{
			for (IPlacable pl : placables)
			{
				if (pl.getClass()==ElementBateau.class)
				{
					return ((ElementBateau)pl);
				}
			}
			throw new ExceptionPasDeBateauIci();
		}	
	}
	
	public EResultat getEnnemyHint() {
		return ennemyHint;
	}
	
	public void setEnnemyHint(EResultat res) {
		ennemyHint = res;
	}
	
	public Resultat onMeTireDessus(int puissance)
	{
		Resultat out = new Resultat();
		out.setPenalite(0);
		ElementBateau elB = null;
		try {
			elB = getElementBateauSaufBombe();
			boolean wasAlive = elB.getBateauAbs().isEnVie();
			elB.handleAttaque(puissance);
			if (!elB.getBateauAbs().isEnVie())
			{
				ennemyHint = EResultat.Coule;
				out.setTypeResultat(EResultat.Coule);
				elB.getBateauAbs().triggerWholeUpdate();
				if (wasAlive && elB.getBateauAbs().getClass()==Plaisance.class)
					out.setPenalite(3);
			}
			else if(elB.getEtatCourant()==elB.getEtatDetruit()) {
				ennemyHint = EResultat.Detruit;
				out.setTypeResultat(EResultat.Detruit);
			}
			else {
				ennemyHint = EResultat.Touche;
				out.setTypeResultat(EResultat.Touche);
			}
		} catch (ExceptionPasDeBateauIci e) {
			ennemyHint = EResultat.Plouf;
			out.setTypeResultat(EResultat.Plouf);
		}
		
		try {
			elB = getElementBombe();
			boolean wasAlive = elB.getBateauAbs().isEnVie();
			elB.handleAttaque(puissance);
			if (!elB.getBateauAbs().isEnVie() && wasAlive)
			{
				elB.setHidden(false); //La bombe est revelée
				Explosion exp = new Explosion();
				exp.setCible(this);
				exp.doAction();
			}
			
		} catch (ExceptionPasDeBateauIci e) {
			
		}
		
		
		this.somethingChanged.notifyObservateurs();
		return out;
	}
	
	
	
	public Case(int x, int y, Grille myGrille)
	{
		this.x=x;
		this.y=y;
		this.placables = new ArrayList<IPlacable>();
		this.myGrille = myGrille;
		somethingChanged = new GenericObservable();
		setNbDeTourVisible(0);
		KernelThread.finDeTourObs.addObservateur(this);
	}
	
	public Grille getGrille()
	{
		return myGrille;
	}
	
	public Case voisin(EDirection direction) throws ExceptionBadInput
	{
		switch (direction)
		{
			case Est:
				return myGrille.getCase(x+1, y);
			case Nord:
					return myGrille.getCase(x, y+1);
			case Ouest:
					return myGrille.getCase(x-1, y);
			case Sud:
					return myGrille.getCase(x, y-1);
			default:
					return null;
		}
	}
	
	public Case rotate(Case pivot,boolean sensTrigo) throws ExceptionBadInput {
		int newX=0,newY=0;
		if (sensTrigo) {
			newX = pivot.getX() - (getY() - pivot.getY());
			newY = pivot.getY() + (getX() - pivot.getX());
		} else {
			newX = pivot.getX() + (getY() - pivot.getY());
			newY = pivot.getY() - (getX() - pivot.getX());
		}
		return getGrille().getCase(newX, newY);
	}
	
	public boolean isEmpty()
	{
		return placables.size() == 0;
	}

	public ElementBateau getElementBombe() throws ExceptionPasDeBateauIci {
		for (IPlacable pl : placables)
		{
			if (pl.getClass()==ElementBateau.class && ((ElementBateau)pl).getBateauAbs().getClass()==Bombe.class)
			{
				return ((ElementBateau)pl);
			}
		}
		throw new ExceptionPasDeBateauIci();
	}

	public int getNbDeTourVisible() {
		return nbDeTourVisible;
	}

	public void setNbDeTourVisible(int nbDeTourVisible) {
		boolean notif = false;
		if (this.nbDeTourVisible>0 && nbDeTourVisible==0)
			notif = true;
		this.nbDeTourVisible = nbDeTourVisible;
		if (notif) {
			somethingChanged.notifyObservateurs();
			//System.out.println("TU VOIS PLUS");
		}
	}

	@Override
	public void doOnNotification() {
		nbDeTourVisible = Math.max(0, nbDeTourVisible-1);
	}
}