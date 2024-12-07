namespace Sem10_MAP.Domain;

public enum KnowledgeLevel
{
    Junior, Mid, Senior
}
public class Angajat:Entity<string>
{
    public string Name { get; set; }
    public float VenitPeOra { get; set; }
    public KnowledgeLevel Nivel { get; set; }

    public override string ToString()
    {
        return Id + " " + Name + " " + Nivel + " " + VenitPeOra;
    }
}