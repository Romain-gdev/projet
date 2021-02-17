package po2.modele;

/**
 * Interface proposant des méthodes de filtre de candidatures.
 */
public interface FiltreCandidatures {

    /**
     * Filtre l'ensemble des candidatures et retourne
     * un sous-ensemble de candidatures ayant un nom de candidat  correspondant à celui indiqué.
     *
     * @param nom le nom du candidat à rechercher
     * @return toutes les candidatures ayant comme nom de famille le nom passé en paramètre
     */
    public Candidatures candidaturesAyantCommeNom(String nom);

    /**
     * Filtre l'ensemble des candidatures et retourne
     * un sous-ensemble des candidatures ayant un diplôme correspondant à la référence diplôme.
     *
     * @param reference la reference de diplômes à chercher
     * @return toutes les candidatures ayant un diplôme correspondant à la référence diplôme
     */
    Candidatures candidaturesParReference(REF_DIPLOME reference);

    /**
     * Filtre l'ensemble des candidatures et retourne
     * un sous-ensemble des candidatures ayant comme série de bac, la serie indiquée.
     *
     * @param serie la série de bac à chercher
     * @return toutes les candidatures ayant comme série de bac, la série indiquée
     */
    Candidatures candidaturesParSerieBac(SERIE serie);
}
