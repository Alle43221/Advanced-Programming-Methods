namespace Lab10_MAP.Repository;
using Lab10_MAP.Domain;

public class JucatorActivFileRepository(string filePath)
    : FileRepository<JucatorActiv, Tuple<long, long>>(filePath, StringToEntity, EntityToString)
{
    public static JucatorActiv StringToEntity(string line)
    {
        string[] data = line.Split(',');
        long id = long.Parse(data[0]);
        long meci = long.Parse(data[1]);
        int puncte = int.Parse(data[2]);
        Enum.TryParse(data[3], out TipuriJucator tip);
        //Console.WriteLine(new JucatorActiv(id, meci, puncte, tip));
        return new JucatorActiv(id, meci, puncte, tip);
    }

    public static string EntityToString(JucatorActiv jucator)
    {
        return jucator.Id + "," + jucator.MeciID + "," + jucator.NrPuncteInscrise + ',' + jucator.TipJucator;
    }
}