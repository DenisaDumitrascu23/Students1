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

            // sortare dupa nume
            studenti.sort(Comparator.comparing(Student::getNume));

            // afisare
            System.out.printf("%14s %20s %16s%n", "numar matricol", "prenume nume", "formatie");
            for (Student s : studenti) {
                System.out.println(s);
            }

            // scriere in fisier
            List<String> out = new ArrayList<>();
            for (Student s : studenti) {
                out.add(s.toString());
            }

            Files.write(Paths.get("studenti_out.txt"), out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}