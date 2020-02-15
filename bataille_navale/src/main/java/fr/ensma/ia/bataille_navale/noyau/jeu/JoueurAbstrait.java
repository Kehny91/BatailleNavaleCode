package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.List;

import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;
import fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur.*;

public abstract class JoueurAbstrait implements IJoueur{
	//< === REFERENCES === >
	private Grille myGrid;
	
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
	public void initialiserGrille(int largeur, int hauteur)
	{
		myGrid = new Grille(largeur,hauteur);
		//A compléter dans les classes filles
	}

	@Override
	public List<IAction> getActionDispo() {
		// TODO Auto-generated method stub
		return null;
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
		initialiserGrille(Parametres.largeur,Parametres.hauteur);
	}
	
	

}
