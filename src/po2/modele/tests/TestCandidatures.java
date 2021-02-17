package po2.modele.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import po2.modele.*;

import java.util.List;

import static po2.modele.tests.myassertions.Assertions.assertContainsSame;
import static po2.modele.tests.myassertions.Assertions.assertIterableSame;
import static org.junit.jupiter.api.Assertions.*;
import static po2.modele.ANNEE.*;
import static po2.modele.REF_DIPLOME.*;
import static po2.modele.SERIE.*;

public class TestCandidatures {

    Candidat albert, bernard, corinne, daniel;

    Candidatures candidatures;

    @BeforeEach
    public void init() throws CandidatureException {
        daniel = Candidat.fabriqueCandidatEtudiant("DANIEL", "daniel", 1997,
                new Bac(A_13_14,"AZE", S),
                new DiplomeSup(A_20_21,"xwxwxc", "yuyuy"));
        daniel.ajouteDiplome(new DiplomeSup(A_16_17, "xwxwxc", "yuyuy"));
        daniel.ajouteDiplome(new DiplomeSup(A_19_20, "xwxwxc", "yuyuy"));
        daniel.ajouteDiplome(new DiplomeSup(A_15_16, "xwxwxc", "yuyuy"));
        //
        corinne = Candidat.fabriqueCandidat("CORINNE", "corinne", 2000,
                STATUT.DEMANDEUR_D_EMPLOI, new Bac(A_12_13, "SFFD", S));
        corinne.ajouteDiplome(new DiplomeSupReference(A_14_15, "qsd", "yutuy", DUT_INFO));
        //
        bernard = Candidat.fabriqueCandidat("BERNARD", "bernard", 1989,
                STATUT.SALARIE, new Bac(A_10_11, "AZE", L));
        bernard.ajouteDiplome(new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SN_IR));
        bernard.ajouteDiplome(new DiplomeSup(A_15_16, "xwxwxc", "yuyuy"));
        //
        albert = Candidat.fabriqueCandidatEtudiant("ALBERT", "albert", 1998,
                new Bac(A_11_12, "qsdf", STI2D),
                new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", BTS_SN_IR)
        );
        albert.ajouteDiplome(new DiplomeSupReference(A_19_20, "qsd", "yutuy", LICENCE_INFO));
        albert.ajouteDiplome(new DiplomeSupReference(A_15_16, "tryyrty", "tyyry", DUT_INFO));
        albert.ajouteDiplome(new DiplomeSup(A_13_14, "xwxwxc", "yuyuy"));
        //
        candidatures = new Candidatures();
    }

    /**
     * L'ordre de creation est donc
     *
     * daniel < corinne < bernard < albert
     *
     * alors que l'ordre alphabetique est
     *
     * albert < bernard < corinne < daniel
     *
     * candidats valides = albert, corinne
     * candidats invalides = bernard, daniel
     *
     */


    /**
     * candidatures tout juste creé est donc bien vide
     */
    @Test
    public void testCandidaturesVide() {
        assertAll(
                ()-> assertEquals(0, candidatures.nbCandidats()),
                ()-> assertTrue(candidatures.candidats().isEmpty())
        );
    }

    /**
     * candidatures créé Ã  partir d'une liste de candidats
     * contient bien les candidats
     */
    @Test
    public void testCandidaturesNonVide() {
        List<Candidat> listeInitiale = List.of(daniel,corinne, bernard, albert);
        candidatures = new Candidatures(listeInitiale);
        // TEST
        assertAll(
                ()-> assertEquals(4, candidatures.nbCandidats()),
                ()-> assertContainsSame(listeInitiale, candidatures.candidats())
        );
    }

    /**
     * On ajoute un candidat ; il est bien le seul
     */
    @Test
    public void testCandidatures1Ajout() throws CandidatureException {
        candidatures.ajoute(bernard);
        assertAll(
                ()-> assertEquals(1, candidatures.nbCandidats()),
                ()-> assertContainsSame(List.of(bernard), candidatures.candidats())
        );
    }

    /**
     * on ajoute plusieurs candidats ; ils sont bien tous lÃ 
     */
    @Test
    public void testCandidaturesPlusieursAjouts() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        // TEST
        assertAll(
                ()-> assertEquals(4, candidatures.nbCandidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  On ajoute 2x le meme candidat => exception
     */
    @Test
    public void testCandidaturesPlusieursAjouts_exception() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        //
        assertThrows(CandidatureException.class,
                ()-> candidatures.ajoute(daniel));
    }

    /**
     *  on obtient bien les candidats valides
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesValides() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cValides = candidatures.candidaturesValides();
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cValides),
                ()-> assertEquals(2, candidatures.nbCandidatsValides()),
                ()-> assertContainsSame(List.of(albert,corinne),cValides.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     * on obtient bien les candidats invalides
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesInValides() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        // TEST
        Candidatures cInvalides = candidatures.candidaturesInvalides();
        assertAll(
                ()-> assertNotSame(candidatures,cInvalides),
                ()-> assertEquals(2, candidatures.nbCandidatsInvalides()),
                ()-> assertContainsSame(List.of(daniel,bernard),cInvalides.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  si pas de candidats invalides, on obtient bien une liste vide
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesInValides_vide() throws CandidatureException {
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        // TEST
        Candidatures cInvalides = candidatures.candidaturesInvalides();
        assertAll(
                ()-> assertNotSame(candidatures,cInvalides),
                ()-> assertEquals(0, candidatures.nbCandidatsInvalides()),
                ()-> assertTrue(cInvalides.candidats().isEmpty())
        );
    }

    /**
      * si on efface un candidat, il n'est plus dans les candidats
     */
    @Test
    public void testCandidatureEffacement() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        // TEST
        candidatures.efface(corinne);
        assertAll(
                ()-> assertEquals(3, candidatures.nbCandidats()),
                ()-> assertContainsSame(List.of(albert,bernard,daniel), candidatures.candidats())
        );
    }

    /**
     * si on efface un candidat pas ajouté dans les candidatures
     * => Exception
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidatureEffacement_exception() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidat albert2 = Candidat.fabriqueCandidat("ALBERT", "albert",
                1998, STATUT.SALARIE, new Bac(A_11_12, "qsdf", STI2D));
        // TEST
        assertAll(
                ()-> assertThrows(CandidatureException.class, ()-> candidatures.efface(albert2)),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient bien les candidatures triées alphabétiquement
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidatureOrdreAlpha() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cAlpha = candidatures.candidaturesParOrdreAlpha();
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cAlpha),
                ()-> assertIterableSame(List.of(albert,bernard,corinne,daniel),cAlpha.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient bien les candidatures triées par ordre de création
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidatureOrdreCreation() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cCreation = candidatures.candidaturesParOrdreCreation();
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cCreation),
                ()-> assertIterableSame(List.of(daniel, corinne, bernard, albert), cCreation.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }


    /**
     *  on otient bien les candidatures filtrées par NOM candidat
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidatureCommeNom1() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesAyantCommeNom("BERNARD");
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertContainsSame(List.of(bernard),cFiltrees.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient bien les candidatures filtrées par NOM candidat
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidatureCommeNom2() throws CandidatureException {
        Candidat albert2 = Candidat.fabriqueCandidat("ALBERT", "albert",
                1998, STATUT.SALARIE, new Bac(A_11_12, "qsdf", STI2D));
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        candidatures.ajoute(albert2);
        Candidatures cFiltrees = candidatures.candidaturesAyantCommeNom(new String("ALBERT"));
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertContainsSame(List.of(albert,albert2),cFiltrees.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel,albert2), candidatures.candidats())
        );
    }


    /**
     *  on obtient bien les candidatures filtrées par BAC
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesSerieBacS() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesParSerieBac(S);
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertContainsSame(List.of(corinne,daniel),cFiltrees.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient bien les candidatures filtrées par BAC
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesSerieBacSTI2D() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesParSerieBac(STI2D);
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertContainsSame(List.of(albert),cFiltrees.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient bien les candidatures filtrées par BAC
     *  aucun candidat avec ce BAC => la liste des candidats est bien vide
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesSerieBacSTMG() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesParSerieBac(STMG);
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertTrue(cFiltrees.candidats().isEmpty()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient les candidatures ayant comme diplome ref celui demandé
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesRef_DUTINFO() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesParReference(DUT_INFO);
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertContainsSame(List.of(albert,corinne),cFiltrees.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient les candidatures ayant comme diplome ref celui demandé
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesRef_BTSSNIR() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesParReference(BTS_SN_IR);
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertContainsSame(List.of(albert, bernard),cFiltrees.candidats()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );
    }

    /**
     *  on obtient les candidatures ayant comme diplome ref celui demandé
     *  aucune candidature correspondant => liste des candidats vide
     *  + c'est bien un nouvel objet candidatures
     *  + les candidatures initiales ne sont pas modifiées
     */
    @Test
    public void testCandidaturesRef_BTSSIO() throws CandidatureException {
        candidatures.ajoute(bernard);
        candidatures.ajoute(daniel);
        candidatures.ajoute(albert);
        candidatures.ajoute(corinne);
        Candidatures cFiltrees = candidatures.candidaturesParReference(BTS_SIO_SLAM);
        // TEST
        assertAll(
                ()-> assertNotSame(candidatures,cFiltrees),
                ()-> assertTrue(cFiltrees.candidats().isEmpty()),
                ()-> assertContainsSame(List.of(albert,bernard,corinne,daniel), candidatures.candidats())
        );


        // TODO : tester la combinaison de plusieurs tris+filtres
    }

}
