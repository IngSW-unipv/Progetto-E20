package it.unipv.ingsw.progettoe20.server.admin.controller;

import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;

/**
 * The type Administrator controller.
 */
/*
 * Controller per la gestione per l'Administrator.
 */
public class AdministratorController {

	private final AdministratorGUI gui;

	/**
	 * Instantiates a new Administrator controller.
	 *
	 * @param gui the gui
	 */
	public AdministratorController(AdministratorGUI gui) {
		this.gui = gui;
		initComponents();
	}

	/**
	 * Init components.
	 */
	public void initComponents() {
		AdministratorListener adminlistener = new AdministratorListener(gui);
		gui.getBtnParkings().addActionListener(adminlistener);
		gui.getBtnPrices().addActionListener(adminlistener);
		gui.getBtnLevels().addActionListener(adminlistener);
	}

}
