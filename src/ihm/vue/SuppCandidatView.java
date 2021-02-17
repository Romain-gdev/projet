package ihm.vue;

import po2.modele.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import ihm.controleurs.*;

public class SuppCandidatView extends JDialog{

	private MainView parent;
	
    private JButton supprimer;
    private JButton annuler;

    public SuppCandidatView(MainView vue){
        super(vue,"Supprimer le candidat courant",true);
        parent = vue;

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel question = new JLabel("Voulez-vous supprimez le candidat courant ?");
        main.add(question);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER));
        supprimer = new JButton("Supprimer");
        nav.add(supprimer);
        annuler = new JButton("Annuler");
        nav.add(annuler);
        main.add(nav);
        main.add(nav);
        
        supprimer.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		Candidat courant = parent.getCourant();
        		if (parent.getModeleActif(0) != parent.getModeleCandidat(0))
        			parent.getModeleCandidat(0).removeElement(courant);
        		parent.getModeleActif(0).removeElement(courant);
        		if (Candidatures.candidatureEstValide(courant)) {
        			if (parent.getModeleActif(1) != parent.getModeleCandidat(1))
            			parent.getModeleCandidat(1).removeElement(courant);
        			parent.getModeleActif(1).removeElement(courant);
        		}
        		else {
        			if (parent.getModeleActif(2) != parent.getModeleCandidat(2))
            			parent.getModeleCandidat(2).removeElement(courant);
        			parent.getModeleActif(2).removeElement(courant);
        		}
        		SuppCandidatView.this.dispose();
        	}
        });
        annuler.addActionListener(new AnnulerFenetreControleur(this));
        
        main.setPreferredSize(new Dimension(300,70));
        this.setContentPane(main);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}