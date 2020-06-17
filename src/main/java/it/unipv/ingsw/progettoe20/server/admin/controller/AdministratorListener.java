package it.unipv.ingsw.progettoe20.server.admin.controller;

import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.LEVEL_MANAGEMENT;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PARKINGLOTS_MANAGEMENT;
import static it.unipv.ingsw.progettoe20.server.admin.model.AdministratorConstants.PRICE_MANAGEMENT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.LevelManagementGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.ParkingLotsManagementGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.PriceManagementGUI;

/**
 * Listener che controlla quale bottone è stato schiacciato nella schermata
 * dell'AdiministratorGUI Apre Parking Management oppure Price Management in
 * base al bottone premuto
 */
public class AdministratorListener implements ActionListener {

	private final AdministratorGUI adminGUI;

	/**
	 * Crea una istanza di AdministratorListener.
	 *
	 * @param gui the gui
	 */
	public AdministratorListener(AdministratorGUI gui) {
		this.adminGUI = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (PARKINGLOTS_MANAGEMENT.equals(event.getActionCommand())) {
			// Se si vuole accedere alla gestione dei posti auto
			ParkingLotsManagementGUI parkingGUI = new ParkingLotsManagementGUI();
			parkingGUI.setVisible(true);
			adminGUI.setVisible(false);
		} else if (PRICE_MANAGEMENT.equals(event.getActionCommand())) {
			// Se si vuole accedere alla gestione delle tariffe
			PriceManagementGUI priceGUI = new PriceManagementGUI();
			priceGUI.setVisible(true);
			adminGUI.setVisible(false);
		} else if (LEVEL_MANAGEMENT.equals(event.getActionCommand())) {
			// Se si vuole accedere alla gestione dei livelli
			LevelManagementGUI levelGUI = new LevelManagementGUI();
			levelGUI.setVisible(true);
			adminGUI.setVisible(false);
		}
	}
}
