package it.unipv.ingsw.progettoe20.server.admin.controller;

import static it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorMessage.showError;
import static it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorMessage.showInfo;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PRICE_HOURLY;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PRICE_MAXIMUM;
import static it.unipv.ingsw.progettoe20.server.database.DBConstants.MINUTES_HOURLY;
import static it.unipv.ingsw.progettoe20.server.database.DBConstants.MINUTES_MAXIMUM;
import static it.unipv.ingsw.progettoe20.server.database.DBConstants.MINUTES_MINIMUM;

import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.PriceManagementGUI;

/**
 * Listener che che controlla le operazioni effettuate nella schermata del
 * PriceManagementGUI Modifica le tariffe disponibili
 */

public class PriceListener extends AbstractListener {

	private final PriceManagementGUI gui;
	private final PriceAdministrator admin;

	/**
	 * Crea una istanza del PriceListener.
	 *
	 * @param gui istanza di PriceManagementGUI
	 */
	public PriceListener(PriceManagementGUI gui) {
		this.gui = gui;
		admin = PriceAdministrator.getInstance();
	}

	@Override
	public void changeGUI() {
		super.changeGUI();
		gui.setVisible(false);
	}

	/**
	 * Legge la nuova tariffa inserita nel JTextField.
	 *
	 * @return price nuova tariffa inserita
	 */
	public double enteredNumber() {
		String price = gui.getPrice().getText();
		if (price != null) {
			try {
				return Double.parseDouble(price);
			} catch (Exception e) {
				return -1;
			}
		}
		return -1;
	}

	/**
	 * Fa le chiamate per aggiornare le tariffe
	 *
	 * @param action   item scelto nella combobox
	 * @param newprice nuova tariffa
	 */
	public void updatePrices(String action, double newprice) {
		int minutes;

		if (PRICE_HOURLY.equals(action)) {
			// Se si vuole modificare la tariffa oraria
			minutes = MINUTES_HOURLY;
		} else if (PRICE_MAXIMUM.equals(action)) {
			// Se si vuole modificare la tariffa massima
			minutes = MINUTES_MAXIMUM;
		} else {
			// Se si vuole modificare la tariffa minima
			minutes = MINUTES_MINIMUM;
		}
		// Modifica della tariffa
		admin.changePrice(newprice, minutes);
		gui.getPrice().setText("");
		showInfo("Price: " + newprice + " euro");
	}

	@Override
	public void change() {
		try {
			double newprice = enteredNumber();
			String action = (String) gui.getCombo().getSelectedItem();

			if (newprice < 0) {
				// Se la tariffa inserita non è valida
				showError("The parking lot's number is not valid");
			} else {
				// Se è valida
				updatePrices(action, newprice);
			}
		} catch (Exception e) {
			showError("Error");
		}
	}
}
