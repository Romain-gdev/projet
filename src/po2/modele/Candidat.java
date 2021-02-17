package po2.modele;

import java.util.*;

import javax.swing.AbstractListModel;


/**
 * Classe d�finissant un candidat.
 */
public class Candidat extends AbstractListModel<Diplome> implements Comparable<Candidat> {

	public static int nbCandidats;
	
	private int numero;
    private String nom;
    private String prenom;
    private int anneeNaissance;
    private STATUT statut;
    private DiplomeSup diplomePrepare;
    private List<Diplome> diplomes;

    /* NB : VOUS CONSERVEREZ LA VISIBILITE PRIVEE POUR LE CONSTRUCTEUR */
    private Candidat(String nom,
                     String prenom,
                     int anneeNaissance,
                     STATUT statut,
                     Bac bacObtenu,
                     DiplomeSup diplomeEnCours) {
    	
    	this.numero = Candidat.nbCandidats;
        Candidat.nbCandidats++;
        
        this.nom = nom;
        this.prenom = prenom;
        this.anneeNaissance = anneeNaissance;
        this.statut = statut;
        this.diplomePrepare = diplomeEnCours;
        
        List<Diplome> liste = new LinkedList<>();
        liste.add(bacObtenu);
        this.diplomes = liste;
        
    }
    /* VOUS N'AJOUTEREZ PAS D'AUTRES CONSTRUCTEURS */


    /**
     * fabrique une nouveau candidat �tudiant
     *
     * @param nom            le nom du candidat
     * @param prenom         le pr�nom du candidat
     * @param anneeNaissance l'ann�e de naissance du candidat
     * @param bacObtenu      le bac obtenu par le candidat
     * @param diplomeEnCours le dipl�me que le candidat est en train de pr�parer
     * @throws CandidatureException si la date du diplome en cours de pr�paration est plus ancienne que la date du bac
     */
    public static Candidat fabriqueCandidatEtudiant(String nom,
                                                    String prenom,
                                                    int anneeNaissance,
                                                    Bac bacObtenu,
                                                    DiplomeSup diplomeEnCours) throws CandidatureException {
        if (diplomeEnCours.compareTo(bacObtenu) <= 0)
        	throw new CandidatureException("Erreur: date du dipl�me en cours de pr�paration ant�rieure � la date du bac");
        else 
        	return new Candidat(nom,prenom,anneeNaissance,STATUT.ETUDIANT,bacObtenu,diplomeEnCours);
    }

    /**
     * fabrique une nouveau candidat salari� ou demandeur d'emploi
     *
     * @param nom            le nom du candidat
     * @param prenom         le pr�nom du candidat
     * @param anneeNaissance l'ann�e de naissance du candidat
     * @param statut         le statut du candidat
     * @param bacObtenu      le bac obtenu par le candidat
     * @throws CandidatureException si le statut du candidat n'est pas salari� ou demandeur d'emploi
     */
    public static Candidat fabriqueCandidat(String nom,
                                            String prenom,
                                            int anneeNaissance,
                                            STATUT statut,
                                            Bac bacObtenu) throws CandidatureException {
    	 if (statut == STATUT.ETUDIANT)
         	throw new CandidatureException("Erreur: le candidat est un �tudiant");
         else 
         	return new Candidat(nom,prenom,anneeNaissance,statut,bacObtenu,null);
     }
    

    /**
     * @return le num�ro de candidature du candidat
     */
    public int numero() {
        return numero;
    }
       
    /**
     * @return le pr�nom du candidat
     */
    public String prenom() {
        return prenom;
    }

    /**
     * @return le nom du candidat
     */
    public String nom() {
        return nom;
    }

    /**
     * @return l'ann�e de naissance du candidat
     */
    public int anneeNaissance() {
        return anneeNaissance;
    }

    /**
     * @return le statut du candidat
     */
    public STATUT statut() {
        return statut;
    }

    /**
     * @return le dipl�me de bac obtenu par le candidat
     */
    public Bac bacObtenu() {
        return (Bac) diplomes.get(0);
    }

    /**
     * @return le dipl�me en cours de pr�paration par le candidat
     * @throws CandidatureException si le candidat n'est pas en train de pr�parer un dipl�me
     */
    public DiplomeSup diplomeEnCours() throws CandidatureException {
        if (diplomePrepare == null) 
        	throw new CandidatureException("Erreur: le candidat ne pr�pare pas de dipl�me");
        return diplomePrepare;
    }

