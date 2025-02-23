﻿namespace Lab10_MAP.Domain.Validator;

public class ValidatorElev
{
    private List<(string, string)> validSchools = new List<(string, string)>
    {
        ("School A", ""),
        ("Scoala Gimnaziala “Horea”", "Houston Rockets"),
        ("Scoala Gimnaziala \"Octavian Goga\"", "Los Angeles Lakers"),
        ("Liceul Teoretic \"Lucian Blaga\"", "LA Clippers"),
        ("Scoala Gimnaziala \"Ioan Bob\"", "Chicago Bulls"),
        ("Scoala Gimnaziala \"Ion Creanga\"", "Cleveland Cavaliers"),
        ("Colegiul National Pedagogic \"Gheorghe Lazar\"", "Utah Jazz"),
        ("Scoala Gimnaziala Internationala SPECTRUM", "Brooklyn Nets"),
        ("Colegiul National „Emil Racovita”", "New Orleans Pelicans"),
        ("Colegiul National \"George Cosbuc\"", "Indiana Pacers"),
        ("Scoala Gimnaziala \"Ion Agarbiceanu\"", "Toronto Raptors"),
        ("Liceul Teoretic \"Avram Iancu\"", "Charlotte Hornets"),
        ("Scoala Gimnaziala \"Constantin Brancusi\"", "Phoenix Suns"),
        ("Liceul Teoretic \"Onisifor Ghibu\"", "Portland TrailBlazers"),
        ("Liceul Teoretic \"Onisifor Ghibu\"", "Golden State Warriors"),
        ("Liceul cu Program Sportiv Cluj-Napoca", "Washington Wizards"),
        ("Liceul Teoretic \"Nicolae Balcescu\"", "San Antonio Spurs"),
        ("Liceul Teoretic \"Gheorghe Sincai\"", "Orlando Magic"),
        ("Scoala \"Nicolae Titulescu\"", "Denver Nuggets"),
        ("Scoala Gimnaziala \"Liviu Rebreanu\"", "Detroit Pistons"),
        ("Scoala Gimnaziala \"Iuliu Hatieganu\"", "Atlanta Hawks"),
        ("Liceul Teoretic \"Bathory Istvan\"", "Dallas Mavericks"),
        ("Colegiul National \"George Baritiu\"", "Sacramento Kings"),
        ("Liceul Teoretic \"Apaczai Csere Janos\"", "Oklahoma City Thunder"),
        ("Seminarul Teologic Ortodox", "Boston Celtics"),
        ("Liceul de Informatica \"Tiberiu Popoviciu\"", "New York Knicks"),
        ("Scoala Gimnaziala „Alexandru Vaida – Voevod\"", "Minnesota Timberwolves"),
        ("Liceul Teoretic ELF", "Miami Heat"),
        ("Scoala Gimnaziala \"Gheorghe Sincai\" Floresti", "Milwaukee Bucks")
    };

    
    public bool ValidateElev(Elev elev)
    {
        return validSchools.Any(school => school.Item1==elev.Scoala);
    }
}