package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.LevelAdministrator;
import it.unipv.ingsw.progettoe20.server.cli.CommandStrings;

public class LevelAdministratorCLI extends AbstractAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public LevelAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	public void handlerAdministratorCLI(Scanner scanner, String insertText) {
		while (true) {
			System.out.println("Digita " + CommandStrings.ADMINCLI_ADD + " per aggiungere, "
					+ CommandStrings.ADMINCLI_REMOVE + " per rimuovere un livello, exit per uscire");
			insertText = scanner.next();
			levelInput(insertText);
			if (insertText.equals(CommandStrings.EXIT)) {
				break;
			}
		}
	}

	private void levelInput(String insertText2) {
		if (insertText.equals(CommandStrings.ADMINCLI_ADD)) {
			addLevel();
		} else if (insertText.equals(CommandStrings.ADMINCLI_REMOVE)) {
			removeLevel();
		}
	}

	public void addLevel() {
		System.out.println("Inserisci nome livello");
		insertText = scanner.next();
		String name = insertText;
		System.out.println("Inserisci posti livello");
		insertText = scanner.next();
		int total = Integer.parseInt(insertText);
		LevelAdministrator.getInstance().addLevel(name, total);
		System.out.println("Livello " + name + " aggiunto.");
	}

	public void removeLevel() {
		System.out.println("Inserisci nome livello");
		insertText = scanner.next();
		String name = insertText;

		LevelAdministrator.getInstance().removeLevel(name);
		System.out.println("Livello " + name + " rimosso.");

	}
}
