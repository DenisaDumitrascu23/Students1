package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public interface ImportStrategy {
    List<Student> importa(String numeFisier);
}

