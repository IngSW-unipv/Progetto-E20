package it.unipv.ingsw.progettoe20.server.switchCommands;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

public class CommandEnd extends Command {
    public CommandEnd(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String s) {
        return true;
    }
}
