package assessments;

public class ProgrammingTest extends Assessment {
    private static final double CAP = 40.0;

    private double programMark;

    private boolean explained;

    public ProgrammingTest(String cid) {
        super( AssessmentFeed.PROGRAMMING_TEST,100, false);

        programMark = AssessmentFeed.getResult(cid,AssessmentFeed.PROGRAMMING_TEST,AssessmentFeed.PROGRAM_MARK);

        double explainedValue = AssessmentFeed.getResult( cid,AssessmentFeed.PROGRAMMING_TEST,AssessmentFeed.EXPLAINED_SUCCESSFULLY);

        explained = ( explainedValue == 1.0);
    }

    @Override

    public double getFinalMark() {
        if ( !explained &&  (programMark > CAP)) {
            return CAP;
        }

        else {
            return programMark;
        }
    }

    @Override
    public void printDetails() {
        System.out.println("Program mark: " +String.format("%.2f", programMark) + "%");
        if (explained) {
            System.out.println(AssessmentFeed.EXPLAINED_SUCCESSFULLY + ": Yes");
        }

        else {
            System.out.println(AssessmentFeed.EXPLAINED_SUCCESSFULLY +  ": No");
        }
    }
}