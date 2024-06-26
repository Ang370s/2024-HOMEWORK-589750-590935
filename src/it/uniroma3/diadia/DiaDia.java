package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;

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
	private IO io;
	
	public DiaDia(IO io) {
		this.io = io;
	}

	public DiaDia(IO io, Labirinto labirinto) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {		
			istruzione = this.io.leggiRiga();
		}while (!processaIstruzione(istruzione) && !this.partita.isFinita());
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione, io);
		comandoDaEseguire.esegui(this.io, this.partita);
		
		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			this.io.mostraMessaggio("Hai esaurito i CFU...\nHai perso!");

		return this.partita.isFinita();
	}

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		try (Scanner scanner = new Scanner(System.in)) {
            IOConsole io = new IOConsole(scanner);
        
			Labirinto labirinto = Labirinto.newBuilder("Labirinto.txt").getLabirinto();
					/*.addStanzaIniziale("LabCampusOne")
					.addAttrezzo("osso", 1)
					.addAttrezzo("chiave", 1)
					.addStanzaVincente("Biblioteca")
					.addStanza("Atrio")
					.addAttrezzo("spada", 7)
					.addAttrezzo("lanterna", 2)
					.addStanza("N11")
					.addPersonaggio("strega", "Sono una strega cattiva!", "LabCampusOne", null)
					.addPersonaggio("cane", "Wof wof!", "Atrio", new Attrezzo("chiave", 1))
					.addPersonaggio("mago", "Sono Merlino, se un attrezzo vuoi avere, a me devi chie dere", "N11", new Attrezzo("bacchetta", 1))
					.addAdiacenza("Atrio", "LabCampusOne", "sud")
					.addAdiacenza("LabCampusOne", "Atrio", "nord")
					.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
					.addAdiacenza("Biblioteca", "LabCampusOne", "est")
					.addAdiacenza("Atrio", "N11", "est")
					.addAdiacenza("N11", "Atrio", "ovest")
					.getLabirinto();*/
	
			DiaDia gioco = new DiaDia(io, labirinto);
			gioco.gioca();
			scanner.close();
		}
	}
}