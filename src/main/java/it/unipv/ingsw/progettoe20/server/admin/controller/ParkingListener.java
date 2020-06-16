package it.unipv.ingsw.progettoe20.server.admin.controller;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.ParkingAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.ParkingManagementGUI;

/**
 * Listener che controlla le operazioni effettuate nella schermata del
 * ParkingManagementGUI Aggiunge o rimuove dei parcheggi.
 */

public class ParkingListener extends AbstractListener {

	private ParkingManagementGUI gui;
	private ParkingAdministrator admin;

	/**
	 * Crea una istanza di ParkingListener.
	 *
	 * @param gui the gui
	 */
	public ParkingListener(ParkingManagementGUI gui) {
		this.gui = gui;
		admin = ParkingAdministrator.getInstance();
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
		String name = gui.getField().getText();

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
		String parkingLots = gui.getField2().getText();
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
		if (action.equals("Add parkings")) {
			// Aggiunge parcheggi
			int capacity = admin.addParkings(name, number);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
		} else {
			// Rimuove dei parcheggi
			int capacity = admin.removeParkings(name, number);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
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
				JOptionPane.showMessageDialog(null, "the parking lot's number is not valid", "Error", 1, null);
			} else if (name.isBlank()) {
				// Se il nome del livello non è valido
				JOptionPane.showMessageDialog(null, "the level name is not valid", "Error", 1, null);
			} else {
				// Se tutte le informazioni sono valide
				updateParkingLots(action, name, number);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1, null);
		}
	}
}
