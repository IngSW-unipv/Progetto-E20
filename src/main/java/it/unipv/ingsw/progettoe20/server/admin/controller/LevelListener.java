package it.unipv.ingsw.progettoe20.server.admin.controller;

import static it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorMessage.showError;
import static it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorMessage.showInfo;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.LEVEL_ADD;

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
	 * Fa la chiamata al metodo dell'amministratore per aggiungere un livello
	 *
	 * @param name           nome del livello inserito
	 * @param newParkingLots disponibilità totale del nuovo livello
	 */
	private void addLevel(String name, int newParkingLots) {
		if (newParkingLots < 0) {
			showError("The parking lot's number is not valid");
		} else if (name == null || name.isEmpty()) {
			showError("The level name is not valid");
		} else {
			admin.addLevel(name, newParkingLots);
			showInfo("Level " + name + " added");
		}
	}

	/**
	 * Fa la chiamata ai metodi dell'amministratore per rimuovere un livello
	 *
	 * @param name nome del livello inserito
	 */
	private void removeLevel(String name) {
		if (name.isEmpty()) {
			showError("The parking lot's number is not valid");
		} else {
			admin.removeLevel(name);
			showInfo("Level " + name + " removed");
		}

	}

	@Override
	protected void change() {
		try {
			String action = (String) gui.getCombo().getSelectedItem();
			String name = enteredLevel();
			int number = enteredParkingLots();

			if (LEVEL_ADD.equals(action)) {
				// Aggiungi un livello
				addLevel(name, number);
			} else {
				// Rimuovi un livello
				removeLevel(name);
			}
		} catch (Exception e) {
			showError("Error");
		}
	}

}
