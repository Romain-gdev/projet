package ihm.vue;

import po2.modele.*;
import java.awt.*;
import javax.swing.*;

import ihm.controleurs.*;

import java.awt.event.*;
import java.util.List;

/**
 * La vue de l'ajout d'un diplome supérieur.
 * @author COURTAND Emeric
 * @author GAUTIER Romain
 */
public class AddDiplomeSupView extends JDialog {
	
	private ModelDiplome modeleListe;
	
	private JCheckBox checkRef;
	private JComboBox<ANNEE> anneeDiplome;
	private JComboBox<REF_DIPLOME> refDiplome;
	private JTextField fieldEtablissement;
	private JTextField fieldLibelle;
	
	private JButton ajouter;
	private JButton annuler;

	
    /**
     * Crée une nouvelle fenetre pour ajouter le diplome supérieur.
     *
     * @param titre       le titre de la fenetre
     * @param candidatures les candidatures : modéle de données métier
     */
    public AddDiplomeSupView(AddCandidatView parent, ModelDiplome model) {
    	super(parent,"Ajout d'un diplome supérieur",true);
        modeleListe = model;
        
        // panneau principal
        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel info = new JPanel(new GridLayout(4,2));
        
        checkRef = new JCheckBox("Diplome référencé",true);
        info.add(checkRef);
        JPanel annee = new JPanel(new GridLayout(1,2));
        annee.add(new JLabel(" Année :"));
        anneeDiplome = new JComboBox<ANNEE>(ANNEE.values());
        annee.add(anneeDiplome);
        info.add(annee);
        
        info.add(new JLabel("Référence :"));
        refDiplome = new JComboBox<REF_DIPLOME>(REF_DIPLOME.values());
        info.add(refDiplome);
        
        info.add(new JLabel("Etablissement :"));
        fieldEtablissement = new JTextField();
        info.add(fieldEtablissement);
        
        info.add(new JLabel("Libellé :"));
        fieldLibelle = new JTextField();
        info.add(fieldLibelle);
        main.add(info);
       
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ajouter = new JButton("Ajouter");
        nav.add(ajouter);
        annuler = new JButton("Annuler");
        nav.add(annuler);
        main.add(nav);
        
        // abonnements aux controleurs
        checkRef.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				refDiplome.setEnabled(refIsChecked());
			}
        });
        ajouter.addActionListener(new AjoutDiplomeSupControleur(AddDiplomeSupView.this, parent, modeleListe));
        annuler.addActionListener(new AnnulerFenetreControleur(this));
        
        main.setPreferredSize(new Dimension(370,150));
        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        
        
        
//        try {
//        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        	SwingUtilities.updateComponentTreeUI(this);
//        } catch (Exception e) {
// 		   e.printStackTrace();
// 		  }
    }
    
    
    /**
     * Créé une nouvelle fenetre pour ajouter le diplome en cours de préparation
     *
     * @param titre       le titre de la fenetre
     * @param candidatures les candidatures : modèle de données métier
     */
    public AddDiplomeSupView(AddCandidatView parent) {
    	super(parent,"Ajout du diplome préparé",true);
        
        // panneau principal
        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel info = new JPanel(new GridLayout(4,2));
        
        checkRef = new JCheckBox("Diplome référencé",true);
        info.add(checkRef);
        JPanel annee = new JPanel(new GridLayout(1,2));
        annee.add(new JLabel(" Année :"));
        anneeDiplome = new JComboBox<ANNEE>(ANNEE.values());
        annee.add(anneeDiplome);
        info.add(annee);
        
        info.add(new JLabel("Référence :"));
        refDiplome = new JComboBox<REF_DIPLOME>(REF_DIPLOME.values());
        info.add(refDiplome);
        
        info.add(new JLabel("Etablissement :"));
        fieldEtablissement = new JTextField();
        info.add(fieldEtablissement);
        
        info.add(new JLabel("Libellé :"));
        fieldLibelle = new JTextField();
        info.add(fieldLibelle);
        main.add(info);
       
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ajouter = new JButton("Ajouter");
        nav.add(ajouter);
        annuler = new JButton("Annuler");
        nav.add(annuler);
        main.add(nav);
        
        // abonnements aux controleurs
        checkRef.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				refDiplome.setEnabled(refIsChecked());
			}
        });
        
        ajouter.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		if (ANNEE.COMP_ANNEE.compare(parent.getAnneeBac(),AddDiplomeSupView.this.getAnnee()) < 0) {
        			if (getEtablissement().equals("") || getLibelle().equals(""))
                		JOptionPane.showMessageDialog(AddDiplomeSupView.this, "Certains champs sont incomplets", "Erreur", JOptionPane.ERROR_MESSAGE);
                	else {
                		DiplomeSup prepare;
                		if (refIsChecked())
                			prepare = new DiplomeSupReference(getAnnee(), getEtablissement(), getLibelle(), getReference());
                		else
                			prepare = new DiplomeSup(getAnnee(), getEtablissement(), getLibelle());
                		
                		parent.setDiplomePrepare(prepare);
                		parent.activeStatut(false);
                		AddDiplomeSupView.this.dispose();
                	}
        		}
        		else
        			JOptionPane.showMessageDialog(AddDiplomeSupView.this, "L'année du dipléme supérieur ne peut pas\nétre antérieure ou égale é l'année du Bac", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        annuler.addActionListener(new AnnulerFenetreControleur(this));
        
        main.setPreferredSize(new Dimension(370,150));
        this.setContentPane(main);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        
        
        
//        try {
//        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        	SwingUtilities.updateComponentTreeUI(this);
//        } catch (Exception e) {
// 		   e.printStackTrace();
// 		  }
    }
    
    public boolean refIsChecked() {
    	return checkRef.isSelected();
    }
    
    public ANNEE getAnnee() {
    	return (ANNEE) anneeDiplome.getSelectedItem();
    }
    
    public REF_DIPLOME getReference() {
    	return (REF_DIPLOME) refDiplome.getSelectedItem();
    }
    
    public String getEtablissement() {
    	return fieldEtablissement.getText();
    }
    
    public String getLibelle() {
    	return fieldLibelle.getText();
    }
}



