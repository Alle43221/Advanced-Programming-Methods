namespace Lab10_MAP.Repository;

using Lab10_MAP.Domain;

public class EchipaFileRepository (string filePath): FileRepository<Echipa, long>(filePath, StringToEntity, EntityToString)
{
    public static Echipa StringToEntity(string line)
    {
        string[] data = line.Split(',');
        long id = long.Parse(data[0]);
        string nume = data[1];
        return new Echipa(id, nume);
    }

    public static string EntityToString(Echipa jucator)
    {
        return jucator.Id + "," + jucator.Nume;
    }
}