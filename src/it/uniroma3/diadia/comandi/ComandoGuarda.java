package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public class ComandoGuarda extends AbstractComando{

	static final private String nome = "Guarda";
	
	public ComandoGuarda() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
	}
}
