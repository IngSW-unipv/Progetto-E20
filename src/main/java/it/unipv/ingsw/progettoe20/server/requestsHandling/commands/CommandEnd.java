package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * comando che gestisce le richieste di chiusura
 */
public class CommandEnd extends Command {
    public CommandEnd(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String s) {
        return true;
    }
}
