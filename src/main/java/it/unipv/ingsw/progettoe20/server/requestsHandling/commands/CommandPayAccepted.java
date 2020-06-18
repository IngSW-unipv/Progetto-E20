package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Ticket;

import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Comando che gestisce le richieste di accettazione di un pagamento
 */
public class CommandPayAccepted extends Command {
    public CommandPayAccepted(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String request) {
        try {
            Ticket ticket = dbFacade.getTicketById(request);
            ticket.setEntranceTime(new Timestamp(System.currentTimeMillis()));
            ticket.setPaymentTime(new Timestamp(System.currentTimeMillis()));
            ticket.setPaid(true);
            dbFacade.updateTicket(ticket);
            out.println(Protocol.RESPONSE_OK);
        } catch (IllegalArgumentException i) {
            Logger.log(i.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
        }
        return false;
    }
}
