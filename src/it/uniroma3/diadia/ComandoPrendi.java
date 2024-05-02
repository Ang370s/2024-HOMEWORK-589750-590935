package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	
	String nomeAttrezzo;
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
        if(attrezzo == null)
        	System.out.println("Questo attrezzo non e' presente nella stanza");
        else {
            if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
                partita.getStanzaCorrente().removeAttrezzo(attrezzo);
                System.out.println("Attrezzo aggiunto nella borsa correttamente.");
            } else
            	System.out.println("Non c'e' abbastanza spazio nella borsa per contenere l'oggetto.");
        }
	}
	
	@Override
	public void setParametro(String parametro) { 
		this.nomeAttrezzo = parametro;
	}
}
