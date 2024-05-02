package it.uniroma3.diadia;

public class ComandoGuarda implements Comando{
	
	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getStanzaCorrente().toString());
		System.out.println("CFU rimanenti: " + partita.getGiocatore().getCfu());
	}

	@Override
	public void setParametro(String parametro) { }
}
