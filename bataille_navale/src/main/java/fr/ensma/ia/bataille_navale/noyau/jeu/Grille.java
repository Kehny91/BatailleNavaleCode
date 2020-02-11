package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;

public class Grille {
	private int largeur;
	private int hauteur;
	private Case[][] grille;
	
	public Grille(int largeur, int hauteur)
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		grille = new Case[largeur][hauteur];
		for (int x = 0 ; x<largeur ; x++){
			for (int y = 0 ; y<hauteur ; y++){
				grille[y][x] = new Case(x,y);
			}
		}
	}
	
	public Case getCase(int x, int y)
	{
		return grille[y][x];
	}
}