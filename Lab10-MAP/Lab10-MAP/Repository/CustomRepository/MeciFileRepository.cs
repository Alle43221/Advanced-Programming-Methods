using Lab10_MAP.Domain;

namespace Lab10_MAP.Repository;

public class MeciFileRepository(string filePath) : FileRepository<Meci, long>(filePath, StringToEntity, EntityToString)
{
    private const string DateFormat = "yyyy-MM-dd";

    public static Meci StringToEntity(string line)
    {
        string[] data = line.Split(',');
        long id = long.Parse(data[0]);
        long echipaHost = long.Parse(data[1]);
        long echipaGuest= long.Parse(data[2]);
        DateTime dataMeci = DateTime.ParseExact(data[3],DateFormat, System.Globalization.CultureInfo.InvariantCulture);
        return new Meci(id, echipaHost, echipaGuest, dataMeci);
    }

    public static string EntityToString(Meci meci)
    {
        return meci.Id + "," + meci.EchipaHost + "," + meci.EchipaGuest + "," + meci.Date.Date.ToString(DateFormat);
    }

}