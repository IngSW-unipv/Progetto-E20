package it.unipv.ingsw.progettoe20.server.model;

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

/**
 * Classe dei Livelli.
 */
public class Level {
    private final String name;
    private int available;
    private int total;

    /**
     * Costruttore dei Livelli.
     *
     * @param name  nome
     * @param total totale posti
     */
    public Level(String name, int total) {
        if (!checkName(name)) {
            throw new IllegalArgumentException(ErrorStrings.WRONG_LEVEL_NAME_LENGTH);
        }
        this.name = name.toUpperCase();
        this.available = total;
        this.total = total;
    }

    /**
     * Costruttore  livelli con tutte le variabili.
     *
     * @param name      nome
     * @param available posti disponibili
     * @param total     posti totali
     */
    public Level(String name, int available, int total) {
        this.name = name.toUpperCase();
        this.available = available;
        this.total = total;
    }

    private boolean checkName(String name) {
        return name.length() == DBConstants.LEVEL_NAME_LENGTH;
    }

    /**
     * getter nome.
     *
     * @return  nome
     */
    public String getName() {
        return name;
    }

    /**
     * getter posti disponibili.
     *
     * @return  posti disponibili
     */
    public int getAvailable() {
        return available;
    }

    /**
     * setter posti disponibili.
     *
     * @param available posti disponibili
     */
    public void setAvailable(int available) {
        this.available = available;
    }

    /**
     * gettr posti totali.
     *
     * @return posti totali
     */
    public int getTotal() {
        return total;
    }

    /**
     * setter posti totali.
     *
     * @param total posti totali
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * aumenta posti disponibili.
     */
    public void increaseAvailable() {
        setAvailable(available += 1);
    }

    /**
     * diminuisce posti disponibili.
     */
    public void decreaseAvailable() {
        setAvailable(available -= 1);
    }
}
