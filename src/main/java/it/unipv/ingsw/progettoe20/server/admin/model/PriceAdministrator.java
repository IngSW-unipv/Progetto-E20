package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Price;

/*
 * Classe per la gestione delle tariffe
 */

/**
 * The type Price administrator.
 */
public class PriceAdministrator {

	private static PriceAdministrator instance;
	private DatabaseFacade databaseFacade;

	/*
	 * Costruttore privato --> Singleton
	 */
	private PriceAdministrator(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;

	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	/*
	 * Restituisce l'istanza dell'amministratore delle tariffe
	 */
	public static PriceAdministrator getInstance() {
		return instance;
	}

	/**
	 * Create.
	 *
	 * @param pDatabaseManager the p database manager
	 */
	/*
	 * Crea l'unica instanza dell'amministratore delle tariffe
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new PriceAdministrator(pDatabaseManager);
	}

	/**
	 * Change price.
	 *
	 * @param price   the price
	 * @param minutes the minutes
	 */
	/*
	 * Modifica le tariffe
	 *
	 * @param newprice nuovo prezzo
	 *
	 * @param type quale prezzo da modificare
	 */
	public void changePrice(double price, int minutes) {
		Price newPrice = new Price(minutes, price);
		databaseFacade.updatePrice(newPrice);
	}
}
