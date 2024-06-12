package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{

	private static final String MESSAGGIO_CON_CHI = "A chi dovrei regararlo?...";
	static final private String nome = "Regala";

	public ComandoRegala() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if(attrezzo == null) {
			io.mostraMessaggio("L'attrezzo non è presente in borsa!");
			return;
		}
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			io.mostraMessaggio(MESSAGGIO_CON_CHI);
			return;
		}
		partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
		io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
	}
}
