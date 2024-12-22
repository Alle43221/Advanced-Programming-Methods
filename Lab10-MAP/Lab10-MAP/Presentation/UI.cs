namespace Lab10_MAP.Presentation;

using Lab10_MAP.Service;

public class UI(Service service)
{
    private Service Service { get; set; } = service;
    private string Meniu=
        "MENU:\n"+
        "-------------------------------\n"+
        "1. Show all players from a team (format: idTeam)\n"+
        "2. Show all active players from a team which participated to a match (format: idTeam, idMatch)\n"+
        "3. Show all matches from a time period (format: YYYY-MM-DD;YYYY-MM-DD)\n"+
        "4. Show the score of a match (format: idMatch)\n"+
        "0. Exit";

    public void Show()
    {
        Console.WriteLine("\n"+Meniu);
        while (true)
        {
            Console.Write(">>>");
            var comanda = Console.ReadLine();
            string content;
            switch (comanda)
            {
                case "0":
                {
                    Console.WriteLine("Exiting Application...");
                    return;
                }
                case "1":
                {
                    Console.WriteLine("Showing all players from a team... Enter Team ID");
                    Console.Write(">>>");
                    content = Console.ReadLine();
                    try
                    {
                        long idTeam = long.Parse(content);
                        Service.FindJucatoriEchipa(idTeam).ForEach(Console.WriteLine);
                    }
                    catch
                    {
                        Console.WriteLine("Invalid input. Try again...");
                    }
                    break;
                }
               case "2":
                {
                    Console.WriteLine("Showing all active players from a team which participated to a match... \nEnter Team ID and Match ID");
                    Console.Write(">>>");
                    content = Console.ReadLine();
                    try
                    {
                        string[] parts=content.Split(";");
                        long idTeam = long.Parse(parts[0]);
                        long idMatch = long.Parse(parts[1]);
                        Service.FindJucatorActivParticipantiLaMeci(idTeam, idMatch).ForEach(Console.WriteLine);
                    }
                    catch
                    {
                        Console.WriteLine("Invalid input. Try again...");
                    }
                    break;
                }
               case "3":
                {
                    
                    Console.WriteLine("Showing all matches from a time period... \nEnter Start Date and End Date");
                    Console.Write(">>>");
                    content = Console.ReadLine();
                    try
                    {
                        string[] parts=content.Split(";");
                        string dateFormat = "yyyy-MM-dd";
                        DateTime start = DateTime.ParseExact(parts[0],dateFormat, System.Globalization.CultureInfo.InvariantCulture);
                        DateTime end = DateTime.ParseExact(parts[1],dateFormat, System.Globalization.CultureInfo.InvariantCulture);
                        Service.FindMeciInPerioada(start, end).ForEach(Console.WriteLine);
                    }
                    catch
                    {
                        Console.WriteLine("Invalid input. Try again...");
                    }
                    break;
                }
               case "4":
                {
                    Console.WriteLine("Showing the score of a match... Enter Match ID");
                    Console.Write(">>>");
                    content = Console.ReadLine();
                    try
                    {
                        long idTeam = long.Parse(content);
                        Console.WriteLine(Service.ScoreMeci(idTeam));
                    }
                    catch
                    {
                        Console.WriteLine("Invalid input. Try again...");
                    }
                    break;
                }
                default:
                {
                    Console.WriteLine("Invalid Command");
                    break;
                }
            }
        }
    }
}