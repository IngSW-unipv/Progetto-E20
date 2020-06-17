package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.ERROR_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.HOURLY_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.MAXIMUM_CLI;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.MINIMUM_CLI;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

public class PriceAdministratorCLI extends AbstractAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public PriceAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	public void handlerAdministratorCLI(Scanner scanner, String insertText2) {
		while (true) {
			System.out.println("Digita " + HOURLY_CLI + " per modificare la tariffa oraria, " + MAXIMUM_CLI
					+ " per modificare la tariffa massima," + MINIMUM_CLI + "per modificare la tariffa minima");
			insertText = scanner.next();
			System.out.println("Digita la nuova tariffa");
			String insertPrice = scanner.next();
			priceInput(insertText, insertPrice);
		}
	}

	private void priceInput(String insertText2, String insertPrice) {
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
}
