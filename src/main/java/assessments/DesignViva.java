package assessments;

public class DesignViva extends Assessment {

    private static final double DESIGN_WEIGHT = 0.5;

    private static final double EXPLANATION_WEIGHT = 0.5;

    private double designMark;

    private double explanationMark;

    public DesignViva(String cid) {

        super(AssessmentFeed.DESIGN_VIVA,100, false);

        designMark = AssessmentFeed.getResult(cid,AssessmentFeed.DESIGN_VIVA,  AssessmentFeed.DESIGN_MARK);

        explanationMark = AssessmentFeed.getResult( cid, AssessmentFeed.DESIGN_VIVA, AssessmentFeed.EXPLANATION_MARK);
    }

    @Override
    public double getFinalMark() {
        return (DESIGN_WEIGHT * designMark + EXPLANATION_WEIGHT * explanationMark);
    }

    @Override
    public void printDetails() {

        System.out.println(AssessmentFeed.DESIGN_MARK + ": " + String.format("%.2f", designMark) + "%");
        System.out.println(AssessmentFeed.EXPLANATION_MARK + ": " + String.format("%.2f", explanationMark) + "%");
    }
}