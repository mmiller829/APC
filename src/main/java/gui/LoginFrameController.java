package gui;

import apc.Connection;
import apc.GlobalVariables;
import apc.LoginFailException;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;

public class LoginFrameController
{

    private final LoginFrame loginFrame;
    private boolean isLoginRunning;

    public LoginFrameController(LoginFrame loginFrame)
    {
        this.loginFrame = loginFrame;
        isLoginRunning = false;
    }

    public void initListener()
    {
        loginFrame.getLoginButton().addActionListener(e -> login());
    }

    public void login()
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
                            loginFrame.dispose();

                            MainFrameController mainFrameController = new MainFrameController(mainFrame, connection);
                            mainFrameController.initListener();

                            mainFrame.setConnectionMenuItems(username, password, lanAddress, wanAddress, wanPort);

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

}
