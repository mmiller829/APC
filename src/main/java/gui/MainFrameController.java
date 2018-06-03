package gui;

import apc.Connection;
import apc.SocketStateManager;
import apc.Task;
import apc.TaskFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrameController
{

    private final MainFrame mainFrame;
    private final Connection connection;

    public MainFrameController(MainFrame mainFrame, Connection connection)
    {
        this.mainFrame = mainFrame;
        this.connection = connection;
        
    }
    
    public void initListener()
    {
        mainFrame.getExitMenuItem().addActionListener(e -> logout());
    }

    private void socketStateTaskThread()
    {
        SocketStateManager socketStateManager = new SocketStateManager(mainFrame);
        final Task socketStateTask = TaskFactory.createSocketStateTask(socketStateManager);

        Thread thread = new Thread(() ->
        {
            // add task to execute every x seconds
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
        thread.start();

    }
    
    private void logout()
    {
        try
        {
            connection.disconnect();
            mainFrame.dispose();
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
