package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.TicketIdGenerator;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

import java.io.PrintWriter;
import java.util.List;

/**
 * Comando che gestisce le richieste di cancellazione dei ticket
 */
public class CommandDel extends Command {


    public CommandDel(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }


    @Override
    public boolean handleRequest(String request) {
        try {
            Level livello = dbFacade.getLevelByName(dbFacade.getTicketById((request)).getLevelName());
            dbFacade.removeTicket(dbFacade.getTicketById(request));
            livello.increaseAvailable();
            dbFacade.updateLevel(livello);
            out.println(Protocol.RESPONSE_OK);
        } catch (IllegalArgumentException e) {
            Logger.log(e.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + e.getMessage());
        }
        return false;
    }
}
