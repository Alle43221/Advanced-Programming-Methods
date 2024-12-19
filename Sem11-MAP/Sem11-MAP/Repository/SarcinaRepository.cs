using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public class SarcinaRepository:FileRepository<Sarcina, string>
{
    public SarcinaRepository(string filename):
        base(filename, LineToEntity, EntityToLine) {}
    

    public static Sarcina LineToEntity(string line)
    {
        string[] parts = line.Split(',');
        string id=parts[0];
        Dificultate dificultate = (Dificultate)Enum.Parse(typeof(Dificultate), parts[1]);
        int nrOres=int.Parse(parts[2]);
        
        return new Sarcina
        {
            Id = id, NrOreEstimate = nrOres, Dificultate = dificultate
        };
    }

    public static string EntityToLine(Sarcina entity)
    {
        return entity.Id + "," + entity.Dificultate+ "," + entity.NrOreEstimate;
    }
}