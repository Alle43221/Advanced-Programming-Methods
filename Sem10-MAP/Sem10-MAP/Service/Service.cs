using Sem10_MAP.Domain;
using Sem10_MAP.Repository;

namespace Sem10_MAP.Service;

public class Service
{
    
    private IRepository<Angajat, string> repositoryAngajat;
    private IRepository<Sarcina, string> repositorySarcina;

    public Service(IRepository<Angajat, string> repositoryAngajat,
        IRepository<Sarcina, string> repositorySarcina)
    {
        this.repositoryAngajat = repositoryAngajat;
        this.repositorySarcina = repositorySarcina;
    }

    public List<Angajat> findAngajati()
    {
        return repositoryAngajat.FindAll().ToList();
    }
    
    
    public List<Sarcina> findSarcini()
    {
        return repositorySarcina.FindAll().ToList();
    }
}