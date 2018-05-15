
package apc;

import enums.Command;

public class TaskFactory
{
    public static Task createSocketStateTask(SocketStateManager socketStateManager)
    {
        return new SocketStateTask(Command.GETSOCKETSTATE, socketStateManager);
    }
    
}
