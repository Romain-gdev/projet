package po2.modele.tests.myassertions;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Assertions {

    /**
     * Assertion vérifiant que les éléments de deux listes sont les mêmes.
     *
     * @param expected la liste témoin
     * @param actual la liste à tester
     */
    public static void assertIterableSame(List<?> expected, List<?> actual) {
        int exSize = expected.size();
        int acSize = actual.size();
        assertEquals(exSize,acSize, "iterable lengths differ, expected: <"+exSize+"> but was: <"+acSize+">)");
        //
        Iterator<?> exIte = expected.iterator();
        Iterator<?> acIte = actual.iterator();
        //
        int index = 0;
        while(exIte.hasNext() && acIte.hasNext()) {
            assertSame(exIte.next(), acIte.next(),
                    "iterable contents differ at index ["+index+"]");
            index++;
        }
    }

    /**
     * Assertion vérifiant que tous les éléments attendus sont bien tous dans
     * les élements à tester, mais pas forcément dans le même ordre, et qu'il n'y a pas
     * d'éléments en plus dans à tester.
     *
     * @param expected la liste témoin
     * @param actual la liste à tester
     */
    public static void assertContainsSame(List<?> expected, List<?> actual) {
        //
        int exSize = expected.size();
        int acSize = actual.size();
        assertEquals(exSize,acSize, "list lengths differ, expected: <"+exSize+"> but was: <"+acSize+">");
        Iterator<?> iteE = expected.iterator();
        while(iteE.hasNext()) {
            Object expectedObj = iteE.next();
            boolean trouve = false;
            Iterator<?> iteA = actual.iterator();
            while(iteA.hasNext() && !trouve) {
                if (expectedObj == iteA.next())
                    trouve = true;
            }
            if (!trouve)
                fail("expected element <"+expectedObj+"> not found into actual");
        }
    }
}
