package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFiserXlsx implements ImportStrategy {
    @Override
    public List<Student> importa(String numeFisier) {
        List<Student> studenti = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(numeFisier);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    int nrmatricol = (int) row.getCell(0).getNumericCellValue();
                    String prenume = row.getCell(1).getStringCellValue();
                    String nume = row.getCell(2).getStringCellValue();
                    String formatie = row.getCell(3).getStringCellValue();
                    double nota = row.getCell(4).getNumericCellValue();

                    studenti.add(new Student(nrmatricol, prenume, nume, formatie, nota));
                }
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }

        return studenti;
    }
}
