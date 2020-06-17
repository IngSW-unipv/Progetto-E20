package it.unipv.ingsw.progettoe20.client.ExitColumn;

import it.unipv.ingsw.progettoe20.client.ClientConstants;
import it.unipv.ingsw.progettoe20.client.ClientStrings;
import it.unipv.ingsw.progettoe20.client.ExitColumn.Controller.ExitColumnController;
import it.unipv.ingsw.progettoe20.client.ExitColumn.Model.ExitColumn;
import it.unipv.ingsw.progettoe20.client.ExitColumn.View.ExitColumnGUI;

import javax.swing.*;


/**
 * MainExitColumn per il client ExitColumn.
 */
public class MainExitColumn {

    /**
     * Main del tester.
     *
     * @param args parametri in ingresso
     */
    public static void main(String[] args) {

        //controllo se viene avviato con parameteo cli come input type

        if (args.length != 0 && args[0].equals(ClientStrings.COMMAND_CLI)) {

            ExitColumn model = new ExitColumn(args[0]);
        } else {

            ExitColumn model = new ExitColumn("");
            ExitColumnGUI gui = new ExitColumnGUI();
            ExitColumnController controller = new ExitColumnController(gui, model);
            gui.setVisible(true);

        }

    }
}
