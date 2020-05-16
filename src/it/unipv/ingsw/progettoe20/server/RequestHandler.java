package it.unipv.ingsw.progettoe20.server;

import it.unipv.ingsw.progettoe20.server.database.DatabaseManager;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Verifica la presenza di comandi validi nella richiesta ed esegue l'azione corrispondente.
 */
public class RequestHandler {
    private DatabaseManager dbManager;
    private PrintWriter out;

    /**
     * Costruisce un RequestHandler.
     *
     * @param dbManager reference al DatabaseManager.
     * @param out       reference PrintWriter sulla socket associata.
     */
    public RequestHandler(DatabaseManager dbManager, PrintWriter out) {
        this.dbManager = dbManager;
        this.out = out;
    }

    /**
     * Divide comando e ID, verifica se il comando è valido ed esegue l'azione corrispondente.
     *
     * @param request richiesta effettuata dal client.
     * @return true se il client ha richiesto la chiusura della connessione, false altrimenti.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     */
    public boolean handle(String request) throws SQLException, IllegalArgumentException {
        String[] parts = splitRequest(request);
      
        switch (parts[0]) {
            // New ID generation requested
        
            case (Protocol.REQUEST_GENERATE_ID):
                try {
                	GenerationIdTicket codice = new GenerationIdTicket();
        			String generato = codice.GeneraId();
        			//dbManager.checkID(generato);
                    dbManager.newRecord(generato); 
                    out.println(Protocol.RESPONSE_OK);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + e.getMessage());
                }
                break;
            // ID existence check requested
            case (Protocol.REQUEST_ID):
                try {
                    dbManager.checkID(parts[1]);
                    out.println(Protocol.RESPONSE_OK);
                } catch (IllegalArgumentException i) {
                    System.out.println(i.getMessage());
                    out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
                }
                break;
            // Pay amount calculation requested
            case (Protocol.REQUEST_PAY_AMOUNT):
                // TODO
                break;
            // Record deletion requested
            case (Protocol.REQUEST_DELETE_ID):
                try {
                    dbManager.removeRecord(parts[1]);
                    out.println(Protocol.RESPONSE_OK);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + e.getMessage());
                }
                break;
            // Controllo flag di pagamento
            case (Protocol.REQUEST_PAYMENT_CHECK):
                try {
                    dbManager.checkPayment(parts[1]);
                    out.println(Protocol.RESPONSE_OK);
                } catch (IllegalArgumentException i) {
                    System.out.println(i.getMessage());
                    out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
                }
                break;
             // Accept payment requested
            case (Protocol.REQUEST_PAYMENT_ACCEPTED):
                try {
                    dbManager.setPaymentTime(parts[1]);
                    out.println(Protocol.RESPONSE_OK);
                } catch (IllegalArgumentException i) {
                    System.out.println(i.getMessage());
                    out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
                }
                break;
            // A friendly ping
            case "ping":
                out.println("pong");
                break;
            // Connection end requested
            case (Protocol.REQUEST_END):
                return true;
        }
        return false;
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
