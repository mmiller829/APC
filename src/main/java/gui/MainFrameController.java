package gui;

import apc.Connection;
import apc.GlobalVariables;
import apc.LoginFileManager;
import apc.LoginProfile;
import apc.SocketStateManager;
import apc.Task;
import apc.TaskExecutorThread;
import apc.TaskFactory;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrameController
{

    private final MainFrame mainFrame;
    private final Connection connection;
    private boolean hidePassword = true;
    private final TaskExecutorThread taskThread;
    private final ArrayList<Integer> selectedSockets;

    public MainFrameController(MainFrame mainFrame, Connection connection)
    {
        this.mainFrame = mainFrame;
        this.connection = connection;

        selectedSockets = new ArrayList();

        taskThread = new TaskExecutorThread();

        initListener();

        socketStateTaskThread();
    }

    private void initListener()
    {
        mainFrame.getExitMenuItem().addActionListener(e -> logout());
        mainFrame.getSaveChangesMenuItem().addActionListener(e -> saveChanges());
        mainFrame.getProfilesDelete().addActionListener(e -> deleteProfile());
        mainFrame.getSetStartupMenuItem().addActionListener(e -> setStartup());
        mainFrame.getOnButton().addActionListener(e -> turnOn());
        mainFrame.getOffButton().addActionListener(e -> turnOff());
        mainFrame.getUsernameTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getProfilesPasswordField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getLanAddressTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getWanAddressTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));
        mainFrame.getWanPortTextField().getDocument().addDocumentListener(new ConnectionMenuListener(mainFrame));

        // listeners for the socket images
        ArrayList<JCheckBox> checkboxes = mainFrame.getCheckboxes();

        ArrayList<JLabel> imageLabels = mainFrame.getimageLabels();

        for (int i = 0; i < imageLabels.size(); i++)
        {
            imageLabels.get(i).addMouseListener(new SocketImageMouseListener(imageLabels.get(i), checkboxes.get(i)));

            // set checkbox listener
            final Integer number = i + 1;
            checkboxes.get(i).addItemListener(e -> checkboxChange(e, number));
        }

        // listener for profiles list
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

    private void turnOn()
    {
        String socketList = " ";

        if (!selectedSockets.isEmpty())
        {
            socketList += Integer.toString(selectedSockets.get(0));
            if (selectedSockets.size() > 0)
            {
                for (int i = 1; i < selectedSockets.size(); i++)
                {
                    socketList += "," + Integer.toString(selectedSockets.get(i));
                }
            }

        }

        Task task = TaskFactory.createOnTask(socketList, connection);
        taskThread.add(task);
        taskThread.start();
        JOptionPane.showMessageDialog(mainFrame, "Power on" + socketList, "Power on", JOptionPane.INFORMATION_MESSAGE);
    }

    private void turnOff()
    {
        String socketList = " ";

        if (!selectedSockets.isEmpty())
        {
            socketList += Integer.toString(selectedSockets.get(0));
            if (selectedSockets.size() > 0)
            {
                for (int i = 1; i < selectedSockets.size(); i++)
                {
                    socketList += "," + Integer.toString(selectedSockets.get(i));
                }
            }

        }

        Task task = TaskFactory.createOffTask(socketList, connection);
        taskThread.add(task);
        taskThread.start();
        JOptionPane.showMessageDialog(mainFrame, "Power off" + socketList, "Power off", JOptionPane.INFORMATION_MESSAGE);
    }

    private void socketStateTaskThread()
    {
        Thread thread = new Thread(() ->
        {
            while (true)
            {
                try
                {

                    SocketStateManager socketStateManager = new SocketStateManager(mainFrame);
                    Task socketStateTask = TaskFactory.createSocketStateTask(socketStateManager, connection);

                    taskThread.add(socketStateTask);
                    taskThread.start();
                    Thread.sleep(5000);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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

    private void checkboxChange(ItemEvent e, Integer number)
    {
        if (e.getStateChange() == 1)
        {
            selectedSockets.add(number);
            Collections.sort(selectedSockets);
        }
        else
        {
            selectedSockets.remove(number);
            Collections.sort(selectedSockets);
        }
    }

    private void logout()
    {
        try
        {
            connection.disconnect();
            mainFrame.dispose();
            System.exit(0);
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

class SocketImageMouseListener implements MouseListener
{

    private final JLabel imageLabel;
    private final JCheckBox checkbox;

    public SocketImageMouseListener(JLabel imageLabel, JCheckBox checkbox)
    {
        this.imageLabel = imageLabel;
        this.checkbox = checkbox;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        SwingUtilities.invokeLater(() ->
        {
            if (checkbox.isSelected())
            {
                checkbox.setSelected(false);
            }
            else
            {
                checkbox.setSelected(true);
            }
        });

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        // change picture to a little
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        //change picture back to defualt
    }
}
