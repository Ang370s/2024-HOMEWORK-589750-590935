package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.List;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Fixture {
	public static IOSimulator creaSimulazionePartitaFacile(List<String> comandiDaLeggere) throws FileNotFoundException, FormatoFileNonValidoException {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = Labirinto.newBuilder("Labirinto.txt").getLabirinto();
				/*.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaComplessa(List<String> comandiDaLeggere) throws FileNotFoundException, FormatoFileNonValidoException {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = Labirinto.newBuilder("Labirinto.txt").getLabirinto();
				/*.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.addStanza("Bagno")
				.addAdiacenza("Bagno", "Atrio", "sud")
				.addAdiacenza("Atrio", "Bagno", "nord")
				.addStanza("Studio")
				.addAdiacenza("Studio", "Atrio", "est")
				.addAdiacenza("Atrio", "Studio", "ovest")
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaMonolocale(List<String> comandiDaLeggere) throws FileNotFoundException, FormatoFileNonValidoException {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto monolocale = Labirinto.newBuilder("Labirinto.txt").getLabirinto();
				/*.addStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(io, monolocale);
		gioco.gioca();
		return io;
	}
	
	
	public static IOSimulator creaSimulazionePartitaBilocale(List<String> comandiDaLeggere) throws FileNotFoundException, FormatoFileNonValidoException {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto bilocale = Labirinto.newBuilder("Labirinto.txt").getLabirinto();
				/*.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) // dove? fa riferimento all’ultima stanza aggiunta
				.addAdiacenza("salotto", "camera", "nord") // camera si trova a nord di salotto
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(io, bilocale);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaTrilocale(List<String> comandiDaLeggere) throws FileNotFoundException, FormatoFileNonValidoException {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = Labirinto.newBuilder("Labirinto.txt").getLabirinto();
				/*.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(io, trilocale);
		gioco.gioca();
		return io;
	}

	public static Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
