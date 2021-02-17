package ihm;

import ihm.vue.*;
import po2.modele.*;

import static org.junit.jupiter.api.Assertions.fail;
import static po2.modele.ANNEE.A_10_11;
import static po2.modele.ANNEE.A_11_12;
import static po2.modele.ANNEE.A_12_13;
import static po2.modele.ANNEE.A_13_14;
import static po2.modele.ANNEE.A_14_15;
import static po2.modele.ANNEE.A_15_16;
import static po2.modele.ANNEE.A_16_17;
import static po2.modele.ANNEE.A_19_20;
import static po2.modele.ANNEE.A_20_21;
import static po2.modele.REF_DIPLOME.BTS_SN_IR;
import static po2.modele.REF_DIPLOME.DUT_INFO;
import static po2.modele.REF_DIPLOME.LICENCE_INFO;
import static po2.modele.SERIE.L;
import static po2.modele.SERIE.S;
import static po2.modele.SERIE.STI2D;

import java.awt.*;

/**
 * The type Appli.
 */
public class Appli2 {
    
    /**
     * point d'entrée de l'application.
     *
     * @param args les arguments entrées
     */
    public static void main(String[] args) {
    	 try{
    		 // le modele
             Candidat albert = Candidat.fabriqueCandidatEtudiant("ALBERT", "albert", 1998,
                     new Bac(A_11_12, "Lycee Livet", STI2D),
                     new DiplomeSupReference(A_20_21, "Lycee Livet", "rerertet", BTS_SN_IR));
             albert.ajouteDiplome(new DiplomeSupReference(A_19_20, "Lycee Livet", "yutuy", LICENCE_INFO));
             albert.ajouteDiplome(new DiplomeSupReference(A_15_16, "Lycee Livet", "tyyry", DUT_INFO));
             albert.ajouteDiplome(new DiplomeSup(A_13_14, "Lycee Livet", "yuyuy"));

             
             Candidat bernard = Candidat.fabriqueCandidat("BERNARD", "bernard", 1989,
                     STATUT.SALARIE, new Bac(A_10_11, "Lycee Livet", L));
             bernard.ajouteDiplome(new DiplomeSupReference(A_16_17, "Lycee Livet", "tyyry", BTS_SN_IR));
             bernard.ajouteDiplome(new DiplomeSup(A_15_16, "Lycee Livet", "yuyuy"));

             
             Candidat corinne = Candidat.fabriqueCandidat("CORINNE", "corinne", 2000,
                     STATUT.DEMANDEUR_D_EMPLOI, new Bac(A_12_13, "Lycee Livet", S));
             corinne.ajouteDiplome(new DiplomeSupReference(A_14_15, "Lycee Livet", "yutuy", DUT_INFO));
        
             Candidatures candidatures = new Candidatures();
             candidatures.ajoute(albert);
             candidatures.ajoute(bernard);
             candidatures.ajoute(corinne);
             
             
             // la vue
             MainView fenetre = new MainView(candidatures);
             //TODO
             fenetre.setPreferredSize(new Dimension(700, 350));
             //TODO
             fenetre.pack();
             fenetre.setVisible(true);
             
    	 }
         catch(CandidatureException ex) {
             fail(ex.getMessage());
         }
    }
}
