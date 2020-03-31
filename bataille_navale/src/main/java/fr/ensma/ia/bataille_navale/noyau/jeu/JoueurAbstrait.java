package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;
import fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur.*;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;

public abstract class JoueurAbstrait implements IJoueur{
	//< === REFERENCES === >
	private Grille myGrid;
	private List<BateauAbs> bateaux;
	
	//< === AUTOMATE === >
	private Endormi etatEndormi;
	private Perdu etatPerdu;
	private Gagne etatGagne;
	private AttendAction etatAttendAction;
	private AttendBateauSource etatAttendBateauSource;
	private AttendParametres etatAttendParametres;
	private RealisationAction etatRealisationAction;
	
	private IEtat etatCourant;

	private GenericObservable stateChanged;
	
	//< === DATA ===>
	private int nbTourAttente;
	
	//< === METHODES === >
	
	@Override
	public IEtat getEtatEndormi() {
		return etatEndormi;
	}

	@Override
	public IEtat getEtatPerdu() {
		return etatPerdu;
	}

	@Override
	public IEtat getEtatGagne() {
		return etatGagne;
	}

	@Override
	public IEtat getEtatAttendAction() {
		return etatAttendAction;
	}

	@Override
	public IEtat getEtatAttendBateauSource() {
		return etatAttendBateauSource;
	}

	@Override
	public IEtat getEtatAttendParametres() {
		return etatAttendParametres;
	}

	@Override
	public IEtat getEtatRealisationAction() {
		return etatRealisationAction;
	}

	@Override
	public void setEtatCourant(IEtat e) {
		etatCourant = e;
	}

	@Override
	public List<EAction> getActionDispo() {
		if (getNbTourAttente()>0) {
			return new ArrayList<EAction>();
		} else {
			int nbDeBateauPouvantTirer = 0;
			for (BateauAbs bateau : getBateaux())
			{
				if (bateau.isPeutTirer())
					nbDeBateauPouvantTirer++;
			}
			ArrayList<EAction> out = new ArrayList<EAction>();
			if (nbDeBateauPouvantTirer>0) {
				out.add(EAction.AttaqueSimple);
				out.add(EAction.AttaqueCroix);
			}
			return out;
		}
	}
	
	@Override
	public void ajouteBateau(BateauAbs bateau) {
		bateaux.add(bateau);
	}
	
	@Override
	public List<BateauAbs> getBateaux() {
		return bateaux;
	}
	
	@Override
	public void initialiseBateaux(IAsker asker) {
		BateauFactory bf;
		boolean retry;
		
		for (EBateau e : EBateau.values())
		{
			if (e!=EBateau.Bombe)
			{
				retry = true;
				while (retry)
				{
					try {
						System.out.println("Creation du bateau " + e.toString());
						bf = BateauFactory.createFactory(e, asker);
						bf.createBateau(this);
						retry = false;
						
					} catch (ExceptionBadInput e1) {
						e1.printStackTrace();
						System.out.println("It failed, try again");
						retry = true;
					}
				}
			}
		}

	}
	
	public Grille getGrille()
	{
		return myGrid;
	}
	
	@Override
	public int getNbTourAttente() {
		return nbTourAttente;
	}
	
	@Override
	public void finDeTour() {
		nbTourAttente = Math.max(0, nbTourAttente-1);
	}

	@Override
	public void setNbTourAttente(int nbTourAttente) {
		this.nbTourAttente = nbTourAttente;
	}

	public JoueurAbstrait() {
		super();
		this.etatEndormi = new Endormi(this);
		this.etatPerdu = new Perdu(this);
		this.etatGagne = new Gagne(this);
		this.etatAttendAction = new AttendAction(this);
		this.etatAttendBateauSource = new AttendBateauSource(this);
		this.etatAttendParametres = new AttendParametres(this);
		this.etatRealisationAction = new RealisationAction(this);
		this.etatCourant = etatEndormi;
		this.stateChanged = new GenericObservable();
		this.bateaux = new ArrayList<BateauAbs>();
		myGrid = new Grille(Parametres.largeur,Parametres.hauteur);
	}
	
	

}
