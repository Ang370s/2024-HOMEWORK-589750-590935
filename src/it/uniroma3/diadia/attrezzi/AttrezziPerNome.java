package it.uniroma3.diadia.attrezzi;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class AttrezziPerNome {
	SortedSet<Attrezzo> attrezzi;
	
	public AttrezziPerNome() {
		attrezzi = new TreeSet<>(new ComparatorePerNome());
	}
	
	public SortedSet<Attrezzo> getAttrezzi(){
		return this.attrezzi;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		Iterator<Attrezzo> iterator = this.attrezzi.iterator();
	    while (iterator.hasNext()) {
	        Attrezzo attrezzo = iterator.next();
	        s.append(attrezzo.toString());
	        if (iterator.hasNext()) {
	            s.append(", ");
	        }
	    }
		s.append(" }");
		
		return s.toString();
	}
}
