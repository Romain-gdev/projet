package po2.modele;

import java.util.Comparator;

/**
 * Classe permettant de comparer deux candidats par ordre alphabétique Nom-Prénom
 */
public class ComparateurOrdreAlpha implements Comparator<Candidat> {

    public ComparateurOrdreAlpha() {
    }

    /**
     * Compare deux candidats.
     *
     * @param c1 le premier candidat
     * @param c2 le second candidat
     * @return  return un entier < 0, =0 ou > 0 si le candidat est plus petit,
     * égale ou plus grande (alpbabétiquement parlant) que le second candidat
     */
    @Override
    public int compare(Candidat c1, Candidat c2) {
    	if (c1.nom().equals(c2.nom())) {
    		String prenom1 = c1.prenom().toUpperCase();
            String prenom2 = c2.prenom().toUpperCase();
            return prenom1.compareTo(prenom2);
    	}
    	else {
    		String nom1 = c1.nom().toUpperCase();
            String nom2 = c2.nom().toUpperCase();
            return nom1.compareTo(nom2);
    	}
    }
}
