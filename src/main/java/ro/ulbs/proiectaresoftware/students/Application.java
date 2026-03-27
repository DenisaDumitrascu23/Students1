package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Application {

    public static void main(String[] args) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("studenti_in.txt"));
            Map<Integer, Student> tineri = new HashMap<>();

            for (String line : lines) {
                String[] parts = line.split(",");

                Student s = new Student(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3]
                );

                tineri.put(s.getNrmatricol(), s);
            }

            List<String> noteLines = Files.readAllLines(Paths.get("note_anon.txt"));

            for (String line : noteLines) {
                String[] parts = line.split(",");

                int nrMat = Integer.parseInt(parts[0]);
                double nota = Double.parseDouble(parts[1]);

                Student s = tineri.get(nrMat);

                if (s != null) {
                    s.setNota(nota);
                }
            }

            System.out.println("Studenti cu note:");
            for (Student s : tineri.values()) {
                System.out.println(s);
            }

            float notaM = gasesteNota("Bianca", "Popescu", tineri);
            float notaN = gasesteNota("Ioan", "Popa", tineri);

            System.out.println("Nota Bianca Popescu: " + notaM);
            System.out.println("Nota Ioan Popa: " + notaN);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float gasesteNota(String prenume, String nume, Map<Integer, Student> tineri) {

        Map<String, Student> map = new HashMap<>();

        for (Student s : tineri.values()) {
            String cheie = s.getPrenume() + "-" + s.getNume();
            map.put(cheie, s);
        }

        String cheieCautata = prenume + "-" + nume;

        Student s = map.get(cheieCautata);

        if (s != null) {
            return (float) s.getNota();
        }

        return 0.0f;
    }
}