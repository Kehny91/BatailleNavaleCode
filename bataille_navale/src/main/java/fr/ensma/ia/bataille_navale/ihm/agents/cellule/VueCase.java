package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class VueCase extends HBox implements IVueCase, EventHandler<MouseEvent> {
	Canvas drawing;
	GraphicsContext graphicContext;
	final int borderLine = 2;
	PresenterCase pres;
	
	private void BGN(Paint p)
	{
		graphicContext.setFill(p);
		graphicContext.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void blitBorder()
	{
		graphicContext.setStroke(Color.LIGHTGREY);
		graphicContext.setLineWidth(borderLine);
		graphicContext.strokeRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void clean() {
		drawing.setWidth(getWidth());
		drawing.setHeight(getHeight());
		BGN(Color.BLUE);
		blitBorder();
	}

	@Override
	public void blitBateau(int totalDef, int def, boolean connectNorth, boolean connectSouth,boolean connectWest, boolean connectEast) {
		clean();
		int width = (int) getWidth();
		int height = (int) getHeight();
		graphicContext.setFill(Color.DARKGRAY);
		graphicContext.fillRect(width/3, height/3, width/3, height/3);
		if (connectNorth)
			graphicContext.fillRect(width/3, 0, width/3, height/3);
		if (connectSouth)
			graphicContext.fillRect(width/3, height*2/3, width/3, height/3);
		if (connectWest)
			graphicContext.fillRect(0, height/3, width/3, height/3);
		if (connectEast)
			graphicContext.fillRect(width*2/3-1, height/3, width/3+1, height/3);
		if(connectNorth && connectEast)
			graphicContext.fillRect(width*2/3-1, 0, width/3+1, height/3);
		if(connectNorth && connectWest)
			graphicContext.fillRect(0, 0, width/3, height/3);
		if(connectSouth && connectWest)
			graphicContext.fillRect(0, height*2/3, width/3, height/3);
		if(connectSouth && connectEast)
			graphicContext.fillRect(width*2/3-1, height*2/3, width/3+1, height/3);
		
		graphicContext.setStroke(Color.LIGHTGREEN);
		graphicContext.setFont(new Font(height/4));
		graphicContext.setLineWidth(2);
		graphicContext.strokeText(String.valueOf(totalDef), width*3/4, height - borderLine, width/4);
		if (def<totalDef)
		{
			graphicContext.setStroke(Color.RED);
			graphicContext.setLineWidth(2);
			graphicContext.setFont(new Font(height/2));
			graphicContext.strokeText(String.valueOf(def), width/3, height*2/3, width/3);
		}
		if (def>totalDef)
		{
			graphicContext.setStroke(Color.GREEN);
			graphicContext.setLineWidth(2);
			graphicContext.setFont(new Font(height/2));
			graphicContext.strokeText(String.valueOf(def), width/3, height*2/3, width/3);
		}
		blitBorder();
	}
	
	@Override
	public void blitHead(EDirection cap) {
		int width = (int) getWidth();
		int height = (int) getHeight();
		graphicContext.setFill(Color.DARKGRAY);
		double[] pointsX = new double[3];
		double[] pointsY = new double[3];
		switch(cap) {
		case Est:
			pointsX[0] = width*2/3-1;
			pointsY[0] = height/3;
			pointsX[1] = width*2/3-1;
			pointsY[1] = height*2/3;
			pointsX[2] = width-borderLine;
			pointsY[2] = height/2;
			break;
		case Ouest:
			pointsX[0] = width/3;
			pointsY[0] = height/3;
			pointsX[1] = width/3;
			pointsY[1] = height*2/3;
			pointsX[2] = borderLine;
			pointsY[2] = height/2;
			break;
		case Nord:
			pointsX[0] = width/3;
			pointsY[0] = height/3;
			pointsX[1] = width*2/3;
			pointsY[1] = height/3;
			pointsX[2] = width/2;
			pointsY[2] = borderLine;
			break;
		case Sud:
			pointsX[0] = width/3;
			pointsY[0] = height*2/3;
			pointsX[1] = width*2/3;
			pointsY[1] = height*2/3;
			pointsX[2] = width/2;
			pointsY[2] = height-borderLine;
			break;
		}
		graphicContext.fillPolygon(pointsX, pointsY, 3);
	}

	@Override
	public void blitTouche() {
		graphicContext.setFill(Color.RED);
		graphicContext.fillRoundRect(getWidth()*2/5, getHeight()*2/5, getWidth()/5, getHeight()/5, 2, 2);
		blitBorder();
	}
	
	
	@Override
	public void blitCoule() {
		graphicContext.setStroke(Color.RED);
		graphicContext.setLineWidth(5);
		graphicContext.strokeLine(0, 0, getWidth(), getHeight());
		graphicContext.strokeLine(getWidth(), 0, 0, getHeight());
		blitBorder();
	}
	
	@Override
	public void blitDetruit() {
		graphicContext.setStroke(Color.RED);
		graphicContext.setLineWidth(5);
		graphicContext.strokeLine(0, 0, getWidth(), getHeight());
		blitBorder();
	}
	
	public VueCase(PresenterCase pres){
		this.pres = pres;
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setMinSize(5, 5);
		
		drawing = new Canvas(getWidth(),getHeight());
		graphicContext = drawing.getGraphicsContext2D();
		
		this.getChildren().add(drawing);
		
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
	}

	@Override
	public void handle(MouseEvent event) {
		pres.handleClick();
	}

	@Override
	public void blitSelected() {
		graphicContext.setStroke(Color.LIGHTSLATEGRAY);
		graphicContext.setLineWidth(getWidth()/5);
		graphicContext.strokeRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void blitUnknown() {
		BGN(Color.GRAY);
		blitBorder();
	}

	@Override
	public void blitPlouf() {
		graphicContext.setFill(Color.SNOW);
		graphicContext.fillRoundRect(getWidth()*2/5, getHeight()*2/5, getWidth()/5, getHeight()/5, 2, 2);
		blitBorder();
	}

	@Override
	public void blitBombe() {
		int width = (int) getWidth();
		int height = (int) getHeight();
		graphicContext.setFill(Color.BLACK);
		graphicContext.fillRect(width/4, height/4, width/2, height/2);
		graphicContext.setStroke(Color.RED);
		graphicContext.setLineWidth(5);
		graphicContext.strokeLine(width/4, height/4, width*3/4, height*3/4);
		graphicContext.strokeLine(width/4, height*3/4, width*3/4, height/4);
		blitBorder();
	}

	

	
	
}
