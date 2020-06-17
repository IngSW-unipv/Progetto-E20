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
			System.out.println("Insert: \n" + ADD_CLI + ": to add a level \n" + REMOVE_CLI + ": to remove a level \n"
					+ LIST_CLI + ": to list the levels \n" + EXIT_CLI + ": to exit");
			insertText = scanner.next();
			if (insertText.equals(LIST_CLI)) {
				// Se si vuole ottenere la lista dei livelli
				printList();
			} else if (insertText.equals(EXIT_CLI)) {
				// Se si vuole uscire
				break;
			} else if (insertText.equals(ADD_CLI) || insertText.equals(REMOVE_CLI)) {
				// Se si vuole aggiungere o togliere un livello

				levelInput(insertText);
			}
		}
	}

	/**
	 * Gestisce l'aggiunta o la rimozione dei livelli
	 *
	 * @param insertText operazione inserita come stringa dall'utente
	 */
	private void levelInput(String insertText) {
		try {
			System.out.println("Insert the level name");
			scanner = new Scanner(System.in);
			String name = scanner.next();
			if (ADD_CLI.equals(insertText)) {
				// Se si vuole aggiungere livello
				System.out.println("Inserte the parking lots");
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

	/**
	 * Stampa la lista di livelli presenti sul database
	 */
	private void printList() {
		List<Level> levels = LevelAdministrator.getInstance().getLevelList();
		System.out.println("Level list");
		for (Level l : levels) {
			System.out.println(
					l.getName() + "\t total lots: " + l.getTotal() + "\t available lots: " + l.getAvailable() + "\n");
		}
	}

	/**
	 * Chiama il metodo del LevelAdministrator per l'aggiunta di un livello
	 *
	 * @param name  nome del nuovo livello
	 * @param total capacità totale del livello
	 */
	public void addLevel(String name, int total) {
		LevelAdministrator.getInstance().addLevel(name, total);
		System.out.println("Level " + name + " added");
	}

	/**
	 * Chiama il metodo del LevelAdministrator per la rimozione di un livello
	 *
	 * @param name nome del livello da eliminare
	 */
	public void removeLevel(String name) {

		LevelAdministrator.getInstance().removeLevel(name);
		System.out.println("Level " + name + " removed");

	}
}
