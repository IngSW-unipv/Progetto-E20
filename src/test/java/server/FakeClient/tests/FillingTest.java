package server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Testa il riempimento del parcheggio
 */
public class FillingTest {
    private BufferedReader in;
    private PrintWriter out;
    private DatabaseFacade dbFacade;

    /**
     * Costruisce un FillingTest.
     *
     * @param in connessione in lettura alla socket.
     * @param out connessione in scrittura alla socket.
     * @param dbFacade reference a un DatabaseFacade.
     */
    public FillingTest(BufferedReader in, PrintWriter out, DatabaseFacade dbFacade) {
        this.in = in;
        this.out = out;
        this.dbFacade = dbFacade;
    }

    /**
     * Effettua il test riempiendo il db.
     *
     * @throws FailedTestException se il test fallisce.
     */
    public void test() throws FailedTestException {
        String  result;
        int totAvailable;

        System.out.println(String.format(TestConstants.TEST_TITLE, "DATABASE FILL"));

        out.println(Protocol.REQUEST_TOTAL_AVAILABILITY);

        try {
            result = in.readLine();
            totAvailable = Integer.parseInt(result.substring(Protocol.RESPONSE_OK.length() + Protocol.SEPARATOR.length()));
            System.out.println(totAvailable);

            fillDb(totAvailable);

            result = in.readLine();
            totAvailable = Integer.parseInt(result.substring(Protocol.RESPONSE_OK.length() + Protocol.SEPARATOR.length()));
            System.out.println(totAvailable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Riempie il db di ticket, fino a lasciare un solo posto libero.
     *
     * @param totAvailable posti disponibili nel db.
     */
    private void fillDb(int totAvailable) throws IOException {
        while (totAvailable > 0) {
            out.println(Protocol.REQUEST_GENERATE_ID);
            String[] parts = in.readLine().split(Protocol.SEPARATOR);
            if (parts.length != 2) {
                totAvailable++;
            }
            totAvailable--;
            System.out.println("Available:" + totAvailable);
        }
    }
}
