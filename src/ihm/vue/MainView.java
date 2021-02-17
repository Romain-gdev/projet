package ihm.vue;

import po2.modele.*;
import ihm.controleurs.*;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

/**
 * La vue de l'application.
 * @author COURTAND Emeric
 * @author GAUTIER Romain
 */
public class MainView extends JFrame {

	private Candidatures candidature;
	private ModelCandidat modeleCandidats;
	private ModelCandidat modeleValides;
	private ModelCandidat modeleInvalides;
	private ModelCandidat modeleFiltre;
	private ModelCandidat modeleFiltreV;
	private ModelCandidat modeleFiltreInv;
	private ModelDiplome modeleDiplomes;
    
	private JTabbedPane onglets;
    private JList<Candidat> listeAll;
    private JList<Candidat> listeValides;
    private JList<Candidat> listeInvalides;
    private Candidat courant;
    
    private JLabel intTotal;
    private JLabel intValides;
    private JLabel intInvalides;
    
    private JButton ajouter;
    private JButton supprimer;
    
    private JRadioButton alpha;
    private JRadioButton creation;
    private ButtonGroup choixTri;
    private JButton trier;
    
    private JLabel nomPrenom;
    
    private JComboBox<String> filtrage;
    private JTextField motif;
    private JButton filtrer;
    private JButton retirerFiltre;
    

