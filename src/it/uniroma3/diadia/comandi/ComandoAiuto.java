package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"prendi", "posa","vai", "aiuto", "fine"};
	final private String nome = "Aiuto";
	
	@Override
	public void esegui(IO io, Partita partita) {
		for(int i=0; i < elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("\n");
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
