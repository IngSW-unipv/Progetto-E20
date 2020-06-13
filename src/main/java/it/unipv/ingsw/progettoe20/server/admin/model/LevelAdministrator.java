package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

/*
 * Classe per la gestione dei livelli
 */

/**
 * The type Level administrator.
 */
public class LevelAdministrator {

	private static LevelAdministrator instance;
	private DatabaseFacade databaseFacade;

	/*
	 * Costruttore privato --> Singleton
	 */
	private LevelAdministrator(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;

	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	/*
	 * Restituisce l'istanza dell'amministratore dei livelli
	 */
	public static LevelAdministrator getInstance() {
		return instance;
	}

	/**
	 * Create.
	 *
	 * @param pDatabaseManager the p database manager
	 */
	/*
	 * Crea l'unica instanza dell'amministratore dei livelli
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new LevelAdministrator(pDatabaseManager);
	}

	/**
	 * Add level.
	 *
	 * @param name     the name
	 * @param capacity the capacity
	 */
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

	/**
	 * Remove level.
	 *
	 * @param name the name
	 */
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
