package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

public abstract class Command {
    DatabaseFacade dbFacade;
    PrintWriter out;


    public Command(DatabaseFacade dbFacade, PrintWriter out) {
        this.dbFacade = dbFacade;
        this.out = out;
    }

    public boolean handleRequest(String s){
        return false;
    }
}
