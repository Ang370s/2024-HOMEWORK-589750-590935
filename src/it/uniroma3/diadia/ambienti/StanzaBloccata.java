package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	private Direzione direzioneBloccata;
	private String attrezzoChiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
		super(nome);
		this.direzioneBloccata = Direzione.valueOf(direzioneBloccata.toUpperCase());
		this.attrezzoChiave = attrezzoChiave;
	}
	
	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public String getAttrezzoChiave() {
		return attrezzoChiave;
	}
	
	public boolean isBloccata() {
		return true;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (Direzione.valueOf(direzione.toUpperCase()).equals(direzioneBloccata))
			if (this.hasAttrezzo(attrezzoChiave))
				return super.getStanzaAdiacente(direzione);
			else
				return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	
	@Override
	public String getDescrizione() {
		return (super.toString() + "\nDirezione bloccata se non hai l'attrezzo \"" + this.attrezzoChiave + "\": " + this.direzioneBloccata);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || o.getClass() != this.getClass())
			return false;
		
		StanzaBloccata that = (StanzaBloccata)o;
		return (this.getNome().equals(that.getNome()) 
				&& this.getDirezioneBloccata().equals(that.getDirezioneBloccata()) 
				&& this.getAttrezzoChiave().equals(that.getAttrezzoChiave()));
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode() + 
				this.getDirezioneBloccata().hashCode() + this.getAttrezzoChiave().hashCode();
	}
}
