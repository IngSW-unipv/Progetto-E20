package it.unipv.ingsw.progettoe20.server.cli;


/**
 * Interpreta e risponde al comando.
 */
class CommandHandler {
    /**
     * Interpreta e risponde al comando.
     *
     * @param command il comando
     */
    static void handle(String command) {
        try {
            CommandStrategy.valueOf(command.toUpperCase()).command();
        } catch (IllegalArgumentException i) {
            System.out.println(CommandStrings.ERROR_INVALID_STATEMENT);
        }
    }
}
