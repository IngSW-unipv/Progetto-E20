package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADD_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ERROR_CLI;
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
	public void handlerAdministratorCLI(Scanner scanner, String insertText2) {
		while (true) {
			System.out.println("Digita " + ADD_CLI + " per aggiungere, " + REMOVE_CLI
					+ " per rimuovere un livello, exit per uscire");
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
			if (ADD_CLI.equals(insertText)) {
				// Se si vuole aggiungere livello
				String lot = scanner.next();
				System.out.println("Inserisci posti livello");
				int total = Integer.parseInt(lot);
				addLevel(name, total);
			} else if (REMOVE_CLI.equals(insertText)) {
				// Se si vuole rimuovere livello
				removeLevel(name);
			}
		} catch (Exception e) {
			System.out.println(ERROR_CLI);
		}
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
