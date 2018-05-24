package gui;

import apc.SocketStateManager;
import apc.Task;
import apc.TaskFactory;

public class MainFrameController
{

    private final MainFrame mainFrame;

    public MainFrameController(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }

    private void socketStateTaskThread()
    {
        SocketStateManager socketStateManager = new SocketStateManager(mainFrame);
        final Task socketStateTask = TaskFactory.createSocketStateTask(socketStateManager);

        Thread thread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                // add task to execute every x seconds
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        thread.start();

    }

}
