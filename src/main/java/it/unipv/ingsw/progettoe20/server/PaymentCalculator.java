package it.unipv.ingsw.progettoe20.server;

import it.unipv.ingsw.progettoe20.server.model.Price;
import it.unipv.ingsw.progettoe20.server.model.Ticket;

import java.util.Collections;
import java.util.List;

public class PaymentCalculator {

    /**
     * Metodo che fa il calcolo del prezzo dato un biglietto e una lista delle tariffe.
     *
     * @param ticket il biglietto.
     * @param pricelist lista delle tariffe.
     * @return il prezzo da pagare.
     */
    public static double getPaymentAmount(Ticket ticket, List<Price> pricelist) {
        Collections.sort(pricelist);
        double amount = 0.0;
        for (Price p : pricelist) {
            if (ticket.TimeDiff() > p.getMinutes()) {
                amount = p.getPrice();
            } else {
                break;
            }
        }
        if (amount == 0.0) {
            amount = pricelist.get(0).getPrice();
        }
        return amount;
    }
}
