package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
	"con una mia magica azione, troverai un nuovo oggetto " +
	"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;
	private static final String MESSAGGIO_GRAZIE = "Grazie mille per il regalo sincero, " +
	"ora te lo restituisco con un peso più leggero!";
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		int pesoDimezzato = attrezzo.getPeso()/2;
		Attrezzo attrezzoDimezzato = new Attrezzo(attrezzo.getNome(), pesoDimezzato);
		partita.getStanzaCorrente().addAttrezzo(attrezzoDimezzato);
		
		return MESSAGGIO_GRAZIE;
	}
}