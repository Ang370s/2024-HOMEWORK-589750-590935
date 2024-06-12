package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.IO;

public class ComandoPrendi extends AbstractComando{
	
	static final private String nome = "Prendi";
	
	public ComandoPrendi() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
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
}
