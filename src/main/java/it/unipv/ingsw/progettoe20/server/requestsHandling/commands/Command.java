package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

import java.io.PrintWriter;

/**
 * Definisce un comando riconosciuto dal server.
 */
public abstract class Command {
    DatabaseFacade dbFacade;
    PrintWriter out;

    /**
     * Costruisce un oggetto Command.
     *
     * @param dbFacade riferimento al DatabaseFacade.
     * @param out riferimento per la scrittura sulla socket.
     */
    public Command(DatabaseFacade dbFacade, PrintWriter out) {
        this.dbFacade = dbFacade;
        this.out = out;
    }

    /**
     * Gestisce il comando e ritorna un booleano di verifica.
     *
     * @param s il comando.
     * @return booleano di verifica.
     */
    public boolean handleRequest(String s) {
        return false;
    }
}
