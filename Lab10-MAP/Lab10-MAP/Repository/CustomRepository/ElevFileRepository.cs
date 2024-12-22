namespace Lab10_MAP.Repository;

using Lab10_MAP.Domain;
public class ElevFileRepository(string filePath): FileRepository<Elev, long>(filePath, StringToEntity, EntityToString)
{
    public static Elev StringToEntity(string line)
    {
        string[] data = line.Split(',');
        long id = long.Parse(data[0]);
        string nume = data[1];
        string scoala= data[2];
        return new Elev(id, nume, scoala);
    }

    public static string EntityToString(Elev jucator)
    {
        return jucator.Id + "," + jucator.Nume + "," + jucator.Scoala;
    }
}