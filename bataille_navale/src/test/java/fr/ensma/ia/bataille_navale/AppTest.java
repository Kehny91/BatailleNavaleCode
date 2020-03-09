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
    	Grille grille = new Grille(20,20);
    	ConsoleAsker consol = new ConsoleAsker();
    	consol.demandeUneCase("File un bateau", grille);
        assertTrue( true );
        
        IJoueur joueur = new JoueurHumain();
        
        BateauFactory bF = BateauFactory.createFactory(EBateau.Porte_Avion, consol);
        try {
			bF.createBateau(joueur);
		} catch (ExceptionBadInput e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
