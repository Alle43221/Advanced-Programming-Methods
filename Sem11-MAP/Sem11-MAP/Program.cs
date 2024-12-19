// See https://aka.ms/new-console-template for more information

using Sem10_MAP.Domain;
using Sem10_MAP.Repository;
using Sem10_MAP.Service;

IRepository<Angajat, string> repositoryA =new AngajatRepository("data/angajati.txt");
IRepository<Sarcina, string> repositoryB =new SarcinaRepository("data/sarcini.txt");
IRepository<Pontaj, Tuple<string, string>> repositoryC =new PontajRepository("data/pontaje.txt", "data/angajati.txt", "data/sarcini.txt");

Service service = new(repositoryA, repositoryB, repositoryC);

service.findAngajati().ForEach(Console.WriteLine);
service.findSarcini().ForEach(Console.WriteLine);

service.getAverageTimePerSarcina();

service.findBestPaidAngajat().ForEach(pair =>
{
    Console.WriteLine($"{pair.Key} {pair.Value}");
});

service.findAngajatByNivel(KnowledgeLevel.Mid).ForEach(Console.WriteLine);
service.findAngajatByNivel(KnowledgeLevel.Junior).ForEach(Console.WriteLine);
service.findAngajatByNivel(KnowledgeLevel.Senior).ForEach(Console.WriteLine);

service.findAngajatiOrderedByNivelAndVenit().ForEach(Console.WriteLine);


