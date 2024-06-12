package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String nomeAttrezzo;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	public boolean isBuia() {
		return true;
	}
	
	public String getNomeAttrezzo() {
		return this.nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if (super.hasAttrezzo(nomeAttrezzo))
			return super.getDescrizione();			
		else
			return "Qui c'è buio pesto!";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || o.getClass() != this.getClass())
			return false;
		
		StanzaBuia that = (StanzaBuia) o;
		
		return this.getNome().equals((that.getNome())) && this.getNomeAttrezzo() == that.getNomeAttrezzo();
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode() + this.getNomeAttrezzo().hashCode();
	}
}
