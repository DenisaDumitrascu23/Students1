package ro.ulbs.proiectaresoftware.students;

import java.util.List;

// Decorator care extinde TimeExecution și implementează IStudentiExport
public class TimeExecutionDecorator extends TimeExecution implements IStudentiExport {

    public TimeExecutionDecorator(IStudentiExport exporter, List<Student> studenti) {
        super(exporter);
    }

    @Override
    public void doExport(List<Student> studenti) {
        long time = executionTime(studenti);
        System.out.println("Timp executie: " + time + " ms");
    }
}

