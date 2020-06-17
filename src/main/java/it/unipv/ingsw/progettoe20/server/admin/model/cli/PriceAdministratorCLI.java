package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.client.ClientStrings;
import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

public class PriceAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public PriceAdministratorCLI(Scanner scanner, String insertText) {
		this.insertText = insertText;
		this.scanner = scanner;
		PriceCLI(insertText);
	}

	private void PriceCLI(String insertText2) {
		while (true) {
			System.out.println("Digita hourly per modificare la tariffa oraria, "
					+ "maximum per modificare la tariffa massima, minimum per modificare la tariffa minima");
			insertText = scanner.next();
			System.out.println("Digita la nuova tariffa");
			String insertPrice = scanner.next();
			priceInput(insertText, insertPrice);
		}
	}

	private void priceInput(String insertText2, String insertPrice) {
		try {
			double newPrice = Double.parseDouble(insertPrice);
			if (insertText.equals("hourly")) {
				// Se si vuole modificare la tariffa oraria
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_HOURLY);
			} else if (insertText.equals("maximum")) {
				// Se si vuole modificare la tariffa massima
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_MAXIMUM);
			} else if (insertText.equals("minimum")) {
				// Se si vuole modificare la tariffa minima
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_MINIMUM);
			}
		} catch (Exception e) {
			System.out.println(ClientStrings.ERROR_GENERIC);

		}
	}
}
