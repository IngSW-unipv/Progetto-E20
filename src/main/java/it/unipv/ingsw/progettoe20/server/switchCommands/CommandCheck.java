package it.unipv.ingsw.progettoe20.server.switchCommands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

public class CommandCheck extends Command {

    public CommandCheck(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String s) {
        if (dbFacade.checkTicketById(s)) {
            out.println(Protocol.RESPONSE_OK);
        } else out.println(Protocol.RESPONSE_ERROR);
        return false;
    }

}