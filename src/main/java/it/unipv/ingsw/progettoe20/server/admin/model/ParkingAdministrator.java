package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

/**
 * Classe per la gestione dei parcheggi
 */

public class ParkingAdministrator {

	private static ParkingAdministrator instance;
	private DatabaseFacade databaseFacade;

	/**
	 * Costruttore privato --> Singleton
	 */
	private ParkingAdministrator(final DatabaseFacade dbFacade) {
		databaseFacade = dbFacade;

	}

	/**
	 * Restituisce l'istanza dell'amministratore dei parcheggi.
	 *
	 * @return the instance
	 */
	public static ParkingAdministrator getInstance() {
		return instance;
	}

	/**
	 * Crea l'unica instanza dell'amministratore dei parcheggi.
	 *
	 * @param dbFacade the p database manager
	 */
	public static void create(final DatabaseFacade dbFacade) {
		instance = new ParkingAdministrator(dbFacade);
	}

	/**
	 * Aggiunge nuovi posti al parcheggio.
	 *
	 * @param name nome del livello
	 * @param n    numero di parcheggi da aggiungere
	 * @return newTotal nuova disponibilità totale
	 */
	public int addParkings(String name, int n) {
		Level level = databaseFacade.getLevelByName(name);
		int newTotal = level.getTotal() + n;
		int newAvailable = level.getAvailable() + n;

		level.setTotal(newTotal);
		level.setAvailable(newAvailable);
		databaseFacade.updateLevel(level);
		return newTotal;
	}

	/**
	 * Rimuove posti dal parcheggio.
	 *
	 * @param name nome del livello
	 * @param n    numero di posti da togliere
	 * @return newTotal nuova disponibilità totale
	 */
	public int removeParkings(String name, int n) {
		Level level = databaseFacade.getLevelByName(name);
		int newTotal = level.getTotal() - n;
		int newAvailable = level.getAvailable() - n;
		if (newTotal < 0 || newAvailable < 0) {
			// Se cerca di togliere più parcheggi di quelli presenti
			throw new IllegalArgumentException("Impossible! You want to remove too many parking lots");
		} else {
			level.setTotal(newTotal);
			databaseFacade.updateLevel(level);
		}
		return newTotal;
	}

}
