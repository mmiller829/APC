
package apc;

import interfaces.SocketStateDisplay;

public class SocketStateManager
{
    private SocketStateDisplay socketStateDisplay;
    
    public  void register(SocketStateDisplay socketStateDisplay)
    {
        this.socketStateDisplay = socketStateDisplay;
    }
    
    public void updateSocketDisplay()
    {
        socketStateDisplay.updateSocketState();
    }
}
