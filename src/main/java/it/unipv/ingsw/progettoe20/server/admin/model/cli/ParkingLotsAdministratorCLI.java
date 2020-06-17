package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADD_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ERROR_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.EXIT_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.REMOVE_CLI;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.ParkingLotsAdministrator;
import it.unipv.ingsw.progettoe20.server.cli.CommandStrings;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ADD_CLI;
public class ParkingLotsAdministratorCLI extends AbstractAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public ParkingLotsAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	protected void handlerAdministratorCLI(Scanner scanner, String insertText2) {
		while (true) {
			System.out.println("Digita " + ADD_CLI + " per aggiungere, " + REMOVE_CLI + " per rimuovere un posti, "
					+ EXIT_CLI + " per uscire");
			insertText = scanner.next();
			if (insertText.equals(ADD_CLI)||insertText.equals(REMOVE_CLI)) {
			System.out.println("Inserisci nome livello");
			String name = scanner.next();
			System.out.println("Inserisci posti livello");
			String lot = scanner.next();
			int total = Integer.parseInt(lot);
			lotInput(insertText, name, total);}
			if (insertText.equals(CommandStrings.EXIT)) {
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
		System.out.println("Posti " + total + " aggiunti al livello " + name);

	}

	public void removeLot(String name, int total) {

		ParkingLotsAdministrator.getInstance().removeParkings(name, total);
		System.out.println("Posti " + total + " rimossi al livello " + name);

	}

}
