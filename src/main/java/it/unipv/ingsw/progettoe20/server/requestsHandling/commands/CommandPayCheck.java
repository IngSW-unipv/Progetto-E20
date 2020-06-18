package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * Comando che gestisce le richieste di controllo dell'accettazione del pagamento collegato a un ticket.
 */
public class CommandPayCheck extends Command {
    public CommandPayCheck(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String request) {
        try {
            if (!dbFacade.getTicketById(request).isPaid()) {
                out.println(Protocol.RESPONSE_PAID_FALSE);
            } else if (!dbFacade.getTicketById(request).obliterationCheck()) {
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
