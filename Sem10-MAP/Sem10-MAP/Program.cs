// See https://aka.ms/new-console-template for more information

using Sem10_MAP.Domain;
using Sem10_MAP.Repository;
using Sem10_MAP.Service;

IRepository<Angajat, string> repositoryA =new AngajatRepository("data/angajati.txt");
IRepository<Sarcina, string> repositoryB =new SarcinaRepository("data/sarcini.txt");

Service service = new(repositoryA, repositoryB);

service.findAngajati().ForEach(Console.WriteLine);
service.findSarcini().ForEach(Console.WriteLine);