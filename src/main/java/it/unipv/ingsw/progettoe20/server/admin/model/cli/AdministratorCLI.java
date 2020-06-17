package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.client.ClientStrings;

public class AdministratorCLI {

	private Scanner scanner;
	private String insertText;

	public AdministratorCLI() {
		initCli();

	}

	void initCli() {
		insertText = "";
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Hai scelto la modalità command line input, inserisci 'lev' per modificare i livelli, 'prices' per le tariffe e 'lot' per i posti del parcheggio");
			insertText = scanner.next();
			if (insertText.equals("lev")) {
				LevelAdministratorCLI levelCLI = new LevelAdministratorCLI(scanner, insertText);
			} else if (insertText.equals("prices")) {
				PriceAdministratorCLI priceCLI = new PriceAdministratorCLI(scanner, insertText);
			} else if (insertText.equals("lot")) {
				ParkingAdministratorCLI parkingCLI = new ParkingAdministratorCLI(scanner, insertText);
			} else if (insertText.equals(ClientStrings.EXIT)) {
				break;
			}
		}
	}

}
