package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.IO;

public class ComandoPrendi implements Comando{
	final private String nome = "Prendi";
	
	String nomeAttrezzo;
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
        if(attrezzo == null)
        	io.mostraMessaggio("Questo attrezzo non e' presente nella stanza");
        else {
            if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
                partita.getStanzaCorrente().removeAttrezzo(attrezzo);
                io.mostraMessaggio("Attrezzo aggiunto nella borsa correttamente.");
            } else
            	io.mostraMessaggio("Non c'e' abbastanza spazio nella borsa per contenere l'oggetto.");
        }
	}
	
	@Override
	public void setParametro(String parametro) { 
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}
