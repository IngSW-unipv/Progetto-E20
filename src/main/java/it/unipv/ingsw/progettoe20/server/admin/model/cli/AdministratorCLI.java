package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.client.ClientStrings;

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
			System.out.println(
					"Hai scelto la modalità command line input, inserisci 'lev' per modificare i livelli, 'prices' per le tariffe e 'lot' per i posti del parcheggio");
			insertText = scanner.next();
			if (insertText.equals("lev")) {
				// Se si vogliono modificare i livelli
				LevelAdministratorCLI levelCLI = new LevelAdministratorCLI(scanner, insertText);
			} else if (insertText.equals("prices")) {
				// Se si vogliono modificare le tariffe
				PriceAdministratorCLI priceCLI = new PriceAdministratorCLI(scanner, insertText);
			} else if (insertText.equals("lot")) {
				// Se si vogliono modificare i posti auto
				ParkingLotsAdministratorCLI parkingCLI = new ParkingLotsAdministratorCLI(scanner, insertText);
			} else if (insertText.equals(ClientStrings.EXIT)) {
				break;
			}
		}
	}

}
