
package apc;

import enums.Command;

public abstract class Task
{
    private final Command command;
    
    public Task(Command command)
    {
        this.command = command;
    }
    
    public abstract void execute();
    
    public String getCommand()
    {
        return command.getCommand();
    }
}
