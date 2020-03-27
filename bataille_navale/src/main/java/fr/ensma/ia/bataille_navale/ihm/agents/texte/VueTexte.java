package fr.ensma.ia.bataille_navale.ihm.agents.texte;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

public class VueTexte extends HBox implements IVueTexte{

	private PresenterTexte pres;
	private TextField tf;
	
	public VueTexte(PresenterTexte pres, int width, int height)
	{
		this.pres = pres;
		this.setBorder(new javafx.scene.layout.Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		this.setWidth(width);
		this.setHeight(height);
		tf = new TextField();
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
