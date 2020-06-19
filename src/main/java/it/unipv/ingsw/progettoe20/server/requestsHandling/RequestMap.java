package it.unipv.ingsw.progettoe20.server.requestsHandling;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.requestsHandling.commands.*;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che viene usata nel pattern command per collegare i vari comandi di gestione delle richieste
 * a stringhe predefinite all'interno di una hash map.
 */

public class RequestMap {
    private final Map<String, Command> REQUESTS;

    public RequestMap(PrintWriter out) {
        DatabaseFacade dbFacade = DatabaseFacade.getInstance();
        final Map<String, Command> requests = new HashMap<>();
        requests.put(Protocol.PING, new CommandPing(dbFacade, out));
        requests.put(Protocol.REQUEST_GENERATE_ID, new CommandGen(dbFacade, out));
        requests.put(Protocol.REQUEST_CHECK_ID, new CommandCheck(dbFacade, out));
        requests.put(Protocol.REQUEST_PAY_AMOUNT, new CommandPay(dbFacade, out));
        requests.put(Protocol.REQUEST_DELETE_ID, new CommandDel(dbFacade, out));
        requests.put(Protocol.REQUEST_PAYMENT_CHECK, new CommandPayCheck(dbFacade, out));
        requests.put(Protocol.REQUEST_TOTAL_AVAILABILITY, new CommandTotAva(dbFacade, out));
        requests.put(Protocol.REQUEST_PAYMENT_ACCEPTED, new CommandPayAccepted(dbFacade, out));
        requests.put(Protocol.REQUEST_END, new CommandEnd(dbFacade, out));

        REQUESTS = Collections.unmodifiableMap(requests);
    }

    public Map<String, Command> getREQUESTS() {
        return REQUESTS;
    }
}


