using Lab10_MAP.Domain;

namespace Lab10_MAP.Repository;

public class JucatorFileRepository(string filePath)
    : FileRepository<Jucator, long>(filePath, StringToEntity, EntityToString)
{
    public static Jucator StringToEntity(string line)
    {
        string[] data = line.Split(',');
        long id = long.Parse(data[0]);
        string nume = data[1];
        string scoala= data[2];
        long echipa= long.Parse(data[3]);
        return new Jucator(id, nume, scoala, echipa);
    }

    public static string EntityToString(Jucator jucator)
    {
        return jucator.Id + "," + jucator.Nume + "," + jucator.Scoala + ',' + jucator.Echipa;
    }
}