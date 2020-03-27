package fr.ensma.ia.bataille_navale.ihm.agents.grille;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.agents.cellule.IVueCase;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class VueGrille extends GridPane implements IVueGrille {
	private List<IVueCase> vueCases;
	private int width,height;
	
	public VueGrille(int width,int height)
	{
		vueCases = new ArrayList<IVueCase>();
		this.width = width;
		this.height = height;
	}

	@Override
	public void ajouteVueCase(IVueCase vue, int x, int y) {
		//Pas beau, mais j'ai pas trop le choix si je veux garder un couplage faible avec la vue (patern facade)
		this.add((Node) vue, x, Parametres.hauteur-1-y);
		vueCases.add(vue);
	}

	@Override
	public int getGrilleWidth() {return width;}

	@Override
	public int getGrilleHeight() {return height;}
}
