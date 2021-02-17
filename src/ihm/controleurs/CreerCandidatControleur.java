package ihm.controleurs;

import po2.modele.*;
import ihm.vue.*;
import javax.swing.*;
import java.awt.event.*;

public class CreerCandidatControleur implements ActionListener {

    private AddCandidatView fenetre;
    private MainView parent;

    public CreerCandidatControleur(AddCandidatView fn, MainView mv) {
        fenetre = fn;
        parent = mv;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (fenetre.activeAjoutDpPrepare())
    		JOptionPane.showMessageDialog(fenetre, "Vous devez créer un diplôme préparé pour l'étudiant\navant de continuer", "Avertissement", JOptionPane.WARNING_MESSAGE);
    	else if (fenetre.fieldsAreEmpty())
    		JOptionPane.showMessageDialog(fenetre, "Veuillez compléter les informations précédentes\navant de continuer", "Avertissement", JOptionPane.WARNING_MESSAGE);
    	else {
    		try {
        		Candidat nouveau;
        		Bac nvBac = new Bac(fenetre.getAnneeBac(), fenetre.getEtablissement(), fenetre.getSerie());
        		String nvNom = fenetre.getNom().toUpperCase();
        		String nvPrenom = fenetre.getPrenom().substring(0, 1).toUpperCase() + fenetre.getPrenom().substring(1).toLowerCase();
        		if (fenetre.getStatut() == STATUT.ETUDIANT)
        			nouveau = Candidat.fabriqueCandidatEtudiant(nvNom, nvPrenom, fenetre.getAnnee(), nvBac, fenetre.getDiplomePrepare());
        		else
        			nouveau = Candidat.fabriqueCandidat(nvNom, nvPrenom, fenetre.getAnnee(), fenetre.getStatut(), nvBac);
        		for (int i=0; i<fenetre.getModeleListe().getSize(); i++)
        			nouveau.ajouteDiplome((DiplomeSup) fenetre.getModeleListe().getElementAt(i));
        		
        		parent.getModeleCandidat(0).addElement(nouveau);
        		if (Candidatures.candidatureEstValide(nouveau))
        			parent.getModeleCandidat(1).addElement(nouveau);
        		else
        			parent.getModeleCandidat(2).addElement(nouveau);
            	fenetre.dispose();
        	}
    		catch (CandidatureException ex) {
    			JOptionPane.showMessageDialog(fenetre, "Impossible de créer le candidat :\n"+ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
}
