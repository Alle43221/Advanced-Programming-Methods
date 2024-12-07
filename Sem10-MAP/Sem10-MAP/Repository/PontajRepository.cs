using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public class PontajRepository:FileRepository<Pontaj, Tuple<string, string>>
{
    public PontajRepository(string filename):
        base(filename, LineToEntity, EntityToLine) {}
    

    public static Pontaj LineToEntity(string line)
    {
        string[] parts = line.Split(',');
        Pontaj entity = new Pontaj();
        string idAngajat=parts[0];
        string idSarcina=parts[1];
        string name = parts[1];
        float venital = float.Parse(parts[2]);
        KnowledgeLevel level=(KnowledgeLevel)Enum.Parse(typeof(KnowledgeLevel), parts[3]);
        return new Pontaj
        {
           
        };
    }

    public static string EntityToLine(Pontaj entity)
    {
        return entity.Id + ","; 
    }
}