
public class UndergraduateStudent extends Student {

    private double assignmentGrade;
    private double examGrade;

    public UndergraduateStudent(int id, String name,
                                String email, String program,
                                double gpa,
                                double assignmentGrade,
                                double examGrade) {

        super(id, name, email, program, gpa);
        this.assignmentGrade = assignmentGrade;
        this.examGrade = examGrade;
    }

    public double getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(double assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(double examGrade) {
        this.examGrade = examGrade;
    }

    @Override
    public double calculateGPA() {
        double finalGrade = assignmentGrade * 0.4
                + examGrade * 0.6;

        return finalGrade / 100 * 4.0;
    }
}
