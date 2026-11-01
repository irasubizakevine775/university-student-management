

public class Student extends Person {

    private String program;
    private double gpa;

    // Constructor
    public Student(int id, String name, String email,
                   String program, double gpa) {
        super(id, name, email);
        this.program = program;
        setGpa(gpa);
    }

    // Getters
    public String getProgram() {
        return program;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setProgram(String program) {
        this.program = program;
    }

    public void setGpa(double gpa) {
        if (!Double.isFinite(gpa) || gpa < 0 || gpa > 4.0) {
            throw new IllegalArgumentException(
                    "GPA must be between 0.0 and 4.0"
            );
        }
        this.gpa = gpa;
    }

    // GPA method to be overridden
    public double calculateGPA() {
        return getGpa();
    }

    // Display student information
    @Override
    public String toString() {
        return "ID: " + getId()
                + " Name: " + getName()
                + " Email: " + getEmail()
                + " Program: " + program
                + " GPA: " + calculateGPA();
    }
}
