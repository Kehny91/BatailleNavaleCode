package fr.ensma.ia.bataille_navale;

import fr.ensma.ia.bataille_navale.ihm.ConsoleAsker;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.VueGrille;
import fr.ensma.ia.bataille_navale.noyau.KernelThread;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.noyau.jeu.JoueurHumain;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	IJoueur moi;
	PresenterGrille presGrille;
	KernelThread kernelThread;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }
    
    
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		moi = new JoueurHumain();
		ConsoleAsker console = new ConsoleAsker();
        Grille maGrille = moi.getGrille();
        presGrille = new PresenterGrille(maGrille, moi);
        presGrille.setVue(new VueGrille(Parametres.widthPix, Parametres.heightPix));
        Scene scene = new Scene((Parent) presGrille.getVue(), Parametres.widthPix, Parametres.heightPix);
        kernelThread = new KernelThread(moi, presGrille, null, null);
        kernelThread.start();
        primaryStage.setScene(scene);
		primaryStage.show();
	}
}