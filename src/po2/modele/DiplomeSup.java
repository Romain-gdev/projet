package po2.modele;

/**
 * Classe définissant un diplome du supérieur (= post-bac) hormis les diplomes référencés.
 */
public class DiplomeSup extends Diplome {

    private String libelle;

    /**
     * Créé un diplôme du supérieur.
     *
     * @param annee         l'année d'obtention du diplôme du spérieur
     * @param etablissement l'etablissement d'obtention du diplôme du supérieur
     * @param libelle       le libellé précis du diplôme du supérieur
     */
    public DiplomeSup(ANNEE annee,
                      String etablissement,
                      String libelle) {
        super(annee, etablissement);
        this.libelle = libelle;
    }

    /**
     * @return donne le libellé du diplôme
     */
    public String libelle() {
        return libelle;
    }
    
    /**
     * @return une chaîne de caractères représentant la candidature
     */
    @Override
    public String toString() {
    	return libelle+" en "+super.toString();
    }


}
