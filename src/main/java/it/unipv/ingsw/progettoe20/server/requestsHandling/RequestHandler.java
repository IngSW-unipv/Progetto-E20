package it.unipv.ingsw.progettoe20.server.requestsHandling;


import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.TicketIdGenerator;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.requestsHandling.commands.Command;

import java.io.PrintWriter;

/**
 * Verifica la presenza di comandi validi nella richiesta ed esegue l'azione corrispondente.
 */
public class RequestHandler {
    private RequestMap requestMap;

    /**
     * Costruisce un RequestHandler.
     *
     * @param out      reference PrintWriter sulla socket associata.
     */
    public RequestHandler(PrintWriter out) {
        requestMap = new RequestMap(out);
    }

    /**
     * Divide comando e ID, verifica se il comando è valido ed esegue l'azione corrispondente.
     *
     * @param request richiesta effettuata dal client.
     * @return true se il client ha richiesto la chiusura della connessione, false altrimenti.
     * @throws IllegalArgumentException se la richiesta non è valida.
     */
    public boolean handle(String request) throws IllegalArgumentException {
        try {
            String[] parts = splitRequest(request);
            Command command = requestMap.getREQUESTS().get(parts[0]);
            return command.handleRequest(parts[1]);
        } catch (NullPointerException n) {
            return false;
        }
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
