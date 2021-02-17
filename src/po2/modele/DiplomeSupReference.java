package po2.modele;

/**
 * Classe d�finissant un diplome du sup�rieur r�f�renc�.
 */
public class DiplomeSupReference extends DiplomeSup {

    private REF_DIPLOME reference;

    /**
     * cr�� un diplome du sup�rieur r�f�renc�.
     *
     * @param annee         l'ann�e d'obtention du dipl�me du sup�rieur
     * @param etablissement l'etablissement d'obtention du dipl�me du sup�rieur
     * @param libelle       le libell� pr�cis du dipl�me du sup�rieur
     * @param reference     la r�f�rence du dipl�me du sup�rieur
     */
    public DiplomeSupReference(ANNEE annee,
                               String etablissement,
                               String libelle,
                               REF_DIPLOME reference) {
        super(annee, etablissement, libelle);
        this.reference = reference;
    }

    /**
     * @return la r�f�rence du diplome consid�r�
     */
    public REF_DIPLOME reference() {
        return reference;
    }
    
    /**
     * @return une cha�ne de caract�res repr�sentant la candidature
     */
    @Override
    public String toString() {
    	return reference.toString()+" "+super.toString();
    }
}
