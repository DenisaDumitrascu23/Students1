package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class TimeExecution implements ITimeExecution {
    protected IStudentiExport exporter;

    public TimeExecution(IStudentiExport exporter) {
        this.exporter = exporter;
    }

    @Override
    public long executionTime(List<Student> studenti) {
        long start = System.currentTimeMillis();
        exporter.doExport(studenti);
        long end = System.currentTimeMillis();
        return end - start;
    }
}

