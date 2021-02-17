package po2.modele;

/**
 * Classe exception levée en cas d'erreur dans les candidatures
 */
public class CandidatureException extends Exception {

    /**
     * créé une nouvelle exception
     *
     * @param message le message associé à l'exception
     */
    public CandidatureException(String message) {
        super(message);
    }
}
