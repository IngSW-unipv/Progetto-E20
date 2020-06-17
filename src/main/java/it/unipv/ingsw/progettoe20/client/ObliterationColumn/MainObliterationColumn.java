package it.unipv.ingsw.progettoe20.client.ObliterationColumn;

import it.unipv.ingsw.progettoe20.client.ClientStrings;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController.OblController;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;


/**
 * tester per obliterazione
 */
public class MainObliterationColumn {
    public static void main(String[] args) {

        //controllo se viene avviato con parameteo cli come input type

        if (args.length != 0 && args[0].equals(ClientStrings.COMMAND_CLI)) {

            ObliterationColumn model = new ObliterationColumn(args[0]);
        } else {

            ObliterationColumn oc = new ObliterationColumn("");
            OblGui oblgui = new OblGui();
            OblController oblc = new OblController(oblgui, oc);
        }
    }
}
