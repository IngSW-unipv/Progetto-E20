package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

/**
 * Classe astratta che mostra una linea guida per il comportamento delle classi
 * per la gestione della CLI dell'amministratore
 *
 *
 */
public abstract class AbstractAdministratorCLI {

	protected String insertText;
	protected Scanner scanner;

	/**
	 * Crea una istanza della classe AbstractAdministratorCLI
	 *
	 * @param scanner    scanner per la lettura da linea di comando
	 * @param insertText stringa inserita dall'utente
	 */
	public AbstractAdministratorCLI(Scanner scanner, String insertText) {
		this.insertText = insertText;
		this.scanner = scanner;
		handlerAdministratorCLI();
	}

	/**
	 * Metodo per gestire le diverse richieste dell'utente
	 *
	 * @param scanner     scanner per la lettura da linea di comando
	 * @param insertText2 stringa inserita dall'utente
	 */
	protected abstract void handlerAdministratorCLI();
}
