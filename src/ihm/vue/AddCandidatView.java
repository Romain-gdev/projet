package ihm.vue;

import po2.modele.*;
import ihm.controleurs.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.Calendar;

/**
 * La vue de l'ajout de candidat.
 * @author COURTAND Emeric
 * @author GAUTIER Romain
 */
public class AddCandidatView extends JDialog {
	
	private DiplomeSup enPreparation;
	private MainView parent;

	private JTextField fieldNom;
	private JTextField fieldPrenom;
	private JTextField fieldAnnee;
	private int AnneeDdN;
	
	private ButtonGroup choixStatut;
	private JRadioButton etudiant;
	private JRadioButton salarie;
	private JRadioButton demandeurEmploi;
	
	private JComboBox<SERIE> serieBac;
	private JComboBox<ANNEE> anneeBac;
	private JTextField fieldEtablissement;
	
	private JLabel labelDiplome;
	private JButton ajoutDpPrepare;
	private JButton ajoutDpSup;
	private JButton suppDpSup;
	private JButton reinitDpSup;
	private JList<Diplome> listeDiplomes;
	private ModelDiplome modeleListe;

	private JButton creer;
	private JButton annuler;
	
    /**
     * Crée une nouvelle fenetre.
     *
     * @param titre       le titre de la fenetre
     * @param candidatures les candidatures : modéle de données métier
     */
    public AddCandidatView(MainView mv) {
        super(mv,"Ajouter un nouveau candidat",true);
        parent = mv;
        enPreparation = null;
        
        // panneau principal
        JPanel main = new JPanel(new BorderLayout());
        JPanel identite = new JPanel(new GridLayout(4,1));
        JPanel nomPrenom = new JPanel(new GridLayout(3,1));
        
        JPanel nom = new JPanel(new GridLayout(1,2));
        nom.add(new JLabel("Nom :"));
        fieldNom = new JTextField();
        nom.add(fieldNom);
        nomPrenom.add(nom);
        
        JPanel prenom = new JPanel(new GridLayout(1,2));
        prenom.add(new JLabel("Prénom :"));
        fieldPrenom = new JTextField();
        prenom.add(fieldPrenom);
        nomPrenom.add(prenom);
        
        JPanel anneeDdN = new JPanel(new GridLayout(1,2));
        anneeDdN.add(new JLabel("Année de naissance :"));
        fieldAnnee = new JTextField();
        anneeDdN.add(fieldAnnee);
        nomPrenom.add(anneeDdN);
        identite.add(nomPrenom);
        
        JPanel statut = new JPanel();
        statut.setLayout(new BoxLayout(statut, BoxLayout.X_AXIS));
        statut.setBorder(BorderFactory.createTitledBorder("Statut"));
        etudiant = new JRadioButton("Etudiant ",true);
        salarie = new JRadioButton("Salarié ");
        demandeurEmploi = new JRadioButton("Demandeur d'emploi");
        choixStatut = new ButtonGroup();
        choixStatut.add(etudiant);
        choixStatut.add(salarie);
        choixStatut.add(demandeurEmploi);
        statut.add(etudiant);
        statut.add(salarie);
        statut.add(demandeurEmploi);
        identite.add(statut);
        
        JPanel bac = new JPanel(new GridLayout(2,2));
        bac.setBorder(BorderFactory.createTitledBorder("Ajout d'un bac"));
        JPanel serie = new JPanel(new GridLayout(1,2));
        serie.add(new JLabel("Série :"));
        serieBac = new JComboBox<SERIE>(SERIE.values());
        serie.add(serieBac);
        bac.add(serie);
        JPanel annee = new JPanel(new GridLayout(1,2));
        annee.add(new JLabel(" Année :"));
        anneeBac = new JComboBox<ANNEE>(ANNEE.values());
        annee.add(anneeBac);
        bac.add(annee);
        bac.add(new JLabel("Etablissement :"));
        fieldEtablissement = new JTextField();
        bac.add(fieldEtablissement);
        identite.add(bac);
        
        JPanel panelDiplome = new JPanel(new GridLayout(2,1));
        panelDiplome.setBorder(BorderFactory.createTitledBorder("Gestion des diplomes supérieurs"));
        JPanel ajoutDiplome = new JPanel(new GridLayout(1,2));
        ajoutDpPrepare = new JButton("Ajouter diplome préparé");
        ajoutDiplome.add(ajoutDpPrepare);
        ajoutDpSup = new JButton("Ajouter diplome Sup.");
        ajoutDiplome.add(ajoutDpSup);
        panelDiplome.add(ajoutDiplome);
        JPanel resetDiplome = new JPanel(new GridLayout(1,2));
        suppDpSup = new JButton("Suppr. diplome selec.");
        suppDpSup.setEnabled(false);
        resetDiplome.add(suppDpSup);
        reinitDpSup = new JButton("Réinit. diplomes");
        reinitDpSup.setEnabled(false);
        resetDiplome.add(reinitDpSup);
        panelDiplome.add(resetDiplome);
        
        identite.add(panelDiplome);
        main.add(identite, BorderLayout.NORTH);
        
        JPanel listeDiplome = new JPanel(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(2,1));
        JPanel dpPrepare = new JPanel();
        dpPrepare.add(new JLabel("Diplome préparé :"));
        labelDiplome = new JLabel("aucun");
        dpPrepare.add(labelDiplome);
        labels.add(dpPrepare);
        labels.add(new JLabel("Liste des diplomes supérieurs"));
        listeDiplome.add(labels, BorderLayout.NORTH);
        modeleListe = new ModelDiplome();
        listeDiplomes = new JList<Diplome>(modeleListe);
        listeDiplome.add(listeDiplomes, BorderLayout.CENTER);
        main.add(listeDiplome, BorderLayout.CENTER);
        
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER));
        creer = new JButton("  Créer  ");
        nav.add(creer);
        annuler = new JButton(" Annuler ");
        nav.add(annuler);
        main.add(new JPanel(new FlowLayout(FlowLayout.CENTER)).add(nav), BorderLayout.SOUTH);
        
        
        // abonnements aux controleurs
        
        ajoutDpSup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (fieldsAreEmpty())
            		JOptionPane.showMessageDialog(AddCandidatView.this, "Veuillez compléter les informations précédentes\navant de continuer", "Avertissement", JOptionPane.WARNING_MESSAGE);
            	else {
            		if (activeAjoutDpPrepare())
                		JOptionPane.showMessageDialog(AddCandidatView.this, "Vous devez créer un diplome préparé pour l'étudiant\navant de continuer", "Avertissement", JOptionPane.WARNING_MESSAGE);
                	else {
                		new AddDiplomeSupView(AddCandidatView.this, modeleListe);
                		reinitDpSup.setEnabled(true);
                	}
            	}
            }
        });
        ajoutDpPrepare.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (fieldsAreEmpty())
        			JOptionPane.showMessageDialog(AddCandidatView.this, "Veuillez compléter les informations précédentes\navant de continuer", "Avertissement", JOptionPane.WARNING_MESSAGE);
        		else new AddDiplomeSupView(AddCandidatView.this);
        	}
        });
        suppDpSup.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		modeleListe.removeElement(listeDiplomes.getSelectedValue());
        		suppDpSup.setEnabled(false);
        		if (modeleListe.getSize() == 0)
        			reinitDpSup.setEnabled(false);
        	}
        });
        reinitDpSup.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		while (modeleListe.getSize() != 0)
            		modeleListe.removeElement(modeleListe.getElementAt(0));
            	setDiplomePrepare(null);
            	activeStatut(true);
            	suppDpSup.setEnabled(false);
        		reinitDpSup.setEnabled(false);
            	if (getStatut() != STATUT.ETUDIANT)
            		activeAjoutDpPrepare(false);
        	}
        });
        listeDiplomes.addListSelectionListener(new ListSelectionListener() {
        	@Override
        	public void valueChanged(ListSelectionEvent e) {
        		if (modeleListe.getSize() == 0) {
        			suppDpSup.setEnabled(false);
        			reinitDpSup.setEnabled(false);
        		}
        		else {
        			if (listeDiplomes.getSelectedValue() == null)
            			suppDpSup.setEnabled(false);
            		else
            			suppDpSup.setEnabled(true);
        		}
        	}
        });
        
        etudiant.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		activeAjoutDpPrepare(choixStatut.isSelected(etudiant.getModel()));
        	}
        });
        salarie.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		activeAjoutDpPrepare(choixStatut.isSelected(etudiant.getModel()));
        	}
        });
        demandeurEmploi.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		activeAjoutDpPrepare(choixStatut.isSelected(etudiant.getModel()));
        	}
        });
        
        creer.addActionListener(new CreerCandidatControleur(this, parent));
        annuler.addActionListener(new AnnulerFenetreControleur(this));
        
        main.setPreferredSize(new Dimension(400,500));
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
    
    public ModelDiplome getModeleListe() {
    	return modeleListe;
    }
    
    public void setDiplomePrepare(DiplomeSup dpSup) {
    	enPreparation = dpSup;
    	if (dpSup == null)
    		labelDiplome.setText("aucun");
    	else
    		labelDiplome.setText(dpSup.toString());
    }
    
    public DiplomeSup getDiplomePrepare() {
    	return enPreparation;
    }
    
    public String getNom() {
    	return fieldNom.getText();
    }
    
    public String getPrenom() {
    	return fieldPrenom.getText();
    }
    
    public void setAnneeDdN() {
    	int res = 0;
    	int year = Calendar.getInstance().get(Calendar.YEAR);
    	try {
    		res = Integer.valueOf(fieldAnnee.getText());
    	}
    	catch (NumberFormatException ex) {
    		JOptionPane.showMessageDialog(AddCandidatView.this, "L'année de naissance doit etre un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
    	}
    	if (res <= (year-100) || res >= year)
    		JOptionPane.showMessageDialog(AddCandidatView.this, "L'année de naissance saisie n'est pas comprise entre "+(year-100)+" et "+year, "Erreur", JOptionPane.ERROR_MESSAGE);
    	else
    		AnneeDdN = res;
    }
    
    public int getAnnee() {
    	setAnneeDdN();
    	return AnneeDdN;
    }
    
    public STATUT getStatut() {
    	return (choixStatut.isSelected(etudiant.getModel()) ? STATUT.ETUDIANT :
    			(choixStatut.isSelected(salarie.getModel()) ? STATUT.SALARIE : STATUT.DEMANDEUR_D_EMPLOI));
    }
    
    public ANNEE getAnneeBac() {
    	return (ANNEE) anneeBac.getSelectedItem();
    }
    
    public String getEtablissement() {
    	return fieldEtablissement.getText();
    }
    
    public SERIE getSerie() {
    	return (SERIE) serieBac.getSelectedItem();
    }
    
    public void activeAjoutDpPrepare(boolean etat) {
    	ajoutDpPrepare.setEnabled(etat);
    }
    
    public boolean activeAjoutDpPrepare() {
    	return ajoutDpPrepare.isEnabled();
    }
    
    public void activeStatut(boolean etat) {
    	etudiant.setEnabled(etat);
    	salarie.setEnabled(etat);
    	demandeurEmploi.setEnabled(etat);
    	anneeBac.setEnabled(etat);
    	activeAjoutDpPrepare(etat);
    }
    
    public boolean fieldsAreEmpty() {
    	return (getNom().equals("") || getPrenom().equals("") ||
    			getAnnee() == 0 || getStatut() == null ||
    			getEtablissement().equals(""));
    }
    
}
