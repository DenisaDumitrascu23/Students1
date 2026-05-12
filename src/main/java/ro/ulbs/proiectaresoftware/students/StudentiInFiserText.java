package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentiInFiserText implements IStudentiExport {
    private final String numeFisier;

    public StudentiInFiserText(String numeFisier) {
        this.numeFisier = numeFisier;
    }

    @Override
    public void doExport(List<Student> studenti) {
        List<String> linii = new ArrayList<>();

        for (Student s : studenti) {
            linii.add(s.toString());
        }

        try {
            Files.write(Paths.get(numeFisier), linii);
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}
