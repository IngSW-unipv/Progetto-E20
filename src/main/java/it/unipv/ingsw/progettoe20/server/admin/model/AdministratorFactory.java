package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

/**
 * Classe Factory per la creazione delle tre classi che gestiscono parcheggi,
 * livelli e tariffeu
 */

public class AdministratorFactory {

	private static AdministratorFactory instance;
	private DatabaseFacade databaseFacade;

	/**
	 * Costruttore privato --> Singleton
	 */
	private AdministratorFactory(final DatabaseFacade dbFacade) {
		databaseFacade = dbFacade;
		ParkingAdministrator.create(databaseFacade);
		LevelAdministrator.create(databaseFacade);
		PriceAdministrator.create(databaseFacade);

	}

	/**
	 * Restituisce l'istanza dell'amministratore.
	 *
	 * @return the instance
	 */
	public static AdministratorFactory getInstance() {
		return instance;
	}

	/**
	 * Crea l'unica instanza dell'amministratore.
	 *
	 * @param dbFacade istanza del DatabaseFacade
	 */
	public static void create(final DatabaseFacade dbFacade) {
		instance = new AdministratorFactory(dbFacade);
	}

}
