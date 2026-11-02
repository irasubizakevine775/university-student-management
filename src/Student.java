
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

    public double getGpa() {
        return gpa;
    }


    public void setProgram(String program) {
        this.program = program;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }


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
