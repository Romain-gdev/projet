package po2.modele.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import po2.modele.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static po2.modele.tests.myassertions.Assertions.assertIterableSame;
import static org.junit.jupiter.api.Assertions.*;
import static po2.modele.ANNEE.*;
import static po2.modele.REF_DIPLOME.*;
import static po2.modele.SERIE.*;

public class TestCandidats {

    Candidat albert, bernard, corinne, daniel;
    Comparator<Candidat> comparateur = new ComparateurOrdreAlpha();

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
        albert.ajouteDiplome(new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM));
        albert.ajouteDiplome(new DiplomeSup(A_13_14, "xwxwxc", "yuyuy"));
        //
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


    // on vérifie que albert < bernard alphabétiquement parlant
    // (en utilisant comparateur OrdreAlpha)
    @Test
    public void testOrdreAlpha1() {
        assertTrue(comparateur.compare(albert, bernard) < 0);
    }

    // on vérifie que albert < daniel alphabétiquement parlant
    // (en utilisant comparateur OrdreAlpha)
    @Test
    public void testOrdreAlpha2() {
        assertTrue(comparateur.compare(albert, daniel) < 0);
    }

    // on vérifie que daniel > bernard alphabétiquement parlant
    // (en utilisant comparateur OrdreAlpha)
    @Test
    public void testOrdreAlpha3() {
        assertTrue(comparateur.compare(daniel, bernard) > 0);
    }

    // on vérifie que ALBERT,albert < ALBERT,beatrice alphabétiquement parlant
    // (en utilisant comparateur OrdreAlpha)
    @Test
    public void testOrdreAlpha4() throws CandidatureException {
        Candidat albertb = Candidat.fabriqueCandidat("ALBERT", "beatrice", 1998,
                STATUT.SALARIE, new Bac(A_11_12, "qsdf", STI2D));
        // TEST
        assertTrue(comparateur.compare(albert,albertb) < 0);
    }

    // on vérifie que ALBERT,albert == ALBERT,albert alphabétiquement parlant
    // (en utilisant comparateur OrdreAlpha)
    @Test
    public void testOrdreAlpha5() throws CandidatureException {
        Candidat albert2 = Candidat.fabriqueCandidat("ALBERT", "albert", 1998,
                STATUT.SALARIE, new Bac(A_11_12, "qsdf", STI2D));
        // TEST
        assertTrue(comparateur.compare(albert,albert2) == 0);
    }

    // on vérifie que ALBERT,albert == ALBERT,albert (meme instance) alphabétiquement parlant
    // (en utilisant comparateur OrdreAlpha)
    @Test
    public void testOrdreAlpha6() throws CandidatureException {
        assertTrue(comparateur.compare(albert,albert) == 0);
    }


    // on verifie qu'on peut bien trié correctement une liste de candidats
    // (en utilisant le comparateur OrdreAlpha)
    @Test
    public void testOrdreAlphaTri() {
        List<Candidat> candidats = Arrays.asList(bernard, daniel, corinne, albert);
        candidats.sort(comparateur);
        // TEST
        assertIterableSame(List.of(albert,bernard,corinne,daniel),candidats);
    }

    // on vérifie que albert plus recent que bernard
    // (ordre naturel = ordre de creation)
    @Test
    public void testComparateurOrdreDepot1() {
        assertTrue(albert.compareTo(bernard) > 0);
    }

    // on vérifie que albert plus recent que daniel
    // (ordre naturel = ordre de creation)
    @Test
    public void testComparateurOrdreDepot2() {
        assertTrue(albert.compareTo(daniel) > 0);
    }

    // on vérifie que daniel plus ancien que bernard
    // (ordre naturel = ordre de creation)
    @Test
    public void testComparateurOrdreDepot3() {
        assertTrue(daniel.compareTo(bernard) < 0);
    }

    // on vérifie qu'un tri par ordre naturel est ok
    // (ordre naturel = ordre de creation)
    @Test
    public void testComparateurOrdreDepotTri() throws CandidatureException{
        Candidat albert2 = Candidat.fabriqueCandidat("ALBERT", "albert",
                1998, STATUT.SALARIE, new Bac(A_11_12, "qsdf", STI2D));
        //
        List<Candidat> candidats = Arrays.asList(bernard, albert2, daniel, corinne, albert);
        //
        candidats.sort(null);
        // TEST
        assertIterableSame(List.of(daniel,corinne,bernard,albert,albert2),candidats);
    }


    // albert (candidat valide) est bien valide
    @Test
    public void testCandidatAlbertValide() throws CandidatureException {
        // TEST
        assertTrue(Candidatures.candidatureEstValide(albert));
    }

    // bernard (candidat invalide) est bien invalide
    @Test
    public void testCandidatBernardInvalide() throws CandidatureException {
        // TEST
        assertFalse(Candidatures.candidatureEstValide(bernard));
    }

    // corinne (candidat valide) est bien valide
    @Test
    public void testCandidatCorinneValide() throws CandidatureException {
        // TEST
        assertTrue(Candidatures.candidatureEstValide(corinne));
    }

    // daniel (candidat invalide) est bien invalide
    @Test
    public void testCandidatDanierInvalide() throws CandidatureException   {
        // TEST
        assertFalse(Candidatures.candidatureEstValide(daniel));
    }


}
