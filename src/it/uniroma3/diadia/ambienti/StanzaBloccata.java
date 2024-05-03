package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private String direzioneBloccata;
	private String attrezzoChiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoChiave = attrezzoChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(direzioneBloccata))
			if (super.hasAttrezzo(attrezzoChiave))
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
}
