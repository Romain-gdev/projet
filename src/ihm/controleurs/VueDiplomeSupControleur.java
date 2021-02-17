package ihm.controleurs;

import po2.modele.*;
import ihm.vue.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class VueDiplomeSupControleur implements ActionListener {

    private AddCandidatView fenetre;
    private String titre;

    public VueDiplomeSupControleur(AddCandidatView fn, String str, MonModel model) {
        fenetre = fn;
        titre = str;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (fenetre.getNom().equals("") || fenetre.getPrenom().equals("") ||
    			fenetre.getAnnee() == 0 || fenetre.getStatut() == null ||
    			fenetre.getEtablissement().equals(""))
    		JOptionPane.showMessageDialog(fenetre, "Veuillez compléter les informations précédentes\nafin de continuer", "Erreur", JOptionPane.ERROR_MESSAGE);
    	else  {
    		try {
    			Candidat nouveau;
        		Bac nvBac = new Bac(fenetre.getAnneeBac(), fenetre.getEtablissement(), fenetre.getSerie());
    			if (fenetre.getStatut() == STATUT.ETUDIANT)
        			nouveau = Candidat.fabriqueCandidatEtudiant(fenetre.getNom(), fenetre.getPrenom(), fenetre.getAnnee(), nvBac, null);
    			else
    				nouveau = Candidat.fabriqueCandidat(fenetre.getNom(), fenetre.getPrenom(), fenetre.getAnnee(), fenetre.getStatut(), nvBac);



    		}
    		catch (CandidatureException ex) {
    			JOptionPane.showMessageDialog(fenetre, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
}
