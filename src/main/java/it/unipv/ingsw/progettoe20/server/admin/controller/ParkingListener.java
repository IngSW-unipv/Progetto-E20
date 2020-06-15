package it.unipv.ingsw.progettoe20.server.admin.controller;

import it.unipv.ingsw.progettoe20.server.admin.model.ParkingAdministrator;
import it.unipv.ingsw.progettoe20.server.admin.view.ParkingManagementGUI;

import javax.swing.*;

/*
 *Listener che controlla le operazioni effettuate nella schermata del ParkingManagementGUI
 *Aggiunge o rimuove dei parcheggi.
 */

/**
 * The type Parking listener.
 */
public class ParkingListener extends AbstractListener {

    private ParkingManagementGUI gui;
    private ParkingAdministrator admin;

    /**
     * Instantiates a new Parking listener.
     *
     * @param gui the gui
     */
    public ParkingListener(ParkingManagementGUI gui) {
        this.gui = gui;
        admin = ParkingAdministrator.getInstance();
    }

    /*
     * Torna alla GUI dell'Administrator
     *
     */
    @Override
    public void changeGUI() {
        super.changeGUI();
        gui.setVisible(false);
    }

    /**
     * Entered level string.
     *
     * @return the string
     */
    /*
     * Legge il nome del livello inserito nel JTextField
     *
     * @return name nome livello richiesto
     *
     */
    public String enteredLevel() {
        String name = gui.getField().getText();

        if (name.equals("")) {
            // Se non viene inserito nessun nome
            JOptionPane.showMessageDialog(null, "Please, enter the level", "Error", 1, null);
            throw new IllegalArgumentException("Impossible! Enter the level name");
        }

        return name;
    }

    /**
     * Entered parking lots int.
     *
     * @return the int
     */
    /*
     * Legge il numero di parcheggi inseriti nel JTextField
     *
     * @return number numero di parcheggi inseriti
     *
     */
    public int enteredParkingLots() {
        String parkingLots = gui.getField2().getText();
        int number = 0;

        if (parkingLots.equals("")) {
            // Se non viene inserito nessun numero
            JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
            throw new IllegalArgumentException("Impossible! Enter the parking lots");
        }

        number = Integer.parseInt(parkingLots);
        return number;
    }

    /*
     * Modifica il numero di parcheggi in base a quanto scelto nella combo box
     *
     */
    @Override
    public void change() {
        try {
            String action = (String) gui.getCombo().getSelectedItem();
            String name = enteredLevel();
            int number = enteredParkingLots();

            if (action.equals("Add parkings")) {
                // Aggiunge parcheggi
                int capacity = admin.addParkings(name, number);
                JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
            } else {
                // Rimuove dei parcheggi
                int capacity = admin.removeParkings(name, number);
                JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
            }
        } catch (IllegalArgumentException i) {
            return;
        }
    }
}
