package it.unipv.ingsw.progettoe20.server;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;
import it.unipv.ingsw.progettoe20.server.model.Price;
import it.unipv.ingsw.progettoe20.server.model.Ticket;
import it.unipv.ingsw.progettoe20.server.switchCommands.Command;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Verifica la presenza di comandi validi nella richiesta ed esegue l'azione corrispondente.
 */
public class RequestHandler {
    private DatabaseFacade dbFacade;
    private PrintWriter out;
    private GenerationIdTicket generator;
    private List<Level> levelList;

    /**
     * Costruisce un RequestHandler.
     *
     * @param dbFacade reference al DatabaseManager.
     * @param out      reference PrintWriter sulla socket associata.
     */
    public RequestHandler(DatabaseFacade dbFacade, PrintWriter out) {
        this.dbFacade = dbFacade;
        this.out = out;
        this.generator = new GenerationIdTicket(dbFacade);

    }

    /**
     * Divide comando e ID, verifica se il comando è valido ed esegue l'azione corrispondente.
     *
     * @param request richiesta effettuata dal client.
     * @return true se il client ha richiesto la chiusura della connessione, false altrimenti.
     */
    public boolean handle(String request) throws IllegalArgumentException {
        String[] parts = splitRequest(request);
        DoRequests doRequests = new DoRequests(dbFacade, out);
        Command command = doRequests.REQUESTS.get(parts[0]);
        return command.handleRequest(parts[1]);
    }

    private String[] splitRequest(String request) {
        String[] out = new String[2];
        String[] parts = request.split(Protocol.SEPARATOR);    // split request into 'command' and 'ID'
        if (parts.length != 2) {
            if (parts.length == 1) {
                out[0] = parts[0];
                return out;
            } else {
                throw new IllegalArgumentException("Request is not protocol compliant!");
            }
        }
        return parts;
    }
}
