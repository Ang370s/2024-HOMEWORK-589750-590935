package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public interface Comando {

	/**
	* esecuzione del comando
	*/
	public void esegui(IO io, Partita partita);
	
	/**
	* set parametro del comando
	*/
	public void setParametro(String parametro);
	
	public String getNome();
	
	public String getParametro();

}