package po2.modele;

import java.util.Comparator;

/**
 * Enum�ration d�finissant des ann�es scolaires.
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
     * Donne le d�but d'une annn�e scolaire.
     *
     * @return un entier correspondant au d�but d'une ann�e scolaire
     */
    public int debut() {
        return debut;
    }

    /**
     * Donne la fin d'une ann�e scolaire.
     *
     * @return un entier correspondant à la fin d'une ann�e scolaire
     */
    public int fin() {
        return fin;
    }

    /**
     * @return une chaine de caract�res repr�sentant une ann�e scolaire
     */
    @Override
    public String toString() {
        return "20" + debut + "-20" + fin;
    }


    /**
     * D�fini un comparateur d'ann�e scolaire
     */
    public static Comparator<ANNEE> COMP_ANNEE = new Comparator<ANNEE>() {
        @Override
        public int compare(ANNEE a1, ANNEE a2) {
            return Integer.compare(a1.debut,a2.debut);
        }
    };

}
