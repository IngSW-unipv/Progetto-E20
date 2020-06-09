package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

public class LevelAdministrator {

	private static LevelAdministrator instance;
	private DatabaseFacade databaseFacade;

	/*
	 * Costruttore privato --> Singleton
	 */
	private LevelAdministrator(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;

	}

	/*
	 * Restituisce l'istanza dell'amministratore dei livelli
	 */
	public static LevelAdministrator getInstance() {
		return instance;
	}

	/*
	 * Crea l'unica instanza dell'amministratore dei livelli
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new LevelAdministrator(pDatabaseManager);
	}

	/*
	 * Aggiunge un livello al parcheggio
	 *
	 * @param level nome del nuovo livello
	 *
	 * @param capacity numbero di parcheggi dentro il nuovo livello
	 */
	public void addLevel(String name, int capacity) {
		Level newLevel = new Level(name, capacity);
		databaseFacade.updateLevel(newLevel);
	}

	/*
	 * Rimuove un livello al parcheggio se presente
	 *
	 * @param name nome del livello da togliere
	 */
	public void removeLevel(String name) {
		Level level = databaseFacade.getLevelByName(name);
		databaseFacade.removeLevel(level);
	}

}
