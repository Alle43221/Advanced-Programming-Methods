namespace Lab9_MAP;
using System;

public class MessageTask(string description, string id, string message, string from, string to, DateTime date)
    : Task(description, id)
{
    private DateTime _date = date;

    private const string Formatter = "yyyy-MM-dd HH:mm";

    private string Message { get; set; } = message;

    private string From { get; set; } = from;

    private string To { get; set; } = to;

    public DateTime Date
    {
        get => _date;
        set => _date = value;
    }

    public override string ToString()
    {
        return $"id={Id} | description={Description} | message={Message} | from={From} | to={To} | date={_date.ToString(Formatter)}";
    }

    public override void Execute()
    {
        Console.WriteLine($"{_date.ToString(Formatter)}: {Message}");
    }
}