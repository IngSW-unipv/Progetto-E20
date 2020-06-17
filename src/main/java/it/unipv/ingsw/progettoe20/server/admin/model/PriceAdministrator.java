package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Price;

/**
 * Classe per la gestione delle tariffe
 */

public class PriceAdministrator {

	private static PriceAdministrator instance;
	private DatabaseFacade databaseFacade;

	/**
	 * Costruttore privato --> Singleton
	 */
	private PriceAdministrator(final DatabaseFacade dbFacade) {
		databaseFacade = dbFacade;

	}

	/**
	 * Restituisce l'istanza dell'amministratore delle tariffe.
	 *
	 * @return instance istanza di PriceAdministrator
	 */
	public static PriceAdministrator getInstance() {
		return instance;
	}

	/**
	 * Crea l'unica instanza dell'amministratore delle tariffe.
	 *
	 * @param dbFacade istanza di DatabaseFacade
	 */
	public static void create(final DatabaseFacade dbFacade) {
		instance = new PriceAdministrator(dbFacade);
	}

	/**
	 * Modifica le tariffe.
	 *
	 * @param price   nuovo prezzo
	 * @param minutes minuti della tariffa
	 */
	public void changePrice(double price, int minutes) {
		Price newPrice = new Price(minutes, price);
		databaseFacade.updatePrice(newPrice);
	}

	/**
	 * Mostra la lista delle tariffe
	 */
	public void getPriceList() {
		databaseFacade.getPriceList();
	}
}
