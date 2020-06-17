package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.client.ClientStrings;
import it.unipv.ingsw.progettoe20.server.admin.model.LevelAdministrator;

public class LevelAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public LevelAdministratorCLI(Scanner scanner, String insertText) {
		this.insertText = insertText;
		this.scanner = scanner;
		LevelCLI(insertText);
	}

	private void LevelCLI(String insertText) {
		while (true) {
			System.out.println("Digita add per aggiungere, rem per rimuovere un livello, exit per uscire");
			insertText = scanner.next();
			levelInput(insertText);
			if (insertText.equals(ClientStrings.EXIT)) {
				break;
			}
		}
	}

	private void levelInput(String insertText2) {
		if (insertText.equals("add")) {
			addLevel();
		} else if (insertText.equals("rem")) {
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
