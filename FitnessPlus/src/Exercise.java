public class Exercise {

    private int id;
    private String name;
    private double hours;

    public Exercise(int id, String name, double hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getHours() { return hours; }

    public void setHours(double hours) { this.hours = hours; }

    public String toString() { return id + " - " + hours; }
}

