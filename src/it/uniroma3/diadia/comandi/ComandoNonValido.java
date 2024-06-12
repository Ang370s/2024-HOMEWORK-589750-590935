package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public class ComandoNonValido extends AbstractComando{

	static final private String nome = "Non valido";

	public ComandoNonValido() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		io.mostraMessaggio("Comando non valido...");
	}
}
