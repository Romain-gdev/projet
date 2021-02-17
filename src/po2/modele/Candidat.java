package po2.modele;

import java.util.*;

import javax.swing.AbstractListModel;


/**
 * Classe définissant un candidat.
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
     * fabrique une nouveau candidat étudiant
     *
     * @param nom            le nom du candidat
     * @param prenom         le prénom du candidat
     * @param anneeNaissance l'année de naissance du candidat
     * @param bacObtenu      le bac obtenu par le candidat
     * @param diplomeEnCours le diplôme que le candidat est en train de préparer
     * @throws CandidatureException si la date du diplome en cours de préparation est plus ancienne que la date du bac
     */
    public static Candidat fabriqueCandidatEtudiant(String nom,
                                                    String prenom,
                                                    int anneeNaissance,
                                                    Bac bacObtenu,
                                                    DiplomeSup diplomeEnCours) throws CandidatureException {
        if (diplomeEnCours.compareTo(bacObtenu) <= 0)
        	throw new CandidatureException("Erreur: date du diplôme en cours de préparation antérieure à la date du bac");
        else 
        	return new Candidat(nom,prenom,anneeNaissance,STATUT.ETUDIANT,bacObtenu,diplomeEnCours);
    }

    /**
     * fabrique une nouveau candidat salarié ou demandeur d'emploi
     *
     * @param nom            le nom du candidat
     * @param prenom         le prénom du candidat
     * @param anneeNaissance l'année de naissance du candidat
     * @param statut         le statut du candidat
     * @param bacObtenu      le bac obtenu par le candidat
     * @throws CandidatureException si le statut du candidat n'est pas salarié ou demandeur d'emploi
     */
    public static Candidat fabriqueCandidat(String nom,
                                            String prenom,
                                            int anneeNaissance,
                                            STATUT statut,
                                            Bac bacObtenu) throws CandidatureException {
    	 if (statut == STATUT.ETUDIANT)
         	throw new CandidatureException("Erreur: le candidat est un étudiant");
         else 
         	return new Candidat(nom,prenom,anneeNaissance,statut,bacObtenu,null);
     }
    

    /**
     * @return le numéro de candidature du candidat
     */
    public int numero() {
        return numero;
    }
       
    /**
     * @return le prénom du candidat
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
     * @return l'année de naissance du candidat
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
     * @return le diplôme de bac obtenu par le candidat
     */
    public Bac bacObtenu() {
        return (Bac) diplomes.get(0);
    }

    /**
     * @return le diplôme en cours de préparation par le candidat
     * @throws CandidatureException si le candidat n'est pas en train de préparer un diplôme
     */
    public DiplomeSup diplomeEnCours() throws CandidatureException {
        if (diplomePrepare == null) 
        	throw new CandidatureException("Erreur: le candidat ne prépare pas de diplôme");
        return diplomePrepare;
    }

    /**
     * Ajoute un nouveau diplôme aux diplômes obtenus par le candidat.
     * CONTRAINTES =
     * + Un seul diplôme peut être ajouté pour une année scolaire donnée,
     * + Aucun diplôme obtenu avant le bac,
     * + le diplôme en cours après tous les diplômes obtnenus
     *
     * @param nouveau le diplôme à ajouter
     * @throws CandidatureException si un diplôme est déjà présent pour l'année du diplôme à ajouter
     *                              OU si l'année du diplôme ajouté est inférieure à celle du bac
     *                              OU si l'année du diplôme ajouté est supérieure à celle du diplôme en cours
     */
    public void ajouteDiplome(DiplomeSup nouveau) throws CandidatureException {
    	if (nouveau == null)
    		throw new CandidatureException("Erreur: diplôme inexistant");
    	
    	for (Diplome d : diplomes)
    		if (d.annee() == nouveau.annee())
    			throw new CandidatureException("Erreur: le candidat possède déjà un diplôme pour l'année "+nouveau.annee().toString());
    	
    	if (nouveau.compareTo(bacObtenu()) <= 0)
        	throw new CandidatureException("Erreur: l'année du diplôme ajouté est inférieure à celle du bac");
    	
        if (diplomePrepare != null && nouveau.compareTo(diplomePrepare) >= 0)
        	throw new CandidatureException("Erreur: l'année du diplôme ajouté est supérieure à celle du diplôme en cours");
        
        int index = diplomes.size();
        diplomes.add(nouveau);
        fireIntervalAdded(diplomes, index, index);
    }

    /**
     * @return une liste de tous les diplomes obtenus (incluant donc le diplôme de bac), triée par année d'obtention.
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
     * @return le dernier diplôme obtenu par la candidat
     */
    public Diplome dernierDiplomeObtenu() {
        return diplomes.get(diplomes.size()-1);
    }

    /**
     * @return la liste des diplomes référencés obtenus ET/OU en cours du candidat, triée par année d'obtention.
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
     * @return une chaîne de caractères représentant la candidature
     */
    @Override
    public String toString() {
    	return " ("+(numero+1)+") "+nom+" "+prenom;
    }

    /**
     * Défini l'ordre naturel comme l'ordre de création des candidatures
     *
     * @param autre le second candidat
     * @return  return un entier <0, =0 ou >0 si la candidature courante est plus petite,
     * égale ou plus grande que la candidature de l'autre candidat
     */
    @Override
    public int compareTo(Candidat autre) {
        return this.numero-autre.numero;
    }
    
    /**
     * Défini un comparateur de numéro de candidature (ou ordre de création)
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
