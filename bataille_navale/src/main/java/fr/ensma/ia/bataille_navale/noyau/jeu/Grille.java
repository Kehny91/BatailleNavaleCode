package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;

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

	@Override
	public String toString() {
		return "Grille [largeur=" + largeur + ", hauteur=" + hauteur + ", grille=" + Arrays.toString(grille) + "]";
	}
	
	
	
	
}