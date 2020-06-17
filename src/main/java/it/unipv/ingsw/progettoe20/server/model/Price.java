package it.unipv.ingsw.progettoe20.server.model;

/**
 * Classe dei prezzi.
 */
public class Price implements Comparable<Price> {

    private int minutes;
    private double price;

    /**
     * costruttore prezzi.
     *
     * @param minutes the minutes
     * @param price   the price
     */
    public Price(int minutes, double price) {
        this.minutes = minutes;
        this.price = price;
    }

    /**
     * getter minuti.
     *
     * @return  minuti
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Getter prezzi.
     *
     * @return prezzi
     */
    public double getPrice() {
        return price;
    }

    /**
     * setter minuti.
     *
     * @param minutes  minuti
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * setter prezzo.
     *
     * @param price  prezzo
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Price p) {
        if (minutes == p.minutes) {
            return 0;
        } else if (minutes > p.minutes) {
            return 1;
        } else return -1;
    }
}
