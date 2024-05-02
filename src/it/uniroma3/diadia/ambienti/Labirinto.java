package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe si occupa della creazione del
 * labitrinto, collegando insieme le stanze e
 * mettendo degli attrezzi al loro interno
 * 
 * @author angel & ale.papa10
 * 
 * @see Stanza
 * @see Attrezzo
 * 
 * @version base
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	private Attrezzo lanterna;
	private Attrezzo osso;
	private Attrezzo spada;
	private Attrezzo martello;
	
	private Stanza atrio;
	private Stanza aulaN11;
	private Stanza aulaN10;
	private Stanza laboratorio;
	private Stanza biblioteca;
	
	public Labirinto() {
		this.creaAttrezzi();
    	this.creaStanze();
	}
	
	private void creaAttrezzi() {
		/* crea gli attrezzi */
    	this.lanterna = new Attrezzo("lanterna",3);
		this.osso = new Attrezzo("osso",1);
		this.spada = new Attrezzo("spada",7);
		this.martello = new Attrezzo("martello",2);
	}
	
	private void creaStanze() {
		this.atrio = new Stanza("Atrio");
		this.aulaN11 = new Stanza("Aula N11");
		this.aulaN10 = new Stanza("Aula N10");
		this.laboratorio = new Stanza("Laboratorio Campus");
		this.biblioteca = new Stanza("Biblioteca");
		
    	this.collegaStanze();
    	this.riempiStanze();

    	// il gioco comincia nell'atrio
        stanzaIniziale = atrio;  
    	stanzaVincente = biblioteca;
	}
	
	private void collegaStanze() {
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
    }
	
	private void riempiStanze() {
		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(spada);
		laboratorio.addAttrezzo(martello);
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
}
