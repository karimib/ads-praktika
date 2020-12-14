public interface CommandExecutor {

    /**
     * execute -- nimmt eine Kommandozeile, tut irgendetwas gescheites, und
     * berichtet das Resultat.
     *
     * @param command Kommandozeile
     * @return Resultat, ueblicherweise eine oder mehrere Zeilen.
     */
	 String execute(String command) throws Exception;
}//interface CommandExecutor
