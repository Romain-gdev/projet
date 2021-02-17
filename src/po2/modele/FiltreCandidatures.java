package po2.modele;

/**
 * Interface proposant des m�thodes de filtre de candidatures.
 */
public interface FiltreCandidatures {

    /**
     * Filtre l'ensemble des candidatures et retourne
     * un sous-ensemble de candidatures ayant un nom de candidat  correspondant � celui indiqu�.
     *
     * @param nom le nom du candidat � rechercher
     * @return toutes les candidatures ayant comme nom de famille le nom pass� en param�tre
     */
    public Candidatures candidaturesAyantCommeNom(String nom);

    /**
     * Filtre l'ensemble des candidatures et retourne
     * un sous-ensemble des candidatures ayant un dipl�me correspondant � la r�f�rence dipl�me.
     *
     * @param reference la reference de dipl�mes � chercher
     * @return toutes les candidatures ayant un dipl�me correspondant � la r�f�rence dipl�me
     */
    Candidatures candidaturesParReference(REF_DIPLOME reference);

    /**
     * Filtre l'ensemble des candidatures et retourne
     * un sous-ensemble des candidatures ayant comme s�rie de bac, la serie indiqu�e.
     *
     * @param serie la s�rie de bac � chercher
     * @return toutes les candidatures ayant comme s�rie de bac, la s�rie indiqu�e
     */
    Candidatures candidaturesParSerieBac(SERIE serie);
}
