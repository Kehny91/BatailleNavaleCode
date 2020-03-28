package fr.ensma.ia.bataille_navale.ihm.agents.texte;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;

public class VueTexte extends GridPane implements IVueTexte{

	private PresenterTexte pres;
	private TextField tf;
	
	public VueTexte(PresenterTexte pres)
	{
		this.pres = pres;
		this.setMinSize(10, 10);
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setBorder(new javafx.scene.layout.Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		this.setGridLinesVisible(true);
		
		tf = new TextField();
		tf.setEditable(false);
		tf.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		GridPane.setHgrow(tf, Priority.ALWAYS);
		GridPane.setVgrow(tf, Priority.ALWAYS);
		this.getChildren().add(tf);
	}
	
	@Override
	public void afficheText(String s) {
		tf.setText(s);
	}

	@Override
	public void clean() {
		tf.setText("");
	}
	
}
