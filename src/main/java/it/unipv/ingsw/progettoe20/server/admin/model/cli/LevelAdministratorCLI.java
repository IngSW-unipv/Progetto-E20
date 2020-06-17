package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADD_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.REMOVE_CLI;

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
			System.out.println("Digita " + ADD_CLI + " per aggiungere, " + REMOVE_CLI
					+ " per rimuovere un livello, exit per uscire");
			insertText = scanner.next();
			levelInput(insertText);
			if (insertText.equals(CommandStrings.EXIT)) {
				break;
			}
		}
	}

	private void levelInput(String insertText2) {
		if (ADD_CLI.equals(insertText)) {
			addLevel();
		} else if (REMOVE_CLI.equals(insertText)) {
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
