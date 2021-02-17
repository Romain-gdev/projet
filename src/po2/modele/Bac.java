package po2.modele;

/**
 * Classe d�finissant un dipl�me de bac
 */
public class Bac extends Diplome {

    private SERIE serie;

    /**
     * cr�� un nouveau dipl�me de bac
     *
     * @param annee         l'ann�e d'obtention du dipl�me de bac
     * @param etablissement l'etablissement d'obtention du dipl�me de bac
     * @param serie         la s�rie du dipl�me de bac
     */
    public Bac(ANNEE annee, String etablissement, SERIE serie) {
        super(annee, etablissement);
        this.serie = serie;
    }

    /**
     * @return donne la s�rie du dipl�me de bac
     */
    public SERIE serie() {
        return serie;
    }
    
    /**
     * @return une cha�ne de caract�res repr�sentant la candidature
     */
    @Override
    public String toString() {
    	return "Bac "+serie.toString()+" en "+super.toString();
    }

}

