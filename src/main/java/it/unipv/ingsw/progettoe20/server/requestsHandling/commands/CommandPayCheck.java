package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * Comando che verifica il pagamento di un ticket.
 */
public class CommandPayCheck extends Command {
    public CommandPayCheck(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String s) {
        try {
            if (!dbFacade.getTicketById(s).isPaid()) {
                out.println(Protocol.RESPONSE_PAID_FALSE);
            } else if (!dbFacade.getTicketById(s).obliterationCheck()) {
                out.println(Protocol.RESPONSE_PAID_TIME_EXPIRED);
            } else {
                out.println(Protocol.RESPONSE_PAID_TRUE);
            }
        } catch (IllegalArgumentException i) {
            Logger.log(i.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
        }
        return false;
    }
}
