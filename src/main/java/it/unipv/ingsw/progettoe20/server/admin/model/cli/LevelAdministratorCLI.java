package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.LevelAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.cli.CommandStrings;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

public class LevelAdministratorCLI extends AbstractAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public LevelAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	public void handlerAdministratorCLI(Scanner scanner, String insertText2) {
		while (true) {
			System.out.println("Digita " + CommandStrings.ADMINCLI_ADD + " per aggiungere, "
					+ CommandStrings.ADMINCLI_REMOVE + " per rimuovere un livello, exit per uscire");
			insertText = scanner.next();
			System.out.println("Inserisci nome livello");
			String name = scanner.next();
			levelInput(insertText, name);
			
			if (insertText.equals(CommandStrings.EXIT)) {
				break;
			}
		}
	}

	
	
	private void levelInput(String insertText, String name) {
		try {
			
			if (this.insertText.equals(CommandStrings.ADMINCLI_ADD)) {
				// Se si vuole aggiungere livello
				String lot = scanner.next();
				System.out.println("Inserisci posti livello");
				
				int total = Integer.parseInt(lot);
				addLevel(name, total);
			} else if (this.insertText.equals(CommandStrings.ADMINCLI_REMOVE)) {
				// Se si vuole rimuovere livello
				removeLevel(name);
			}
		} catch (Exception e) {
			System.out.println(CommandStrings.ERROR_GENERIC);}

		}
	public void addLevel(String name, int total) {
		
		LevelAdministrator.getInstance().addLevel(name, total);
		System.out.println("Livello " + name + " aggiunto.");
	}

	public void removeLevel(String name) {

		LevelAdministrator.getInstance().removeLevel(name);
		System.out.println("Livello " + name + " rimosso.");

	}
}
