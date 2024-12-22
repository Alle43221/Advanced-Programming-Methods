using Lab10_MAP.Domain;

namespace Lab10_MAP.Domain;

public class Elev: Entity<long>
{
    public string Nume { set; get; }
    public string Scoala { set; get; }

    public Elev(long id, string nume, string scoala)
    {
        Id = id;
        Nume = nume;
        Scoala = scoala;
    }
    
    public override string ToString()
    {
        return Id + " "+ Nume +" "+ Scoala;
    }
}