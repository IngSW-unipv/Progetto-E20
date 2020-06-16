package it.unipv.ingsw.progettoe20.server.admin.model;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.client.ClientStrings;

public class cliAdministrator {
	public cliAdministrator() {
		initCli();
		
	}
	void initCli() {
		 String insertText = "";
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("Hai scelto la modalità command line input, inserisci 'lev' per modificare i livelli, 'prices' per le tariffe e 'lot' per i posti del parcheggio");
	            insertText = scanner.next();
	            
	           
	        }
		
	}
}
