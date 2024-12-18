namespace Lab9_MAP;

using System;

public abstract class Task(string description, string id)
{
    protected string Description { get; set; } = description;

    protected string Id { get; set; } = id;

    public override string ToString()
    {
        return $"Task{{ id='{Id}', description='{Description}' }}";
    }

    public override bool Equals(object obj)
    {
        if (this == obj) return true;
        if (obj == null || GetType() != obj.GetType()) return false;
        var task = (Task)obj;
        return Id == task.Id && Description == task.Description;
    }

    public override int GetHashCode()
    {
        return HashCode.Combine(Id, Description);
    }

    public abstract void Execute();
}