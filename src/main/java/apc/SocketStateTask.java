
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
