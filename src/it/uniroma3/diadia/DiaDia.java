package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IOConsole IO;

	public DiaDia(IOConsole IO) {
		this.IO = IO;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.IO.leggiRiga();
		while (!processaIstruzione(istruzione) && !this.partita.isFinita());
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		
		if (this.partita.vinta())
			System.out.println("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			System.out.println("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 *
	private void aiuto() {
		for(int i=0; i < elencoComandi.length; i++) 
			this.IO.mostraMessaggio(elencoComandi[i]+" ");
		this.IO.mostraMessaggio("\n");
	}/

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 *
	private void vai(String direzione) {
		if(direzione==null)
			this.IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
			if (cfu == 0 && !partita.vinta()) {
				this.IO.mostraMessaggio("Hai perso...Hai finito tutti i CFU");
				this.partita.setFinita();
			}
		}
		this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}/
	
	
	/**
	 * Comando "Prendi".
	 *
	public void prendi(String nomeAttrezzo) {
        Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
        if(attrezzo == null)
        	this.IO.mostraMessaggio("Questo attrezzo non e' presente nella stanza");
        else {
            if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
                this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
                this.IO.mostraMessaggio("Attrezzo aggiunto nella borsa correttamente.");
            } else
            	this.IO.mostraMessaggio("Non c'e' abbastanza spazio nella borsa per contenere l'oggetto.");
        }
    }*/
	
	/**
	 * Comando "Posa".
	 *
	public void posa(String nomeAttrezzo) {
        Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
        if(attrezzo == null)
        	this.IO.mostraMessaggio("Questo attrezzo non e' presente nella tua borsa");
        else {
            if(this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
                this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
                this.IO.mostraMessaggio("Attrezzo posato nella stanza");
            } else
            	this.IO.mostraMessaggio("Non e' possibile posare l'attrezzo qui perche' la stanza e' piena");
        }
    }*

	/**
	 * Comando "Fine".
	 *
	private void fine() {
		this.IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	} */

	public static void main(String[] argc) {
		IOConsole IO = new IOConsole();
		DiaDia gioco = new DiaDia(IO);
		gioco.gioca();
	}
}