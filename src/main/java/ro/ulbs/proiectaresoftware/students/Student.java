package ro.ulbs.proiectaresoftware.students;
import java.util.Objects;
public class Student {
    int nrmatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;
    double nota;

    Student(int nrmatricol, String prenume, String nume, String formatieDeStudiu){
        this.nrmatricol = nrmatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = 0;
    }
    public int getNrmatricol(){
        return this.nrmatricol;
    }
    public String getPrenume(){
        return this.prenume;
    }
    public String getNume(){
        return this.nume;
    }
    public String getFormatieDeStudiu(){
        return this.formatieDeStudiu;
    }
    public double getNota(){ return this.nota; }

    // setter pentru nota
    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString(){
        return String.format("%14d %20s %8s",this.nrmatricol, this.prenume+" " + this.nume , this.formatieDeStudiu , this.nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return prenume.equals(s.prenume) && nume.equals(s.nume) && formatieDeStudiu.equals(s.formatieDeStudiu) && (nrmatricol == s.nrmatricol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu, nrmatricol);
    }
}