package server.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import it.unipv.ingsw.progettoe20.server.PaymentCalculator;
import it.unipv.ingsw.progettoe20.server.model.Price;
import it.unipv.ingsw.progettoe20.server.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PaymentCalculatorTest {
    private static String ID = "testtest";
    private static boolean FLAG = false;

    private static ArrayList<Price> list;
    private static Ticket fiveMinTicket;
    private static Ticket nineHoursTicket;
    private static Ticket fourHoursTicket;



    @BeforeAll
    public static void init() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Init prices list
        Price price0 = new Price(30, 0.60);
        Price price1 = new Price(60, 1.00);
        Price price2 = new Price(8 * 60, 7.77);
        list = new ArrayList<>();
        list.add(price0);
        list.add(price1);
        list.add(price2);

        // Init 5 min ticket
        int fiveMinutes = 5 * 60 * 1000;
        Timestamp fiveMinBefore = new Timestamp(System.currentTimeMillis() - fiveMinutes);
        fiveMinTicket = new Ticket(ID, fiveMinBefore, null, FLAG);

        // Init 9 hours ticket
        int nineHours = 9 * 60 * 60 * 1000;
        Timestamp nineHoursBefore = new Timestamp(System.currentTimeMillis() - nineHours);
        nineHoursTicket = new Ticket(ID, nineHoursBefore, null, FLAG);

        // Init (less than) 4 hours ticket
        int fourHours = (int) 3.9 * 60 * 60 * 1000;
        Timestamp fourHoursBefore = new Timestamp(System.currentTimeMillis() - fourHours);
        fourHoursTicket = new Ticket(ID, fourHoursBefore, null, FLAG);
    }

    @DisplayName("Minimum price")
    @Test
    public void testMin() {
        assertEquals(list.get(0).getPrice(), PaymentCalculator.getPaymentAmount(fiveMinTicket, list), "Price should be " + list.get(0).getPrice());

    }

    @DisplayName("Maximum price")
    @Test
    public void testMax() {
        assertEquals(list.get(2).getPrice(), PaymentCalculator.getPaymentAmount(nineHoursTicket, list), "Price should be " + list.get(2).getPrice());
    }

    @DisplayName("Hourly price")
    @Test
    public void testHourly() {
        assertEquals(list.get(1).getPrice() * 4, PaymentCalculator.getPaymentAmount(fourHoursTicket, list), "Price should be " + list.get(1).getPrice() * 4);

    }

}
