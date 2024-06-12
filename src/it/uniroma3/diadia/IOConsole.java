package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe gestisce i messaggi da visualizzare
 * e gli input inseriti dall'utente
 * 
 * @author  angel & ale.papa10 
 * 			(da un'idea del docente POO)
 * 
 * @version base
 */
public class IOConsole implements IO {
	
	private Scanner scannerDiLinee;

    public IOConsole(Scanner scanner) {
        this.scannerDiLinee = scanner;
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public String leggiRiga() {
        return scannerDiLinee.nextLine();
    }
}