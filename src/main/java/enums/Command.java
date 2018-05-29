package enums;

public enum Command
{

    GET_SOCKET_STATE("status");

    private final String command;
    Command(String command)
    {
        this.command = command;
    }
    
    public String getCommand()
    {
        return command;
    }
}
