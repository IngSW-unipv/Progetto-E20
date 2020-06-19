package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.PaymentCalculator;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * Comando che gestisce le richieste di calcolo del pagamento
 */
public class CommandPay extends Command {
    public CommandPay(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String request) {
        try {
            if (dbFacade.checkTicketById(request)) {
                double amount = PaymentCalculator.getPaymentAmount(dbFacade.getTicketById(request), dbFacade.getPriceList());
                out.println(amount);
            } else out.println(Protocol.RESPONSE_ERROR);

        } catch (IllegalArgumentException i) {
            Logger.log(i.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
        }
        return false;
    }
}
