package gui;

import apc.Connection;
import apc.GlobalVariables;
import apc.LoginFailException;
import apc.LoginFileManager;
import apc.LoginVariables;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;

public class LoginFrameController
{

    private final LoginFrame loginFrame;
    private LoginFileManager loginFileManager;
    private boolean isLoginRunning;

    public LoginFrameController(LoginFrame loginFrame)
    {
        this.loginFrame = loginFrame;
        loginFileManager = loadLoginFileManager(GlobalVariables.loginFileName);
        isLoginRunning = false;
        initComboBox();
    }

    private void initListener()
    {
        loginFrame.getLoginButton().addActionListener(e -> login());
        loginFrame.getUsernameComboBox().addActionListener((e -> onComboBoxChange()));
    }
    
    private void initComboBox()
    {
        JComboBox usernameComboBox = loginFrame.getUsernameComboBox();
        usernameComboBox.removeAllItems();
        for (String username : loginFileManager.getUsernames())
        {
            usernameComboBox.addItem(username);
        }
        
        usernameComboBox.setSelectedIndex(-1);
    }

    private void login()
    {
        if (!isLoginRunning)
        {
            isLoginRunning = true;
            Thread thread = new Thread(() ->
            {
                if (loginFrame.getUsername().length() == 0)
                {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    loginFrame.setUsernameFocus();
                }
                else if (loginFrame.getPassword().length == 0)
                {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                    loginFrame.setPasswordFocus();
                }
                else if (loginFrame.getLanAddress().length() == 0)
                {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter a lan address", "Error", JOptionPane.ERROR_MESSAGE);
                    loginFrame.setLanAddressFocus();
                }
                else
                {
                    String username = loginFrame.getUsername();
                    String password = new String(loginFrame.getPassword());
                    String lanAddress = loginFrame.getLanAddress();
                    String wanAddress = loginFrame.getWanAddress();
                    String wanPort = loginFrame.getWanPort();
                    
                    addLoginEntry(username, lanAddress, wanAddress, wanPort);

                    Connection connection = new Connection(lanAddress, GlobalVariables.port);
                    try
                    {
                        loginFrame.setActivity("Connecting to " + lanAddress);
                        connection.connect();
                        connection.login(username, password);

                        // create main frame when login succeeds
                        EventQueue.invokeLater(() ->
                        {
                            MainFrame mainFrame = new MainFrame(loginFrame);
                            mainFrame.setConnectionMenuItems(username, password, lanAddress, wanAddress, wanPort);

                            MainFrameController mainFrameController = new MainFrameController(mainFrame, connection);

                            loginFrame.dispose();

                            mainFrame.setVisible(true);
                        });

                    }
                    catch (IOException | InvalidTelnetOptionException ex)
                    {
                        loginFrame.setActivity("");
                        JOptionPane.showMessageDialog(loginFrame, "Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    catch (LoginFailException ex)
                    {
                        loginFrame.setActivity("");
                        JOptionPane.showMessageDialog(loginFrame, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
                        try
                        {
                            connection.disconnect();
                        }
                        catch (IOException ex1)
                        {
                            Logger.getLogger(LoginFrameController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
                isLoginRunning = false;
            });
            thread.start();
        }

    }

    /**
     * Sets the LAN address, WAN address, and WAN port fields if the username is
     * found in loginFileManager.
     */
    private void onComboBoxChange()
    {
        String username = loginFrame.getUsername();
        
        if (loginFileManager.contains(username))
        {
            LoginVariables login = loginFileManager.get(username);
            loginFrame.setPassword("");
            loginFrame.setLanAddress(login.getLanAddress());
            loginFrame.setWanAddress(login.getWanAddress());
            loginFrame.setWanPort(login.getWanPort());
        }
    }
    
    /**
     * Updates the ComboBox, adds entry to loginFileManager, and serializes to file.
     */
    private void addLoginEntry(String username, String lanAddress, String wanAddress, String wanPort)
    {
        // add to comboxbox if not already in loginFileManager
        if (!loginFileManager.contains(username))
        {
            JComboBox usernameComboBox = loginFrame.getUsernameComboBox();
            usernameComboBox.addItem(username);
        }
        
        // add to loginFileManager
        loginFileManager.add(username, lanAddress, wanAddress, wanPort);
        
        // serialize to file
        saveLoginFileManager(GlobalVariables.loginFileName, loginFileManager);
    }
    
    /**
     * Returns deserialized LoginFileManager or a new LoginFileManager if it
     * fails.
     */
    private LoginFileManager loadLoginFileManager(String filename)
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {
            return (LoginFileManager)in.readObject();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            return new LoginFileManager();
        }
    }
    
    /**
     * Returns true if serializing LoginFileManager is successful.
     */
    private boolean saveLoginFileManager(String filename, LoginFileManager loginFileManager)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            out.writeObject(loginFileManager);
            return true;
        }
        catch (FileNotFoundException ex)
        {
            return false;
        }
        catch (IOException ex)
        {
            return false;
        }
    }
}
