package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{

	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	static final private String nome = "Interagisci";
	private String messaggio;

	public ComandoInteragisci() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			io.mostraMessaggio(this.messaggio);
		} else
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}