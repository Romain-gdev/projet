package po2.modele;

import java.util.*;

import javax.swing.AbstractListModel;



/**
 * Classe permettant d'enregistrer des candidats
 */
public class Candidatures extends AbstractListModel<Candidat> implements FiltreCandidatures, TriCandidatures {

    private List<Candidat> candidats;

    /**
     * créé une nouvelle classe prête à enregistrer des candidats.
     */
    public Candidatures() {
        candidats = new ArrayList<>();
    }

    /**
     * créé une nouvelle classe et enregistre directement les candidats donnés
     *
     * @param listeDeCandidats des candidats à enregistrer
     */
    public Candidatures(List<Candidat> listeDeCandidats) {
        candidats = listeDeCandidats;
    }

    /**
     * ajoute un nouveau candidat
     *
     * @throws CandidatureException si le candidat est déjà enregistré
     */
    public void ajoute(Candidat candidat) throws CandidatureException {
    	if (candidat == null)
        	throw new CandidatureException("Erreur: candidat inexistant");
        if (candidats.contains(candidat))
        	throw new CandidatureException("Erreur: le candidat est déjà enregistré");
        candidats.add(candidat);
    }

    /**
     * @return le nombre de candidats enregistrés
     */
    public Integer nbCandidats() {
        return candidats.size();
    }

    /**
     * @return une liste de toutes les candidats
     */
    public List<Candidat> candidats() {
        return candidats;
    }


    /**
     * indique si la candidature d'un candidat est valide, càd si
     *  + le bac obtenu par le candidat est un bac S ou bac STI2D
     *  + le candidat a obtenu (au moins) un des diplômes référencés, ou
     *  + le candidat est en train de préparer l'un des diplômes référencés
     *
     *
     * @param candidat le candidat à valider
     * @return vrai si la candidature est valide, faux sinon
     */
    public static Boolean candidatureEstValide(Candidat candidat) {
    	if (!(candidat.bacObtenu().serie() == SERIE.S || candidat.bacObtenu().serie() == SERIE.STI2D))
    		return false;
    	try {
    		if (!candidat.diplomesReferencesTriesParAnnee().isEmpty() || candidat.diplomeEnCours() instanceof DiplomeSupReference)
    			return true;
    	}
        catch (CandidatureException ex) {
        	return false;
        }
		return false;
    }


    /**
     * @return le nombre de candidatures valides
     */
    public Integer nbCandidatsValides() {
        return candidaturesValides().nbCandidats();
    }

    /**
     * @return le nombre de candidatures invalides
     */
    public Integer nbCandidatsInvalides() {
    	return candidaturesInvalides().nbCandidats();
    }

    /**
     * @return toutes les candidatures invalides
     */
    public Candidatures candidaturesInvalides() {
    	Candidatures invalide = new Candidatures();
        for (Candidat c : candidats) {
        	if (!candidatureEstValide(c)) {
				try {
					invalide.ajoute(c);
				}
        		catch (CandidatureException e) {
					System.out.println(e.getMessage());
				}
        	}
        }
        return invalide;
    }

    /**
     * @return toutes les candidatures valides
     */
    public Candidatures candidaturesValides() {
        Candidatures valide = new Candidatures();
        for (Candidat c : candidats) {
        	if (candidatureEstValide(c)) {
				try {
					valide.ajoute(c);
				}
        		catch (CandidatureException e) {
					System.out.println(e.getMessage());
				}
        	}
        }
        return valide;
    }

    /**
     * efface le candidat des candidatures enregistrées
     *
     * @param candidat le candidat à effacer
     * @throws CandidatureException si le candidat à effacer n'est pas dans les canditures enregistrées
     */
    public void efface(Candidat candidat) throws CandidatureException {
    	if (candidat == null)
        	throw new CandidatureException("Erreur: candidat inexistant");
        if (!candidats.contains(candidat))
        	throw new CandidatureException("Erreur: le candidat n'est pas enregistré");
        candidats.remove(candidat);
    }

    @Override
    public Candidatures candidaturesParOrdreAlpha() {
    	Candidatures trie = new Candidatures();
    	trie.candidats().addAll(candidats);
    	trie.candidats().sort(new ComparateurOrdreAlpha());
        return trie;
    }

    @Override
    public Candidatures candidaturesParOrdreCreation() {
    	Candidatures trie = new Candidatures();
    	trie.candidats().addAll(candidats);
    	trie.candidats().sort(Candidat.COMP_NUMERO);
        return trie;
    }

    @Override
    public Candidatures candidaturesAyantCommeNom(String nom) {
        Candidatures filtre = new Candidatures();
        for (Candidat c : candidats)
        	if (c.nom().equals(nom)) {
				try {
					filtre.ajoute(c);
				}
        		catch (CandidatureException e) {
					System.out.println(e.getMessage());
				}
        	}
        return filtre;
    }

    @Override
    public Candidatures candidaturesParSerieBac(SERIE serie) {
    	Candidatures filtre = new Candidatures();
        for (Candidat c : candidats)
        	if (c.bacObtenu().serie() == serie) {
				try {
					filtre.ajoute(c);
				}
        		catch (CandidatureException e) {
					System.out.println(e.getMessage());
				}
        	}
        return filtre;
    }

    @Override
    public Candidatures candidaturesParReference(REF_DIPLOME reference) {
    	Candidatures filtre = new Candidatures();
    	List<DiplomeSupReference> dRef;
        for (Candidat c : candidats) {
        	dRef = c.diplomesReferencesTriesParAnnee();
        	if (dRef != null) {
        		for (DiplomeSupReference d : dRef) {
        			if (d.reference() == reference) {
        				try {
        					filtre.ajoute(c);
        				}
                		catch (CandidatureException e) {
        					System.out.println(e.getMessage());
        				}
        			}
        		}
        	}
    	}
        return filtre;
    }

    @Override
    public String toString() {
        return candidats.toString();
    }

	@Override
	public int getSize() {
		return nbCandidats();
	}

	@Override
	public Candidat getElementAt(int index) {
		return candidats().get(index);
	}
}
