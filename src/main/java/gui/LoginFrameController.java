package gui;

import apc.Connection;
import apc.GlobalVariables;
import apc.LoginFailException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;

public class LoginFrameController
{

    private final LoginFrame loginFrame;

    public LoginFrameController(LoginFrame loginFrame)
    {
        this.loginFrame = loginFrame;
    }

    public void initListener()
    {
        loginFrame.getLoginButton().addActionListener(e -> login());
    }

    public void login()
    {
        if(loginFrame.getUsername().length() == 0)
        {
            JOptionPane.showMessageDialog(loginFrame, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
            loginFrame.setUsernameFocus();
        }
        else if(loginFrame.getPassword().length == 0)
        {
            JOptionPane.showMessageDialog(loginFrame, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
            loginFrame.setPasswordFocus();
        }
        else if(loginFrame.getLanAddress().length() == 0)
        {
            JOptionPane.showMessageDialog(loginFrame, "Please enter a lan address", "Error", JOptionPane.ERROR_MESSAGE);
            loginFrame.setLanAddressFocus();
        }
        else
        {
            String username = loginFrame.getUsername();
            String password = new String(loginFrame.getPassword());
            String ipAddress = loginFrame.getLanAddress();
            
            Connection connection = new Connection(ipAddress, GlobalVariables.port);
            try
            {
                connection.connect();
                connection.login(username, password);
                
                // create main frame when login succeeds
                MainFrame mainFrame = new MainFrame(loginFrame);
                loginFrame.dispose();
                mainFrame.setVisible(true);
            }
            catch (IOException | InvalidTelnetOptionException ex)
            {
                JOptionPane.showMessageDialog(loginFrame, "Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);

            }
            catch (LoginFailException ex)
            {
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
    }

}
