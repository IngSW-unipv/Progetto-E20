package it.unipv.ingsw.progettoe20.server.cli;

import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.admin.model.AdministratorFactory;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

class CommandHandler {
	static void handle(String command) {
		try {
			CommandStrategy.valueOf(command).command();
		} catch (IllegalArgumentException i){
			System.out.println("Invalid Statement");
		}
	}
}
