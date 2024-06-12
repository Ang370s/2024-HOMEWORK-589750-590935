package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto extends AbstractComando{

	static final private String[] elencoComandi = {"guarda", "prendi", "posa", "vai", "saluta", "interagisci", "regala", "aiuto", "fine"};
	static final private String nome = "Aiuto";
	
	public ComandoAiuto() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		for(int i=0; i < elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("\n");
	}
}
