package po2.modele.tests;
import org.junit.jupiter.api.Test;
import po2.modele.*;

import static org.junit.jupiter.api.Assertions.fail;
import static po2.modele.ANNEE.*;
import static po2.modele.REF_DIPLOME.*;
import static po2.modele.SERIE.*;


/** 
 * classe donnant un usage minimal des classe Candidat et Candidatures.
 */
public class TestUsage {

    Candidat albert, bernard, corinne, daniel;
    Candidatures candidatures;

    @Test
    void unSimpleScenarioUsage() {
        try{
            // initialise Albert
            albert = Candidat.fabriqueCandidatEtudiant("ALBERT", "albert", 1998,
                    new Bac(A_11_12, "qsdf", STI2D),
                    new DiplomeSupReference(A_20_21, "ghfhgfh", "rerertet", BTS_SN_IR)
            );
            albert.ajouteDiplome(new DiplomeSupReference(A_19_20, "qsd", "yutuy", LICENCE_INFO));
            albert.ajouteDiplome(new DiplomeSupReference(A_15_16, "tryyrty", "tyyry", DUT_INFO));
            albert.ajouteDiplome(new DiplomeSup(A_13_14, "xwxwxc", "yuyuy"));


            // initialise Bernard
            bernard = Candidat.fabriqueCandidat("BERNARD", "bernard", 1989,
                    STATUT.SALARIE, new Bac(A_10_11, "AZE", L));
            bernard.ajouteDiplome(new DiplomeSupReference(A_16_17, "tryyrty", "tyyry", BTS_SN_IR));
            bernard.ajouteDiplome(new DiplomeSup(A_15_16, "xwxwxc", "yuyuy"));

            // initialise Corinne
            corinne = Candidat.fabriqueCandidat("CORINNE", "corinne", 2000,
                    STATUT.DEMANDEUR_D_EMPLOI, new Bac(A_12_13, "SFFD", S));
            corinne.ajouteDiplome(new DiplomeSupReference(A_14_15, "qsd", "yutuy", DUT_INFO));

            // créé un ensemble de candidatures
            candidatures = new Candidatures();

            candidatures.ajoute(albert);
            candidatures.ajoute(bernard);
            candidatures.ajoute(corinne);

            System.out.println("Nb candidats : " + candidatures.nbCandidats() + " (attendu 3)");

            Candidatures candidaturesInvalides =
                    candidatures.candidaturesInvalides();
            System.out.println("Nb candidats invalides : " + candidaturesInvalides.nbCandidats() + " (attendu 1)");

            Candidatures candidaturesValidesParAlpha =
                    candidatures.candidaturesValides().candidaturesParOrdreAlpha();
            System.out.println("Nb candidats valides : " + candidaturesValidesParAlpha.nbCandidats() + " (attendu 2)");

            Candidatures candidaturesDutInfoParCreation =
                    candidatures.candidaturesParReference(DUT_INFO).candidaturesParOrdreCreation();
            System.out.println("Nb candidats Dut info : " + candidaturesDutInfoParCreation.nbCandidats() + " (attendu 2)");
        }
        catch(CandidatureException ex) {
            fail(ex.getMessage());
        }

    }

}
