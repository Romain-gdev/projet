package po2.modele;

/**
 * Interface proposant des méthodes de tri de candidatures.
 */
public interface TriCandidatures {

    /**
     * Trie l'ensemble des candidatures courantes et retourne une nouvelle instance.
     *
     * @return un nouvel ensemble de candidatures trié alphabétiquement
     */
    Candidatures candidaturesParOrdreAlpha();

    /**
     * Trie l'ensemble des candidatures courantes et retourne une nouvelle instance.
     *
     * @return un nouvel ensemble de candidatures trié par ordre de création
     */
    Candidatures candidaturesParOrdreCreation();
}
