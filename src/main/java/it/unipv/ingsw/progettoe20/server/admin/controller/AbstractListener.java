package it.unipv.ingsw.progettoe20.server.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;

public abstract class AbstractListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Home")) {
			// Se preme il bottone di home
			changeGUI();
		} else {
			// Se preme il bottone di confirm
			change();
		}
	}

	/*
	 * Opera le modifiche sul database richieste tramite la GUI
	 */
	protected abstract void change();

	/*
	 * Torna alla GUI dell'Administrator
	 *
	 */
	public void changeGUI() {
		AdministratorGUI adminGUI = new AdministratorGUI();
		adminGUI.setVisible(true);
	}

}
