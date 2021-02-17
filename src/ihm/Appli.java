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
public class Appli {
    
    /**
     * point d'entrée de l'application.
     *
     * @param args les arguments entrées
     */
    public static void main(String[] args) {
    	Candidatures candidatures = new Candidatures();
             // la vue
             MainView fenetre = new MainView(candidatures);
             //TODO
             fenetre.setPreferredSize(new Dimension(800, 410));
             //TODO
             fenetre.pack();
             fenetre.setVisible(true);
    }
}
