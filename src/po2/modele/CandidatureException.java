package po2.modele;

/**
 * Classe exception lev�e en cas d'erreur dans les candidatures
 */
public class CandidatureException extends Exception {

    /**
     * cr�� une nouvelle exception
     *
     * @param message le message associ� � l'exception
     */
    public CandidatureException(String message) {
        super(message);
    }
}
