package it.unipv.ingsw.progettoe20.server;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.switchCommands.*;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DoRequests {
    public  final Map<String, Command> REQUESTS;
    static DatabaseFacade db;
    static PrintWriter out;

    public DoRequests(DatabaseFacade db, PrintWriter out) {
        this.db = db;
        this.out = out;
        final Map<String, Command> requests = new HashMap<>();
        requests.put(Protocol.PING, new CommandPing(db, out));
        requests.put(Protocol.REQUEST_GENERATE_ID, new CommandGen(db, out));
        requests.put(Protocol.REQUEST_CHECK_ID, new CommandCheck(db, out));
        requests.put(Protocol.REQUEST_PAY_AMOUNT, new CommandPay(db, out));
        requests.put(Protocol.REQUEST_DELETE_ID, new CommandDel(db, out));
        requests.put(Protocol.REQUEST_PAYMENT_CHECK, new CommandPayCheck(db, out));
        requests.put(Protocol.REQUEST_TOTAL_AVAILABILITY, new CommandAva(db, out));
        requests.put(Protocol.REQUEST_PAYMENT_ACCEPTED, new CommandPayAccepted(db, out));
        requests.put(Protocol.REQUEST_END, new CommandEnd(db, out));

        REQUESTS = Collections.unmodifiableMap(requests);
    }



}


