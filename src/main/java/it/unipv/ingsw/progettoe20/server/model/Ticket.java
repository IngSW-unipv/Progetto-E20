package it.unipv.ingsw.progettoe20.server.model;

import it.unipv.ingsw.progettoe20.server.database.DBConstants;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * Classe dei Ticket.
 */
public class Ticket {
    private String id;
    private Timestamp entranceTime;
    private Timestamp paymentTime;
    private boolean paid;

    /**
     * Costruttore Ticket tramite id.
     *
     * @param id  id
     * @throws IllegalArgumentException  illegal argument exception
     */
    public Ticket(String id) throws IllegalArgumentException {
        checkIdLength(id);
        this.id = id;
        entranceTime = new Timestamp(System.currentTimeMillis());
        paymentTime = null;
        paid = false;
    }

    /**
     * Costruttore Ticket tramite tutte le sue variabili.
     *
     * @param id            id
     * @param entranceTime  tempo di entrata
     * @param paymentTime   tempo di pagamento
     * @param paid          booleano stato pagamento
     * @throws IllegalArgumentException  illegal argument exception
     */
    public Ticket(String id, Timestamp entranceTime, Timestamp paymentTime, boolean paid) throws IllegalArgumentException {
        checkIdLength(id);
        this.id = id;
        this.entranceTime = entranceTime;
        this.paymentTime = paymentTime;
        this.paid = paid;
    }

    private void checkIdLength(String id) throws IllegalArgumentException {
        if (id.length() != DBConstants.TICKET_ID_LENGTH) {
            throw new IllegalArgumentException("ID length must be " + DBConstants.TICKET_ID_LENGTH + "!");
        }
    }

    /**
     * Getter id.
     *
     * @return  id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter tempo di entrata.
     *
     * @return  tempo di entrata
     */
    public Timestamp getEntranceTime() {
        return entranceTime;
    }

    /**
     * Getter tempo del pagamento.
     *
     * @return tempo del pagamento
     */
    public Timestamp getPaymentTime() {
        return paymentTime;
    }


    /**
     * metodo che verifica la correttezza dell'obliterazione (flag e data pagamento)
     *
     * @return un booleano di verifica.
     */
    public boolean obliterationCheck() {
        if (paymentTime==null) return false;

        Timestamp callTime = new Timestamp(System.currentTimeMillis());
        long diff = callTime.getTime() - paymentTime.getTime();
        long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);

        return (diffSeconds <= DBConstants.TICKET_MAX_EXIT_TIME_TOTAL_SECONDS);
    }

    /**
     * Getter nome livello.
     *
     * @return nome livello
     */
    public String getLevelName() {
        return id.substring(0, 1);
    }

    /**
     * setter tempo del pagmento.
     *
     * @param paymentTime tempo del pagamento
     */
    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * setter tempo di entrata
     *
     * @param entranceTime tempo di entrata
     */
    public void setEntranceTime(Timestamp entranceTime) {
        this.entranceTime = entranceTime;
    }

    /**
     * check booleano pagamento.
     *
     * @return  booleano
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * setter booleano pagamento.
     *
     * @param paid il flag di pagamento
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * serve per calcolare l'ammontare da pagare
     *
     * @return differenza di tempo in minuti tra entrata e uscita (in double)
     */
    public double TimeDiff() {
        return (System.currentTimeMillis() - entranceTime.getTime()) / 60000.0;
    }

}
