package po2.modele;

/**
 * Classe définissant un diplome du supérieur référencé.
 */
public class DiplomeSupReference extends DiplomeSup {

    private REF_DIPLOME reference;

    /**
     * créé un diplome du supérieur référencé.
     *
     * @param annee         l'année d'obtention du diplôme du supérieur
     * @param etablissement l'etablissement d'obtention du diplôme du supérieur
     * @param libelle       le libellé précis du diplôme du supérieur
     * @param reference     la référence du diplôme du supérieur
     */
    public DiplomeSupReference(ANNEE annee,
                               String etablissement,
                               String libelle,
                               REF_DIPLOME reference) {
        super(annee, etablissement, libelle);
        this.reference = reference;
    }

    /**
     * @return la référence du diplome considéré
     */
    public REF_DIPLOME reference() {
        return reference;
    }
    
    /**
     * @return une chaîne de caractères représentant la candidature
     */
    @Override
    public String toString() {
    	return reference.toString()+" "+super.toString();
    }
}
