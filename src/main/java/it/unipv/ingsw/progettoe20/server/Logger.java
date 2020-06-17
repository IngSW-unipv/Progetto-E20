package it.unipv.ingsw.progettoe20.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Log degli eventi su file di testo.
 */
public class Logger {
    public static final String LOG_FILE = "ServerLOGS";
    private static PrintWriter writer = null;

    // Code executed as init
    static {
        checkFileExistence();
        try {
            writer = new PrintWriter(LOG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrive una string nel file di log. Timestamp aggiunto di default.
     * @param log string da scrivere.
     */
    public static void log(String log) {
        writer.println("[" + new Timestamp(System.currentTimeMillis()) + "]    " + log);
    }

    /**
     * Chiusura del  file.
     */
    public static void close() {
        writer.flush();
        writer.close();
    }

    /**
     * Controlla se il file di log esiste già, se è così viene rinominato in "ServerLOGS.old".
     */
    private static void checkFileExistence() {
        File f = new File(LOG_FILE);
        if(f.exists() && !f.isDirectory()) {
            File old = new File(LOG_FILE + ".old");
            f.renameTo(old);
        }
    }
}
