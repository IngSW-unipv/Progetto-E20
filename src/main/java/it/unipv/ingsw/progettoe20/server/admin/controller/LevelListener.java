package it.unipv.ingsw.progettoe20.server.admin.controller;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.LevelAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.LevelManagementGUI;

/**
 * The type Level listener.
 */
public class LevelListener extends AbstractListener {

	private LevelManagementGUI gui;
	private LevelAdministrator admin;

	/**
	 * Instantiates a new Level listener.
	 *
	 * @param gui the gui
	 */
	public LevelListener(LevelManagementGUI gui) {
		this.gui = gui;
		admin = LevelAdministrator.getInstance();
	}

	/*
	 * Legge il numero di parcheggi inseriti nel JTextField
	 *
	 * @return number numero di parcheggi inseriti
	 *
	 */
	private int enteredParkingLots() {
		String str = gui.getParkingLots().getText();
		int number = 0;

		if (str.equals("")) {
			// Se non viene inserito nessun numero
			JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the parking lots");
		}

		number = Integer.parseInt(str);
		return number;
	}

	@Override
	public void changeGUI() {
		super.changeGUI();
		gui.setVisible(false);

	}

	/*
	 * Legge il nome del livello inserito nel JTextField
	 *
	 * @return name nome livello richiesto
	 *
	 */
	private String enteredLevel() {
		String name = gui.getLevelname().getText();

		if (name.equals("")) {
			// Se non viene inserito nessun nome
			JOptionPane.showMessageDialog(null, "Please, enter the level name", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the level name");
		}

		return name;
	}

	/*
	 * Modifica i livelli in base a quanto scelto nella combo box
	 *
	 */
	@Override
	protected void change() {
		String action = (String) gui.getCombo().getSelectedItem();
		String name = enteredLevel();

		if (action.equals("Add level")) {
			// Aggiunge un livello
			int number = enteredParkingLots();
			admin.addLevel(name, number);
			JOptionPane.showMessageDialog(null, "Level " + name + " added", "Info", 1, null);
		} else {
			// Rimuove un livello
			admin.removeLevel(name);
			JOptionPane.showMessageDialog(null, "Level " + name + " removed", "Info", 1, null);

		}

	}

}
