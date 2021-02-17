package po2.modele.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import po2.modele.Bac;
import po2.modele.Candidat;
import po2.modele.CandidatureException;
import po2.modele.Candidatures;
import po2.modele.Diplome;
import po2.modele.DiplomeSup;
import po2.modele.DiplomeSupReference;
import po2.modele.STATUT;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static po2.modele.ANNEE.*;
import static po2.modele.REF_DIPLOME.*;
import static po2.modele.SERIE.*;


/** classe donnant un usage minimal des classe Candidat et Candidatures.
 *
 */
public class TestUsageMalo {

    Candidat Remm, Lanoix, Faucou, Berdjugin,Nachouki,Mottu,RemmJumeau,Hadj_Rabia;
    Candidatures candidatures;
    Bac BacRemm,BacLanoix,BacFaucou,BacBerdjugin,BacNachouki,BacMottu;
    DiplomeSup DiplomeEnCoursRemm, DiplomeEnCoursLanoix, DiplomeART, DiplomeSciencesHumaines;
    DiplomeSupReference DiplomeDUTinfo, DiplomeBTS_SN_IR, DiplomeLICENCE_INFO;

    @BeforeEach
    void SetUp() {
        try{
            //initialise les Bacs des différents candidats
            BacRemm = new Bac(A_11_12, "Lycée Langevin-Wallon", L);
            BacLanoix = new Bac(A_11_12, "Lycée Notre-Dame", STI2D);
            BacFaucou = new Bac(A_10_11, "Lycée Henri IV", S);
            BacBerdjugin = new Bac(A_11_12, "Lycée Clemenceau", S);
            BacNachouki = new Bac(A_10_11, "Lycée Livet", STMG);

            //Initialise des Diplomes de Candidats Etudiants
            DiplomeEnCoursRemm = new DiplomeSup(A_20_21, "Ecole Pivaut", "ART");
            DiplomeEnCoursLanoix = new DiplomeSupReference(A_20_21, "IUT Nantes", "Licence d'info", LICENCE_INFO);

            //Initialise différents type de diplomes
            DiplomeART = new DiplomeSup(A_13_14, "Ecole Pivaut", "ART");
            DiplomeDUTinfo = new DiplomeSupReference(A_15_16, "IUT Nantes", "DUT INFO", DUT_INFO);
            DiplomeBTS_SN_IR = new DiplomeSupReference(A_18_19, "St Felix-La Salle", "BTS SN IR", BTS_SN_IR);
            DiplomeLICENCE_INFO = new DiplomeSupReference(A_19_20, "UFR Sciences et Techniques", "LICENSE INFO", LICENCE_INFO);
            DiplomeSciencesHumaines = new DiplomeSup(A_12_13,"Fac de Nantes","Sciences Humaines");

            // initialise Remm
            Remm = Candidat.fabriqueCandidatEtudiant("REMM", "Jean-François", 1958,
                    BacRemm,
                    DiplomeEnCoursRemm);
            // initialise RemmJumeau
            RemmJumeau = Candidat.fabriqueCandidatEtudiant("REMM", "Jumeau de Jean-François", 1958,
                    BacRemm,
                    DiplomeEnCoursRemm);
            // initialise Lanoix
            Lanoix = Candidat.fabriqueCandidatEtudiant("LANOIX", "Arnaud", 1978,
                    BacLanoix,
                    DiplomeEnCoursLanoix);
            // initialise Faucou
            Faucou = Candidat.fabriqueCandidat("FAUCOU", "Sébastien", 1979,
                    STATUT.SALARIE, BacFaucou);
            // initialise Berdjugin
            Berdjugin = Candidat.fabriqueCandidat("BERDJUGIN", "Jean-François", 1978,
                    STATUT.DEMANDEUR_D_EMPLOI, BacBerdjugin);
            // initialise Nachouki
            Nachouki = Candidat.fabriqueCandidat("NACHOUKI", "Jean-François", 1982,
                    STATUT.DEMANDEUR_D_EMPLOI, BacNachouki);
//            // initialise Mottu
            Mottu = Candidat.fabriqueCandidat("MOTTU", "Jean-Marie", 1977,
                    STATUT.DEMANDEUR_D_EMPLOI, BacMottu);

            // créé un ensemble de candidatures
            candidatures = new Candidatures();
        }
        catch(CandidatureException ex) {
            fail(ex.getMessage());
        }

    }

