package it.unipv.ingsw.progettoe20.server.admin.controller;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.PriceAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.PriceManagementGUI;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

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
		String price = gui.getField().getText();
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

		if (action.equals("Hourly price")) {
			// Se si vuole modificare la tariffa oraria
			minutes = DBConstants.MINUTES_HOURLY;

		} else if (action.equals("Maximum price")) {
			// Se si vuole modificare la tariffa massima
			minutes = DBConstants.MINUTES_MAXIMUM;

		} else {
			// Se si vuole modificare la tariffa minima
			minutes = DBConstants.MINUTES_MINIMUM;
		}
		// Modifica della tariffa
		admin.changePrice(newprice, minutes);
		gui.getField().setText("");
		JOptionPane.showMessageDialog(null, "Price: " + newprice + " euro", "Info", 1, null);
	}

	@Override
	public void change() {
		try {
			double newprice = enteredNumber();
			String action = (String) gui.getCombo().getSelectedItem();

			if (newprice < 0) {
				// Se la tariffa inserita non è valida
				JOptionPane.showMessageDialog(null, "the parking lot's number is not valid", "Error", 1, null);
			} else {
				// Se è valida
				updatePrices(action, newprice);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1, null);
		}
	}
}
