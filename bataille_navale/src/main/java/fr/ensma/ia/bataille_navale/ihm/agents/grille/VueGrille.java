package fr.ensma.ia.bataille_navale.ihm.agents.grille;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.agents.cellule.IVueCase;
import javafx.scene.Node;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class VueGrille extends GridPane implements IVueGrille {
	private List<IVueCase> vueCases;
	private PresenterGrille pres;
	
	public VueGrille(PresenterGrille pres)
	{
		//this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setMinSize(10, 10);
		vueCases = new ArrayList<IVueCase>();
		this.pres = pres;
		this.setBorder(new javafx.scene.layout.Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, new BorderWidths(4))));
		for (int i=0; i<Parametres.largeur;i++)
		{
			ColumnConstraints columnC = new ColumnConstraints();
			columnC.setPercentWidth(100.0/Parametres.largeur);
			this.getColumnConstraints().add(columnC);
		}
		for (int i=0; i<Parametres.hauteur;i++)
		{
			RowConstraints rowC = new RowConstraints();
			rowC.setPercentHeight(100.0/Parametres.hauteur);
			this.getRowConstraints().add(rowC);
		}
	}
	

	@Override
	public void ajouteVueCase(IVueCase vue, int x, int y) {
		//Le cast n'est pas beau, mais j'ai pas trop le choix si je veux garder un couplage faible avec la vue (patern facade)
		//GridPane.setHgrow((Node)vue, Priority.ALWAYS);
		//GridPane.setVgrow((Node)vue, Priority.ALWAYS);
		this.add((Node) vue, x, Parametres.hauteur-1-y); 
		vueCases.add(vue);
	}
}
