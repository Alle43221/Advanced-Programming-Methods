using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public delegate E LineToEntity<E>(string input);
public delegate string EntityToLine<E>(E input);

public class FileRepository<E, ID> : InMemoryRepository<E, ID> where E : Entity<ID>
{
    public string Filename { get; set; }
    private LineToEntity<E> LineToEntity;
    private EntityToLine<E> EntityToLine;

    public FileRepository(string filename, LineToEntity<E> lineToEntity, EntityToLine<E> entityToLine)
    {
        Filename = filename;
        LineToEntity = lineToEntity;
        EntityToLine = entityToLine;
        ReadFromFile();
    }

    private void ReadFromFile()
    {
        using (StreamReader sr = new StreamReader(Filename))
        {
            string line;
            while ((line = sr.ReadLine()) != null)
            {
                E entity = LineToEntity(line);
                base.Save(entity);
            }
        }
    }

    private void WriteToFile()
    {
        using (StreamWriter sr = new StreamWriter(Filename))
        {
            foreach (E entity in base.FindAll())
                sr.WriteLine(EntityToLine(entity));
        }
    }

    public override E Save(E entity)
    {
        E? e= base.Save(entity);
        if (e != null)
        {
            WriteToFile();
        }
        return e;
    }
    
    public override E Delete(ID id)
    {
        E? e= base.Delete(id);
        if (e != null)
        {
            WriteToFile();
        }
        return e;
    }
    
    public override E Update(E entity)
    {
        E? e= base.Update(entity);
        if (e != null)
        {
            WriteToFile();
        }
        return e;
    }
}