    /**
     * Ajoute un nouveau dipl�me aux dipl�mes obtenus par le candidat.
     * CONTRAINTES =
     * + Un seul dipl�me peut �tre ajout� pour une ann�e scolaire donn�e,
     * + Aucun dipl�me obtenu avant le bac,
     * + le dipl�me en cours apr�s tous les dipl�mes obtnenus
     *
     * @param nouveau le dipl�me � ajouter
     * @throws CandidatureException si un dipl�me est d�j� pr�sent pour l'ann�e du dipl�me � ajouter
     *                              OU si l'ann�e du dipl�me ajout� est inf�rieure � celle du bac
     *                              OU si l'ann�e du dipl�me ajout� est sup�rieure � celle du dipl�me en cours
     */
    public void ajouteDiplome(DiplomeSup nouveau) throws CandidatureException {
    	if (nouveau == null)
    		throw new CandidatureException("Erreur: dipl�me inexistant");
    	
    	for (Diplome d : diplomes)
    		if (d.annee() == nouveau.annee())
    			throw new CandidatureException("Erreur: le candidat poss�de d�j� un dipl�me pour l'ann�e "+nouveau.annee().toString());
    	
    	if (nouveau.compareTo(bacObtenu()) <= 0)
        	throw new CandidatureException("Erreur: l'ann�e du dipl�me ajout� est inf�rieure � celle du bac");
    	
        if (diplomePrepare != null && nouveau.compareTo(diplomePrepare) >= 0)
        	throw new CandidatureException("Erreur: l'ann�e du dipl�me ajout� est sup�rieure � celle du dipl�me en cours");
        
        int index = diplomes.size();
        diplomes.add(nouveau);
        fireIntervalAdded(diplomes, index, index);
    }

    /**
     * @return une liste de tous les diplomes obtenus (incluant donc le dipl�me de bac), tri�e par ann�e d'obtention.
     */
    public List<Diplome> diplomesObtenusTriesParAnnee() {
    	List<ANNEE> annees = new ArrayList<>();
    	List<Diplome> tmp = new ArrayList<>();
    	tmp.addAll(diplomes);
    	diplomes.clear();
    	for (Diplome d : tmp)
    		if (d == null) return null;
    		else annees.add(d.annee());
    	annees.sort(ANNEE.COMP_ANNEE);
    	for (ANNEE an : annees)
    		for (Diplome d : tmp)
    			if (d.annee() == an)
    				diplomes.add(d);
        return diplomes;
    }

    /**
     * @return le dernier dipl�me obtenu par la candidat
     */
    public Diplome dernierDiplomeObtenu() {
        return diplomes.get(diplomes.size()-1);
    }

    /**
     * @return la liste des diplomes r�f�renc�s obtenus ET/OU en cours du candidat, tri�e par ann�e d'obtention.
     */
    public List<DiplomeSupReference> diplomesReferencesTriesParAnnee() {
    	List<ANNEE> annees = new ArrayList<>();
    	List<DiplomeSupReference> dSupRef = new ArrayList<>();
    	for (Diplome d : diplomes)
    		if (d instanceof DiplomeSupReference)
    			dSupRef.add((DiplomeSupReference) d);
    	if (diplomePrepare instanceof DiplomeSupReference)
			dSupRef.add((DiplomeSupReference) diplomePrepare);
    	List<DiplomeSupReference> tmp = new ArrayList<>();
    	tmp.addAll(dSupRef);
    	dSupRef.clear();
    	for (DiplomeSupReference d : tmp)
    		annees.add(d.annee());
    	annees.sort(ANNEE.COMP_ANNEE);
    	for (ANNEE an : annees)
    		for (DiplomeSupReference d : tmp)
    			if (d.annee() == an)
    				dSupRef.add(d);
        return dSupRef;
    }

    /**
     * @return une cha�ne de caract�res repr�sentant la candidature
     */
    @Override
    public String toString() {
    	return " ("+(numero+1)+") "+nom+" "+prenom;
    }

    /**
     * D�fini l'ordre naturel comme l'ordre de cr�ation des candidatures
     *
     * @param autre le second candidat
     * @return  return un entier <0, =0 ou >0 si la candidature courante est plus petite,
     * �gale ou plus grande que la candidature de l'autre candidat
     */
    @Override
    public int compareTo(Candidat autre) {
        return this.numero-autre.numero;
    }
    
    /**
     * D�fini un comparateur de num�ro de candidature (ou ordre de cr�ation)
     */
    public static Comparator<Candidat> COMP_NUMERO = new Comparator<Candidat>() {
		@Override
		public int compare(Candidat c1, Candidat c2) {
			return c1.compareTo(c2);
		}
    };

	@Override
	public int getSize() {
		return diplomes.size();
	}
	@Override
	public Diplome getElementAt(int index) {
		return diplomes.get(index);
	}
}
