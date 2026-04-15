package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AplicatieCuBursaTest {

    @Test
    void testSorteaza() {

        AplicatieCuBursa app = new AplicatieCuBursa();
        List<StudentBursier> lista = app.genereaza();

        List<StudentBursier> sortata = app.sorteaza(lista);

        // verificăm că lista este sortată corect
        for (int i = 0; i < sortata.size() - 1; i++) {

            StudentBursier s1 = sortata.get(i);
            StudentBursier s2 = sortata.get(i + 1);

            int rezultat = Comparator
                    .comparing(StudentBursier::getFormatieDeStudiu)
                    .thenComparing(StudentBursier::getNume)
                    .thenComparing(StudentBursier::getPrenume)
                    .thenComparing(StudentBursier::getNota)
                    .thenComparing(StudentBursier::getCuantumBursa)
                    .compare(s1, s2);

            assertTrue(rezultat <= 0);
        }
    }
}