package po2.modele;

/**
 * Enumeration d�finissant les statuts possible pour un candidat.
 */
public enum STATUT {

    ETUDIANT("Etudiant"),
    SALARIE("Salari�"),
    DEMANDEUR_D_EMPLOI("Demandeur d'emploi");

    private final String str;

    STATUT(String str) {
        this.str = str;
    }

    /**
     * @return donne le statut sous forme de chaines de caract�res
     */
    @Override
    public String toString() {
        return str;
    }
}
