package fr.ensma.ia.bataille_navale;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.ensma.ia.bataille_navale.ihm.ConsoleAsker;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.noyau.jeu.JoueurAbstrait;
import fr.ensma.ia.bataille_navale.noyau.jeu.JoueurHumain;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
    	ConsoleAsker consol = new ConsoleAsker();
        assertTrue( true );
        
        IJoueur joueur = new JoueurHumain();
        
        BateauFactory bF = BateauFactory.createFactory(EBateau.Porte_Avion, consol);
        try {
			bF.createBateau(joueur);
		} catch (ExceptionBadInput e) {
			System.out.println("Couldn't create the ship");
		}
        
        bF = BateauFactory.createFactory(EBateau.Torpilleur, consol);
        try {
			bF.createBateau(joueur);
		} catch (ExceptionBadInput e) {
			System.out.println("Couldn't create the ship");
		}
        
        for (int y=Parametres.hauteur-1;y>=0;y--)
        {
        	for (int x=0;x<Parametres.largeur;x++)
        	{
        		try {
					System.out.print(joueur.getGrille().getCase(x,y).getPlacables().size());
				} catch (ExceptionBadInput e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	System.out.println("");
        }
        
        System.out.println(joueur.getBateaux().get(0).getElementsBateau().toString());
    }
}
