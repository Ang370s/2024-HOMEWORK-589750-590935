package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
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
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
			return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if(attrezzi[i] != null)
				peso += this.attrezzi[i].getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	    Attrezzo a = null;
	    int posAttrezzo = -1;
	    for (int i = 0; i < attrezzi.length; i++) {
	        if (attrezzi[i] != null && attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            a = attrezzi[i];
	            posAttrezzo = i;
	            numeroAttrezzi--;
	            break;
	        }
	    }
	    if (posAttrezzo != -1) {
	        for (int i = posAttrezzo; i < attrezzi.length - 1; i++) {
	            attrezzi[i] = attrezzi[i + 1];
	        }
	        attrezzi[attrezzi.length - 1] = null;
	    }
	    return a;
	}

	
	public String toString() {
		StringBuilder s = new StringBuilder();
		 if (!this.isEmpty()) {
			 s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			 for (int i= 0; i<this.numeroAttrezzi; i++)
			 s.append(attrezzi[i].toString()+" ");
		 }
		 else
			 s.append("Borsa vuota");
		 return s.toString();
	}
}