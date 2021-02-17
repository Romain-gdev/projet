package po2.modele;

/**
 * Interface proposant des m�thodes de tri de candidatures.
 */
public interface TriCandidatures {

    /**
     * Trie l'ensemble des candidatures courantes et retourne une nouvelle instance.
     *
     * @return un nouvel ensemble de candidatures tri� alphab�tiquement
     */
    Candidatures candidaturesParOrdreAlpha();

    /**
     * Trie l'ensemble des candidatures courantes et retourne une nouvelle instance.
     *
     * @return un nouvel ensemble de candidatures tri� par ordre de cr�ation
     */
    Candidatures candidaturesParOrdreCreation();
}
