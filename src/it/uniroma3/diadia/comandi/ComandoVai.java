package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

import it.uniroma3.diadia.IO;

public class ComandoVai extends AbstractComando{
	static final private String nome = "Vai";
	
	public ComandoVai() {
		super(nome);
	}
	
	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(IO io, Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione!");
			return;
		}
		Direzione direzione;
		try {
			direzione = Direzione.valueOf(this.getParametro().toUpperCase());
		} catch (IllegalArgumentException e) {
			//caso in cui viene specificata una direzione non contemplata dall'enum Direzione
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione.name());
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
}