namespace Lab10_MAP.Domain;

public enum TipuriJucator
{
    Participant, Rezerva
}

public class JucatorActiv:Entity<Tuple<long, long>>
{
    public long JucatorID {set;get;} 
    public long MeciID {set;get;} 
    public int NrPuncteInscrise {set;get;} 
    public TipuriJucator TipJucator {set;get;} 

    public JucatorActiv(long jucatorId, long meciId, int nrPuncteInscrise, TipuriJucator jucator)
    {
        Id = new Tuple<long, long>(jucatorId, meciId);
        JucatorID = jucatorId;
        MeciID = meciId;
        NrPuncteInscrise = nrPuncteInscrise;
        TipJucator = jucator;
    }

    public override string ToString()
    {
        return JucatorID + " " + MeciID + " " + NrPuncteInscrise + " " + TipJucator;
    }
}