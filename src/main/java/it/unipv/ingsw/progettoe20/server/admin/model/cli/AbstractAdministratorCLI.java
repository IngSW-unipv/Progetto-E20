package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

public abstract class AbstractAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public AbstractAdministratorCLI(Scanner scanner, String insertText) {
		this.insertText = insertText;
		this.scanner = scanner;
		handlerAdministratorCLI(scanner, insertText);
	}

	protected abstract void handlerAdministratorCLI(Scanner scanner, String insertText2);
}
