package apc;

public class TaskFactory
{

    public static Task createOnTask(String socketList, Connection connection)
    {
        return new PowerTask("on", socketList, connection);
    }

    public static Task createOffTask(String socketList, Connection connection)
    {
        return new PowerTask("off", socketList, connection);
    }

    public static Task createSocketStateTask(SocketStateManager socketStateManager, Connection connection)
    {
        return new SocketStateTask("status", socketStateManager, connection);
    }

}
