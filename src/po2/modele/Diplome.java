package po2.modele;

/**
 * Classe abstraite définissant n'importe quel diplôme.
 */
public abstract class Diplome implements Comparable<Diplome> {

    private ANNEE annee;
    private String etablissement;


    /**
     * Créé un nouveau diplôme.
     *
     * @param annee         l'année d'obtention du diplôme
     * @param etablissement l'etablissement d'obtention du diplôme
     */
    public Diplome(ANNEE annee, String etablissement) {
        this.annee = annee;
        this.etablissement = etablissement;
    }

    /**
     * @return donne l'année du diplôme
     */
    public ANNEE annee() {
        return annee;
    }

    /**
     * @return donne l'établissement du diplôme
     */
    public String etablissement() {
        return etablissement;
    }

    /**
     * permet de comparer le diplôme courant à un autre diplôme :
     * un diplôme est plus petit qu'un autre si il est plus vieux.
     *
     * @param autre le diplôme à comparer
     * @return un entier < 0, =0 ou > 0 si le diplôme courant est plus petit, égal ou plus grand
     * que l'autre diplôme
     */
    @Override
    public int compareTo(Diplome autre) {
    	return this.annee.debut() - autre.annee().debut();
    }

    /**
     * @return une chaine de caractère correspondant au diplôme
     */
    @Override
    public String toString() {
	    return this.annee.toString()+" ("+this.etablissement+")";
    }
}

