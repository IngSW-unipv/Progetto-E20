package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

/*
 * Classe Factory per la creazione delle tre classi che gestiscono parcheggi, livelli e tariffeu
 */

public class AdministratorFactory {

	private static AdministratorFactory instance;
	private DatabaseFacade databaseFacade;

	/*
	 * Costruttore privato --> Singleton
	 */
	private AdministratorFactory(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;
		ParkingAdministrator.create(databaseFacade);
		LevelAdministrator.create(databaseFacade);
		PriceAdministrator.create(databaseFacade);

	}

	/*
	 * Restituisce l'istanza dell'amministratore
	 */
	public static AdministratorFactory getInstance() {
		return instance;
	}

	/*
	 * Crea l'unica instanza dell'amministratore
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new AdministratorFactory(pDatabaseManager);
	}

}
