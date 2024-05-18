package it.uniroma3.diadia.attrezzi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AttrezziRaggruppati {
	Map<Integer, Set<Attrezzo>> attrezzi;
	
	public AttrezziRaggruppati() {
		attrezzi = new HashMap<>();
	}
	
	public Map<Integer, Set<Attrezzo>> getAttrezzi(){
		return this.attrezzi;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		//scorro tutte le chiavi
		for (Integer chiave : this.attrezzi.keySet()) {
			s.append("(" + chiave + ", { ");
			
			//scorro tuuti gli attrezzi con quella chiave
			Iterator<Attrezzo> iterator = this.attrezzi.get(chiave).iterator();
			while(iterator.hasNext()) {
				s.append(iterator.next().toString());
				if(iterator.hasNext())
					s.append(", ");
			}
			s.append(" } ); ");
		}
		
		return s.toString();
	}
}
