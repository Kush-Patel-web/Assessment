package assessments;

public abstract class Assessment {
    private String title;
    private double maxMark;
    private boolean aiPermitted;

    public Assessment(String title,double maxMark,boolean aiPermitted) {
        this.title = title;
        this.maxMark = maxMark;
        this.aiPermitted = aiPermitted;
    }

    public String getTitle() {
        return title;
    }

    public double getMaxMark() {
        return maxMark;
    }

    public boolean isAiPermitted() {
        return aiPermitted;
    }


    public abstract double getFinalMark();

    public abstract void printDetails();

}