
package apc;

public class TaskFactory
{
    public static Task createSocketStateTask(SocketStateManager socketStateManager)
    {
        return new SocketStateTask(socketStateManager);
    }
    
}
