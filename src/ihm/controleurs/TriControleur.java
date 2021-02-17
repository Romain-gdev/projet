package ihm.controleurs;

import ihm.vue.*;
import po2.modele.Candidatures;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class TriControleur implements ActionListener {

    private MainView fenetre;

    public TriControleur(MainView fn) {
        fenetre = fn;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	switch (fenetre.optionTri()) {
 
    		case 0 :
    			JOptionPane.showMessageDialog(fenetre, "Aucune option de tri sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
    	
    		case 1:
    			fenetre.setDonneesModele(new Candidatures(fenetre.getModeleCandidat(0).getDonnees()).candidaturesParOrdreAlpha(), 0);
    			fenetre.setDonneesModele(new Candidatures(fenetre.getModeleCandidat(1).getDonnees()).candidaturesParOrdreAlpha(), 1);
    			fenetre.setDonneesModele(new Candidatures(fenetre.getModeleCandidat(2).getDonnees()).candidaturesParOrdreAlpha(), 2);
    			fenetre.resetOptionTri();
    		break;
    		
    		case 2:
    			fenetre.setDonneesModele(new Candidatures(fenetre.getModeleCandidat(0).getDonnees()).candidaturesParOrdreCreation(), 0);
    			fenetre.setDonneesModele(new Candidatures(fenetre.getModeleCandidat(1).getDonnees()).candidaturesParOrdreCreation(), 1);
    			fenetre.setDonneesModele(new Candidatures(fenetre.getModeleCandidat(2).getDonnees()).candidaturesParOrdreCreation(), 2);
    			fenetre.resetOptionTri();
    		break;
    	}
    }
}