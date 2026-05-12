package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StudentiInFisierXlsx implements IStudentiExport {
    private final String numeFisier;

    public StudentiInFisierXlsx(String numeFisier) {
        this.numeFisier = numeFisier;
    }

    @Override
    public void doExport(List<Student> studenti) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Studenti");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("nrmatricol");
            header.createCell(1).setCellValue("prenume");
            header.createCell(2).setCellValue("nume");
            header.createCell(3).setCellValue("formatie");
            header.createCell(4).setCellValue("nota");

            for (int i = 0; i < studenti.size(); i++) {
                Student s = studenti.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(s.getNrmatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            FileOutputStream file = new FileOutputStream(numeFisier);
            workbook.write(file);
            workbook.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}
