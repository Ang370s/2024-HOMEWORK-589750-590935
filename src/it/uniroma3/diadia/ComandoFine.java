package it.uniroma3.diadia;

public class ComandoFine implements Comando{
	
	@Override
	public void esegui(Partita partita) {
		System.out.println("Grazie di aver giocato!");
	}
	
	@Override
	public void setParametro(String parametro) { }
}
