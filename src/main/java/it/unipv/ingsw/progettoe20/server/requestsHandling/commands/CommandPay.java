package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.PaymentCalculator;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;
//TODO JAVADOC
public class CommandPay extends Command {
    public CommandPay(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String s) {
        try {
            if (dbFacade.checkTicketById(s)) {
                double amount = PaymentCalculator.getPaymentAmount(dbFacade.getTicketById(s), dbFacade.getPriceList());
                out.println(amount);
            } else out.println(Protocol.RESPONSE_ERROR);

        } catch (IllegalArgumentException i) {
            Logger.log(i.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
        }
        return false;
    }
}
