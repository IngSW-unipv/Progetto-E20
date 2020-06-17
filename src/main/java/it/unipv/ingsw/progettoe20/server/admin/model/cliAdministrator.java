package it.unipv.ingsw.progettoe20.server.admin.model;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.client.ClientStrings;

public class cliAdministrator {
	private Scanner scanner;
	private String insertText;
	public cliAdministrator() {
		initCli();
		
	}
	void initCli() {
		 insertText = "";
	        scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("Hai scelto la modalità command line input, inserisci 'lev' per modificare i livelli, 'prices' per le tariffe e 'lot' per i posti del parcheggio");
	            insertText = scanner.next();
	            if (insertText.equals("lev")) {
	            	while (true) {
	            	 System.out.println("Digita add per aggiungere, rem per rimuovere un livello, exit per uscire");
	            	 insertText = scanner.next();
	            	 levelInput(insertText);
	            	 if(insertText.equals(ClientStrings.EXIT)) break;
	            	}
	            }else if(insertText.equals("prices")){
	            	
	            }else if(insertText.equals("lot")){
	            	
	            }else if (insertText.equals(ClientStrings.EXIT)) break;
	            
	           
	        }
	        
		
	}
	public void levelInput(String insertText) {
		if (insertText.equals("add")) {
			addLevel();
		}else if(insertText.equals("rem")) {
			removeLevel();
		}	
		
	}
	public void addLevel() {
		System.out.println("Inserisci nome livello");
        insertText = scanner.next();
        String name= insertText;
        System.out.println("Inserisci posti livello");
        insertText = scanner.next(); 
        int total= Integer.parseInt(insertText);
        LevelAdministrator.getInstance().addLevel(name, total);
        System.out.println("Livello "+name+" aggiunto.");
	}
	public void removeLevel() {
		System.out.println("Inserisci nome livello");
        insertText = scanner.next();
        String name= insertText;
        
        LevelAdministrator.getInstance().removeLevel(name);
        System.out.println("Livello "+name+" rimosso.");
		
		
	}
}
