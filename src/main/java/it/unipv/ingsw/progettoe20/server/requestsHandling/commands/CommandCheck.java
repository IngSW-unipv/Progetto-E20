package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * Comando che gestisce le richieste di controllo dell'id
 */
public class CommandCheck extends Command {

    public CommandCheck(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }


    @Override
    public boolean handleRequest(String request) {
        if (dbFacade.checkTicketById(request)) {
            out.println(Protocol.RESPONSE_ID_FOUND);
        } else out.println(Protocol.RESPONSE_ID_NOT_FOUND);
        return false;
    }

}