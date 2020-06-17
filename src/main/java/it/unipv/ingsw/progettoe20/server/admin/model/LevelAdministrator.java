package it.unipv.ingsw.progettoe20.server.admin.model;

import java.util.List;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

/**
 * Classe per la gestione dei livelli
 */

public class LevelAdministrator {

	private static LevelAdministrator instance;
	private DatabaseFacade databaseFacade;

	/**
	 * Costruttore privato --> Singleton
	 */
	private LevelAdministrator(final DatabaseFacade dbFacade) {
		databaseFacade = dbFacade;

	}

	/**
	 * Restituisce l'istanza dell'amministratore dei livelli.
	 *
	 * @return the instance
	 */
	public static LevelAdministrator getInstance() {
		return instance;
	}

	/**
	 * Crea l'unica instanza dell'amministratore dei livelli.
	 *
	 * @param dbFacade istanza del DatabaseFacade
	 */

	public static void create(final DatabaseFacade dbFacade) {
		instance = new LevelAdministrator(dbFacade);
	}

	/**
	 * Aggiunge un livello al parcheggio.
	 *
	 * @param name  nome del livello da aggiungere
	 * @param total disponibilità totale del nuovo livello
	 */
	public void addLevel(String name, int total) {
		Level newLevel = new Level(name, total);
		databaseFacade.updateLevel(newLevel);
	}

	/**
	 * Rimuove un livello al parcheggio se presente.
	 *
	 * @param name nome del livello da togliere
	 */
	public void removeLevel(String name) {
		Level level = databaseFacade.getLevelByName(name);
		databaseFacade.removeLevel(level);
	}

	/**
	 * Mostra la lista di Livelli
	 *
	 * @return lista dei livelli
	 */
	public List<Level> getLevelList() {
		return databaseFacade.getLevelList();
	}
}
