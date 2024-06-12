package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	static final private String CIBO_PREFERITO = "osso";
	private String msg;
	private Attrezzo attrezzo;
			
	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
			
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return "Sei stato morso!";
	}

	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = "Che fortuna! Era proprio il suo cibo preferito e ha lasciato cadere un attrezzo!";
		}
		else {
			msg = "Il regalo non è piaciuto..." + this.agisci(partita);
		}
		return msg;
	}
}
