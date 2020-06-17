package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.EXIT_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.LEVEL_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PARKINGLOTS_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PRICE_CLI;

import java.util.Scanner;

/**
 * Classe per la gestione dell'interfaccia testuale dell'amministratore
 *
 */
public class AdministratorCLI {

	private Scanner scanner;
	private String insertText;

	/**
	 * Crea una istanza dell'AdministratorCLI
	 */
	public AdministratorCLI() {
		initCLI();
	}

	/**
	 * Inizia l'interfaccia da riga di comando e smista i compiti alle tre classi
	 * per la gestione dei livelli, prezzi e posti.
	 */
	void initCLI() {
		insertText = "";
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Hai scelto la modalità command line input. \n Inserisci: \n" + LEVEL_CLI
					+ "per modificare i livelli \n" + PRICE_CLI + " per le tariffe \n" + PARKINGLOTS_CLI
					+ " per i posti del parcheggio");
			insertText = scanner.next();
			if (LEVEL_CLI.equals(insertText)) {
				// Se si vogliono modificare i livelli
				LevelAdministratorCLI levelCLI = new LevelAdministratorCLI(scanner, insertText);
			} else if (PRICE_CLI.equals(insertText)) {
				// Se si vogliono modificare le tariffe
				PriceAdministratorCLI priceCLI = new PriceAdministratorCLI(scanner, insertText);
			} else if (PARKINGLOTS_CLI.equals(insertText)) {
				// Se si vogliono modificare i posti auto
				ParkingLotsAdministratorCLI parkingCLI = new ParkingLotsAdministratorCLI(scanner, insertText);
			} else if (EXIT_CLI.equals(insertText)) {
				break;
			}
		}
	}

}
