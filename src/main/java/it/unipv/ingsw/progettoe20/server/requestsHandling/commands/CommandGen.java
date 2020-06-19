package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;


import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.TicketIdGenerator;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;
import it.unipv.ingsw.progettoe20.server.model.Ticket;

import java.io.PrintWriter;

/**
 * Comando che gestisce le richieste di generazione dei ticket
 */
public class CommandGen extends Command {
    private TicketIdGenerator generator;
    public CommandGen(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
        this.generator = new TicketIdGenerator(dbFacade);
    }

    @Override
    public boolean handleRequest(String request)  {
        String id;
        do {
            id = generator.generateId();
        } while (dbFacade.checkTicketById(id));
        Ticket newTicket = new Ticket(id);
        dbFacade.updateTicket(newTicket);
        Level level = dbFacade.getLevelByName(generator.getAvailableLevel());
        level.decreaseAvailable();
        dbFacade.updateLevel(level);
        out.println(Protocol.RESPONSE_OK + Protocol.SEPARATOR + id);
        return false;
    }
}
