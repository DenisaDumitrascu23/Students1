package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @Test
    void testImparteInDouaFormatiiNumarImpar() {
        List<Student> studenti = new ArrayList<>();
        studenti.add(new Student(1, "A", "A", "INIT", 8.0));
        studenti.add(new Student(2, "B", "B", "INIT", 7.0));
        studenti.add(new Student(3, "C", "C", "INIT", 9.0));

        List<List<Student>> rezultat = Application.imparteInDouaFormatii(studenti, "F1", "F2");

        assertEquals(2, rezultat.get(0).size());
        assertEquals(1, rezultat.get(1).size());
        assertEquals("F1", rezultat.get(0).get(0).getFormatieDeStudiu());
        assertEquals("F2", rezultat.get(1).get(0).getFormatieDeStudiu());
    }

    @Test
    void testMutaStudentInAltaFormatie() {
        List<Student> studenti = new ArrayList<>();
        studenti.add(new Student(10, "Ion", "Pop", "F1", 9.5));
        studenti.add(new Student(11, "Ana", "Ionescu", "F1", 8.2));

        List<Student> rezultat = Application.mutaStudentInAltaFormatie(studenti, 10, "F2");

        assertEquals("F2", rezultat.get(0).getFormatieDeStudiu());
        assertEquals("F1", studenti.get(0).getFormatieDeStudiu());
    }

    @Test
    void testStudentEsteImutabilPrinCopiere() {
        Student initial = new Student(20, "Mara", "Popa", "F1", 7.75);

        Student cuAltaNota = initial.cuNota(9.0);
        Student cuAltaFormatie = initial.cuFormatieDeStudiu("F2");

        assertEquals(7.75, initial.getNota());
        assertEquals("F1", initial.getFormatieDeStudiu());
        assertEquals(9.0, cuAltaNota.getNota());
        assertEquals("F2", cuAltaFormatie.getFormatieDeStudiu());
    }
}