package ihm.controleurs;

import po2.modele.*;
import ihm.vue.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FiltreControleur implements ActionListener {

    private MainView fenetre;

    public FiltreControleur(MainView fn) {
        fenetre = fn;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	Candidatures filtre0 = new Candidatures(fenetre.getModeleCandidat(0).getDonnees());
    	Candidatures filtre1 = new Candidatures(fenetre.getModeleCandidat(1).getDonnees());
    	Candidatures filtre2 = new Candidatures(fenetre.getModeleCandidat(2).getDonnees());
    	switch (fenetre.optionFiltre()) {
    	
    	case 0 :
    		String motif = fenetre.getMotif().toUpperCase();
    		if (filtre0.candidaturesAyantCommeNom(motif).candidats().isEmpty())
    			JOptionPane.showMessageDialog(fenetre, "Le motif à filtrer n'est pas valide\nAucun nom ne correspond au nom saisi", "Erreur", JOptionPane.ERROR_MESSAGE);
    		else {
    			fenetre.setDonneesModele(filtre0.candidaturesAyantCommeNom(motif), 10);
    			fenetre.setModele(fenetre.getModeleCandidat(10), 0);
    			fenetre.setDonneesModele(filtre1.candidaturesAyantCommeNom(motif), 11);
    			fenetre.setModele(fenetre.getModeleCandidat(11), 1);
    			fenetre.setDonneesModele(filtre2.candidaturesAyantCommeNom(motif), 12);
    			fenetre.setModele(fenetre.getModeleCandidat(12), 2);
    			fenetre.resetAffichageListe(true);
    			fenetre.setRetirerFiltre(true);
    		}
    		break;
    		
    	case 1:
    		ArrayList<String> refValues = new ArrayList<>();
    		refValues.add("DUT"); refValues.add("Licence");
    		for (int i=0; i<REF_DIPLOME.values().length; i++ )
    			refValues.add(REF_DIPLOME.values()[i].toString());
    		if (refValues.contains(fenetre.getMotif())) {
    			fenetre.setDonneesModele(filtre0.candidaturesParReference(REF_DIPLOME.getRefFromString(fenetre.getMotif())), 10);
    			fenetre.setModele(fenetre.getModeleCandidat(10), 0);
    			fenetre.setDonneesModele(filtre1.candidaturesParReference(REF_DIPLOME.getRefFromString(fenetre.getMotif())), 11);
    			fenetre.setModele(fenetre.getModeleCandidat(11), 1);
    			fenetre.setDonneesModele(filtre2.candidaturesParReference(REF_DIPLOME.getRefFromString(fenetre.getMotif())), 12);
    			fenetre.setModele(fenetre.getModeleCandidat(12), 2);
    			fenetre.resetAffichageListe(true);
    			fenetre.setRetirerFiltre(true);
    		}
    		else
    			JOptionPane.showMessageDialog(fenetre, "Le motif à filtrer n'est pas valide\nValeurs possibles :\n"+refValues.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
    		
    	case 2:
    		ArrayList<String> serieValues = new ArrayList<>();
    		for (int i=0; i<SERIE.values().length; i++ )
    			serieValues.add(SERIE.values()[i].toString());
    		if (serieValues.contains(fenetre.getMotif())) {
    			fenetre.setDonneesModele(filtre0.candidaturesParSerieBac(SERIE.getSerieFromString(fenetre.getMotif())), 10);
    			fenetre.setModele(fenetre.getModeleCandidat(10), 0);
    			fenetre.setDonneesModele(filtre1.candidaturesParSerieBac(SERIE.getSerieFromString(fenetre.getMotif())), 11);
    			fenetre.setModele(fenetre.getModeleCandidat(11), 1);
    			fenetre.setDonneesModele(filtre2.candidaturesParSerieBac(SERIE.getSerieFromString(fenetre.getMotif())), 12);
    			fenetre.setModele(fenetre.getModeleCandidat(12), 2);
    			fenetre.resetAffichageListe(true);
    			fenetre.setRetirerFiltre(true);
    		}
    		else
    			JOptionPane.showMessageDialog(fenetre, "Le motif à filtrer n'est pas valide\nValeurs possibles :\n"+serieValues.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
    	}
    }
}