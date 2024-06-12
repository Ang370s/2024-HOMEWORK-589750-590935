package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";     

	/* prefisso della riga contenente tutti i nomi delle stanze buie nel formato <nomeStanza> <nomeAttrezzoLuce> */
	private static final String STANZE_BUIE_MARKER = "Buie: ";      

	/* prefisso della riga contenente tutti i nomi delle stanze magiche nel formato <nomeStanza> <sogliaMagica> */
	private static final String STANZE_MAGICHE_MARKER = "Magiche: ";       

	/* prefisso della riga contenente tutti i nomi delle stanze bloccate nel formato <nomeStanza> <direzioneBloccata> <AttrezzoChiave> */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccate: "; 

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: "; 
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeMago> <presentazione> <attrezzo> <peso> <nomeStanza> */
	private static final String PERSONAGGI_MARKER_MAGO = "Mago:";
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> <presentazione> <nomeStanza> */
	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <presentazione> <attrezzo> <peso> <nomeStanza> */
	private static final String PERSONAGGI_MARKER_CANE = "Cane:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un stanze (vedi POO-26-eccezioni-file.pdf)

		Stanze: LabCampusOne, Biblioteca
		Buie: 
		Magiche: 
		Bloccate: 
		Inizio: LabCampusOne
		Vincente: Biblioteca
		Attrezzi: 
		Mago: 
		Strega: 
		Cane: 
		Uscite: Biblioteca est LabCampusOne

	 */
	private BufferedReader reader;

	private Map<String,Stanza> stanze;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private int numeroLinea;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.stanze = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.stanze = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiStanzeBuie();
			this.leggiStanzeMagiche();
			this.leggiStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			numeroLinea++;
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
        List<String> listStanze = separaStringheAlleVirgole(nomiStanze);
		for(String nomeStanza : listStanze) {
			this.stanze.put(nomeStanza, new Stanza(nomeStanza));
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",\\s*");
		try (Scanner scannerDiParole = scanner) {
			while (scanner.hasNext()) {
				result.add(scanner.next());
			}
		}
		return result;
	}

	private void leggiStanzeBuie() throws FormatoFileNonValidoException {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(stanzeBuie)) {
	        scannerDiLinea.useDelimiter(" |, ");
			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
				String nomeStanzaBuia = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo che illumina la stanza buia "+nomeStanzaBuia));
				String nomeAttrezzo = scannerDiLinea.next();
				this.impostaStanzaBuia(nomeStanzaBuia, nomeAttrezzo);
			}
		}
	}
	
	private void impostaStanzaBuia(String nomeStanza, String nomeAttrezzo) throws FormatoFileNonValidoException {
		check(isStanzaValida(nomeStanza),"Stanza da rendere buia non esistente " + nomeStanza);
		this.stanze.put(nomeStanza, new StanzaBuia(nomeStanza, nomeAttrezzo));
	}

	private void leggiStanzeMagiche() throws FormatoFileNonValidoException {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(stanzeMagiche)) {
	        scannerDiLinea.useDelimiter(" |, ");
			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza magica."));
				String nomeStanzaMagica = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la soglia di una stanza magica "+nomeStanzaMagica));
				String sogliaMagica = scannerDiLinea.next();
				this.impostaStanzaMagica(nomeStanzaMagica, sogliaMagica);
			}
		}
	}
	
	private void impostaStanzaMagica(String nomeStanza, String soglia) throws FormatoFileNonValidoException {
		int sogliaMagica;
		try {
			sogliaMagica = Integer.parseInt(soglia);
			check(isStanzaValida(nomeStanza),"Stanza da rendere magica non esistente " + nomeStanza);
			this.stanze.put(nomeStanza, new StanzaMagica(nomeStanza, sogliaMagica));
		}
		catch (NumberFormatException e) {
			check(false, "Soglia magica di "+nomeStanza+" non valido");
		}
		
	}
	
	private void leggiStanzeBloccate() throws FormatoFileNonValidoException {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(stanzeBloccate)) {
	        scannerDiLinea.useDelimiter(" |, ");
			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
				String nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata di una uscita della stanza "+nomeStanza));
				String dirBloccata = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+nomeStanza+" nella direzione "+dirBloccata));
				String nomeAttrezzoChiave = scannerDiLinea.next();
				
				this.impostaStanzaBloccata(nomeStanza, dirBloccata, nomeAttrezzoChiave);
			}
		}
	}
	
	private void impostaStanzaBloccata(String nomeStanza, String dirBloccata, String nomeAttrezzo) throws FormatoFileNonValidoException {
		check(isStanzaValida(nomeStanza),"Stanza da bloccare non esistente " + nomeStanza);
		this.stanze.put(nomeStanza, new StanzaBloccata(nomeStanza, dirBloccata, nomeAttrezzo));
	}
	
	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(nomeStanzaIniziale)) {
			scannerDiLinea.useDelimiter(" |, ");
			check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
			String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
			check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
			this.stanzaIniziale = this.stanze.get(nomeStanzaIniziale);
			this.stanzaVincente = this.stanze.get(nomeStanzaVincente);
		}
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.stanze.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	
	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del mago ...\n"));
					String mago = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del mago\n"));
					String presentazione = scannerDiLinea.next();					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione del nome dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String nomeAttrezzo = scannerDiLinea.next();				
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione nel peso dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String pesoAttrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il mago non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
	
					try {
						int peso = Integer.parseInt(pesoAttrezzo);
						Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);

						AbstractPersonaggio personaggio = new Mago(mago, presentazione, attrezzo);
						this.stanze.get(nomeStanza).setPersonaggio(personaggio);
					}
					catch (NumberFormatException e) {
						check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
					}
				}
			}
		} 
	}
	
	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione della strega ...\n"));
					String strega = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione della strega\n"));
					String presentazione = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere la strega non esiste\n"));
					String nomeStanza = scannerDiLinea.next();					


					AbstractPersonaggio personaggio = new Strega(strega, presentazione);
					this.stanze.get(nomeStanza).setPersonaggio(personaggio);
				}
			}
		} 
	}
	
	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del cane ...\n"));
					String cane = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del cane\n"));
					String presentazione = scannerDiLinea.next();					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione del nome dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String nomeAttrezzo = scannerDiLinea.next();				
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione nel peso dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String pesoAttrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il cane non esiste\n"));
					String nomeStanza = scannerDiLinea.next();				
					
					try {
						int peso = Integer.parseInt(pesoAttrezzo);
						Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);

						AbstractPersonaggio personaggio = new Cane(cane, presentazione, attrezzo);
						this.stanze.get(nomeStanza).setPersonaggio(personaggio);
					}
					catch (NumberFormatException e) {
						check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
					}
				}
			}
		} 
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.stanze.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {
	        scannerDiLinea.useDelimiter(" |, ");
			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();
				
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Direzione direzione = Direzione.valueOf(dir.toUpperCase());
		this.stanze.get(stanzaDa).impostaStanzaAdiacente(direzione.toString(), this.stanze.get(nomeA));
		this.stanze.get(nomeA).impostaStanzaAdiacente(direzione.opposta().toString(), this.stanze.get(stanzaDa));
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Map<String,Stanza> getStanze() {
		return this.stanze;
	}
}
