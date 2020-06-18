package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * Classe astratta che rappresenta la struttura base di tutti i comandi di gestione delle richieste
 */

public abstract class Command {
    DatabaseFacade dbFacade;
    PrintWriter out;


    public Command(DatabaseFacade dbFacade, PrintWriter out) {
        this.dbFacade = dbFacade;
        this.out = out;
    }

    /**
     * Metodo che gestisce la richiesta
     *
     * @param request richiesta
     * @return false
     */
    public boolean handleRequest(String request) {
        return false;
    }
}
