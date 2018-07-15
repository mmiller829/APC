
package apc;

import interfaces.SocketStateDisplay;
import java.util.ArrayList;

public class SocketStateManager
{
    private SocketStateDisplay socketStateDisplay;

    public SocketStateManager(SocketStateDisplay socketStateDisplay)
    {
        this.socketStateDisplay = socketStateDisplay;
    }
    
    public void updateSocketDisplay(ArrayList<Socket> sockets)
    {
        socketStateDisplay.updateSocketState(sockets);
    }
}
