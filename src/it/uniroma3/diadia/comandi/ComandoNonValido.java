package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public class ComandoNonValido implements Comando{
	final private String nome = "Non valido";
	
	@Override
	public void esegui(IO io, Partita partita) {
		io.mostraMessaggio("Comando non valido...");
	}

	@Override
	public void setParametro(String parametro) { }
	
	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
