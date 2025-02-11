package ubb.scs.map.template.domain;

public class Request extends Entity<Integer> {
    private Integer idSursa;
    private Integer idAnimal;

    public Request(Integer Id, Integer idSursa, Integer idAnimal) {
        this.idSursa = idSursa;
        this.idAnimal = idAnimal;
        super.setId(Id);
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }


    public Integer getIdSursa() {
        return idSursa;
    }

}
