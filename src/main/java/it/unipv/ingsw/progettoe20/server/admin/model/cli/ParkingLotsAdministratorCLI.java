package it.unipv.ingsw.progettoe20.server.admin.model.cli;

import java.util.Scanner;

import it.unipv.ingsw.progettoe20.server.admin.model.ParkingLotsAdministrator;

import it.unipv.ingsw.progettoe20.server.cli.CommandStrings;

public class ParkingLotsAdministratorCLI extends AbstractAdministratorCLI {

	private String insertText;
	private Scanner scanner;

	public ParkingLotsAdministratorCLI(Scanner scanner, String insertText) {
		super(scanner, insertText);
	}

	@Override
	protected void handlerAdministratorCLI(Scanner scanner, String insertText2) {
		while (true) {
			System.out.println("Digita " + CommandStrings.ADMINCLI_ADD + " per aggiungere, "
				+ CommandStrings.ADMINCLI_REMOVE + " per rimuovere un posti, exit per uscire");
			insertText = scanner.next();
			System.out.println("Inserisci nome livello");
			String name = scanner.next();
			System.out.println("Inserisci posti livello");
			String lot = scanner.next();
			int total = Integer.parseInt(lot);
			lotInput(insertText, name, total);
			if (insertText.equals(CommandStrings.EXIT)) {
				break;
			}
		}

	}

	private void lotInput(String insertText, String name, int lot) {
		try {
			
			if (insertText.equals(CommandStrings.ADMINCLI_ADD)) {
				// Se si vuole modificare la tariffa oraria
				addLot(name, lot);
			} else if (insertText.equals(CommandStrings.ADMINCLI_REMOVE)) {
				// Se si vuole modificare la tariffa massima
				removeLot(name, lot);
			}else{
				System.out.println("CICII");
			}}catch (Exception e) {
			System.out.println(CommandStrings.ERROR_GENERIC);
		}
	
}
		public void addLot(String name, int total) {
			
			ParkingLotsAdministrator.getInstance().addParkings(name,total);
			System.out.println("Posti " + total + " aggiunti al livello "+name);
			
			
		}
		public void removeLot(String name, int total) {
		
			ParkingLotsAdministrator.getInstance().removeParkings(name,total);
			System.out.println("Posti " + total + " rimossi al livello "+name);
			
		}
	
	}

