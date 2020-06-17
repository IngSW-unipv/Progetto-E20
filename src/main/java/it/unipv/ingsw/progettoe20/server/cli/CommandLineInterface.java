package it.unipv.ingsw.progettoe20.server.cli;

import java.util.Scanner;

/**
 * Command line interface del server
 */
public class CommandLineInterface extends Thread {

    public void run() {
        String command = null;
        Scanner in = new Scanner(System.in);
        while (true){
            command = in.nextLine();
            CommandHandler.handle(command);
        }
    }
}
