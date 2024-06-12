package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{

	static final private String nome = "Saluta";
	static private final String MESSAGGIO_CHI = "Chi devo salutare?";
	
	public ComandoSaluta() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null)
			io.mostraMessaggio(MESSAGGIO_CHI);
		else	
			io.mostraMessaggio(personaggio.saluta());
	}
}
