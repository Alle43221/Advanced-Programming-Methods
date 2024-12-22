namespace Lab10_MAP.Domain;

public class Meci:Entity<long>
{
    public long EchipaHost {get; set;}
    public long EchipaGuest {get; set;}
    public DateTime Date {get; set;}
    
    private const string DateFormat = "yyyy-MM-dd";

    public Meci(long id, long host, long guest, DateTime date)
    {
        Id = id;
        EchipaHost = host;
        EchipaGuest = guest;
        Date = date;
    }
    
    public override string ToString()
    {
        return EchipaHost + " " + EchipaGuest + " " + Date.Date.ToString(DateFormat);
    }
}