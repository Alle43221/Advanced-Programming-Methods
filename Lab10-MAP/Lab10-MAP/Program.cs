// See https://aka.ms/new-console-template for more information

using Lab10_MAP.Domain;
using Lab10_MAP.Presentation;
using Lab10_MAP.Repository;
using Lab10_MAP.Service;

IRepository<Elev, long> repositoryElev=new ElevFileRepository("data/elevi.txt");
IRepository<Meci, long> repositoryMeci=new MeciFileRepository("data/meciuri.txt");
IRepository<Jucator, long> repositoryJucator=new JucatorFileRepository("data/jucatori.txt");
IRepository<Echipa, long> repositoryEchipa=new EchipaFileRepository("data/echipe.txt");
IRepository<JucatorActiv, Tuple<long, long>> repositoryJucatorActiv=new JucatorActivFileRepository("data/jucatoriActivi.txt");

Service service = new Service(repositoryJucator, repositoryJucatorActiv, repositoryMeci, repositoryElev, repositoryEchipa);

// service.FindElevi().ForEach(
//         Console.WriteLine);

// service.FindEchipe().ForEach(
//     Console.WriteLine);

// service.FindJucatori().ForEach(
//     Console.WriteLine);

// service.FindMeciuri().ForEach(
//     Console.WriteLine);

// service.FindJucatoriActivi().ForEach(
//     Console.WriteLine);

// service.FindJucatoriEchipa(1).ForEach(
//     j => Console.WriteLine(j));

// service.FindJucatorActivParticipantiLaMeci(2, 3).ForEach(
    // j => Console.WriteLine(j));

//service.FindMeciInPerioada(new DateTime(2024, 01, 01), new DateTime(2024, 08, 01)).ForEach(Console.WriteLine);

//Console.WriteLine(service.ScoreMeci(3));

UI ui=new UI(service);
ui.Show();