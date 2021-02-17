package po2.modele;

/**
 * Enumeration définissant les statuts possible pour un candidat.
 */
public enum STATUT {

    ETUDIANT("Etudiant"),
    SALARIE("Salarié"),
    DEMANDEUR_D_EMPLOI("Demandeur d'emploi");

    private final String str;

    STATUT(String str) {
        this.str = str;
    }

    /**
     * @return donne le statut sous forme de chaines de caractères
     */
    @Override
    public String toString() {
        return str;
    }
}
