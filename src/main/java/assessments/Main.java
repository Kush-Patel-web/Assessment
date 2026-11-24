package assessments;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {


    private static final String[] ASSESSMENTS = {
            AssessmentFeed.PROGRAMMING_TEST, AssessmentFeed.AI_PROJECT, AssessmentFeed.DESIGN_VIVA};

    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        for (String cid : AssessmentFeed.getCIDs()) {
            students.add(new Student(cid));

        }


        printReport();


        JFrame frame = new JFrame("Software Engineering Module Results");
        frame.setSize(1300, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(students.size() + 1, ASSESSMENTS.length + 4));


        addCell(frame, "<html><b>Name</b></html>", Color.LIGHT_GRAY);
        addCell(frame, "<html><b>CID</b></html>", Color.LIGHT_GRAY);
        addCell(frame, "<html><b>Type</b></html>", Color.LIGHT_GRAY);
        for (String title : ASSESSMENTS) {
            addCell(frame, "<html><b>" + title + "</b></html>", Color.LIGHT_GRAY);
        }
        addCell(frame, "<html><b>Module mark</b></html>", Color.LIGHT_GRAY);


        for (Student s : students) {
            Color colour = Color.WHITE;
            if (s.isOverReliantOnAI()) {
                colour = Color.RED;
            }

            addCell(frame, s.getName(), colour);
            addCell(frame, s.getCid(), colour);
            addCell(frame, s.getType(), colour);
            for (String title : ASSESSMENTS) {
                Assessment a = s.getAssessment(title);
                if (a == null) {
                    addCell(frame, "N/A", colour);
                } else {
                    addCell(frame, String.format("%.2f", a.getFinalMark()) + "%", colour);
                }
            }
            addCell(frame, String.format("%.2f", s.getModuleMark()) + "%", colour);
        }


        frame.setVisible(true);
    }


    private static void addCell(JFrame frame, String text, Color colour) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(colour);
        panel.add(new JLabel(text));
        frame.add(panel);
    }


    private static void printReport() {
        System.out.println("Module results report");
        for (Student s : students) {
            System.out.println(s.getName() + " (CID " + s.getCid() + ", " + s.getType() + ")");

            for (String title : ASSESSMENTS) {
                Assessment a = s.getAssessment(title);
                if (a == null) {
                    System.out.println("  " + title + ": N/A");
                } else {
                    System.out.println("  " + title + ": " + String.format("%.2f", a.getFinalMark()) + "%");
                    a.printDetails();
                }
            }

            System.out.println("Module mark: " + String.format("%.2f", s.getModuleMark()) + "%");
            if (s.isOverReliantOnAI()) {
                System.out.println("AI discrepancy: YES");
            } else {
                System.out.println("AI discrepancy: No");
            }
            System.out.println();
        }

    }
}
