package fr.ensma.ia.bataille_navale.outilsMultithread;

import java.util.concurrent.Semaphore;

/*
 * Attente d'une valeur FRAICHE (ie publiée après l'appel de waitForValue)
 */
public class MDD<T> {
	private T data;
	private Semaphore containsData;
	private Semaphore protectionData;
	private boolean enAttente;
	
	public MDD(){
		containsData = new Semaphore(0);
		protectionData = new Semaphore(1);
		enAttente = false;
	}
	
	public T waitNewValue() throws InterruptedException {
		enAttente = true;
		
		containsData.acquire();
		//Si on se fait interrompre, on a rien a rendre
		
		protectionData.acquire();
		//Si on se fait interrompre, on a perdu cette valeur de data, mais c'est pas grave, de toute façon on passe a autre chose
		
		T out = data;
		data = null;
		
		protectionData.release();
		return out;
	}
	
	public void pushValue(T value){
		try {
			protectionData.acquire();
		} catch (InterruptedException e) {;}
		//Si on se fait interrompre, on a rien a rendre
		//J'ignore l'interruption car il est très peu probable qu'on soit interrompu pendant l'execution de cette fonction
		//Mais, si cette fonction throw qqchose, ça va alourdir le code....
		
		data = value;
		if (enAttente)
			containsData.release();
		protectionData.release();
	}
}
