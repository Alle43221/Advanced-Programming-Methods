namespace Lab10_MAP.Domain;

public class Echipa:Entity<long>
{
    public string Nume { get; set; }

    public Echipa(long id, string nume)
    {
        Id = id;
        Nume = nume;
    }
    
    public override string ToString()
    {
        return Id + " "+ Nume;
    }
    
}