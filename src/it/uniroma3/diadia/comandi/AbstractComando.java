package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	
	private String nome;
	private String parametro;
	
	public AbstractComando(String nome) {
		this.nome = nome;
	}
	
	@Override
	public abstract void esegui(IO io, Partita partita);

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
}
