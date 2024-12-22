using Lab10_MAP.Domain;
using Lab10_MAP.Repository;

namespace Lab10_MAP.Service;

public class Service(
    IRepository<Jucator, long> repositoryJucator,
    IRepository<JucatorActiv, Tuple<long, long>> repositoryJucatorActiv,
    IRepository<Meci, long> repositoryMeci,
    IRepository<Elev, long> repositoryElev,
    IRepository<Echipa, long> repositoryEchipa)
{
    private IRepository<Jucator, long> RepositoryJucator { get; set; } = repositoryJucator;
    private IRepository<JucatorActiv, Tuple<long, long>> RepositoryJucatorActiv { get; set; } = repositoryJucatorActiv;
    private IRepository<Meci, long> RepositoryMeci { get; set; } = repositoryMeci;
    private IRepository<Elev, long> RepositoryElev { get; set; } = repositoryElev;
    private IRepository<Echipa, long> RepositoryEchipa { get; set; } = repositoryEchipa;

    public List<Elev> FindElevi()
    {
        return RepositoryElev.FindAll().ToList();
    }
    
    public List<Echipa> FindEchipe()
    {
        return RepositoryEchipa.FindAll().ToList();
    }

    public List<Jucator> FindJucatori()
    {
        return RepositoryJucator.FindAll().ToList();
    }

    public List<JucatorActiv> FindJucatoriActivi()
    {
        return RepositoryJucatorActiv.FindAll().ToList();
    }

    public List<Meci> FindMeciuri()
    {
        return RepositoryMeci.FindAll().ToList();
    }
    
    // toti jucatorii unei echipe date
    public List<Jucator> FindJucatoriEchipa(long idEchipa)
    {
        return (from e in RepositoryJucator.FindAll() where e.Echipa==idEchipa select e).ToList();
    }

    // jucatori participanti ai unei echipe la un meci
    public List<Jucator> FindJucatorActivParticipantiLaMeci(long meci, long echipa)
    {
        List<JucatorActiv> jucatoriParticipantiMeci = (from e in RepositoryJucatorActiv.FindAll() where (e.MeciID == meci &&  e.TipJucator == TipuriJucator.Participant)select e).ToList();
        //jucatoriParticipantiMeci.ForEach(Console.WriteLine);
        List<Jucator> jucatoriEchipa = (from e in RepositoryJucator.FindAll() where e.Echipa == echipa select e).ToList();
        //jucatoriEchipa.ForEach(Console.WriteLine);
        return (from e in jucatoriEchipa join t in jucatoriParticipantiMeci on e.Id equals t.JucatorID select e).ToList();
    }

    //meciuri dintr-o anumita perioada
    public List<Meci> FindMeciInPerioada(DateTime dateStart, DateTime dateEnd)
    {
        return (from e in RepositoryMeci.FindAll() where e.Date >= dateStart && e.Date <= dateEnd select e).ToList();
    }

    // scorul unui meci dat
    public (int, int) ScoreMeci(long meci)
    {
        Meci m = RepositoryMeci.FindOne(meci);
        
        List<Jucator> jucatoriHost =
            (from e in RepositoryJucator.FindAll() where e.Echipa == m.EchipaHost select e).ToList();
        List<Jucator> jucatoriGuest =
            (from e in RepositoryJucator.FindAll() where e.Echipa == m.EchipaGuest select e).ToList();
        
        List<int> jucatoriActiviHost =
            (from e in RepositoryJucatorActiv.FindAll() join t in jucatoriHost on e.JucatorID equals t.Id select e.NrPuncteInscrise).ToList();
        List<int> jucatoriActiviGuest =
            (from e in RepositoryJucatorActiv.FindAll() join t in jucatoriGuest on e.JucatorID equals t.Id select e.NrPuncteInscrise).ToList();
        
        return (jucatoriActiviHost.Sum(), jucatoriActiviGuest.Sum());

    }
}