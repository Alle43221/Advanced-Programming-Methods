using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public class AngajatRepository:FileRepository<Angajat, string>
{
    public AngajatRepository(string filename):
        base(filename, LineToEntity, EntityToLine) {}
    

    public static Angajat LineToEntity(string line)
    {
        string[] parts = line.Split(',');
        string id=parts[0];
        string name = parts[1];
        KnowledgeLevel level=(KnowledgeLevel)Enum.Parse(typeof(KnowledgeLevel), parts[2]);
        float venital = float.Parse(parts[3]);

        return new Angajat
        {
            Id = id, Nivel = level, VenitPeOra = venital, Name = name
        };
    }

    public static string EntityToLine(Angajat entity)
    {
        return entity.Id + "," + entity.Name+  "," + entity.Nivel + "," + entity.VenitPeOra;
    }
}