package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

/*
 * Classe per la gestione dei parcheggi
 */
public class ParkingAdministrator {

	private static ParkingAdministrator instance;
	private DatabaseFacade databaseFacade;

	/*
	 * Costruttore privato --> Singleton
	 */
	private ParkingAdministrator(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;

	}

	/*
	 * Restituisce l'istanza dell'amministratore dei parcheggi
	 */
	public static ParkingAdministrator getInstance() {
		return instance;
	}

	/*
	 * Crea l'unica instanza dell'amministratore dei parcheggi
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new ParkingAdministrator(pDatabaseManager);
	}

	/*
	 * Aggiunge nuovi posti al parcheggio
	 *
	 * @param name nome del livello a cui aggiungere i parcheggi
	 *
	 * @param n numbero di parcheggi da aggiungere
	 *
	 * @return newCapacity nuova disponibilità totale del livello
	 */
	public int addParkings(String name, int n) {
		Level level = databaseFacade.getLevelByName(name);
		int newCapacity = level.getTotal() + n;

		level.setTotal(newCapacity);
		databaseFacade.updateLevel(level);
		return newCapacity;
	}

	/*
	 * Toglie posti al parcheggio
	 *
	 * @param level nome del livello a cui togliere i parcheggi
	 *
	 * @param n numbero di parcheggi da togliere
	 *
	 * @return newCapacity nuova disponibilità totale del livello
	 */
	public int removeParkings(String name, int n) {
		Level level = databaseFacade.getLevelByName(name);
		int newCapacity = level.getTotal() - n;
		if (newCapacity < 0) {
			// Se cerca di togliere più parcheggi di quelli presenti
			throw new IllegalArgumentException("Impossible! You want to remove too many parking lots");
		} else {
			level.setTotal(newCapacity);
			databaseFacade.updateLevel(level);
		}
		return newCapacity;
	}

}
