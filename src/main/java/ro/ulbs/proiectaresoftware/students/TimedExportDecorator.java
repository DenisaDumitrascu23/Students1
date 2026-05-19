package ro.ulbs.proiectaresoftware.students;

import java.util.List;

// Decorator simplu care măsoară timpul de execuție al unei strategii de export
public class TimedExportDecorator implements IStudentiExport {
    private final IStudentiExport delegate;

    public TimedExportDecorator(IStudentiExport delegate) {
        this.delegate = delegate;
    }

    @Override
    public void doExport(List<Student> studenti) {
        long start = System.currentTimeMillis();
        // apelăm strategia originală (nu o modificăm)
        delegate.doExport(studenti);
        long end = System.currentTimeMillis();
        System.out.println("Timp executie export (ms): " + (end - start));
    }
}

