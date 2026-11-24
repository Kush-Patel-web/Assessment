package assessments;
import java.util.ArrayList;

public class Student {

    private static final double UG_TEST_WEIGHT = 0.30;

    private static final double UG_PROJECT_WEIGHT = 0.70;

    private static final double MSC_TEST_WEIGHT = 0.25;

    private static final double MSC_PROJECT_WEIGHT = 0.45;

    private static final double MSC_VIVA_WEIGHT = 0.30;

    private static final double AI_GAP_LIMIT = 20.0;

    private String name;
    private String cid;
    private String type;
    private ArrayList<Assessment> assessments = new ArrayList<>();


    public Student(String cid) {
        this.cid = cid;
        this.name = AssessmentFeed.getStudentName(cid);

        this.type = AssessmentFeed.getStudentType(cid);

        assessments.add(new ProgrammingTest(cid));

        assessments.add(new AIProject(cid));

        if (type.equals("MSc")) {
            assessments.add(new DesignViva(cid));

        }

    }

    public String getName() {
        return name;
    }

    public String getCid() {
        return cid;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    public Assessment getAssessment(String title) {
        for (Assessment a : assessments) {

            if (a.getTitle().equals(title)) {
                return a;
            }
        }
        return null;
    }

    public double getWeighting( String title) {
        if (type.equals("UG")) {
            if (title.equals(AssessmentFeed.PROGRAMMING_TEST)) {
                return UG_TEST_WEIGHT;
            }
            if (title.equals(AssessmentFeed.AI_PROJECT)) {
                return UG_PROJECT_WEIGHT;
            }
        }

        else {
            if (title.equals(AssessmentFeed.PROGRAMMING_TEST)) {
                return MSC_TEST_WEIGHT;
            }
            if (title.equals(AssessmentFeed.AI_PROJECT)) {
                return MSC_PROJECT_WEIGHT;
            }
            if (title.equals(AssessmentFeed.DESIGN_VIVA)) {
                return MSC_VIVA_WEIGHT;
            }
        }
        return 0;
    }

    public double getModuleMark() {
        double total = 0;

        for (Assessment a : assessments) {

            total +=  a.getFinalMark() * getWeighting(a.getTitle());
        }

        return total;

    }
    public boolean isOverReliantOnAI(){
        double aiTotal = 0;

        double nonAiTotal = 0;

        int aiCount = 0;

        int nonAiCount = 0;

        for (Assessment a : assessments) {
            if (a.isAiPermitted()) {

                aiTotal += a.getFinalMark();
                aiCount++;
            } else {
                nonAiTotal += a.getFinalMark();
                nonAiCount++;
            }
        }


        if (aiCount == 0 || nonAiCount == 0) {
            return false;

        }


        double aiAverage = aiTotal / aiCount;
        double nonAiAverage = nonAiTotal / nonAiCount;

        return aiAverage - nonAiAverage >= AI_GAP_LIMIT;
    }













}





