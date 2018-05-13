package enums;

public enum Command
{

    GETSOCKETSTATE(new String[1]);

    private String[] commandSequence;
    Command(String[] commandSequence)
    {
        this.commandSequence = commandSequence;
    }
    
    public String[] getCommandSequence()
    {
        return commandSequence;
    }
}
