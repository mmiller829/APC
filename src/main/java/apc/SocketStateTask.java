
package apc;

import enums.Command;

public class SocketStateTask extends Task
{
    private final SocketStateManager socketStateManager;
    
    public SocketStateTask(Command command, SocketStateManager socketStateManager)
    {
        super(command);
        this.socketStateManager = socketStateManager;
    }

    @Override
    public void execute()
    {
        // send the status command
        // read and parse the list of outlets
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
