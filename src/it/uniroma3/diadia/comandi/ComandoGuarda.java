package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public class ComandoGuarda implements Comando{
	final private String nome = "Guarda";
	
	@Override
	public void esegui(IO io, Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().toString());
		io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
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
