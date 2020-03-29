package fr.ensma.ia.bataille_navale;

import fr.ensma.ia.bataille_navale.ihm.ConsoleAsker;
import fr.ensma.ia.bataille_navale.ihm.TransitionScreen;
import fr.ensma.ia.bataille_navale.ihm.agents.global.PresenterGlobal;
import fr.ensma.ia.bataille_navale.ihm.agents.global.VueGlobal;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.VueGrille;
import fr.ensma.ia.bataille_navale.noyau.KernelThread;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.noyau.jeu.JoueurHumain;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	IJoueur j1,j2;
	PresenterGlobal presGlobalJ1;
	PresenterGlobal presGlobalJ2;
	KernelThread kernelThread;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }
    
    
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		j1 = new JoueurHumain();
		j2 = new JoueurHumain();
		
        presGlobalJ1 = new PresenterGlobal(j1, j2);
        presGlobalJ1.setVue(new VueGlobal(presGlobalJ1));
        
        presGlobalJ2 = new PresenterGlobal(j2, j1);
        presGlobalJ2.setVue(new VueGlobal(presGlobalJ2));
        
        
        Scene sceneJ1 = new Scene((Parent) presGlobalJ1.getVue(), Parametres.widthPix, Parametres.heightPix);
        Scene sceneJ2 = new Scene((Parent) presGlobalJ2.getVue(), Parametres.widthPix, Parametres.heightPix);
        Scene j1Toj2Scene = new Scene(new TransitionScreen("C'est au tour de J2") , Parametres.widthPix, Parametres.heightPix);
        Scene j2Toj1Scene = new Scene(new TransitionScreen("C'est au tour de J1") , Parametres.widthPix, Parametres.heightPix);
        kernelThread = new KernelThread(j1, presGlobalJ1, j2, presGlobalJ2, sceneJ1, sceneJ2,j1Toj2Scene, j2Toj1Scene, primaryStage);
        primaryStage.setScene(sceneJ1);
		primaryStage.show();
		presGlobalJ1.getPresGrilleMyBoats().updateAll();
		presGlobalJ1.getPresGrilleEnnemy().updateAll();
        kernelThread.start();
	}
}