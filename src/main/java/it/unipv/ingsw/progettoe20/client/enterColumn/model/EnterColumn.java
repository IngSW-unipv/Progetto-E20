package it.unipv.ingsw.progettoe20.client.enterColumn.model;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.client.ClientConstants;
import it.unipv.ingsw.progettoe20.client.ClientStrings;

import java.io.BufferedReader;
import java.util.Observable;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Questa classe rappresenta la colonna di entrata nel parcheggio, si occupa di mantenere
 * aggiornato lo stato dei posti disponibili, della generazione del ticket nel caso ci siano
 * disponibilita' nel parcheggio.
 * Inoltre vi e' l'indicazione del livello a cui � associato il ticket.
 */
public class EnterColumn extends Observable {
    private int totalLot;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isConnected = false;
    private String answer;
    private Socket clientSocket;
    private String inputType;
    private String id = "";

    /**
     * costruttore
     *
     * @param inputType: paramentro che fornisce il tipo di interfaccia richiesta(utenteo grafica)
     */
    public EnterColumn(String inputType) {
        this.inputType = inputType;//gli viene passato dal tester (args[0])
        checkServerConnection();
        setAvailability();
    }

    /**
     * metodo che imposta la connessione al database
     */
    public void checkServerConnection() {

        try {
            clientSocket = new Socket(ClientConstants.HOST, ClientConstants.PORT);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            isConnected = true;
            checkInputType();
        } catch (IOException i) {
        	System.out.println(ClientStrings.NO_CONNECTION);
            isConnected = false;
        }
    }

    /**
     * metodo che chiude la socket
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
     * metodo che manda la richiesta per la generazione del Ticket
     *
     * @return true se l'id è stato generato correttamente, false se invece non lo è
     * @throws IOException the io exception
     */
    public boolean genTicket() throws IOException {
        try {
            out.println(Protocol.REQUEST_GENERATE_ID);
            answer = in.readLine();
            id = answer.substring(5, answer.length());
            
            if (id.equals(Protocol.RESPONSE_NO_LEVEL)) {
                return false;
            } else {
                System.out.println(answer);
                return true;
            }

        } catch (IOException i) {
            return false;
        } catch (NullPointerException n) {
            isConnected = false;
            return false;
        }
    }


    /**
     * Check input type.
     *
     * @throws IOException the io exception
     */
    public void checkInputType() throws IOException {
        if (inputType.equals(ClientStrings.COMMAND_CLI)) {
            cli();
        } else System.out.println(ClientStrings.GUI_STARTED);

    }

    /**
     * Metodo che rappresenta l'interfaccia testuale
     *
     * @throws IOException
     */
    private void cli() throws IOException {
    	if(isConnected==false) {
    		System.out.println("No Connection");
    	}
        String insertText = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("You've chosen the command line input mode,you have to enter 'gen' if you want to get your ID ticket or 'exit' to quit.");
            insertText = scanner.next();
            if (insertText.equals(ClientStrings.EXIT)) break;
            if (insertText.equals(ClientStrings.COMMAND_GEN)) {
                this.setAvailability();
                System.out.println("Lots Available:" + this.totalLot);
                if (genTicket()) {
                    this.setAvailability();
                    System.out.println("Please, go to " + this.id.substring(0, 1) + " level.");
                    System.out.println("Lot Available updated is:" + this.totalLot);
                } else
                    System.out.println("Sorry, no Parking space available: Ticket ID not created.");
            }
        }
        System.out.println("Execution Terminated.");
        this.closeSocket();
        scanner.close();
        System.exit(0);
    }

    /**
     * metodo che controlla la connessione
     *
     * @return the is conn
     */
    public boolean getIsConn() {

        return this.isConnected;
    }


    /**
     * metodo che manda la richiesta di aggiornamento dei posti disponibili del parcheggio
     *
     * @return true se la richiesta � stata portata a termine correttamente, false se invece non lo è
     */
    public Boolean setAvailability() {
        try {
            out.println(Protocol.REQUEST_TOTAL_AVAILABILITY);
            answer = in.readLine();

            String stringLot = answer.substring(5, answer.length());
            totalLot = Integer.parseInt(stringLot);
            if (answer.equals(Protocol.RESPONSE_OK + ":" + totalLot)) {
                return true;
            } else {
                return false;
            }

        } catch (IOException i) {
            return false;
        } catch (NullPointerException n) {
            isConnected = false;
            return false;
        }
    }


    /**
     * Gets id ticket.
     *
     * @return the id ticket
     */
    public String getIdTicket() {
        int countString = answer.length();
        String id = answer.substring(5, countString);
        return id;

    }

    /**
     * Gets availability.
     *
     * @return the availability
     */
    public int getAvailability() {
        return this.totalLot;
    }

    /**
     * Ritorna l'id.
     *
     * @return the id
     */
    public String getId() {
        return this.getId();
    }

    /**
     * Ritorna la disponibilit� attuale.
     * Notifica agli observer il cambiamento di disponibilit�
     *
     * @param availability the availability
     */
    public void setAvailability(int availability) {
        this.totalLot = availability;
        this.setChanged();
        this.notifyObservers();

    }

}
