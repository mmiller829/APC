package gui;

import apc.Connection;
import apc.GlobalVariables;
import apc.LoginFileManager;
import apc.LoginProfile;
import apc.SocketStateManager;
import apc.Task;
import apc.TaskFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrameController
{
    
    private final MainFrame mainFrame;
    private final Connection connection;
    private boolean hidePassword = true;
    
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
        mainFrame.getProfilesPasswordField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getLanAddressTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getWanAddressTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getWanPortTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        
        mainFrame.getProfilesDelete().addActionListener(e -> deleteProfile());
        
        mainFrame.getSetStartupMenuItem().addActionListener(e -> setStartup());
        
        mainFrame.getProfilesList().addMouseListener(new MouseListener()
        {
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                LoginProfile profile = (LoginProfile) mainFrame.getProfilesList().getSelectedValue();
                mainFrame.updateProfilesMenuItems(profile);
                mainFrame.setSetStartupEnabled(true);
            }
            
            @Override
            public void mousePressed(MouseEvent e)
            {
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e)
            {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                
            }
        });
        
        mainFrame.getStatusPasswordField().addMouseListener(new MouseListener()
        {
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (hidePassword == true)
                {
                    hidePassword = false;
                    mainFrame.hidePassword(false);
                }
                else
                {
                    hidePassword = true;
                    mainFrame.hidePassword(true);
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e)
            {
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e)
            {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                
            }
        });
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
        if (mainFrame.getUsernameTextField().getText().length() == 0)
        {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (mainFrame.getProfilesPasswordField().getPassword().length == 0)
        {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (mainFrame.getLanAddressTextField().getText().length() == 0)
        {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a lan address", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            String username = mainFrame.getUsernameTextField().getText();
            String password = new String(mainFrame.getProfilesPasswordField().getPassword());
            String lanAddress = mainFrame.getLanAddressTextField().getText();
            String wanAddress = mainFrame.getWanAddressTextField().getText();
            String wanPort = mainFrame.getWanPortTextField().getText();
            
            LoginProfile profile = new LoginProfile(username, password, lanAddress, wanAddress, wanPort);
            
            GlobalVariables.loginProfilesManager.add(profile);
            mainFrame.updateProfilesList();
            
            try
            {
                LoginFileManager.saveProfiles();
                JOptionPane.showMessageDialog(mainFrame, username + " has been saved.", "Saved", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ex)
            {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void deleteProfile()
    {
        String username = mainFrame.getUsernameTextField().getText();
        GlobalVariables.loginProfilesManager.remove(username);
        
        mainFrame.updateProfilesList();
        
        try
        {
            LoginFileManager.saveProfiles();
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(mainFrame, username + " has been deleted.", "Delete", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void setStartup()
    {
        LoginProfile profile = (LoginProfile) mainFrame.getProfilesList().getSelectedValue();
        GlobalVariables.loginProfilesManager.setStartup(profile);
        
        try
        {
            LoginFileManager.saveProfiles();
            JOptionPane.showMessageDialog(mainFrame, profile.getUsername() + " is now startup profile.", "Profile", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

class ConnectionMenuListener implements DocumentListener
{
    
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
