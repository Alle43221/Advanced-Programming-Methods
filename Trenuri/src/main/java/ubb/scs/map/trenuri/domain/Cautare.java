package ubb.scs.map.trenuri.domain;

import java.util.Objects;

public class Cautare {
    private String idOm;
    private String idDeparture;
    private String idDestination;

    public Cautare(String idOm, String idDeparture, String idDestination) {
        this.idOm=idOm;
        this.idDeparture=idDeparture;
        this.idDestination=idDestination;
    }

    public String getIdOm() {
        return idOm;
    }

    public void setIdOm(String idOm) {
        this.idOm = idOm;
    }

    public String getIdDeparture() {
        return idDeparture;
    }

    public void setIdDeparture(String idDeparture) {
        this.idDeparture = idDeparture;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cautare cautari = (Cautare) o;
        return idOm.equals(cautari.idOm) && idDeparture.equals(cautari.idDeparture) && idDestination.equals(cautari.idDestination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOm, idDeparture, idDestination);
    }
}
