package enums;

public enum Command
{

    GET_SOCKET_STATE(new String[1]);

    private final String[] commandSequence;
    Command(String[] commandSequence)
    {
        this.commandSequence = commandSequence;
    }
    
    public String[] getCommandSequence()
    {
        return commandSequence;
    }
}
