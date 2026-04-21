package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    tineri.put(nrMat, s.cuNota(nota));
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

            List<Student> studentiSortati = new ArrayList<>(tineri.values());
            studentiSortati.sort((s1, s2) -> Integer.compare(s1.getNrmatricol(), s2.getNrmatricol()));

            List<List<Student>> formatii = imparteInDouaFormatii(studentiSortati, "FORM_1", "FORM_2");
            List<Student> listaNoua = new ArrayList<>();
            listaNoua.addAll(formatii.get(0));
            listaNoua.addAll(formatii.get(1));

            if (!formatii.get(0).isEmpty()) {
                int nrMatricolMutat = formatii.get(0).get(0).getNrmatricol();
                listaNoua = mutaStudentInAltaFormatie(listaNoua, nrMatricolMutat, "FORM_2");
            }

            System.out.println("\nLista noua dupa impartire si mutare student:");
            for (Student s : listaNoua) {
                System.out.println(s);
            }

            List<StudentBursier> bursieri = new ArrayList<>();

            bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
            bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
            bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
            bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));

            System.out.println("\nBursieri:");
            for (StudentBursier b : bursieri) {
                System.out.println(b);
            }

            salveazaInFisier("bursieri_out.txt", bursieri);

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

    public static List<List<Student>> imparteInDouaFormatii(List<Student> studenti, String formatie1, String formatie2) {
        int dimensiunePrimaFormatie = (studenti.size() + 1) / 2;
        List<Student> primaFormatie = new ArrayList<>();
        List<Student> aDouaFormatie = new ArrayList<>();

        for (int i = 0; i < studenti.size(); i++) {
            Student studentActualizat = (i < dimensiunePrimaFormatie)
                    ? studenti.get(i).cuFormatieDeStudiu(formatie1)
                    : studenti.get(i).cuFormatieDeStudiu(formatie2);

            if (i < dimensiunePrimaFormatie) {
                primaFormatie.add(studentActualizat);
            } else {
                aDouaFormatie.add(studentActualizat);
            }
        }

        List<List<Student>> rezultat = new ArrayList<>();
        rezultat.add(primaFormatie);
        rezultat.add(aDouaFormatie);
        return rezultat;
    }

    public static List<Student> mutaStudentInAltaFormatie(List<Student> studenti, int nrMatricol, String formatieNoua) {
        List<Student> rezultat = new ArrayList<>(studenti.size());
        boolean studentGasit = false;

        for (Student student : studenti) {
            if (student.getNrmatricol() == nrMatricol) {
                rezultat.add(student.cuFormatieDeStudiu(formatieNoua));
                studentGasit = true;
            } else {
                rezultat.add(student);
            }
        }

        if (!studentGasit) {
            throw new IllegalArgumentException("Studentul cu nr. matricol " + nrMatricol + " nu exista in lista.");
        }

        return rezultat;
    }

    public static void salveazaInFisier(String numeFisier, Collection<? extends Student> colectie) {

        List<String> linii = new ArrayList<>();

        for (Student s : colectie) {
            linii.add(s.toString());
        }

        try {
            Files.write(Paths.get(numeFisier), linii);
            System.out.println("Fisier salvat: " + numeFisier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

