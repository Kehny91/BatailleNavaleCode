package fr.ensma.ia.bataille_navale.noyau;

import fr.ensma.ia.bataille_navale.observation.GenericObservable;
import fr.ensma.ia.bataille_navale.observation.IObservateur;

/*
 * Permet d'interrompre le kernel thread si le bouton retour est appuié, ou si le timeout est dépassé
 */
public class KernelThreadKillerThread extends Thread implements IObservateur{
	private KernelThread kernelThread;
	private boolean stop;
	private boolean theEnd;
	private GenericObservable obsBoutonRetour;
	
	public KernelThreadKillerThread(final KernelThread kernelThread, final GenericObservable obsBoutonRetour) {
		this.kernelThread = kernelThread;
		stop = false;
		this.obsBoutonRetour = obsBoutonRetour;
		obsBoutonRetour.addObservateur(this);
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(15000);
				theEnd = true;
				//J'ai pu dormir, je kill le kernel
				kernelThread.interrupt();
			} catch (InterruptedException e) {
				//Parfait, si je suis interrompu, c'est qu'on ne m'a pas laissé dormir
			}
		}
	}
	
	public void ping() {
		this.interrupt();
	}
	
	public void kill() {
		stop = true;
		this.interrupt();
	}
	
	public boolean itsTheEnd() {
		return theEnd;
	}

	@Override
	public void doOnNotification() {
		theEnd = false; // C'est pas la fin, mais il faut quand meme arreter ce que tu fais
		kernelThread.interrupt();
	}
}
