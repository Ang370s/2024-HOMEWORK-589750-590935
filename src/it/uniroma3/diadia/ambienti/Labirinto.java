package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Questa classe si occupa della creazione del
 * labitrinto, collegando insieme le stanze e
 * mettendo degli attrezzi al loro interno
 * 
 * @author angel & ale.papa10
 * 
 * @see Stanza
 * @see Attrezzo
 * 
 * @version base
 */

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> stanze;
	
	public Labirinto() {}
	
	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanze = c.getStanze();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

    private Labirinto(StringReader reader) throws FormatoFileNonValidoException, FileNotFoundException {
        CaricatoreLabirinto c = new CaricatoreLabirinto(reader);
        c.carica();
		this.stanze = c.getStanze();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
    }
	
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	public static LabirintoBuilder newBuilder(StringReader reader) throws FormatoFileNonValidoException, FileNotFoundException {
        return new LabirintoBuilder(reader);
    }
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	public Map<String,Stanza> getStanze() {
		return this.stanze;
	}
	
	public static class LabirintoBuilder extends Labirinto{
		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> stanze;
		private Stanza stanzaIniziale;
		private Stanza stanzaVincente;
		
		private LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
            this.labirinto = new Labirinto(labirinto);
    		this.stanze = this.labirinto.getStanze();
    		this.stanzaIniziale = this.labirinto.getStanzaIniziale();
    		this.stanzaVincente = this.labirinto.getStanzaVincente();
        }
		
		private LabirintoBuilder(StringReader reader) throws FormatoFileNonValidoException, FileNotFoundException {
		    this.labirinto = new Labirinto(reader);
		    this.stanze = this.labirinto.getStanze();
    		this.stanzaIniziale = this.labirinto.getStanzaIniziale();
    		this.stanzaVincente = this.labirinto.getStanzaVincente();
		}

	    
	    public Map<String, Stanza> getStanze() {
	    	return this.stanze;
	    }
		
		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			Stanza s = new Stanza(stanzaIniziale);
			this.labirinto.setStanzaIniziale(s);
			this.stanzaIniziale = s;
			this.ultimaStanzaAggiunta = s;
			this.stanze.put(stanzaIniziale, s);
			
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			Stanza s= new Stanza(stanzaVincente);
			this.labirinto.setStanzaVincente(s);
			this.ultimaStanzaAggiunta = s;
			this.stanze.put(stanzaVincente, s);
			
			return this;
		}
		
		public LabirintoBuilder addStanza(String stanza) {
			
			if (this.stanze.containsKey(stanza)) {
		        this.stanze.put(stanza, new Stanza(stanza));
		    } else {
		        Stanza s = new Stanza(stanza);
		        this.ultimaStanzaAggiunta = s;
		        this.stanze.put(stanza, s);
		    }
			
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
			this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo, String nomeStanza) {
			Stanza stanza = this.stanze.get(nomeStanza);
			stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String s1, String s2, String dir) {
			this.stanze.get(s1).impostaStanzaAdiacente(dir, stanze.get(s2));

			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			Stanza sM = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
			this.ultimaStanzaAggiunta = sM;
			this.stanze.put(nomeStanzaMagica, sM);
			
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzioneBloccata, String attrezzoChiave) {
			Stanza sB = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, attrezzoChiave);
			this.ultimaStanzaAggiunta = sB;
			this.stanze.put(nomeStanzaBloccata, sB);
			
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzo) {
			Stanza sB = new StanzaBuia(nomeStanzaBuia, nomeAttrezzo);
			this.ultimaStanzaAggiunta = sB;
			this.stanze.put(nomeStanzaBuia, sB);
			
			return this;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Stanza getStanzaVincente() {
			return this.stanzaVincente;
		}
		
		public Stanza getStanzaIniziale() {
			return this.stanzaIniziale;
		}
	}
}
