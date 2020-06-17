package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADD_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ERROR_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.EXIT_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.REMOVE_CLI;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.ParkingLotsAdministrator;

public class ParkingLotsAdministratorCLI extends AbstractAdministratorCLI {

	/**
	 * Crea una istanza di ParkingLotsAdministratorCLI
	 *
	 * @param scanner    scanner per la lettura da linea di comando
	 * @param insertText stringa inserita dall'utente
	 */
	public ParkingLotsAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	protected void handlerAdministratorCLI() {
		while (true) {
			System.out.println("Insert: \n" + ADD_CLI + ": to add parking lots \n" + REMOVE_CLI
					+ ": to remove parking lots \n" + EXIT_CLI + " to exit");
			insertText = scanner.next();
			if (insertText.equals(ADD_CLI) || insertText.equals(REMOVE_CLI)) {
				System.out.println("Inster level name");
				String name = scanner.next();
				System.out.println("Insert parking lots");
				String lot = scanner.next();
				int total = Integer.parseInt(lot);
				lotInput(insertText, name, total);
			}
			if (EXIT_CLI.equals(insertText)) {
				break;
			}
		}

	}

	private void lotInput(String insertText, String name, int lot) {
		try {

			if (insertText.equals(ADD_CLI)) {
				// Se si vuole modificare la tariffa oraria
				addLot(name, lot);
			} else if (insertText.equals(REMOVE_CLI)) {
				// Se si vuole modificare la tariffa massima
				removeLot(name, lot);
			}
		} catch (Exception e) {
			System.out.println(ERROR_CLI);
		}

	}

	public void addLot(String name, int total) {

		ParkingLotsAdministrator.getInstance().addParkings(name, total);
		System.out.println("New " + total + " parking lots added to level " + name);

	}

	public void removeLot(String name, int total) {

		ParkingLotsAdministrator.getInstance().removeParkings(name, total);
		System.out.println(total + " parking lots removed from level " + name);

	}

}
