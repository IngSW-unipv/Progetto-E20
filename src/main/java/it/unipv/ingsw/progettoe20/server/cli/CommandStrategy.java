package it.unipv.ingsw.progettoe20.server.cli;

import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.admin.model.AdministratorFactory;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

public enum CommandStrategy {
    PING {
        @Override
        public void command() {
            System.out.println(CommandStrings.PONG);
        }
    },

    GUI {
        @Override
        public void command() {
            AdministratorFactory.create(DatabaseFacade.getInstance());
            AdministratorGUI adminGui = new AdministratorGUI();
        }
    },

    EXIT {
        @Override
        public void command() {
            System.out.println(CommandStrings.EXIT_REQUESTED);
            Logger.close();
            System.exit(0);
        }
    };

    public abstract void command();
}
