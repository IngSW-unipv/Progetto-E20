package it.unipv.ingsw.progettoe20.server;

/**
 * Main per avviare il server.
 */
public class Main {
    /**
     * Main.
     *
     * @param args argomenti in input
     */
    public static void main(String[] args) {
        ServerFacade serverFacade = new ServerFacade();
        serverFacade.init();
        serverFacade.start();
    }
}
