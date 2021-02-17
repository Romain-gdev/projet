package po2.modele;

/**
 * Classe d�finissant un diplome du sup�rieur (= post-bac) hormis les diplomes r�f�renc�s.
 */
public class DiplomeSup extends Diplome {

    private String libelle;

    /**
     * Cr�� un dipl�me du sup�rieur.
     *
     * @param annee         l'ann�e d'obtention du dipl�me du sp�rieur
     * @param etablissement l'etablissement d'obtention du dipl�me du sup�rieur
     * @param libelle       le libell� pr�cis du dipl�me du sup�rieur
     */
    public DiplomeSup(ANNEE annee,
                      String etablissement,
                      String libelle) {
        super(annee, etablissement);
        this.libelle = libelle;
    }

    /**
     * @return donne le libell� du dipl�me
     */
    public String libelle() {
        return libelle;
    }
    
    /**
     * @return une cha�ne de caract�res repr�sentant la candidature
     */
    @Override
    public String toString() {
    	return libelle+" en "+super.toString();
    }


}
