using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public class PontajRepository:FileRepository<Pontaj, Tuple<string, string>>
{
    private const string DataFormat="yyyy:MM:dd";

    public PontajRepository(string filename, string pathAngajat, string pathSarcina) :
        base(filename, LineToEntity, EntityToLine)
    {
        ReadSubEntities(pathAngajat, pathSarcina);
    }

    private void ReadSubEntities(string pathAngajat, string pathSarcina)
    {
        List<Angajat> angajats = ReadFromFile(pathAngajat, AngajatRepository.LineToEntity);
        List<Sarcina> sarcini = ReadFromFile(pathSarcina, SarcinaRepository.LineToEntity);
        foreach (var value in Dictionary.Values)
        {
            value.Angajat=angajats.Find(x=> x.Id ==value.Id.Item1);
            value.Sarcina=sarcini.Find(x=> x.Id ==value.Id.Item2);
        }
    }

    public static Pontaj LineToEntity(string line)
    {
        string[] parts = line.Split(',');
        Pontaj entity = new Pontaj(); 
       string idAngajat=parts[0];
        string idSarcina=parts[1];
        DateTime date=DateTime.ParseExact( parts[2], DataFormat,System.Globalization.CultureInfo.InvariantCulture);
        entity.Id= new Tuple<string, string>(idAngajat, idSarcina);
        entity.Data = date;
        return entity;
    }

    public static string EntityToLine(Pontaj entity)
    {
        return entity.Id + ","; 
    }
}