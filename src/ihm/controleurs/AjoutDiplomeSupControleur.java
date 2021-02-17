package ihm.controleurs;

import po2.modele.*;
import ihm.vue.*;
import javax.swing.*;
import java.awt.event.*;

public class AjoutDiplomeSupControleur implements ActionListener {

    private AddDiplomeSupView fenetre;
    private AddCandidatView parent;
    private ModelDiplome model;

    public AjoutDiplomeSupControleur(AddDiplomeSupView fn, AddCandidatView vue, ModelDiplome mm) {
        fenetre = fn;
        parent = vue;
        model = mm;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    		for (int i=0; i<model.getSize(); i++)
        		if (ANNEE.COMP_ANNEE.compare(model.getElementAt(i).annee(), fenetre.getAnnee()) == 0) {
        			JOptionPane.showMessageDialog(fenetre, "Le candidat possède déjà un diplôme supérieur pour l'année saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        	if (ANNEE.COMP_ANNEE.compare(parent.getAnneeBac(),fenetre.getAnnee()) < 0) {
        		if ((parent.getStatut() != STATUT.ETUDIANT) || (parent.getDiplomePrepare() != null && ANNEE.COMP_ANNEE.compare(parent.getDiplomePrepare().annee(),fenetre.getAnnee()) > 0)) {
        			if (fenetre.getEtablissement().equals("") || fenetre.getLibelle().equals(""))
                		JOptionPane.showMessageDialog(fenetre, "Certains champs sont incomplets", "Erreur", JOptionPane.ERROR_MESSAGE);
                	else {
                		DiplomeSup nouveau;
            			if (fenetre.refIsChecked())
                			nouveau = new DiplomeSupReference(fenetre.getAnnee(), fenetre.getEtablissement(), fenetre.getLibelle(), fenetre.getReference());
                		else 
                			nouveau = new DiplomeSup(fenetre.getAnnee(), fenetre.getEtablissement(), fenetre.getLibelle());
            			
            			model.addElement(nouveau);
            			parent.activeStatut(false);
            			fenetre.dispose();
            		}
        		}
        		else
            		JOptionPane.showMessageDialog(fenetre, "L'année du diplôme supérieur ne peut pas\nêtre supérieure ou égale à l'année du diplôme en cours de préparation", "Erreur", JOptionPane.ERROR_MESSAGE);
        	}
        	else
        		JOptionPane.showMessageDialog(fenetre, "L'année du diplôme supérieur ne peut pas\nêtre antérieure ou égale à l'année du Bac", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
