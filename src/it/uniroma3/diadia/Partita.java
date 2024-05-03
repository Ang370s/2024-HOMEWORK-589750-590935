package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.ambienti.Stanza;

import it.uniroma3.diadia.giocatore.Giocatore;


/**
 * Questa classe modella una partita del gioco
 *
 * @author  angel & ale.papa10 
 * 			(da un'idea del docente POO)	
 * @see Labirinto
 * @see Stanza
 * @see Giocatore
 * 
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private boolean finita;
	private Stanza stanzaCorrente;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto();
		this.finita = false;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}
	
	/**
	 * Restituisce vero se e solo se i cfu sono finiti
	 * @return vero se cfu finiti
	 */
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (!this.giocatoreIsVivo());
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}
