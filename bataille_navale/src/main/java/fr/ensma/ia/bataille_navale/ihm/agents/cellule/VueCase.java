package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class VueCase extends HBox implements IVueCase {
	Canvas drawing;
	GraphicsContext graphicContext;
	int width,height;
	final int borderLine = 2;
	PresenterCase pres;
	
	private void BGN(Paint p)
	{
		graphicContext.setFill(p);
		graphicContext.fillRect(0, 0, width, height);
	}
	
	private void blitBorder()
	{
		graphicContext.setStroke(Color.LIGHTGREY);
		graphicContext.setLineWidth(borderLine);
		graphicContext.strokeRect(0, 0, width, height);
	}

	@Override
	public void clean() {
		BGN(Color.BLUE);
		blitBorder();
	}

	@Override
	public void blitBateau(int totalDef, int def, boolean connectNorth, boolean connectSouth,boolean connectWest, boolean connectEast) {
		clean();
		graphicContext.setFill(Color.DARKGRAY);
		graphicContext.fillRect(width/3, height/3, width*2/3, height*2/3);
		if (connectNorth)
			graphicContext.fillRect(width/3, 0, width*2/3, height/3);
		if (connectSouth)
			graphicContext.fillRect(width*2/3, 0, width*2/3, height/3);
		if (connectWest)
			graphicContext.fillRect(0, height/3, width/3, height*2/3);
		if (connectEast)
			graphicContext.fillRect(width*2/3, height/3, width/3, height*2/3);
		graphicContext.setStroke(Color.DARKBLUE);
		graphicContext.setLineWidth(3);
		graphicContext.strokeText(String.valueOf(totalDef), width*4/5, height*4/5, width/5);
		if (def<totalDef)
		{
			graphicContext.setStroke(Color.RED);
			graphicContext.setLineWidth(3);
			graphicContext.strokeText(String.valueOf(def), width/3, height/3, width/3);
		}
		blitBorder();
		
	}

	@Override
	public void blitTouche() {
		clean();
		graphicContext.setFill(Color.RED);
		graphicContext.fillRoundRect(width*2/5, height*2/5, width/5, height/5, 2, 2);
		blitBorder();
	}
	
	@Override
	public void blitCoule() {
		clean();
		graphicContext.setStroke(Color.RED);
		graphicContext.setLineWidth(5);
		graphicContext.strokeLine(0, 0, width, height);
		graphicContext.strokeLine(width, 0, 0, height);
		blitBorder();
	}
	
	public VueCase(int width, int height, PresenterCase pres){
		drawing = new Canvas(width,height);
		graphicContext = drawing.getGraphicsContext2D();
		this.getChildren().add(drawing);
		this.width = width;
		this.height = height;
		this.pres = pres;
	}

	
	
}
