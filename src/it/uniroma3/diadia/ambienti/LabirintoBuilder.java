package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> stanze;
	
	public LabirintoBuilder() {
		labirinto = new Labirinto();
		stanze = new HashMap<>();
	}
    
    public Map<String, Stanza> getStanze() {
    	return this.stanze;
    }
	
	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza s = new Stanza(stanzaIniziale);
		this.labirinto.setStanzaIniziale(s);
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
		Stanza s = new Stanza(stanza);
		this.ultimaStanzaAggiunta = s;
		this.stanze.put(stanza, s);
		
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
		this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, pesoAttrezzo));
		
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
	
	
}
