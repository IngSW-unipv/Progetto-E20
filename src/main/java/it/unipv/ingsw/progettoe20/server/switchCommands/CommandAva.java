package it.unipv.ingsw.progettoe20.server.switchCommands;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.GenerationIdTicket;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;
import it.unipv.ingsw.progettoe20.server.switchCommands.Command;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommandAva extends Command {
    private GenerationIdTicket generator;
    private List<Level> levelList;

    public CommandAva(DatabaseFacade dbFacade, PrintWriter out) {
        super(dbFacade, out);
        this.generator = new GenerationIdTicket(dbFacade);
    }

    @Override
    public boolean handleRequest(String s) {
        try {
            levelList = new ArrayList<>();
            levelList = dbFacade.getLevelList();
            int contLevel = levelList.size();
            int i = 0, totalLot = 0;
            do {
                totalLot += levelList.get(i).getAvailable();
                i++;
            } while (i < contLevel);
            out.println(Protocol.RESPONSE_OK + Protocol.SEPARATOR + totalLot);
        } catch (IllegalArgumentException i) {
            Logger.log(i.getMessage());
            out.println(Protocol.RESPONSE_ERROR + Protocol.SEPARATOR + i.getMessage());
        }
        return false;
    }
}
