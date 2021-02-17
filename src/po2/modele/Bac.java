package po2.modele;

/**
 * Classe définissant un diplôme de bac
 */
public class Bac extends Diplome {

    private SERIE serie;

    /**
     * créé un nouveau diplôme de bac
     *
     * @param annee         l'année d'obtention du diplôme de bac
     * @param etablissement l'etablissement d'obtention du diplôme de bac
     * @param serie         la série du diplôme de bac
     */
    public Bac(ANNEE annee, String etablissement, SERIE serie) {
        super(annee, etablissement);
        this.serie = serie;
    }

    /**
     * @return donne la série du diplôme de bac
     */
    public SERIE serie() {
        return serie;
    }
    
    /**
     * @return une chaîne de caractères représentant la candidature
     */
    @Override
    public String toString() {
    	return "Bac "+serie.toString()+" en "+super.toString();
    }

}

