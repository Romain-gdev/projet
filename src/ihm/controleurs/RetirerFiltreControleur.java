package ihm.controleurs;

import ihm.vue.*;
import po2.modele.Candidatures;

import java.awt.event.*;

public class RetirerFiltreControleur implements ActionListener {

    private MainView fenetre;

    public RetirerFiltreControleur(MainView fn) {
        fenetre = fn;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	fenetre.setModele(fenetre.getModeleCandidat(0), 0);
    	fenetre.setModele(fenetre.getModeleCandidat(1), 1);
    	fenetre.setModele(fenetre.getModeleCandidat(2), 2);
    	fenetre.resetOptionFiltre();
    }
}