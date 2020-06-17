package it.unipv.ingsw.progettoe20.server.admin.controller;

import javax.swing.JOptionPane;

/**
 * Classe per gestire i messaggi mostrati nella GUi dell'amministratore.
 *
 */
public class AdministratorMessage {

	/**
	 * Mostra un messaggio di errore
	 * 
	 * @param errorMsg messaggio di errore
	 */
	static void showError(String errorMsg) {
		JOptionPane.showMessageDialog(null, errorMsg, "Error", 1, null);
	}

	/**
	 * Mostra un messaggio di informazione se le operazioni sono state fatte con
	 * successo
	 * 
	 * @param infoMsg messaggio di informazione
	 */
	static void showInfo(String infoMsg) {
		JOptionPane.showMessageDialog(null, infoMsg, "Info", 1, null);
	}
}
