package gui;

import apc.Connection;
import apc.SocketStateManager;
import apc.Task;
import apc.TaskFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrameController
{

    private final MainFrame mainFrame;
    private final Connection connection;

    public MainFrameController(MainFrame mainFrame, Connection connection)
    {
        this.mainFrame = mainFrame;
        this.connection = connection;
        
        initListener(); 
    }
    
    private void initListener()
    {
        mainFrame.getExitMenuItem().addActionListener(e -> logout());
        mainFrame.getSaveChangesMenuItem().addActionListener(e -> saveChanges());
        mainFrame.getUsernameTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getPasswordField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getLanAddressTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getWanAddressTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getWanPortTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
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
    
    private void saveChanges()
    {
        mainFrame.setSaveChangesEnabled(false);
        // write to file
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

class ConnectionMenuListener implements DocumentListener{

    MainFrame mainFrame;
    public ConnectionMenuListener(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        mainFrame.setSaveChangesEnabled(true);
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        mainFrame.setSaveChangesEnabled(true);
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        // this event doesn't fire
    }
}
