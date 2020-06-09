package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

/*
 * Classe principale per la gestione dell'interfaccia dell'amministratore.
 * Può aggiungere o rimuovere parcheggi e modificare le tariffe.
 */

public class Administrator {

	private static Administrator instance;
	private DatabaseFacade databaseFacade;

	/*
	 * Costruttore privato --> Singleton
	 */
	private Administrator(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;
		ParkingAdministrator.create(databaseFacade);
		LevelAdministrator.create(databaseFacade);
		PriceAdministrator.create(databaseFacade);

	}

	/*
	 * Restituisce l'istanza dell'amministratore
	 */
	public static Administrator getInstance() {
		return instance;
	}

	/*
	 * Crea l'unica instanza dell'amministratore
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new Administrator(pDatabaseManager);
	}

}
