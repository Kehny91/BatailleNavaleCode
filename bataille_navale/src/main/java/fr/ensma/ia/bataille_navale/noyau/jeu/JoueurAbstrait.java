package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;
import fr.ensma.ia.bataille_navale.observation.IObservateur;
import fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur.*;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.SousMarin;
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
	private AttendParametres etatAttendParametres;
	private Execution etatExecution;
	
	private IEtat etatCourant;

	private GenericObservable stateChanged;
	public GenericObservable failObs;
	public GenericObservable successObs;
	
	//< === DATA ===>
	private int nbTourAttente;
	private int nbDeFailsConsecutif;
	private int nbDeFailsConsecutifAdverse;
	private int nbSoinsDispo;
	private int nbFlareDispo;
	
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
	public IEtat getEtatAttendParametres() {
		return etatAttendParametres;
	}

	@Override
	public IEtat getEtatExecution() {
		return etatExecution;
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
			int nbDeBateauEnVie = 0;
			boolean sousMarinPeutTirer = false;
			for (BateauAbs bateau : getBateaux())
			{
				if (bateau.isPeutTirer())
					nbDeBateauPouvantTirer++;
				if (bateau.isEnVie())
					nbDeBateauEnVie++;
				if (bateau.isEnVie() && bateau.getClass()==SousMarin.class) {
					sousMarinPeutTirer=true;
				}
			}
			ArrayList<EAction> out = new ArrayList<EAction>();
			if (nbDeBateauPouvantTirer>0) {
				out.add(EAction.AttaqueSimple);
				out.add(EAction.AttaqueCroix);
			}
			if (nbDeBateauEnVie>0) {
				out.add(EAction.Translation);
				out.add(EAction.Rotation);
			}
			if (nbDeBateauEnVie>0 && nbFlareDispo>0 && sousMarinPeutTirer)
				out.add(EAction.Flare);
			if (nbSoinsDispo>0 && nbDeBateauEnVie>0) {
				out.add(EAction.Soins);
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
	public void finDeTour(boolean hitSomething) {
		nbTourAttente = Math.max(0, nbTourAttente-1);
		if (hitSomething) {
			successObs.notifyObservateurs();
			nbDeFailsConsecutif = 0;
		} else {
			failObs.notifyObservateurs();
			nbDeFailsConsecutif++;
			if (nbDeFailsConsecutif>=4) {
				nbDeFailsConsecutif=0;
				nbFlareDispo++;
			}
		}
	}

	@Override
	public void setNbTourAttente(int nbTourAttente) {
		this.nbTourAttente = nbTourAttente;
	}
	
	@Override
	public IEtat getEtatCourant() {
		return etatCourant;
	}
	
	public void setObservateurFailAdverse(GenericObservable failAdverse) {
		failAdverse.addObservateur(new IObservateur() {
			
			@Override
			public void doOnNotification() {
				nbDeFailsConsecutifAdverse++;
				if (nbDeFailsConsecutifAdverse==3) {
					nbSoinsDispo++;
					nbDeFailsConsecutifAdverse = 0;
				}
			}
		});
	}
	
	public void setObservateurSuccessAdverse(GenericObservable successAdverse) {
		successAdverse.addObservateur(new IObservateur() {
			
			@Override
			public void doOnNotification() {
				nbDeFailsConsecutifAdverse = 0;
			}
		});
	}

	public JoueurAbstrait() {
		super();
		this.etatEndormi = new Endormi(this);
		this.etatPerdu = new Perdu(this);
		this.etatGagne = new Gagne(this);
		this.etatAttendAction = new AttendAction(this);
		this.etatAttendParametres = new AttendParametres(this);
		this.etatExecution = new Execution(this);
		this.etatCourant = etatEndormi;
		this.stateChanged = new GenericObservable();
		this.bateaux = new ArrayList<BateauAbs>();
		myGrid = new Grille(Parametres.largeur,Parametres.hauteur);
		nbDeFailsConsecutif = 0;
		nbDeFailsConsecutifAdverse = 0;
		failObs = new GenericObservable();
		successObs = new GenericObservable();
		nbSoinsDispo = 0;
		nbFlareDispo = 0;
	}
	
	

}
