package it.unipv.ingsw.progettoe20.client.enterColumn.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import it.unipv.ingsw.progettoe20.client.ClientStrings;
import it.unipv.ingsw.progettoe20.client.enterColumn.model.EnterColumn;
import it.unipv.ingsw.progettoe20.client.enterColumn.view.EnterColumnGui;


/**
 * Controller del client EnterColumn
 * contiene il Listener associato al bottone getTicket
 * il quale avvia il metodo del model per generare il Ticket
 * in base all'esito verra' modificato il panel.
 */
public class Controller {
    private EnterColumnGui g;
    private EnterColumn model;
    private Timer timer;
    /**
     * Instantiates a new Controller.
     *
     * @param g     interfacciaGrafica
     * @param model model
     * @throws IOException the io exception
     */
    public Controller(EnterColumnGui g, EnterColumn model) throws IOException {
        this.g = g;
        this.model = model;
        checkConn();
        initListener();
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	  @Override
        	  public void run() {
        	    model.setAvailability();
        	    model.setAvailability(model.getAvailability());;
        	  }
        	}, 10*1000, 10*1000);
    }

    /**
     * Inizializza il listener.
     */
    public void initListener() {
        g.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    g.getButton().setEnabled(false);
                    g.getButton().setBackground(new Color(143, 0, 255));
                    if (g.getAvailability() > 0) {

                        Boolean checkIdGeneration = model.genTicket();
                        if (checkIdGeneration) {
                            setTransitionElements();
                        } else {
                            g.setIdTicket(ClientStrings.ERROR_GENERIC);
                        }
                    } else {
                        g.setNoAvailability();//i livelli non hanno disponibilit�
                    }
                } catch (Exception ex) {
                    g.setIdTicket(ClientStrings.ERROR_GENERIC);
                }
            }
        });
        closingWindowsListener();
    }

    /**
     * Imposta elementi dopo l'avvio del metodo della generazione del ticket.
     */
    public void setTransitionElements() {
        g.setIdTicket(model.getIdTicket());
        g.setLevelLabel(model.getIdTicket().substring(0, 1));
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                model.setAvailability();
                model.setAvailability(model.getAvailability());
                g.getShowTicketId().setText(String.valueOf(""));
                g.setTransitionObject();
                g.getButton().setEnabled(true);
                model.setAvailability();
                g.setEmptyLevLabel();
                model.setAvailability(model.getAvailability());
            }
        }, 10000);
    }

    /**
     * Chiusura della windows listener.
     */
    public void closingWindowsListener() {
        g.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                model.closeSocket();
            }
        });
    }

    /**
     * Metodo che controlla la connessione.
     *
     * @throws IOException the io exception
     */
    public void checkConn() throws IOException {

        if (!this.model.getIsConn()) {
            this.g.initErrorGui();
            System.exit(0);
        }
    }
}
