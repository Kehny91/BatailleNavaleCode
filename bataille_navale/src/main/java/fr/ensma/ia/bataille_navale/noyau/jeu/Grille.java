package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.Arrays;
import java.util.Random;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.Parametres;

public class Grille {
	private int largeur;
	private int hauteur;
	private Case[][] grille;
	
	public Grille(int largeur, int hauteur)
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		grille = new Case[hauteur][largeur];
		for (int x = 0 ; x<largeur ; x++){
			for (int y = 0 ; y<hauteur ; y++){
				grille[y][x] = new Case(x,y,this);
			}
		}
	}
	
	public Case getCase(int x, int y) throws ExceptionBadInput
	{
		if (x>=largeur || x<0 || y>=hauteur || y<0)
			throw new ExceptionBadInput();
		else
			return grille[y][x];
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
	
	public Case getRandomCase() {
		Random r = new Random();
		int x = r.nextInt(Parametres.largeur);
		int y = r.nextInt(Parametres.hauteur);
		try {
			return getCase(x, y);
		} catch (ExceptionBadInput e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return "Grille [largeur=" + largeur + ", hauteur=" + hauteur + ", grille=" + Arrays.toString(grille) + "]";
	}
	
	
	
	
}