package po2.modele.tests;
import org.junit.jupiter.api.Test;
import po2.modele.*;

import java.util.List;

import static po2.modele.tests.myassertions.Assertions.assertIterableSame;
import static org.junit.jupiter.api.Assertions.*;
import static po2.modele.ANNEE.*;
import static po2.modele.REF_DIPLOME.*;
import static po2.modele.SERIE.*;
import static po2.modele.STATUT.*;

public class TestCandidat {

    /**
     * fabriquer un candidat etudiant correct ne leve pas d'exception
     */
    @Test
    public void testFabriqueEtu_noException() {
        assertDoesNotThrow(()-> Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                new Bac(A_16_17, "qsdf", S),
                new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO)));
    }

    /**
     * fabriquer un candidat etudiant correct renvoie bien un candidat étudiant
     * @throws CandidatureException
     */
    @Test
    public void testFabriqueEtu() throws CandidatureException {
            Candidat c = Candidat.fabriqueCandidatEtudiant(
                    "AZERT",
                    "aeeere",
                    1998,
                    new Bac(A_16_17, "qsdf", S),
                    new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO));
            // TEST
            assertAll(
                    ()-> assertNotNull(c),
                    ()-> assertTrue(c instanceof Candidat),
                    ()-> assertEquals(ETUDIANT, c.statut()));
    }

    /**
     * fabriquer un candidat etudiant avec bac et diplomeRef meme annee leve une exception
     */
    @Test
    public void testFabriqueEtu_Exception_egaleBac() {
        assertThrows(CandidatureException.class,
                ()-> Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                new Bac(A_16_17, "qsdf", S),
                new DiplomeSupReference(A_16_17, "ghfhgfh", "rerertet", DUT_INFO)));
    }

    /**
     * fabriquer un candidat etudiant avec diplomeRef < bac leve une exception
     */
    @Test
    public void testFabriqueEtu_Exception_plusAncienneBac() {
        assertThrows(CandidatureException.class,
                ()-> Candidat.fabriqueCandidatEtudiant(
                        "AZERT",
                        "aeeere",
                        1998,
                        new Bac(A_16_17, "qsdf", S),
                        new DiplomeSupReference(A_15_16, "ghfhgfh", "rerertet", DUT_INFO)));
    }

    /**
     *
     * fabriquer un candidat salarie correct renvoie bien un candidat salarié
     */
    @Test
    public void testFabriqueSalarie() throws CandidatureException {
        Candidat c = Candidat.fabriqueCandidat(
                "fgfg",
                "rtry",
                1996,
                SALARIE,
                new Bac(A_15_16, "hgfhfgh", STI2D));
        // TEST
        assertAll(
                ()->assertNotNull(c),
                ()-> assertTrue(c instanceof Candidat),
                ()->assertEquals(SALARIE, c.statut()));
    }

    /**
     *  fabriquer un candidat demandeur d'emploi renvoie bien un candidat demandeur d'emploi
     */
    @Test
    public void testFabriqueDemandeurEmploi() throws CandidatureException {
        Candidat c = Candidat.fabriqueCandidat(
                "WXXWXW",
                "ytuyyu",
                2000,
                DEMANDEUR_D_EMPLOI,
                new Bac(A_18_19, "hgythgh", L)
        );
        // TEST
        assertAll(
                ()->assertNotNull(c),
                ()-> assertTrue(c instanceof Candidat),
                ()->assertEquals(DEMANDEUR_D_EMPLOI, c.statut()));
    }

    /**
     * fabriquer un candidat (statut etudiant) leve une exception
     */
    @Test
    public void testFabriqueDemandeurEmploiException() {
        assertThrows(CandidatureException.class,
                () -> Candidat.fabriqueCandidat(
                        "WXXWXW",
                        "ytuyyu",
                        2000,
                        ETUDIANT,
                        new Bac(A_18_19, "hgythgh", L)));
    }

    /**
     * vérifie l'etat d'un candidat etudiant tout juste créé :
     * bac  = bac
     * diplomes obtenus = bac
     * dernier diplome = bac
     * diplome en cours = diplome en cours
     */
    @Test
    public void testEtatCandidatureJusteCree() throws CandidatureException {
        Bac bac = new Bac(A_16_17, "qsdf", S);
        DiplomeSup ref = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                ref);
        // TEST
        assertAll(
                ()-> assertSame(bac, c.bacObtenu()),
                ()-> assertIterableSame(List.of(bac),c.diplomesObtenusTriesParAnnee()),
                ()-> assertSame(bac, c.dernierDiplomeObtenu()),
                ()-> assertSame(ref, c.diplomeEnCours()));
    }
    /**
     * vérifie l'etat d'un candidat (non etudiant) tout juste créé :
     * bac  = bac
     * diplomes obtenus = bac
     * dernier diplome = bac
     * diplome en cours => Exception
     */
    @Test
    public void testEtatCandidatureJusteCree2() throws CandidatureException {
            Bac bac = new Bac(A_15_16, "hgfhfgh", STI2D);
            Candidat c = Candidat.fabriqueCandidat(
                    "fgfg",
                    "rtry",
                    1996,
                    SALARIE,
                    bac);
            // TEST
            assertAll(
                    ()-> assertSame(bac, c.bacObtenu()),
                    ()-> assertIterableSame(List.of(bac),c.diplomesObtenusTriesParAnnee()),
                    ()-> assertSame(bac, c.dernierDiplomeObtenu()),
                    ()-> assertThrows(CandidatureException.class,()-> c.diplomeEnCours()));
    }

    /**
     * on ajoute un diplome d1 correct et on verifie l'etat
     * bac  = bac
     * diplomes obtenus = bac, d1
     * dernier diplome = d1
     * diplome en cours = diplome en cours
     */
    @Test
    public void testAjoutUnDiplomeObtenu() throws CandidatureException {
        Bac bac = new Bac(A_16_17, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours);
        DiplomeSup d1 = new DiplomeSup(A_19_20, "qsd", "yutuy");
        c.ajouteDiplome(d1);
        // TEST
        assertAll(
                ()-> assertSame(bac, c.bacObtenu()),
                ()-> assertIterableSame(List.of(bac,d1),c.diplomesObtenusTriesParAnnee()),
                ()-> assertSame(d1, c.dernierDiplomeObtenu()),
                ()-> assertSame(enCours, c.diplomeEnCours()));
    }

    /**
     *  on ajoute diplome < bac  => Exception
     */
    @Test
    public void testAjoutUnDiplomeObtenu_plusAncienQueBac() throws CandidatureException {
        Bac bac = new Bac(A_16_17, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours);
        DiplomeSup d1 = new DiplomeSup(A_12_13, "qsd", "yutuy");
        // TEST
        assertThrows(CandidatureException.class, ()-> c.ajouteDiplome(d1));
    }

    /**
     *  on ajoute diplome == bac  => Exception
     */
    @Test
    public void testAjoutUnDiplomeObtenu_memeAnneeBac() throws CandidatureException {
        Bac bac = new Bac(A_16_17, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours);
        DiplomeSup d1 = new DiplomeSup(A_16_17, "qsd", "yutuy");
        // TEST
        assertThrows(CandidatureException.class, ()-> c.ajouteDiplome(d1));
    }

    /**
     *  on ajoute diplome == diplomeEnCours  => Exception
     */
    @Test
    public void testAjoutUnDiplomeObtenu_memeAnneeEnCours() throws CandidatureException {
        Bac bac = new Bac(A_16_17, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours);
        DiplomeSup d1 = new DiplomeSup(A_20_21, "qsd", "yutuy");
        // TEST
        assertThrows(CandidatureException.class, ()-> c.ajouteDiplome(d1));
    }

    /**
     *  on ajoute diplome > diplomeEnCours  => Exception
     */
    @Test
    public void testAjoutUnDiplomeObtenu_plusRecentQueEnCours() throws CandidatureException {
        Bac bac = new Bac(A_16_17, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_18_19, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours
        );
        DiplomeSup d1 = new DiplomeSup(A_20_21, "qsd", "yutuy");
        // TEST
        assertThrows(CandidatureException.class, ()-> c.ajouteDiplome(d1));
    }


    /**
     *  on ajoute plusieurs diplomes d1, d2, d3 corrects et on verifie l'etat
     *      * bac  = bac
     *      * diplomes obtenus = bac, d3, d2, d1
     *      * dernier diplome = d1
     *      * diplome en cours = diplome en cours
     */
    @Test
    public void testAjoutPlusieursDiplomesObtenus() throws CandidatureException {
        Bac bac = new Bac(A_11_12, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours
        );
        DiplomeSup d1 = new DiplomeSup(A_19_20, "qsd", "yutuy");
        c.ajouteDiplome(d1);
        DiplomeSup d2 = new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM);
        c.ajouteDiplome(d2);
        DiplomeSup d3 = new DiplomeSup(A_13_14, "xwxwxc", "yuyuy");
        c.ajouteDiplome(d3);
        // TEST
        assertAll(
                ()-> assertSame(bac, c.bacObtenu()),
                ()-> assertIterableSame(List.of(bac,d3,d2,d1),c.diplomesObtenusTriesParAnnee()),
                ()-> assertSame(d1, c.dernierDiplomeObtenu()),
                ()-> assertSame(enCours, c.diplomeEnCours())
        );
    }

    /**
     *  on ajoute plusieurs diplomes d1, d2, d3 corrects et on verifie l'etat
     *      * bac  = bac
     *      * diplomes obtenus = bac, d3, d2, d1
     *      * dernier diplome = d1
     *      * diplome en cours = > Exception
     */
    @Test
    public void testAjoutPlusieursDiplomesObtenus2() throws CandidatureException {
        Bac bac = new Bac(A_11_12, "qsdf", S);
        Candidat c = Candidat.fabriqueCandidat(
                "AZERT",
                "aeeere",
                1998,
                DEMANDEUR_D_EMPLOI,
                bac
        );
        DiplomeSup d1 = new DiplomeSup(A_19_20, "qsd", "yutuy");
        c.ajouteDiplome(d1);
        DiplomeSup d2 = new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM);
        c.ajouteDiplome(d2);
        DiplomeSup d3 = new DiplomeSup(A_13_14, "xwxwxc", "yuyuy");
        c.ajouteDiplome(d3);
        // TEST
        assertAll(
                ()-> assertSame(bac, c.bacObtenu()),
                ()-> assertIterableSame(List.of(bac,d3,d2,d1),c.diplomesObtenusTriesParAnnee()),
                ()-> assertSame(d1, c.dernierDiplomeObtenu()),
                ()-> assertThrows(CandidatureException.class,()-> c.diplomeEnCours())
        );
    }

    /**
     *  on ajoute plusieurs diplomes d1, d2, d3 avec annees identiques
     *  => Exception
     */
    @Test
    public void testAjoutPlusieursDiplomesObtenusMemeAnnee_exception() throws CandidatureException {
        Bac bac = new Bac(A_11_12, "qsdf", S);
        DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", DUT_INFO);
        Candidat c = Candidat.fabriqueCandidatEtudiant(
                "AZERT",
                "aeeere",
                1998,
                bac,
                enCours
        );
        DiplomeSup d1 = new DiplomeSup(A_19_20, "qsd", "yutuy");
        c.ajouteDiplome(d1);
        DiplomeSup d2 = new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM);
        c.ajouteDiplome(d2);
        DiplomeSup d3 = new DiplomeSup(A_16_17, "xwxwxc", "yuyuy");
        // TEST
        assertThrows(CandidatureException.class, ()->c.ajouteDiplome(d3));
    }

    /**
     *  on ajoute plusieurs diplomes d1, d2, d3 (aucun reference)
     *  on vérifie que DiplomesReferences donne liste vide
     */
    @Test
    public void testDiplomesReferences_aucun() throws CandidatureException {
            Bac bac = new Bac(A_11_12, "qsdf", S);
            DiplomeSup enCours = new DiplomeSup(A_20_21, "ghfhgfh", "rerertet");
            Candidat c = Candidat.fabriqueCandidatEtudiant(
                    "AZERT",
                    "aeeere",
                    1998,
                    bac,
                    enCours
            );
            DiplomeSup d1 = new DiplomeSup(A_19_20, "qsd", "yutuy");
            c.ajouteDiplome(d1);
            DiplomeSup d2 = new DiplomeSup(A_16_17, "tryyrty", "tyyry");
            c.ajouteDiplome(d2);
            DiplomeSup d3 = new DiplomeSup(A_13_14, "xwxwxc", "yuyuy");
            c.ajouteDiplome(d3);
            List<DiplomeSupReference> resultat = c.diplomesReferencesTriesParAnnee();
            // TEST
            assertTrue(resultat.isEmpty());
    }

    /**
     *  on ajoute plusieurs diplomes d1, d2, d3 (1 seul reference)
     *  on vérifie que DiplomesReferences donne le bon diplome
     */
    @Test
    public void testDiplomesReferences_1seul() throws CandidatureException {
            Bac bac = new Bac(A_11_12, "qsdf", S);
            DiplomeSup enCours = new DiplomeSup(A_20_21, "ghfhgfh", "rerertet");
            Candidat c = Candidat.fabriqueCandidatEtudiant(
                    "AZERT",
                    "aeeere",
                    1998,
                    bac,
                    enCours
            );
            DiplomeSup d1 = new DiplomeSup(A_19_20, "qsd", "yutuy");
            c.ajouteDiplome(d1);
            DiplomeSup d2 = new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM);
            c.ajouteDiplome(d2);
            DiplomeSup d3 = new DiplomeSup(A_13_14, "xwxwxc", "yuyuy");
            c.ajouteDiplome(d3);
            List<DiplomeSupReference> resultat = c.diplomesReferencesTriesParAnnee();
            // TEST
            assertAll(
                    ()-> assertTrue(resultat.contains(d2)),
                    ()-> assertEquals(1, resultat.size()),
                    ()-> assertIterableSame(List.of(d2),resultat)
            );
    }

    /**
     *  on ajoute plusieurs diplomes d1, d2, d3 (plusieurs reference)
     *  on vérifie que DiplomesReferences donne les bons diplomes
     */
    @Test
    public void testDiplomesReferences_plusieurs() throws CandidatureException {
            Bac bac = new Bac(A_11_12, "qsdf", S);
            DiplomeSup enCours = new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", BTS_SN_IR);
            Candidat c = Candidat.fabriqueCandidatEtudiant(
                    "AZERT",
                    "aeeere",
                    1998,
                    bac,
                    enCours
            );
            DiplomeSup d1 = new DiplomeSupReference(A_19_20, "qsd", "yutuy", LICENCE_INFO);
            c.ajouteDiplome(d1);
            DiplomeSup d2 = new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM);
            c.ajouteDiplome(d2);
            DiplomeSup d3 = new DiplomeSup(A_13_14, "xwxwxc", "yuyuy");
            c.ajouteDiplome(d3);
            //
            List<DiplomeSupReference> resultat = c.diplomesReferencesTriesParAnnee();
            assertAll(
                    ()-> assertEquals(3, resultat.size()),
                    ()-> assertIterableSame(List.of(d2, d1, enCours),resultat)
            );
    }

    /**
     *  on ajoute plusieurs diplome (1 seul reference)
     *  on vérifie que DiplomesReferences donne le bon diplome
     */
    @Test
    public void testDiplomesReferences_unSeul2() throws CandidatureException {
        Candidat bernard = Candidat.fabriqueCandidat("BERNARD", "bernard", 1989,
                STATUT.SALARIE, new Bac(A_10_11, "AZE", L));
        bernard.ajouteDiplome(new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SN_IR));
        bernard.ajouteDiplome(new DiplomeSup(A_15_16, "xwxwxc", "yuyuy"));
        List<DiplomeSupReference> resultat = bernard.diplomesReferencesTriesParAnnee();
        assertAll(
                ()-> assertEquals(1, resultat.size())
        );
    }

    /**
     *  on ajoute plusieurs diplome (1 seul reference)
     *  on vérifie que DiplomesReferences donne le bon diplome
     */
    @Test
    public void testDiplomesReferences_unSeul3() throws CandidatureException {
        Candidat corinne = Candidat.fabriqueCandidat("CORINNE", "corinne", 2000,
                STATUT.DEMANDEUR_D_EMPLOI, new Bac(A_12_13, "SFFD", S));
        corinne.ajouteDiplome(new DiplomeSupReference(A_14_15, "qsd", "yutuy", DUT_INFO));
        List<DiplomeSupReference> resultat = corinne.diplomesReferencesTriesParAnnee();
        assertAll(
                ()-> assertEquals(1, resultat.size())
        );
    }


}
