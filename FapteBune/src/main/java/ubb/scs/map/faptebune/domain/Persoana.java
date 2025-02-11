package ubb.scs.map.faptebune.domain;

public class Persoana extends Entity<Long> {

    private String nume;
    private String prenume;
    private String username;
    private String password;
    private String oras;
    private String strada;
    private String numarStrada;
    private String telefon;

    public Persoana() {}

    public Persoana(Long id, String nume, String prenume, String username, String password, String oras, String strada, String numarStrada, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.password = password;
        this.oras = oras;
        this.strada = strada;
        this.numarStrada = numarStrada;
        this.telefon = telefon;
        this.setId(id);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumarStrada() {
        return numarStrada;
    }

    public void setNumarStrada(String numarStrada) {
        this.numarStrada = numarStrada;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return getId() + "," + nume + "," +prenume +
                ","+username + "," + password + ","+oras + "," +strada + "," +numarStrada + "," +telefon;
    }
}
