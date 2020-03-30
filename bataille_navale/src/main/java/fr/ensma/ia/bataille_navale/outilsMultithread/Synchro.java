package fr.ensma.ia.bataille_navale.outilsMultithread;

import java.util.concurrent.Semaphore;

public class Synchro {
	Semaphore s;
	boolean synchroEnAttente;
	
	public Synchro() {
		s = new Semaphore(0);
		synchroEnAttente = false;
	}
	
	public void waitUnlock() throws InterruptedException {
		synchroEnAttente = true;
		s.acquire();
		synchroEnAttente = false;
	}
	
	public void unlock() {
		if (synchroEnAttente)
			s.release();
	}
	
}
