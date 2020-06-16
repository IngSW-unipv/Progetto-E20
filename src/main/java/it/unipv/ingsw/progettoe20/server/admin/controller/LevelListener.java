package it.unipv.ingsw.progettoe20.server.admin.controller;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.LevelAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.LevelManagementGUI;

/**
 * istener che che controlla le operazioni effettuate nella schermata del
 * LevelManagementGUI Modifica le tariffe disponibili
 */
public class LevelListener extends AbstractListener {

	private LevelManagementGUI gui;
	private LevelAdministrator admin;

	/**
	 * Crea una istanza di LevelListener.
	 *
	 * @param gui the gui
	 */
	public LevelListener(LevelManagementGUI gui) {
		this.gui = gui;
		admin = LevelAdministrator.getInstance();
	}

	/**
	 * Legge il numero di parcheggi inseriti nel JTextField.
	 *
	 * @return number numero di parcheggi inseriti
	 *
	 */
	private int enteredParkingLots() {
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

	@Override
	public void changeGUI() {
		super.changeGUI();
		gui.setVisible(false);

	}

	/**
	 * Legge il nome del livello inserito nel JTextField
	 *
	 * @return name nome livello richiesto
	 *
	 */
	private String enteredLevel() {
		String name = gui.getLevelname().getText();

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
	 * Fa la chiamata al metodo dell'amministratore per aggiungere un livelo
	 *
	 * @param name     nome del livello inserito
	 * @param capacity disponibilità totale del nuovo livello
	 */
	private void addLvel(String name, int capacity) {
		if (validationforAddLevel(name, capacity)) {
			admin.addLevel(name, capacity);
			JOptionPane.showMessageDialog(null, "Level " + name + " added", "Info", 1, null);
		}
	}

	/**
	 * Valida i dati inseriti nella GUI per compiere l'aggiunta di un livello
	 *
	 * @param name   nome del livello inserito
	 * @param number disponibilità totale del nuovo livello
	 * @return true se i dati sono validi
	 */
	private boolean validationforAddLevel(String name, int number) {
		if (number < 0) {
			JOptionPane.showMessageDialog(null, "the parking lot's number is not valid", "Error", 1, null);
			return false;
		} else if (name.isBlank()) {
			JOptionPane.showMessageDialog(null, "the level name is not valid", "Error", 1, null);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Fa la chiamata ai metodi dell'amministratore per rimuovere un livello
	 *
	 * @param name nome del livello inserito
	 */
	private void removeLevel(String name) {
		if (validationforRemoveLevel(name)) {
			admin.removeLevel(name);
			JOptionPane.showMessageDialog(null, "Level " + name + " removed", "Info", 1, null);
		}

	}

	/**
	 * Valida i dati inseriti nella GUI per compiere la rimozione di un livello
	 *
	 * @param name nome del livello inserito
	 * @return true se i dati sono validi
	 */
	private boolean validationforRemoveLevel(String name) {
		if (name.isBlank()) {
			JOptionPane.showMessageDialog(null, "the parking lot's number is not valid", "Error", 1, null);
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected void change() {
		try {
			String action = (String) gui.getCombo().getSelectedItem();
			String name = enteredLevel();
			int number = enteredParkingLots();

			if (action.equals("Add Level")) {
				// Aggiungi un livello
				addLvel(name, number);
			} else {
				// Rimuovi un livello
				removeLevel(name);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1, null);
		}
	}

}
