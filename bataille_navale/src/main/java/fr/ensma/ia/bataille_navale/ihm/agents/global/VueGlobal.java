package fr.ensma.ia.bataille_navale.ihm.agents.global;

import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.agents.action.IVueAction;
import fr.ensma.ia.bataille_navale.ihm.agents.action.VueAction;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.IVueGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.VueGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.texte.IVueTexte;
import fr.ensma.ia.bataille_navale.ihm.agents.texte.VueTexte;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class VueGlobal extends GridPane implements IVueGlobal{
	private PresenterGlobal pres;
	private IVueGrille vueGrilleMyBoats, vueGrilleEnnemy;
	private IVueTexte vueTexteJoueur,vueTexteConsigne,vueTexteAide;
	private IVueAction vueAction;
	
	public VueGlobal(PresenterGlobal pres) {
		//this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.pres = pres;
		
		for (int i=0; i<7;i++)
		{
			ColumnConstraints columnC = new ColumnConstraints();
			columnC.setPercentWidth(100.0/7.0);
			this.getColumnConstraints().add(columnC);
		}
		for (int i=0; i<20;i++)
		{
			RowConstraints rowC = new RowConstraints();
			rowC.setPercentHeight(100.0/20.0);
			this.getRowConstraints().add(rowC);
		}
		
		vueTexteJoueur = new VueTexte(pres.getPresTexteJoueur());
		vueTexteConsigne = new VueTexte(pres.getPresTexteConsigne());
		vueTexteAide = new VueTexte(pres.getPresTexteAide());
		vueGrilleMyBoats = new VueGrille(pres.getPresGrilleMyBoats());
		vueGrilleEnnemy = new VueGrille(pres.getPresGrilleEnnemy());
		vueAction = new VueAction(pres.getPresAction());
		
		this.add((Node)vueTexteJoueur, 0, 0, 1, 1);
		this.add((Node)vueTexteConsigne, 1, 0, 5, 1);
		this.add((Node)vueTexteAide, 0, 1, 6, 3);
		this.add((Node)vueGrilleMyBoats, 0, 4, 3, 16);
		this.add((Node)vueGrilleEnnemy, 3, 4, 3, 16);
		this.add((Node)vueAction,6,0,1,20);
	}

	@Override
	public IVueGrille getVueGrilleMyBoats() {
		return vueGrilleMyBoats;
	}

	@Override
	public IVueGrille getVueGrilleEnnemy() {
		return vueGrilleEnnemy;
	}

	@Override
	public IVueTexte getVueTexteJoueur() {
		return vueTexteJoueur;
	}

	@Override
	public IVueTexte getVueTexteConsigne() {
		return vueTexteConsigne;
	}

	@Override
	public IVueTexte getVueTexteAide() {
		return vueTexteAide;
	}

	@Override
	public IVueAction getVueAction() {
		return vueAction;
	}
}
