package it.uniroma3.diadia.giocatore;

/**
 * Classe che gestisce il giocatore e i suoi CFU
 * 
 * @author angel & ale.papa10
 * 
 * @see Borsa
 * 
 * @version base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private Borsa borsa;
	private int cfu;
	
	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}
