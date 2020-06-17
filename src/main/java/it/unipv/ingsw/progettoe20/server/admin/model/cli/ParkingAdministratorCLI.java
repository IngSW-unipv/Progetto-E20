package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

public class ParkingAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public ParkingAdministratorCLI(Scanner scanner, String insertText) {
		this.insertText = insertText;
		this.scanner = scanner;
		ParkingCLI(insertText);
	}

	private void ParkingCLI(String insertText2) {
		// TODO Auto-generated method stub

	}
}
