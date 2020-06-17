package it.unipv.ingsw.progettoe20.server.admin.controller;

import static it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorMessage.showError;
import static it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorMessage.showInfo;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PARKINGLOTS_ADD;

import it.unipv.ingsw.progettoe20.server.admin.model.ParkingLotsAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.ParkingLotsManagementGUI;

/**
 * Listener che controlla le operazioni effettuate nella schermata del
 * ParkingManagementGUI Aggiunge o rimuove dei parcheggi.
 */

public class ParkingLotsListener extends AbstractListener {

	private ParkingLotsManagementGUI gui;
	private ParkingLotsAdministrator admin;

	/**
	 * Crea una istanza di ParkingListener.
	 *
	 * @param gui istanza di ParkingLotsManagementGUI
	 */
	public ParkingLotsListener(ParkingLotsManagementGUI gui) {
		this.gui = gui;
		admin = ParkingLotsAdministrator.getInstance();
	}

	@Override
	public void changeGUI() {
		super.changeGUI();
		gui.setVisible(false);
	}

	/**
	 * Legge il nome del livello inserito nel JTextField.
	 *
	 * @return name nome del livello inserito
	 */

	public String enteredLevel() {
		String name = gui.getLevelName().getText();

		if (name != null) {
			try {
				return name;
			} catch (Exception e) {
				return "";
			}
		}
		return "";
	}

	/**
	 * Legge il numero di parcheggi inseriti nel JTextField.
	 *
	 * @return number numero di parcheggi inseriti
	 */
	public int enteredParkingLots() {
		String parkingLots = gui.getParkingLots().getText();
		if (parkingLots != null) {
			try {
				return Integer.parseInt(parkingLots);
			} catch (Exception e) {
				return -1;
			}
		}
		return -1;
	}

	/**
	 * Fa le chiamate per aggiornare i posti auto nel parcheggio
	 *
	 * @param action item scelto nella combobox
	 * @param name   nome del livello a cui aggiungere/togliere i parcheggi
	 * @param number numbero di posti auto da aggiungere/togliere
	 */
	public void updateParkingLots(String action, String name, int number) {
		if (PARKINGLOTS_ADD.equals(action)) {
			// Aggiunge parcheggi
			int capacity = admin.addParkings(name, number);
			showInfo("Available parking lots: " + capacity);
		} else {
			// Rimuove dei parcheggi
			int capacity = admin.removeParkings(name, number);
			showInfo("Available parking lots: " + capacity);
		}
	}

	@Override
	public void change() {
		try {
			String action = (String) gui.getCombo().getSelectedItem();
			String name = enteredLevel();
			int number = enteredParkingLots();

			if (number < 0) {
				// Se il numero di parcheggi inserito non è valido
				showInfo("The parking lot's number is not valid");
			} else if (name.isEmpty()) {
				// Se il nome del livello non è valido
				showInfo("The level name is not valid");
			} else {
				// Se tutte le informazioni sono valide
				updateParkingLots(action, name, number);
			}
		} catch (Exception e) {
			showError("Error");
		}
	}
}
