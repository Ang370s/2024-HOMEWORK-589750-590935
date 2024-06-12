package it.uniroma3.diadia.personaggi;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	final static private String MESSAGGIO_RISATE = "Hehehehe! Ora l'attrezzo è mio! Hihihi!";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}
	/*
	 * Strega: se interagiamo con una strega questa ci
	 * trasferisce in una stanza tra quelle adiacenti.
	 * Siccome è permalosa:
	 * • se non l’abbiamo ancora salutata, ci «trasferisce» nella
	 *   stanza adiacente che contiene meno attrezzi
	 * • altrimenti in quella che contiene più attrezzi

	 */
	@Override
	public String agisci(Partita partita) {
	    List<Stanza> stanzeAdiacenti = new ArrayList<>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		
	    if(stanzeAdiacenti.isEmpty())
	    	return "Non ci sono stanze adiacenti in cui mandarti!";
	    
		if(this.haSalutato()) {
			partita.setStanzaCorrente(Collections.max(stanzeAdiacenti));
			return "Sei stato trasferito nella stanza con più attrezzi!";
		} else {
			partita.setStanzaCorrente(Collections.min(stanzeAdiacenti));
			return "Sei stato trasferito nella stanza con meno attrezzi...";
		}
	}

	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RISATE;
	}
}
