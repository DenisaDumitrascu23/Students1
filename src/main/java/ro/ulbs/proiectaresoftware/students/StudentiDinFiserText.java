package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFiserText implements ImportStrategy {
    @Override
    public List<Student> importa(String numeFisier) {
        List<Student> studenti = new ArrayList<>();

        try {
            // Citim toate liniile din fisier
            List<String> linii = Files.readAllLines(Paths.get(numeFisier));

            // Parcurgem fiecare linie
            for (String linie : linii) {
                // Separam datele cu virgula
                String[] parti = linie.split(",");

                // Construim un Student din parti
                if (parti.length >= 5) {
                    Student s = new Student(
                            Integer.parseInt(parti[0]),
                            parti[1],
                            parti[2],
                            parti[3],
                            Double.parseDouble(parti[4]) //linia asta face conversia de la String la double pentru nota
                    );
                    studenti.add(s);
                }
            }

            System.out.println("OK: " + numeFisier + " (" + studenti.size() + " studenti)");
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        return studenti;
    }
}

