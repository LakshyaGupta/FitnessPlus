public class Dashboard {

    private int id;
    private double avg_exercise;
    private double avg_food;
    private double avg_sleep;
    private double diff_exercise;
    private double diff_food;
    private double diff_sleep;

    public Dashboard(int id, double avg_exercise, double avg_food, double avg_sleep, double diff_exercise, double diff_food, double diff_sleep) {
        this.id = id;
        this.avg_exercise = avg_exercise;
        this.avg_exercise = avg_exercise;
        this.avg_sleep = avg_sleep;
        this.diff_exercise = diff_exercise;
        this.diff_food = diff_food;
        this.diff_sleep = diff_sleep;
    }

    public int getId() { return id; }

    public String toString() { return id + ""; }

    public double getAvg_exercise() {
        return avg_exercise;
    }

    public void setAvg_exercise(double avg_exercise) {
        this.avg_exercise = avg_exercise;
    }

    public double getAvg_food() {
        return avg_food;
    }

    public void setAvg_food(double avg_food) {
        this.avg_food = avg_food;
    }

    public double getAvg_sleep() {
        return avg_sleep;
    }

    public void setAvg_sleep(double avg_sleep) {
        this.avg_sleep = avg_sleep;
    }

    public double getDiff_exercise() {
        return diff_exercise;
    }

    public void setDiff_exercise(double diff_exercise) {
        this.diff_exercise = diff_exercise;
    }

    public double getDiff_food() {
        return diff_food;
    }

    public void setDiff_food(double diff_food) {
        this.diff_food = diff_food;
    }

    public double getDiff_sleep() {
        return diff_sleep;
    }

    public void setDiff_sleep(double diff_sleep) {
        this.diff_sleep = diff_sleep;
    }
}

