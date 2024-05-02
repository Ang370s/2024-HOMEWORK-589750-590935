package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	
	String nomeAttrezzo;
	
	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
	       if(attrezzo == null)
	    	   System.out.println("Questo attrezzo non e' presente nella tua borsa");
	       else {
	    	   if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
	    		   partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
	    		   System.out.println("Attrezzo posato nella stanza");
	           } else
	        	   System.out.println("Non e' possibile posare l'attrezzo qui perche' la stanza e' piena");
	        }
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro; 
	}
}
