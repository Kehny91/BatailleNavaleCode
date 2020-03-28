package fr.ensma.ia.bataille_navale.ihm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/*
 * Petite classe un peu sale écrite a l'arrache
 */
public class TransitionScreen extends VBox implements EventHandler<ActionEvent>{
	private Button buttonOk;
	private TextField text;
	private boolean buttonHasBeenPressed;
	
	public TransitionScreen(String affiche) {
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		buttonOk = new Button();
		buttonOk.setText("OK");
		text = new TextField(affiche);
		text.setFont(new Font(30));
		text.setAlignment(Pos.CENTER);
		buttonOk.setFont(new Font(30));
		text.setEditable(false);
		text.setDisable(true);
		text.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		buttonOk.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(text, Priority.ALWAYS);
		VBox.setVgrow(buttonOk, Priority.ALWAYS);
		VBox.setMargin(text, new Insets(20));
		VBox.setMargin(buttonOk, new Insets(20));
		buttonHasBeenPressed = false;
		
		buttonOk.setOnAction(this);
		
		this.getChildren().addAll(text,buttonOk);
	}
	
	public boolean buttonPressed() {
		boolean out = buttonHasBeenPressed;
		buttonHasBeenPressed = false;
		return out;
	}

	@Override
	public void handle(ActionEvent event) {
		buttonHasBeenPressed = true;
	}
}
