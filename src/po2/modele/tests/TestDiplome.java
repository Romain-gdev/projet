package po2.modele.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import po2.modele.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static po2.modele.SERIE.*;
import static po2.modele.ANNEE.*;
import static po2.modele.REF_DIPLOME.*;
import static po2.modele.tests.myassertions.Assertions.*;

public class TestDiplome {

    Bac bac;
    DiplomeSup d1;
    DiplomeSup d2;
    DiplomeSup d3;

    @BeforeEach
    public void init() {
        bac = new Bac(A_10_11, "aze", ES);
        d1 = new DiplomeSup(A_20_21, "qsd", "yutuy");
        d2 = new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SIO_SLAM);
        d3 = new DiplomeSup(A_13_14, "xwxwxc", "yuyuy");
    }

    /**
     * Ici l'ordre naturel attendu des diplomes doit donc être
     *                 bac < d3 < d2 < d1
     */

    // vérifie que bac < d1
    @Test
    public void testComparaisonDiplome1() {
       assertTrue(bac.compareTo(d1) < 0);
    }

    // vérifie que bac < d2
    @Test
    public void testComparaisonDiplome2() {
        assertTrue(bac.compareTo(d2) < 0);
    }

    // vérifie que d1 > d2
    @Test
    public void testComparaisonDiplome3() {
        assertTrue(d1.compareTo(d2) > 0);
    }

    // vérifie que d3 < d2
    @Test
    public void testComparaisonDiplome4() {
        assertTrue(d3.compareTo(d2) < 0);
    }

    // on vérifie qu'un tri de diplômes utilisant l'ordre naturel est correct
    @Test
    public void testTriDiplomes() {
        List<Diplome> diplomes = Arrays.asList(bac,d1,d2,d3);
        diplomes.sort(null);
        assertIterableSame(List.of(bac,d3,d2,d1), diplomes);
    }
}