    public void RemplirDeCandidats() {
        try {
            candidatures.ajoute(Berdjugin);
            candidatures.ajoute(Faucou);
            candidatures.ajoute(Remm);
            candidatures.ajoute(Nachouki);
            candidatures.ajoute(Lanoix);


        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
    }

    public void RemplirDiplomeCandidat() {
        try {
            Remm.ajouteDiplome(DiplomeSciencesHumaines);

            Lanoix.ajouteDiplome(DiplomeDUTinfo);
            Lanoix.ajouteDiplome(DiplomeBTS_SN_IR);
            Lanoix.ajouteDiplome(DiplomeART);

            Faucou.ajouteDiplome(DiplomeBTS_SN_IR);
            Faucou.ajouteDiplome(DiplomeDUTinfo);
            Faucou.ajouteDiplome(DiplomeLICENCE_INFO);

            Berdjugin.ajouteDiplome(DiplomeDUTinfo);
            Berdjugin.ajouteDiplome(DiplomeSciencesHumaines);

            Nachouki.ajouteDiplome(DiplomeBTS_SN_IR);

        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void bacObtenu() {
        assertEquals(BacRemm,Remm.bacObtenu());
    }

    @Test
    void bacObtenuNull() {
        assertEquals(null,Mottu.bacObtenu());
    }

    @Test
    void ajouteDiplome() {
        try {
            assertEquals(BacFaucou,Faucou.dernierDiplomeObtenu());
            Faucou.ajouteDiplome(DiplomeLICENCE_INFO);
            assertEquals(DiplomeLICENCE_INFO,Faucou.dernierDiplomeObtenu());
            assertThrows(CandidatureException.class, () -> Faucou.ajouteDiplome(DiplomeLICENCE_INFO));
            Faucou.ajouteDiplome(DiplomeART);
            assertEquals(DiplomeLICENCE_INFO,Faucou.dernierDiplomeObtenu());
        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void dernierDiplomeObtenuNull() {
        assertEquals(null,Mottu.dernierDiplomeObtenu());
    }

    @Test
    void diplomeEnCours() {
        RemplirDiplomeCandidat();
        try {
            assertEquals(DiplomeEnCoursLanoix,Lanoix.diplomeEnCours());
        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void diplomeEnCoursNull() {
        RemplirDiplomeCandidat();
        assertThrows(CandidatureException.class, () -> Mottu.diplomeEnCours());
    }

    @Test
    void diplomesObtenusTriesParAnnee() {
        RemplirDiplomeCandidat();
        List<Diplome> diplomeTrie = Faucou.diplomesObtenusTriesParAnnee();
        assertEquals(BacFaucou,diplomeTrie.get(0));
        assertEquals(DiplomeDUTinfo,diplomeTrie.get(1));
        assertEquals(DiplomeBTS_SN_IR,diplomeTrie.get(2));
        assertEquals(DiplomeLICENCE_INFO,diplomeTrie.get(3));
    }

    @Test
    void diplomesObtenusTriesParAnneeNull() {
        RemplirDiplomeCandidat();
        assertEquals(null,Mottu.diplomesObtenusTriesParAnnee());
    }

    @Test
    void diplomesReferencesTriesParAnnee() {
        RemplirDiplomeCandidat();
        List<DiplomeSupReference> diplomeTrieRef = Lanoix.diplomesReferencesTriesParAnnee();
        assertEquals(DiplomeDUTinfo.reference(),diplomeTrieRef.get(0).reference());
        assertEquals(DiplomeBTS_SN_IR.reference(),diplomeTrieRef.get(1).reference());
    }

    @Test
    void diplomesReferencesTriesParAnneeNull() {
        RemplirDiplomeCandidat();
        assertEquals(null,Mottu.diplomesObtenusTriesParAnnee());
    }

    @Test
    void ajouteCandidat() {
        RemplirDiplomeCandidat();
        try {
            candidatures.ajoute(Nachouki);
        } catch (CandidatureException e) {
            e.printStackTrace();
        }
        assertEquals(Nachouki,candidatures.candidats().get(0));
        assertThrows(CandidatureException.class, () -> candidatures.ajoute(Nachouki));
        try {
            candidatures.ajoute(Remm);
            candidatures.ajoute(Lanoix);
            candidatures.ajoute(Faucou);
        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
        assertEquals(Remm,candidatures.candidats().get(1));
        assertEquals(Faucou,candidatures.candidats().get(3));
    }

    @Test
    void ajouteCandidatNull() {
        assertThrows(CandidatureException.class, () -> candidatures.ajoute(Hadj_Rabia));
    }

    @Test
    void nbCandidats() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        assertEquals(5,candidatures.nbCandidats());
    }

    @Test
    void candidatureEstValide() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        assertTrue(Candidatures.candidatureEstValide(Lanoix));
        assertFalse(Candidatures.candidatureEstValide(Remm));
        assertTrue(Candidatures.candidatureEstValide(Berdjugin));
        assertTrue(Candidatures.candidatureEstValide(Faucou));
        assertFalse(Candidatures.candidatureEstValide(Nachouki));
    }

    @Test
    void nbCandidatsValides() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        assertEquals(3,candidatures.nbCandidatsValides());
    }

    @Test
    void nbCandidatsInvalides() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        assertEquals(2,candidatures.nbCandidatsInvalides());
    }

    @Test
    void candidaturesInvalides() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        Candidatures candidaturesInvalides = candidatures.candidaturesInvalides();
        assertEquals(Remm,candidaturesInvalides.candidats().get(0));
        assertEquals(Nachouki,candidaturesInvalides.candidats().get(1));
    }

    @Test
    void candidaturesValides() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        Candidatures candidaturesValides = candidatures.candidaturesValides();
        assertEquals(Berdjugin,candidaturesValides.candidats().get(0));
        assertEquals(Faucou,candidaturesValides.candidats().get(1));
        assertEquals(Lanoix,candidaturesValides.candidats().get(2));
    }

    @Test
    void efface() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        assertEquals(Faucou,candidatures.candidats().get(1));
        try {
            candidatures.efface(Faucou);
        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
        assertEquals(4,candidatures.nbCandidats());
        assertEquals(Remm,candidatures.candidats().get(1));
    }

    @Test
    void candidaturesParOrdreAlpha() {
        // /!\ prise en compte de 2 candidats ayant le mÃªme nom de famille
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        try {
            candidatures.ajoute(RemmJumeau);
        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
        Candidatures candidaturesAlpha = candidatures.candidaturesParOrdreAlpha();
        System.out.println(candidaturesAlpha.toString());
        assertEquals(Berdjugin,candidaturesAlpha.candidats().get(0));
        assertEquals(Faucou,candidaturesAlpha.candidats().get(1));
        assertEquals(Lanoix,candidaturesAlpha.candidats().get(2));
        assertEquals(Nachouki,candidaturesAlpha.candidats().get(3));
        assertEquals(Remm,candidaturesAlpha.candidats().get(4));
        assertEquals(RemmJumeau,candidaturesAlpha.candidats().get(5));
    }

    @Test
    void candidaturesParOrdreCreation() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        Candidatures candidaturesCrea = candidatures.candidaturesParOrdreCreation();
        assertEquals(Remm,candidaturesCrea.candidats().get(0));
        assertEquals(Lanoix,candidaturesCrea.candidats().get(1));
        assertEquals(Faucou,candidaturesCrea.candidats().get(2));
        assertEquals(Berdjugin,candidaturesCrea.candidats().get(3));
        assertEquals(Nachouki,candidaturesCrea.candidats().get(4));
    }

    @Test
    void candidaturesAyantCommeNom() {
        // /!\ prise en compte de 2 candidats ayant le mÃªme nom de famille
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        try {
            candidatures.ajoute(RemmJumeau);
        } catch(CandidatureException ex) {
            fail(ex.getMessage());
        }
        Candidatures candidaturesNom = candidatures.candidaturesAyantCommeNom("REMM");
        assertEquals(Remm,candidaturesNom.candidats().get(0));
        assertEquals(RemmJumeau,candidaturesNom.candidats().get(1));
    }

    @Test
    void candidaturesParSerieBac() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        Candidatures candidaturesNom = candidatures.candidaturesParSerieBac(S);
        assertEquals(Berdjugin,candidaturesNom.candidats().get(0));
        assertEquals(Faucou,candidaturesNom.candidats().get(1));
    }

    @Test
    void candidaturesParReference() {
        RemplirDiplomeCandidat();
        RemplirDeCandidats();
        Candidatures candidaturesNom = candidatures.candidaturesParReference(BTS_SN_IR);
        assertEquals(Faucou,candidaturesNom.candidats().get(0));
        assertEquals(Nachouki,candidaturesNom.candidats().get(1));
        assertEquals(Lanoix,candidaturesNom.candidats().get(2));

    }

}
