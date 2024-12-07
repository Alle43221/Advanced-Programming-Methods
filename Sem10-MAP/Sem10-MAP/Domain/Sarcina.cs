namespace Sem10_MAP.Domain;


public enum Dificultate
{
    Usoara, Grea, Medie
}

public class Sarcina:Entity<string>
{
    public Dificultate Dificultate { get; set; }
    public int NrOreEstimate { get; set; }

    public override string ToString()
    {
        return Id + " " + Dificultate + " " + NrOreEstimate;
    }
}