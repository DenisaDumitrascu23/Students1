package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {
    public static void main(String... args) {
        List<Student> studenti = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10),
                new Student(1029, "Maria", "Pana", "TI131/", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        Exporter exporter = new Exporter();

        IStudentiExport strategyConsole = new StudentiInConsola();
        exporter.startExport(strategyConsole, studenti);

        IStudentiExport strategyFisierText = new StudentiInFiserText("studentiStrategyText.txt");
        exporter.startExport(strategyFisierText, studenti);

        IStudentiExport strategyFisierExcel = new StudentiInFisierXlsx("studentiStrategyExcel.xlsx");
        exporter.startExport(strategyFisierExcel, studenti);

        ImportStrategy importText = new StudentiDinFiserText();
        List<Student> studentiDinText = importText.importa("studentiStrategyText.txt");
        System.out.println("\nCititi din text:");
        for (Student s : studentiDinText) {
            System.out.println(s);
        }

        ImportStrategy importXlsx = new StudentiDinFiserXlsx();
        List<Student> studentiDinXlsx = importXlsx.importa("studentiStrategyExcel.xlsx");
        System.out.println("\nCititi din XLSX:");
        for (Student s : studentiDinXlsx) {
            System.out.println(s);
        }
    }
}
