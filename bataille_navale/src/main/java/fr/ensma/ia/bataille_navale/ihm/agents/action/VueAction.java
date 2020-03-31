package fr.ensma.ia.bataille_navale.ihm.agents.action;

import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

class ButtonForAction extends Button{
	public EAction action;

	public ButtonForAction(final PresenterAction pres, EAction action, String affichage) {
		super(affichage);
		this.action = action;
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				pres.handleChoix(action);
			}
		});
	}
	
}

public class VueAction extends VBox implements IVueAction{
	private ButtonForAction[] buttons = new ButtonForAction[8]; 
	
	private PresenterAction pres;
	
	public VueAction(PresenterAction pres) {
		this.pres = pres;
		int i = 0;
		for (EAction action : EAction.values()) {
			if (action!=EAction.Explosion) {
				buttons[i] = new ButtonForAction(pres, action, action.toString());
				VBox.setVgrow(buttons[i], Priority.SOMETIMES);
				VBox.setMargin(buttons[i], new Insets(5));
				this.getChildren().add(buttons[i]);
				i++;
			}
		}
	}
	
	private ButtonForAction getButton(EAction action) {
		for (ButtonForAction b : buttons) {
			if (b.action == action) {
				return b;
			}
		}
		return null;
	}

	@Override
	public void setAvailable(EAction action, boolean available) {
		getButton(action).setDisable(!available);
	}
	
	
	
}
