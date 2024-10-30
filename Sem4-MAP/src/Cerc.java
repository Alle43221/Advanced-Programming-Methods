public class Cerc {
    private double raza;

    public Cerc(double raza) {
        this.raza = raza;
    }
    public double getRaza() {
        return raza;
    }

    @Override
    public String toString() {
        return "{Raza: " + raza + "}";
    }
}
