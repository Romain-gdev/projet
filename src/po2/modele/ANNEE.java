package po2.modele;

import java.util.Comparator;

/**
 * Enumération définissant des années scolaires.
 */
public enum ANNEE {

    A_20_21(20, 21),
    A_19_20(19, 20),
    A_18_19(18, 19),
    A_17_18(17, 18),
    A_16_17(16, 17),
    A_15_16(15, 16),
    A_14_15(14, 15),
    A_13_14(13, 14),
    A_12_13(12, 13),
    A_11_12(11, 12),
    A_10_11(10, 11);

    private int debut = 0;
    private int fin = 0;

    ANNEE(int debut, int fin) {
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * Donne le début d'une annnée scolaire.
     *
     * @return un entier correspondant au début d'une année scolaire
     */
    public int debut() {
        return debut;
    }

    /**
     * Donne la fin d'une année scolaire.
     *
     * @return un entier correspondant Ã  la fin d'une année scolaire
     */
    public int fin() {
        return fin;
    }

    /**
     * @return une chaine de caractères représentant une année scolaire
     */
    @Override
    public String toString() {
        return "20" + debut + "-20" + fin;
    }


    /**
     * Défini un comparateur d'année scolaire
     */
    public static Comparator<ANNEE> COMP_ANNEE = new Comparator<ANNEE>() {
        @Override
        public int compare(ANNEE a1, ANNEE a2) {
            return Integer.compare(a1.debut,a2.debut);
        }
    };

}
