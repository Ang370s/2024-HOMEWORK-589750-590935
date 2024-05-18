package it.uniroma3.diadia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {
	
	private List<String> righeDaLeggere;
	private int indiceRigheDaLeggere;
	private Map<Integer, List<String>> righeProdotte;
	private int ultimoIndiceMappaMostrato;
	private int ultimoIndiceListaMostrato;
	
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheDaLeggere = 0;
		this.righeProdotte = new HashMap<Integer, List<String>>();
		this.ultimoIndiceListaMostrato = 0;
		this.ultimoIndiceListaMostrato = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		if(!this.righeProdotte.containsKey(this.indiceRigheDaLeggere))
			this.righeProdotte.put(this.indiceRigheDaLeggere, new LinkedList<String>());
		List<String> l = this.righeProdotte.get(this.indiceRigheDaLeggere);
		l.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere.get(this.indiceRigheDaLeggere);
		this.indiceRigheDaLeggere++;
		return rigaLetta;
	}
	
	public String nextMessaggio() {
		List<String> messaggiDaMostrare = this.righeProdotte.get(this.ultimoIndiceMappaMostrato);
		if(this.ultimoIndiceListaMostrato < messaggiDaMostrare.size()) {
			String messaggio = messaggiDaMostrare.get(this.ultimoIndiceListaMostrato);
			this.ultimoIndiceListaMostrato++;
			return messaggio;
		}
		this.ultimoIndiceListaMostrato = 0;
		this.ultimoIndiceMappaMostrato++;
		return this.nextMessaggio();
	}
	
	public boolean hasNextMessaggio() {
		List<String> messaggiDaMostrare = this.righeProdotte.get(this.ultimoIndiceMappaMostrato);
		if(this.ultimoIndiceListaMostrato < messaggiDaMostrare.size()) 
			return true;
		else
			return this.righeProdotte.containsKey(this.ultimoIndiceMappaMostrato +1);
	}

}