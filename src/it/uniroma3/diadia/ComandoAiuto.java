package it.uniroma3.diadia;

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"prendi", "posa","vai", "aiuto", "fine"};
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i < elencoComandi.length; i++) 
			System.out.println(elencoComandi[i]+" ");
		System.out.println();
	}
	
	@Override
	public void setParametro(String parametro) { }
}
