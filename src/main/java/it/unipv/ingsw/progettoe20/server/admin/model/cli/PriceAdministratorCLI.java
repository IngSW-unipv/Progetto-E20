package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.cli.CommandStrings;
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
			System.out.println("Digita " + CommandStrings.ADMINCLI_HOURLY + " per modificare la tariffa oraria, "
					+ CommandStrings.ADMINCLI_MAXIMUM + " per modificare la tariffa massima,"
					+ CommandStrings.ADMINCLI_MINIMUM + "per modificare la tariffa minima");
			insertText = scanner.next();
			System.out.println("Digita la nuova tariffa");
			String insertPrice = scanner.next();
			priceInput(insertText, insertPrice);
		}
	}

	private void priceInput(String insertText2, String insertPrice) {
		try {
			double newPrice = Double.parseDouble(insertPrice);
			if (insertText.equals(CommandStrings.ADMINCLI_HOURLY)) {
				// Se si vuole modificare la tariffa oraria
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_HOURLY);
			} else if (insertText.equals(CommandStrings.ADMINCLI_MAXIMUM)) {
				// Se si vuole modificare la tariffa massima
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_MAXIMUM);
			} else if (insertText.equals(CommandStrings.ADMINCLI_MINIMUM)) {
				// Se si vuole modificare la tariffa minima
				PriceAdministrator.getInstance().changePrice(newPrice, DBConstants.MINUTES_MINIMUM);
			}
		} catch (Exception e) {
			System.out.println(CommandStrings.ERROR_GENERIC);

		}
	}
}
