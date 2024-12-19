using Sem10_MAP.Domain;
using Sem10_MAP.Repository;

namespace Sem10_MAP.Service;

public class Service
{
    
    private IRepository<Angajat, string> repositoryAngajat;
    private IRepository<Sarcina, string> repositorySarcina;
    private IRepository<Pontaj, Tuple<string, string>> repositoryPontaj;

    public Service(IRepository<Angajat, string> repositoryAngajat,
        IRepository<Sarcina, string> repositorySarcina, IRepository<Pontaj, Tuple<string, string>> repositoryPontaj)
    {
        this.repositoryAngajat = repositoryAngajat;
        this.repositorySarcina = repositorySarcina;
        this.repositoryPontaj = repositoryPontaj;
    }

    public List<Angajat> findAngajati()
    {
        return repositoryAngajat.FindAll().ToList();
    }
    
    
    public List<Sarcina> findSarcini()
    {
        return repositorySarcina.FindAll().ToList();
    }

    public List<Angajat> findAngajatByNivel(KnowledgeLevel nivel)
    {
        //method/API syntax
        //return repositoryAngajat.FindAll().Where(n => n.Nivel == nivel).ToList();
        
        //query syntax
        return (from e in repositoryAngajat.FindAll() where e.Nivel == nivel select e).ToList();
    }

    public List<Angajat> findAngajatiOrderedByNivelAndVenit()
    {
        //return repositoryAngajat.FindAll().OrderBy(n => n.Nivel).ThenByDescending(n => n.VenitPeOra).ToList();

        return (from e in repositoryAngajat.FindAll() orderby e.Nivel, e.VenitPeOra descending select e).ToList();
    }

    public List<KeyValuePair<Dificultate, double>> getAverageTimePerSarcina()
    {
        // cu query
        //return (from s in repositorySarcina.FindAll() group s by s.Dificultate into g select new KeyValuePair<Dificultate, double>(g.Key, g.Average(s=>s.NrOreEstimate))).ToList();

        return repositorySarcina.FindAll()
            .GroupBy(s => s.Dificultate)
            .Select(g=> new KeyValuePair<Dificultate,double>(g.Key, g.Average(s=>s.NrOreEstimate)))
            .ToList();
    }

    public List<KeyValuePair<string, float>> findBestPaidAngajat()
    {
        // return (from p in repositoryPontaj.FindAll() 
        //     group p by p.Angajat into g
        //     let salar=g.Sum(p=>p.Sarcina.NrOreEstimate * p.Angajat.VenitPeOra)
        //     orderby salar descending
        //     select new KeyValuePair<string,float>(g.Key.Name, salar)).Take(2).ToList();
        //
        return repositoryPontaj.FindAll()
            .GroupBy(p=> p.Angajat)
            .Select(g => new KeyValuePair<string,float>(g.Key.Name, g.Sum(p=> p.Sarcina.NrOreEstimate*p.Angajat.VenitPeOra)))
            .OrderByDescending(p=>p.Value)
            .Take(2).ToList();
    }
}