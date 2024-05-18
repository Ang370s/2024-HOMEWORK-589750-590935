package it.uniroma3.diadia.attrezzi;

import java.lang.Comparable;


/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  angel & ale.papa10 
 * 			(da un'idea del docente POO)
 * 
 * @see Stanza
 * 
 * @version base
 */
public class Attrezzo implements Comparable<Attrezzo>{

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public int compareTo(Attrezzo that) {
		Integer ris = Integer.valueOf(this.getPeso()).compareTo(that.getPeso());
		if ( ris == 0)
			return this.getNome().compareTo(that.getNome());
		
		return ris;
	}
	
	@Override
	public boolean equals(Object o) {
		Attrezzo that = (Attrezzo) o;
		return (this.getNome().equals(that.getNome()) && this.getPeso() == that.getPeso());
	}

}