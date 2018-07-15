
package apc;


public abstract class Task
{
    private final String command;
    
    public Task(String command)
    {
        this.command = command;
    }
    
    public abstract void execute();
    
    public String getCommand()
    {
        return command;
    }
}
