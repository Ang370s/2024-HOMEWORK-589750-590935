package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public class ComandoFine implements Comando{
	final private String nome = "Fine";
	
	@Override
	public void esegui(IO io, Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");
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
