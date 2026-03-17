package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Application {

    public static void main(String[] args) {

        try {
            // citire din fisier
            List<String> lines = Files.readAllLines(Paths.get("studenti_in.txt"));

            List<Student> studenti = new ArrayList<>();

            // transformare in obiecte Student
            for (String line : lines) {
                String[] parts = line.split(",");

                Student s = new Student(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3]
                );

                studenti.add(s);
            }

            studenti.sort(
                    Comparator.comparing(Student::getFormatieDeStudiu)
                            .thenComparing(Student::getNume)
            );

            // scriere in fisier (cerinta 3.5.3)
            List<String> out = new ArrayList<>();
            for (Student s : studenti) {
                out.add(s.toString());
            }

            Files.write(Paths.get("studenti_out_sorted.txt"), out);
            //Files.write(Paths.get("studenti_out.txt"), out);

            // afisare
            System.out.println("Lista sortata:");
            for (Student s : studenti) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}