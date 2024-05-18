package it.uniroma3.diadia.attrezzi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttrezziPerPeso {
	private List<Attrezzo> attrezzi;
	
	public AttrezziPerPeso(Collection<Attrezzo> attrezzi) {
		this.attrezzi = new ArrayList<>(attrezzi);
	}
	
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		for (int i = 0; i < this.attrezzi.size(); i++) {
	        s.append(this.attrezzi.get(i).toString());
	        if (i < this.attrezzi.size() - 1) {
	            s.append(", ");
	        }
	    }
		s.append(" ]");
		
		return s.toString();
	}
}
