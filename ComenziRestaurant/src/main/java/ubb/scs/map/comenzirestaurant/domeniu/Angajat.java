package ubb.scs.map.comenzirestaurant.domeniu;


public class Angajat extends Entity<Long>{
    private String nume;
    public Angajat(String nume){
        this.nume=nume;
    }
    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                '}';
    }
}
