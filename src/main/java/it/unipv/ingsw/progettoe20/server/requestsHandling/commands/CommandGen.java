package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.GenerationIdTicket;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;
import it.unipv.ingsw.progettoe20.server.model.Ticket;

import java.io.PrintWriter;
import java.util.List;

public class CommandGen extends Command {
    private GenerationIdTicket generator;
    private List<Level> levelList;
    public CommandGen(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
        this.generator = new GenerationIdTicket(dbFacade);
    }

    @Override
    public boolean handleRequest(String s) {
        String id;
        do {
            id = generator.GenerateId();
        } while (dbFacade.checkTicketById(id));
        //se non ci sono posti disponibili in tutti i livelli?
        Ticket newTicket = new Ticket(id);
        dbFacade.updateTicket(newTicket);
        Level level = dbFacade.getLevelByName(generator.getAvailableLevel());
        level.decreaseAvailable();
        dbFacade.updateLevel(level);
        out.println(Protocol.RESPONSE_OK + Protocol.SEPARATOR + id);
        return false;
    }
}
