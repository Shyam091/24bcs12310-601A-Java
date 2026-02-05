import java.util.Scanner;

interface ModerationRule {
    int apply(int marks);
}

class AttendanceModeration implements ModerationRule {
    public int apply(int marks) {
        System.out.println("Attendance moderation applied (+5)");
        return marks + 5;
        
    }
}

class DifficultyModeration implements ModerationRule {
    public int apply(int marks) {
        System.out.println("Difficulty moderation applied (+10)");
        return marks + 10;
    }
}

class ManualModeration implements ModerationRule {
    public int apply(int marks) {
        System.out.println("Manual moderation applied (+3)");
        return marks + 3;
    }
}
abstract class EvaluationEngine {

    protected ModerationRule moderationRule;
    protected Scanner sc = new Scanner(System.in);

    public EvaluationEngine(ModerationRule moderationRule) {
        this.moderationRule = moderationRule;
    }
    public final void evaluate() {
        int theory = collectTheoryMarks();
        int lab = collectLabMarks();

        int total = calculateTotal(theory, lab);
        total = moderationRule.apply(total);

        generateGrade(total);
        System.out.println(" ");
    }

    protected abstract int collectTheoryMarks();
    protected abstract int collectLabMarks();
    protected abstract int calculateTotal(int theory, int lab);
    protected abstract void generateGrade(int total);
}
class BTechEvaluation extends EvaluationEngine {

    public BTechEvaluation(ModerationRule rule) {
        super(rule);
    }

    protected int collectTheoryMarks() {
        System.out.print("Enter B.Tech Theory Marks: ");
        return sc.nextInt();
    }

    protected int collectLabMarks() {
        System.out.print("Enter B.Tech Lab Marks: ");
        return sc.nextInt();
    }

    protected int calculateTotal(int theory, int lab) {
        return theory + lab;
    }

    protected void generateGrade(int total) {
        System.out.println("B.Tech Total Marks: " + total);
        System.out.println("B.Tech Grade: " + (total >= 80 ? "A" : "B"));
    }
}

class MCAEvaluation extends EvaluationEngine {

    public MCAEvaluation(ModerationRule rule) {
        super(rule);
    }

    protected int collectTheoryMarks() {
        System.out.print("Enter MCA Theory Marks: ");
        return sc.nextInt();
    }

    protected int collectLabMarks() {
        System.out.print("Enter MCA Lab Marks: ");
        return sc.nextInt();
    }

    protected int calculateTotal(int theory, int lab) {
        return (theory * 2 + lab) / 3;
    }

    protected void generateGrade(int total) {
        System.out.println("MCA Total Marks: " + total);
        System.out.println("MCA Grade: " + (total >= 75 ? "A" : "B"));
    }
}

class PhDEvaluation extends EvaluationEngine {

    public PhDEvaluation(ModerationRule rule) {
        super(rule);
    }

    protected int collectTheoryMarks() {
        System.out.print("Enter PhD Theory Marks: ");
        return sc.nextInt();
    }

    protected int collectLabMarks() {
        System.out.print("Enter PhD Lab Marks: ");
        return sc.nextInt();
    }

    protected int calculateTotal(int theory, int lab) {
        return (theory + lab) / 2;
    }

    protected void generateGrade(int total) {
        System.out.println("PhD Total Marks: " + total);
        System.out.println("PhD Result: " + (total >= 85 ? "PASS" : "FAIL"));
    }
}

public class MainApp{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Program:");
        System.out.println("1. B.Tech");
        System.out.println("2. MCA");
        System.out.println("3. PhD");
        int programChoice = sc.nextInt();

        System.out.println("Choose Moderation Rule:");
        System.out.println("1. Attendance Moderation");
        System.out.println("2. Difficulty Moderation");
        System.out.println("3. Manual Moderation");
        int modChoice = sc.nextInt();

        ModerationRule rule;
        if (modChoice == 1)
            rule = new AttendanceModeration();
        else if (modChoice == 2)
            rule = new DifficultyModeration();
        else
            rule = new ManualModeration();

        EvaluationEngine engine;

        if (programChoice == 1)
            engine = new BTechEvaluation(rule);
        else if (programChoice == 2)
            engine = new MCAEvaluation(rule);
        else
            engine = new PhDEvaluation(rule);

        engine.evaluate();
    }
}