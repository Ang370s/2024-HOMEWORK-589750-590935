package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.IO;

public class ComandoPosa extends AbstractComando{
	static final private String nome = "Posa";
	
	public ComandoPosa() {
		super(nome);
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
	       if(attrezzo == null)
	    	   io.mostraMessaggio("Questo attrezzo non e' presente nella tua borsa");
	       else {
	    	   if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
	    		   partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
	    		   io.mostraMessaggio("Attrezzo posato nella stanza");
	           } else
	        	   io.mostraMessaggio("Non e' possibile posare l'attrezzo qui perche' la stanza e' piena");
	        }
	}
}
