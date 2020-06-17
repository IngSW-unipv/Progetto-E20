package it.unipv.ingsw.progettoe20.server.admin.controller;

import javax.swing.JOptionPane;

public class AdministratorMessage {

	static void showError(String errorMsg) {
		JOptionPane.showMessageDialog(null, errorMsg, "Error", 1, null);
	}

	static void showInfo(String infoMsg) {
		JOptionPane.showMessageDialog(null, infoMsg, "Info", 1, null);
	}
}
