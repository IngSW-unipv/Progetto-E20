package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ERROR_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.EXIT_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.HOURLY_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.LIST_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.MAXIMUM_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.MINIMUM_CLI;

import java.util.List;
import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;
import it.unipv.ingsw.progettoe20.server.model.Price;

public class PriceAdministratorCLI extends AbstractAdministratorCLI {

	/**
	 * Crea una istanza di PriceAdministratorCLI
	 *
	 * @param scanner    scanner per la lettura da linea di comando
	 * @param insertText stringa inserita dall'utente
	 */
	public PriceAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	public void handlerAdministratorCLI() {
		while (true) {
			System.out.println("Insert: \n" + HOURLY_CLI + ": to change the hourly price \n" + MAXIMUM_CLI
					+ ": to change the maximum price \n" + MINIMUM_CLI + ": to change the minimum price \n" + LIST_CLI
					+ ": to list the prices \n" + EXIT_CLI + ": to exit");
			insertText = scanner.next();
			if (insertText.equals(LIST_CLI)) {
				// Se si vuole ottenere la lista delle tariffe
				printList();
			} else if (insertText.equals(EXIT_CLI)) {
				// Se si vuole uscire
				break;
			} else if (insertText.equals(MAXIMUM_CLI) || insertText.equals(MINIMUM_CLI)
					|| insertText.equals(HOURLY_CLI)) {
				// Se si vuole modificare una tariffa
				System.out.println("Insert the new price");
				String insertPrice = scanner.next();
				priceInput(insertText, insertPrice);
			}
		}
	}

	public void priceInput(String priceType, String insertPrice) {
		try {
			double newPrice = Double.parseDouble(insertPrice);
			if (HOURLY_CLI.equals(insertText)) {
				// Se si vuole modificare la tariffa oraria
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_HOURLY);
			} else if (MAXIMUM_CLI.equals(insertText)) {
				// Se si vuole modificare la tariffa massima
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_MAXIMUM);
			} else if (MINIMUM_CLI.equals(insertText)) {
				// Se si vuole modificare la tariffa minima
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_MINIMUM);
			}
		} catch (Exception e) {
			System.out.println(ERROR_CLI);
		}
	}

	public void printList() {
		List<Price> prices = PriceAdministrator.getInstance().getPriceList();
		System.out.println("Price list:");
		for (Price p : prices) {
			System.out.println("Minutes: " + p.getMinutes() + "\t Price: " + p.getPrice() + "\n");
		}
	}
}
