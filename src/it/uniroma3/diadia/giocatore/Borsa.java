package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.attrezzi.AttrezziPerNome;
import it.uniroma3.diadia.attrezzi.AttrezziPerPeso;
import it.uniroma3.diadia.attrezzi.AttrezziRaggruppati;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

/**
 * Questa classe gestisce la borsa che possiede il giocatore
 * dove può riporre gli attrezzi che prende dalle stanze
 * fino a quando c'è spazio in essa. Può anche decidere
 * di togliere attrezzi dalla borsa
 * 
 * @author  angel & ale.papa10 
 * 			(da un'idea del docente POO)
 * 
 * @see Attrezzo
 * 
 * @version base
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = ConfigurazioniIniziali.getPesoMax();
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	/**
	 * Costruttore che crea una borsa con il numero
	 * fisso (10) di peso massimo ospitabile
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() + attrezzo.getPeso() <= this.pesoMax) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso(){
		 int pesoTotale = 0;
		 for(Attrezzo a : this.attrezzi.values())
			 pesoTotale += a.getPeso();
		 return pesoTotale;
	}


	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}


	public String toString() {
		StringBuilder s = new StringBuilder();
		 if (!this.attrezzi.isEmpty()) {
			 s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			 s.append("\n	Ordinato per peso: ");
			 s.append(this.getContenutoOrdinatoPerPeso().toString());
			 s.append("\n	Ordinato per nome: ");
			 s.append(this.getContenutoOrdinatoPerNome().toString());
			 s.append("\n	Raggruppato per peso: ");
			 s.append(this.getContenutoRaggruppatoPerPeso().toString());
		 }
		 else
			 s.append("Borsa vuota");
		 return s.toString();
	}
	
	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome
	 * @return
	 */
	public AttrezziPerPeso getContenutoOrdinatoPerPeso(){
		AttrezziPerPeso attrezziOrdinatiPerPeso = new AttrezziPerPeso(this.attrezzi.values());
		
		Collections.sort(attrezziOrdinatiPerPeso.getAttrezzi());
		
		return attrezziOrdinatiPerPeso;
	}
	
	/**
	 * Restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 */
	public AttrezziPerNome getContenutoOrdinatoPerNome(){
		AttrezziPerNome attrezziOrdinatiPerNome = new AttrezziPerNome();
		
		attrezziOrdinatiPerNome.getAttrezzi().addAll(this.attrezzi.values());
		
		return attrezziOrdinatiPerNome;
	}
	
	/**
	 * Restituisce una mappa che associa un intero (rappresentante un
	 * peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
	 * peso: tutti gli attrezzi dell'insieme che figura come valore hanno
	 * lo stesso peso pari all'intero che figura come chiave
	 */
	public AttrezziRaggruppati getContenutoRaggruppatoPerPeso(){
		AttrezziRaggruppati attrezziRaggrupatiPerPeso = new AttrezziRaggruppati();
		
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			int peso = attrezzo.getPeso();
			
			//se la mappa non contiene ancora quel peso la aggiungo come chiavi e ci collego un set come valore
			if (!attrezziRaggrupatiPerPeso.getAttrezzi().containsKey(peso)) {
				attrezziRaggrupatiPerPeso.getAttrezzi().put(peso, new HashSet<Attrezzo>());
			}
			
			//aggiungo l'attrezzo nel Set di quel peso
			attrezziRaggrupatiPerPeso.getAttrezzi().get(peso).add(attrezzo);
		}
		
		return attrezziRaggrupatiPerPeso;
	}
	
	
	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome
	 * @return
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> attrezziOrdinatiPerPeso = new TreeSet<>(new ComparatorePerPeso());
		
		attrezziOrdinatiPerPeso.addAll(this.attrezzi.values());
		
		return attrezziOrdinatiPerPeso;
	}
}