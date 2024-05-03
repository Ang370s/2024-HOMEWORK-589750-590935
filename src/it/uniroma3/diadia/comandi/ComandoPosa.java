package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.IO;

public class ComandoPosa implements Comando{
	final private String nome = "Posa";
	
	String nomeAttrezzo;
	
	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	@Override
	public void esegui(IO io, Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
	       if(attrezzo == null)
	    	   io.mostraMessaggio("Questo attrezzo non e' presente nella tua borsa");
	       else {
	    	   if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
	    		   partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
	    		   io.mostraMessaggio("Attrezzo posato nella stanza");
	           } else
	        	   io.mostraMessaggio("Non e' possibile posare l'attrezzo qui perche' la stanza e' piena");
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
