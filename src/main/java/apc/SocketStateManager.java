
package apc;

import interfaces.SocketStateDisplay;

public class SocketStateManager
{
    private SocketStateDisplay socketStateDisplay;

    public SocketStateManager(SocketStateDisplay socketStateDisplay)
    {
        this.socketStateDisplay = socketStateDisplay;
    }
    
    public void updateSocketDisplay()
    {
        socketStateDisplay.updateSocketState();
    }
}
