package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADD_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.EXIT_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.LIST_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.REMOVE_CLI;

import java.util.List;
import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.LevelAdministrator;
import it.unipv.ingsw.progettoe20.server.model.Level;

/**
 * Classe per la gestione dei livelli nella CLI dell'amministratore
 */
public class LevelAdministratorCLI extends AbstractAdministratorCLI {

	/**
	 * Crea una istanza di LevelAdministratorCLI
	 *
	 * @param scanner    scanner per la lettura da linea di comando
	 * @param insertText stringa inserita dall'utente
	 */
	public LevelAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	public void handlerAdministratorCLI() {
		while (true) {
			System.out.println("Inserisci:\n" + ADD_CLI + " per aggiungere un livello \n" + REMOVE_CLI
					+ " per rimuovere un livello \n" + LIST_CLI + " per ottenere la lista dei livelli \n" + EXIT_CLI
					+ " per uscire");
			insertText = scanner.next();
			if (insertText.equals(LIST_CLI)) {
				// Se si vuole ottenere la lista dei livelli
				printList();
			} else if (insertText.equals(EXIT_CLI)) {
				// Se si vuole uscire
				break;
			} else {
				// Se si vuole aggiungere o togliere un livello

				levelInput(insertText);
			}
		}
	}

	private void levelInput(String insertText) {
		try {
			System.out.println("Inserisci nome livello");
			scanner = new Scanner(System.in);
			String name = scanner.next();
			if (ADD_CLI.equals(insertText)) {
				// Se si vuole aggiungere livello
				System.out.println("Inserisci posti livello");
				String lot = scanner.next();
				int total = Integer.parseInt(lot);
				addLevel(name, total);
			} else if (REMOVE_CLI.equals(insertText)) {
				// Se si vuole rimuovere livello
				removeLevel(name);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	private void printList() {
		List<Level> levels = LevelAdministrator.getInstance().getLevelList();
		System.out.println("Elenco dei livelli:");
		for (Level l : levels) {
			System.out.println(l.getName() + "\t posti totali: " + l.getTotal() + "\t posti disponibili: "
					+ l.getAvailable() + "\n");
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
