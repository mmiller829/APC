package apc;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PowerTask extends Task
{

    private final Connection connection;

    public PowerTask( String command, String socketList, Connection connection)
    {
        super(command + socketList);
        this.connection = connection;
    }

    @Override
    public void execute()
    {
        try
        {
            connection.send(getCommand() + "\r");
            connection.readResponse();
        }
        catch (IOException ex)
        {
            Logger.getLogger(PowerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
