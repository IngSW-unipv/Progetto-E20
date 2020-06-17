package it.unipv.ingsw.progettoe20.client.ExitColumn.Model;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.client.ClientConstants;
import it.unipv.ingsw.progettoe20.client.ClientStrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Questa classe rappresenta la colonna di uscita dal parcheggio, si occupa di controllare
 * che il ticket sia valido per l'uscita verficandone l'obliterazione e il tempo intercorso,
 * in caso positivo permette l'uscita del veicolo
 * in caso negativo richiede di recarsi alla colonnina di obliterazione.
 */

public class ExitColumn {

    private Socket clientSocket;
    private Boolean isConnected;
    private BufferedReader in;
    private PrintWriter out;
    private String inputType;

    /**
     * Costruttore del client Exit column.
     *
     * @param inputType tipologia di input (GUI o CLI)
     */
    public ExitColumn(String inputType) {
        try {
            this.inputType = inputType; //gli viene passato dal tester (args[0])
            clientSocket = new Socket(ClientConstants.HOST, ClientConstants.PORT);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            isConnected = true;
            checkInputType(); //verifica se viene utilizzata GUI o cli
        } catch (IOException i) {
            isConnected = false;
        }
    }

    /**
     * Metodo che verifica la metodologia di input (GUI o cli).
     */
    public void checkInputType() {
        if (inputType.equals(ClientStrings.COMMAND_CLI)) {
            String insertText = "";
            Scanner scanner = new Scanner(System.in);
            while (true) {

                System.out.println("You chose command line input mode, insert TicketID or exit to terminate process.");
                insertText = scanner.next();
                if (insertText.equals(ClientStrings.EXIT)) break; //Viene chiamata l'uscita
                checkObliteration(insertText);
            }
            System.out.println("Process terminated");
            this.closeSocket();
            System.exit(0);

        } else System.out.println(ClientStrings.GUI_STARTED);

    }


    /**
     * Metodo che verifica l'obliterazione.
     *
     * @param id riferimento al ticket
     * @return Response Enum con i vari esiti del check
     */
    public ResponseEnum checkObliteration(String id) {
        System.out.println(ClientStrings.PERFORM_REQUEST);

        if (checkId(id)) {
            try {
                out.println(Protocol.REQUEST_PAYMENT_CHECK + Protocol.SEPARATOR + id);
                String answer = in.readLine();
                System.out.println(answer);
                if (answer.equals(Protocol.RESPONSE_PAID_TRUE)) {
                    deleteTicket(id);       //commentarlo in caso di test per prevenire cancellazione record
                    return ResponseEnum.CONFIRMED_EXIT;
                } else return ResponseEnum.NO_PAID;
            } catch (IOException i) {
                return ResponseEnum.ERROR_GENERIC;
            } catch (NullPointerException n) {
                isConnected = false;
                return ResponseEnum.ERROR_GENERIC;
            }
        } else return ResponseEnum.NO_ID_FOUND;

    }

    /**
     * Metodo che elimina il Ticket.
     *
     * @param id riferimento al ticket
     * @return true se il ticket (a cui è associato l'id) è stato eliminato,false in caso contrario
     */
    public Boolean deleteTicket(String id) {
        try {
            out.println(Protocol.REQUEST_DELETE_ID + Protocol.SEPARATOR + id);
            String answer = in.readLine();
            System.out.println(answer);
            return answer.equals(Protocol.RESPONSE_OK);
        } catch (IOException i) {
            return false;
        } catch (NullPointerException n) {
            isConnected = false;
            return false;
        }

    }

    /**
     * metodo che cerca l'id nel database.
     *
     * @param id riferimento al ticket
     * @return true se l'id é presente nel database, false se invece non lo è
     */
    public boolean checkId(String id) {
        try {
            out.println(Protocol.REQUEST_CHECK_ID + Protocol.SEPARATOR + id);
            String answer = in.readLine();
            System.out.println(answer);
            return answer.equals(Protocol.RESPONSE_ID_FOUND);
        } catch (IOException i) {
            return false;
        } catch (NullPointerException n) {
            isConnected = false;
            return false;
        }
    }

    /**
     * metodo per chiudere il clientSocket.
     */
    public void closeSocket() {
        try {
            clientSocket.close();
        } catch (IOException i) {
            System.out.println(ClientStrings.ERROR_SOCKET);
        } catch (NullPointerException n) {
            isConnected = false;
        }
    }

    /**
     * getter stato connessione con il Server.
     *
     * @return Boolean
     */
    public Boolean getIsConnected() {
        return isConnected;
    }
}
