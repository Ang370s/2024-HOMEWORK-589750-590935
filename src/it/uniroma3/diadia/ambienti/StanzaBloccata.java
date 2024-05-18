package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String attrezzoChiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoChiave = attrezzoChiave;
	}
	
	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public String getAttrezzoChiave() {
		return attrezzoChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(direzioneBloccata))
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
		StanzaBloccata that = (StanzaBloccata)o;
		return (this.getNome().equals(that.getNome()) 
				&& this.getDirezioneBloccata().equals(that.getDirezioneBloccata()) 
				&& this.getAttrezzoChiave().equals(that.getAttrezzoChiave()));
	}
}
