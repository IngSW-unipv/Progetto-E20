package it.unipv.ingsw.progettoe20.server.admin.controller;

import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;

/**
 * Controller per la gestione per l'Administrator
 */
public class AdministratorController {

	private final AdministratorGUI gui;

	/**
	 * Crea una istanza di AdministratorController.
	 *
	 * @param gui istanza di AdministratorGUI
	 */
	public AdministratorController(AdministratorGUI gui) {
		this.gui = gui;
		initListener();
	}

	/**
	 * Inizializza il listener ai diversi bottoni.
	 */
	public void initListener() {
		AdministratorListener adminlistener = new AdministratorListener(gui);
		gui.getBtnParkings().addActionListener(adminlistener);
		gui.getBtnPrices().addActionListener(adminlistener);
		gui.getBtnLevels().addActionListener(adminlistener);
	}

}
