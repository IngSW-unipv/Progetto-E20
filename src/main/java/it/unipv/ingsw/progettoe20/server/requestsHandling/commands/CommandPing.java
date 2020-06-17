package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * comando che gestisce le richieste di ping del server
 */
public class CommandPing extends Command {

    public CommandPing(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String s) {
        out.println("pong");
        return false;
    }
}
