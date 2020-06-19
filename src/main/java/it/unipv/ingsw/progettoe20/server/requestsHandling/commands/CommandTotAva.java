package it.unipv.ingsw.progettoe20.server.requestsHandling.commands;

import it.unipv.ingsw.progettoe20.Protocol;

import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;

import java.io.PrintWriter;
import java.util.List;

/**
 * comando che gestisce le richieste del numero totale di posti del parcheggio
 */
public class CommandTotAva extends Command {

    public CommandTotAva(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
    }

    @Override
    public boolean handleRequest(String request) {
        try {
            List<Level> levelList;

            levelList = dbFacade.getLevelList();
            int contLevel = levelList.size();

            int i = 0, totalLot = 0;
            if (contLevel != 0) {
                do {
                    totalLot += levelList.get(i).getAvailable();
                    i++;
                } while (i < contLevel);
                out.println(Protocol.RESPONSE_OK + Protocol.SEPARATOR + totalLot);
            } else {
                out.println(Protocol.RESPONSE_OK + Protocol.SEPARATOR + "0");
            }
        } catch (IllegalArgumentException i) {
            Logger.log(i.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
        }

        return false;
    }
}
