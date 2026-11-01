
public class GraduateStudent extends Student {

    private double researchGrade;
    private double examGrade;

    public GraduateStudent(int id, String name,
                           String email, String program,
                           double gpa,
                           double researchGrade,
                           double examGrade) {

        super(id, name, email, program, gpa);
        this.researchGrade = researchGrade;
        this.examGrade = examGrade;
    }

    public double getResearchGrade() {
        return researchGrade;
    }

    public void setResearchGrade(double researchGrade) {
        this.researchGrade = researchGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(double examGrade) {
        this.examGrade = examGrade;
    }

    @Override
    public double calculateGPA() {
        double finalGrade = researchGrade * 0.7
                + examGrade * 0.3;

        return finalGrade / 100 * 4.0;
    }
}
