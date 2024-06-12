package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

public class ComandoFine extends AbstractComando{

	static final private String nome = "Fine";

	public ComandoFine() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
}
