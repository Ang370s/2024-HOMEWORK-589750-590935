package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurazioniIniziali {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static Properties properties = null;
	
	public static int getCFU() {
		if(properties == null)
			carica();
		return Integer.parseInt(properties.getProperty(CFU));
	}
	
	public static int getPesoMax() {
		if(properties == null)
			carica();
		return Integer.parseInt(properties.getProperty(PESO_MAX));
	}

	private static void carica() {
		properties = new Properties();
		try {
			InputStream inputStream = ConfigurazioniIniziali.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES);
			if (inputStream == null) {
				System.err.println("Errore: File " + DIADIA_PROPERTIES + " non trovato nel classpath.");
				throw new IOException("File diadia.properties non trovato nel classpath");
			}
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
