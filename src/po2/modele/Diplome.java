package po2.modele;

/**
 * Classe abstraite d�finissant n'importe quel dipl�me.
 */
public abstract class Diplome implements Comparable<Diplome> {

    private ANNEE annee;
    private String etablissement;


    /**
     * Cr�� un nouveau dipl�me.
     *
     * @param annee         l'ann�e d'obtention du dipl�me
     * @param etablissement l'etablissement d'obtention du dipl�me
     */
    public Diplome(ANNEE annee, String etablissement) {
        this.annee = annee;
        this.etablissement = etablissement;
    }

    /**
     * @return donne l'ann�e du dipl�me
     */
    public ANNEE annee() {
        return annee;
    }

    /**
     * @return donne l'�tablissement du dipl�me
     */
    public String etablissement() {
        return etablissement;
    }

    /**
     * permet de comparer le dipl�me courant � un autre dipl�me :
     * un dipl�me est plus petit qu'un autre si il est plus vieux.
     *
     * @param autre le dipl�me � comparer
     * @return un entier < 0, =0 ou > 0 si le dipl�me courant est plus petit, �gal ou plus grand
     * que l'autre dipl�me
     */
    @Override
    public int compareTo(Diplome autre) {
    	return this.annee.debut() - autre.annee().debut();
    }

    /**
     * @return une chaine de caract�re correspondant au dipl�me
     */
    @Override
    public String toString() {
	    return this.annee.toString()+" ("+this.etablissement+")";
    }
}