    /**
     * Crée une nouvelle fenetre.
     *
     * @param candidatures les candidatures : modele de donnees metier
     */
    @SuppressWarnings("unchecked")
	public MainView(Candidatures candidatures) {
        super("Gestion de candidatures pour LP MiAR");
        // initialisation des diff�rents modeles utilis�es dans l'application
        candidature = candidatures;
        modeleCandidats = new ModelCandidat(candidature.candidats());
        modeleValides = new ModelCandidat(candidature.candidaturesValides().candidats());
        modeleInvalides = new ModelCandidat(candidature.candidaturesInvalides().candidats());
        modeleFiltre = new ModelCandidat();
        modeleFiltreV = new ModelCandidat();
        modeleFiltreInv = new ModelCandidat();
        
        // panneau principal
        JPanel main = new JPanel(new BorderLayout());
        
        // panneau lat�ral de droite
        JPanel options = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JPanel gestion = new JPanel(new GridLayout(2,1));
        ajouter = new JButton("Ajouter");
        supprimer = new JButton("Supprimer");
        gestion.add(ajouter);
        gestion.add(supprimer);
        options.add(gestion);
        
        JPanel tri = new JPanel(new GridLayout(3,1));
        tri.setBorder(BorderFactory.createTitledBorder("Trier par ordre :"));
        alpha = new JRadioButton("alphabétique");
        creation = new JRadioButton("de création");
        choixTri = new ButtonGroup();
        choixTri.add(alpha);
        choixTri.add(creation);
        tri.add(alpha);
        tri.add(creation);
        trier = new JButton("Trier");
        tri.add(trier);
        options.add(tri);
        
        JPanel filtre = new JPanel(new GridLayout(4,1));
        filtre.setBorder(BorderFactory.createTitledBorder("Filtrer par :"));
        String[] choixFiltre = {"Nom", "Référence", "Série de bac"};
        filtrage = new JComboBox<String>(choixFiltre);
        filtre.add(filtrage);
        motif = new JTextField("Motif à filtrer");
        filtre.add(motif);
        filtrer = new JButton("Filtrer");
        filtre.add(filtrer);
        retirerFiltre = new JButton("Retirer filtre");
        setRetirerFiltre(false);
        filtre.add(retirerFiltre);
        options.add(filtre);
        
        
        // cr�ation des diff�rents onglets pour le panel central
        onglets = new JTabbedPane();
        JPanel all = new JPanel(new BorderLayout());
        JPanel northAll = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nbAll = new JLabel("Nombre total de candidatures : ");
        northAll.add(nbAll);
        intTotal = new JLabel();
        setCompteur(intTotal, modeleCandidats.getSize());
        northAll.add(intTotal);
        all.add(northAll, BorderLayout.NORTH);
        listeAll = new JList<Candidat>(modeleCandidats);
        all.add(new JScrollPane(listeAll), BorderLayout.CENTER);
        onglets.addTab("All",all);
        listeAll.setSelectedIndex(0);
        
        JPanel valides = new JPanel(new BorderLayout());
        JPanel northV = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nbValides = new JLabel("Nombre de candidatures valides : ");
        northV.add(nbValides);
        intValides = new JLabel();
        setCompteur(intValides, modeleValides.getSize());
        northV.add(intValides);
        valides.add(northV, BorderLayout.NORTH);
        listeValides = new JList<Candidat>(modeleValides);
        valides.add(new JScrollPane(listeValides), BorderLayout.CENTER);
        onglets.addTab("Valides",valides);
        
        JPanel invalides = new JPanel(new BorderLayout());
        JPanel northInv = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nbInvalides = new JLabel("Nombre de candidatures invalides : ");
        northInv.add(nbInvalides);
        intInvalides = new JLabel();
        setCompteur(intInvalides, modeleInvalides.getSize());
        northInv.add(intInvalides);
        invalides.add(northInv, BorderLayout.NORTH);
        listeInvalides = new JList<Candidat>(modeleInvalides);
        invalides.add(new JScrollPane(listeInvalides), BorderLayout.CENTER);
        onglets.addTab("Invalides",invalides);
        
        
        // panneau lat�ral d'informations de gauche
        JPanel info = new JPanel(new BorderLayout());
        Candidat courant = modeleCandidats.getElementAt(0);
        
        JPanel identite = new JPanel(new GridLayout(8,1));
        nomPrenom = new JLabel(courant.toString());
        nomPrenom.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        identite.add(nomPrenom);
        JLabel annee = new JLabel(""+courant.anneeNaissance());
        annee.setFont(new Font(Font.SANS_SERIF,Font.ITALIC, 16));
        identite.add(annee);
        JLabel statut = new JLabel(courant.statut().toString());
        statut.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 16));
        identite.add(statut);
        
        JLabel labelBac = new JLabel("<html><body><u>Bac :</u></body></html>");
        labelBac.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 16));
        identite.add(labelBac);
        JLabel bac = new JLabel(courant.bacObtenu().toString());
        bac.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 14));
        identite.add(bac);
        
        JLabel labelDpPrepare = new JLabel("<html><body><u>Diplome en cours de préparation :</u></body></html>");
        labelDpPrepare.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 16));
        identite.add(labelDpPrepare);
        JLabel diplomePrepare;
        try {
        	diplomePrepare = new JLabel(courant.diplomeEnCours().toString());
        }
        catch (CandidatureException ex) {
        	diplomePrepare = new JLabel("Aucun");
        }
        diplomePrepare.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 14));
        identite.add(diplomePrepare);
        
        JLabel labelDiplome = new JLabel("<html><body><u>Diplome(s) obtenu(s) :</u></body></html>");
        labelDiplome.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 16));
        identite.add(labelDiplome);
      
        info.add(identite, BorderLayout.NORTH);
        modeleDiplomes = new ModelDiplome(courant.diplomesObtenusTriesParAnnee());
        JList<Diplome> listeDiplomes = new JList<Diplome>(modeleDiplomes);
        info.add(new JScrollPane(listeDiplomes), BorderLayout.CENTER);
        
        
        // ajout des panneaux cr��s au panel principal
        options.setPreferredSize(new Dimension(110,300));
        main.add(options, BorderLayout.EAST);
        JSplitPane consultation = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        consultation.setLeftComponent(info);
        consultation.setRightComponent(onglets);
        main.add(consultation, BorderLayout.CENTER);
        
        
        // abonnements aux controleurs
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		new AddCandidatView(MainView.this);
            		resetAffichageListe(false);
            }
        });
        
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (getCourant() == null)
            		JOptionPane.showMessageDialog(MainView.this, "Veuillez selectionner un candidat à supprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
            	else {
            		new SuppCandidatView(MainView.this);
                    resetAffichageListe(retirerFiltreIsEnabled());
            	}
            }
        });

        trier.addActionListener(new TriControleur(this));
        filtrer.addActionListener(new FiltreControleur(this));
        retirerFiltre.addActionListener(new RetirerFiltreControleur(this));
        
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
    
    public ModelCandidat getModeleCandidat(int i) {
    	ModelCandidat res = null;
    	switch (i) {
    		case 0:
    			res = modeleCandidats;
    		break;
    		case 1:
    			res = modeleValides;
    		break;
    		case 2:
    			res = modeleInvalides;
    		break;
    		case 10:
    			res = modeleFiltre;
    		break;
    		case 11:
    			res = modeleFiltreV;
    		break;
    		case 12:
    			res = modeleFiltreInv;
    		break;
    	}
    	return res;
    }
    
    public ModelCandidat getModeleActif(int i) {
    	ModelCandidat model = null;
    	switch (i) {
    		case 0:
    			model = (ModelCandidat) listeAll.getModel();
    		break;
    		case 1:
    			model = (ModelCandidat) listeValides.getModel();
    		break;
    		case 2:
    			model = (ModelCandidat) listeInvalides.getModel();
    		break;
    	}
		return model;
    }
    
    public void setDonneesModele(Candidatures cand, int i) {
    	getModeleCandidat(i).setDonnees(cand.candidats());
    }
    
    public void setModele(ModelCandidat nouveau, int i) {
    	switch (i) {
			case 0:
				listeAll.setModel(nouveau);
			break;
			case 1:
				listeValides.setModel(nouveau);
			break;
			case 2:
				listeInvalides.setModel(nouveau);
			break;
		}
   }
    
    public Candidat getCourant() {
    	Candidat courant = null;
    	switch (onglets.getSelectedIndex()) {
    		case 0:
    			courant = listeAll.getSelectedValue();
    		break;
    		case 1:
    			courant = listeValides.getSelectedValue();
    		break;
    		case 2:
    			courant = listeInvalides.getSelectedValue();
    		break;
    	}
		return courant;
    }

    public int optionTri() {
    	if (choixTri.isSelected(alpha.getModel()))
    		return 1;
    	if (choixTri.isSelected(creation.getModel()))
    		return 2;
    	return 0;
    }
    
    public int optionFiltre() {
    	return filtrage.getSelectedIndex();
    }
    
    public void resetOptionTri() {
    	choixTri.clearSelection();
    	resetAffichageListe(false);
    }
    
    public void resetOptionFiltre() {
    	resetAffichageListe(false);
    	setRetirerFiltre(false);
    	filtrage.setSelectedIndex(0);
		motif.setText("Motif � filtrer");
    }
    
    public void resetAffichageListe(boolean filtre) {
    	if (onglets.getSelectedIndex() == 0)
    		onglets.setSelectedIndex(1);
    	onglets.setSelectedIndex(0);
    	if (!filtre) {
    		setCompteur(intTotal, listeAll.getModel().getSize());
            setCompteur(intValides, listeValides.getModel().getSize());
            setCompteur(intInvalides, listeInvalides.getModel().getSize());
    	}
    	else {
    		setCompteurFiltre(intTotal, listeAll.getModel().getSize(), getModeleCandidat(0).getSize());
            setCompteurFiltre(intValides, listeValides.getModel().getSize(), getModeleCandidat(1).getSize());
            setCompteurFiltre(intInvalides, listeInvalides.getModel().getSize(), getModeleCandidat(2).getSize());
    	}
    }
    
    public String getMotif() {
    	return motif.getText();
    }
    
    public void setRetirerFiltre(boolean etat) {
    	retirerFiltre.setEnabled(etat);
    	filtrer.setEnabled(!etat);
    	ajouter.setEnabled(!etat);
    }
    
    private boolean retirerFiltreIsEnabled() {
    	return retirerFiltre.isEnabled();
    }
    
    private void setCompteur(JLabel label, int valeur) {
    	label.setText(""+valeur);
    }
    
    private void setCompteurFiltre(JLabel label, int filtre, int total) {
    	label.setText(filtre+" / "+total);
    }
    
}
