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

		if (name.equals("")) {
			// Se non viene inserito nessun nome
			JOptionPane.showMessageDialog(null, "Please, enter the level", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the level name");
		}

		return name;
	}

	/**
	 * Legge il numero di parcheggi inseriti nel JTextField.
	 *
	 * @return number numero di parcheggi inseriti
	 */
	public int enteredParkingLots() {
		String parkingLots = gui.getField2().getText();
		int number = 0;

		if (parkingLots.equals("")) {
			// Se non viene inserito nessun numero
			JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the parking lots");
		}

		number = Integer.parseInt(parkingLots);
		return number;
	}

	@Override
	public void change() {
		try {
			String action = (String) gui.getCombo().getSelectedItem();
			String name = enteredLevel();
			int number = enteredParkingLots();

			if (action.equals("Add parkings")) {
				// Aggiunge parcheggi
				int capacity = admin.addParkings(name, number);
				JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
			} else {
				// Rimuove dei parcheggi
				int capacity = admin.removeParkings(name, number);
				JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
			}
		} catch (IllegalArgumentException i) {
			return;
		}
	}
}
