package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;

/**
 * Classe modella una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author  angel & ale.papa10 
 * 			(da un'idea del docente POO)	
 * 
 * @see Attrezzo
 * @see Stanza
 * 
 * @version base
*/

public class Stanza implements Comparable<Stanza>{

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String, Attrezzo> attrezzi;
    private Map<Direzione, Stanza> stanzeAdiacenti;
    
    private AbstractPersonaggio personaggio;

    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>(NUMERO_MASSIMO_DIREZIONI);
        this.attrezzi = new HashMap<>(NUMERO_MASSIMO_ATTREZZI);
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if (this.stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI) {
    		Direzione dir;
    		try {
    			dir = Direzione.valueOf(direzione.toUpperCase());
    		} catch (IllegalArgumentException e) {
    			//caso in cui viene specificata una direzione non contemplata dall'enum Direzione
    			System.out.println("Direzione inesistente");
    			return;
    		}
            this.stanzeAdiacenti.put(dir, stanza);
        }
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		Direzione dir;
		try {
			dir = Direzione.valueOf(direzione.toUpperCase());
		} catch (IllegalArgumentException e) {
			//caso in cui viene specificata una direzione non contemplata dall'enum Direzione
			System.out.println("Direzione inesistente");
			return null;
		}
		return this.stanzeAdiacenti.get(dir);
    }

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }
    
    public Map<String, Attrezzo> getMapAttrezzi() {
    	return this.attrezzi;
    }
    
    public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
    	return this.stanzeAdiacenti;
    }
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
    	this.personaggio = personaggio;
    }
    
    public AbstractPersonaggio getPersonaggio() {
    	return this.personaggio;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	return true;
        }
        else {
        	return false;
        }
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.stanzeAdiacenti.keySet())
    		if(direzione != null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi.values())
    		if(attrezzo != null)
    			risultato.append(attrezzo.toString()+" ");
    	if(this.getPersonaggio() != null)
    		risultato.append("\nPersonaggio: " + this.getPersonaggio().getNome());
    		
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);	
	}

	/**
	 * Rimuove un attrezzo dalla stanza e restituisce true se
	 * viene rimosso correttamente, false altrimenti
	 * @param attrezzo
	 * @return
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}

	/**
	 * Restituisce la collezione contente le direzioni della stanza
	 * @return
	 */
	public Set<Direzione> getDirezioni() {
	    return this.stanzeAdiacenti.keySet();
    }
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza)o;
		return (this.getNome().equals(that.getNome()));
	}

	@Override
	public int compareTo(Stanza that) {
	    if (that == null)
	        throw new NullPointerException("L'oggetto Stanza comparato non può essere null");
			
		return this.getAttrezzi().size() - that.getAttrezzi().size();
	}
}