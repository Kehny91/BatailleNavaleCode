package fr.ensma.ia.bataille_navale.ihm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VictoryScreen extends VBox{
	public VictoryScreen(String nomVainqueur) {
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		TextField text = new TextField(nomVainqueur + " a gagné !!!");
		text.setFont(new Font(30));
		text.setAlignment(Pos.CENTER);
		text.setEditable(false);
		text.setDisable(true);
		text.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(text, Priority.ALWAYS);
		VBox.setMargin(text, new Insets(20));
		this.getChildren().add(text);
	}
}
