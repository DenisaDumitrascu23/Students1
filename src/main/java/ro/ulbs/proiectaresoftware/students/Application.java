package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static boolean existaStudent(List<Student> lista, Student s) {

        for (Student st : lista) {
            if (st.getPrenume().equals(s.getPrenume()) && st.getNume().equals(s.getNume()) && st.getFormatieDeStudiu().equals(s.getFormatieDeStudiu())) {
                return true;
            }
        }
        return false;
    }
    static void main(String[] args) {
        Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
        Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
        Student s3 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");
        List<Student> studenti = new ArrayList<>();
        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);
        studenti.add(s5);
        System.out.printf("%14s %20s %16s%n", "numar matricol", "prenume nume", "formatieDeStudiu");

        // afisare cu foreach
        for (Student s : studenti) {
            System.out.println(s);
        }
        System.out.println();

        Student cautat1 = new Student(120, "Alis", "Popa", "TI21/2");
        Student cautat2 = new Student(112, "Ana", "Popescu", "TI21/1");
        System.out.println("Student 1 exista: " + existaStudent(studenti, cautat1));
        System.out.println("Student 2 exista: " + existaStudent(studenti, cautat2));
    }
}
