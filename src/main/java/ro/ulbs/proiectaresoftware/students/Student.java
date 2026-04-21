package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    private final int nrmatricol;
    private final String prenume;
    private final String nume;
    private final String formatieDeStudiu;
    private final double nota;

    Student(int nrmatricol, String prenume, String nume, String formatieDeStudiu) {
        this(nrmatricol, prenume, nume, formatieDeStudiu, 0.0);
    }

    Student(int nrmatricol, String prenume, String nume, String formatieDeStudiu, double nota) {
        this.nrmatricol = nrmatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = nota;
    }

    public int getNrmatricol() {
        return this.nrmatricol;
    }

    public String getPrenume() {
        return this.prenume;
    }

    public String getNume() {
        return this.nume;
    }

    public String getFormatieDeStudiu() {
        return this.formatieDeStudiu;
    }

    public double getNota() {
        return this.nota;
    }

    public Student cuNota(double notaNoua) {
        return new Student(nrmatricol, prenume, nume, formatieDeStudiu, notaNoua);
    }

    public Student cuFormatieDeStudiu(String formatieNoua) {
        return new Student(nrmatricol, prenume, nume, formatieNoua, nota);
    }

    @Override
    public String toString() {
        return String.format("%14d %20s %8s %5.2f\"", this.nrmatricol, this.prenume + " " + this.nume, this.formatieDeStudiu, this.nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return prenume.equals(s.prenume)
                && nume.equals(s.nume)
                && formatieDeStudiu.equals(s.formatieDeStudiu)
                && nrmatricol == s.nrmatricol
                && Double.compare(s.nota, nota) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu, nrmatricol, nota);
    }
}