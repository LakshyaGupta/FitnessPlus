public class Sleep {

    private int id;
    private double hours;

    public Sleep(int id, double hours) {
        this.id = id;
        this.hours = hours;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getHours() { return hours; }

    public void setHours(double hours) { this.hours = hours; }

    public String toString() { return id + " - " + hours; }
}

