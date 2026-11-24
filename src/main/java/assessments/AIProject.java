package assessments;

public class AIProject extends Assessment {
    private static final double FUNCTIONALITY_WEIGHT = 0.4;
    private static final double VERIFICATION_WEIGHT = 0.6;

    private double functionalityMark;

    private double verificationMark;

    public AIProject(String cid ) {

        super(AssessmentFeed.AI_PROJECT,100, true);
        functionalityMark = AssessmentFeed.getResult( cid, AssessmentFeed.AI_PROJECT,AssessmentFeed.FUNCTIONALITY_MARK);

        verificationMark = AssessmentFeed.getResult(cid,AssessmentFeed.AI_PROJECT, AssessmentFeed.VERIFICATION_MARK);
    }


    @Override
    public double getFinalMark() {
        return (FUNCTIONALITY_WEIGHT * functionalityMark + VERIFICATION_WEIGHT * verificationMark);
    }

    @Override
    public void printDetails() {
        System.out.println(AssessmentFeed.FUNCTIONALITY_MARK + ": " +String.format("%.2f", functionalityMark) + "%");
        System.out.println(AssessmentFeed.VERIFICATION_MARK + ": " +String.format("%.2f", verificationMark) + "%");
    }
}
