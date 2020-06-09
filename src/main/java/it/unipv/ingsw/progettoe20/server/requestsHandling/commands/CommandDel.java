package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.GenerationIdTicket;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

import java.io.PrintWriter;
import java.util.List;

public class CommandDel extends Command {

    private GenerationIdTicket generator;
    private List<Level> levelList;

    public CommandDel(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
        this.generator = new GenerationIdTicket(dbFacade);
    }

    @Override
    public boolean handleRequest(String s) {
        try {
            Level livello = dbFacade.getLevelByName(dbFacade.getTicketById((s)).getLevelName());
            dbFacade.removeTicket(dbFacade.getTicketById(s));
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
