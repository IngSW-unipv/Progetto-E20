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

	private String insertText;
	private Scanner scanner;

	public PriceAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	public void handlerAdministratorCLI(Scanner scanner, String insertText2) {
		while (true) {
			System.out.println("Inserisci:\n" + HOURLY_CLI + " per modificare la tariffa oraria \n" + MAXIMUM_CLI
					+ " per modificare la tariffa massima \n" + MINIMUM_CLI + " per modificare la tariffa minima \n"
					+ LIST_CLI + " per ottenere la lista delle tariffe \n" + EXIT_CLI + " per uscire");
			insertText = scanner.next();
			if (insertText.equals(LIST_CLI)) {
				// Se si vuole ottenere la lista delle tariffe
				printList();
			} else if (insertText.equals(EXIT_CLI)) {
				// Se si vuole uscire
				break;
			} else {
				// Se si vuole modificare una tariffa
				System.out.println("Digita la nuova tariffa");
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
		System.out.println("Elenco delle tariffe:");
		for (Price p : prices) {
			System.out.println("Minuti: " + p.getMinutes() + "\t Prezzo: " + p.getPrice() + "\n");
		}
	}
}
