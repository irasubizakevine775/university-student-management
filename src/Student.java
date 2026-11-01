
public class Student extends Person {

    private String program;
    private double gpa;

    public Student(int id, String name, String email,
                   String program, double gpa) {

        super(id, name, email);
        this.program = program;
        this.gpa = gpa;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return getId() + "  " + getName()
                + "  " + getEmail()
                + "  " + program
                + "  GPA: " + gpa;
    }
}
