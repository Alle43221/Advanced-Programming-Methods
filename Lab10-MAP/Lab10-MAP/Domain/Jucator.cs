namespace Lab10_MAP.Domain;

public class Jucator(long id, string nume, string scoala, long echipa)
    : Elev(id, nume, scoala)
{
    public long Echipa { get; set; } = echipa;

    public override  string ToString()
    {
        return base.ToString() + " " +  Echipa;
    }
}