/*
 * This is the entry point for the Application
 */
package apc;

import com.bulenkov.darcula.DarculaLaf;
import gui.LoginFrame;
import gui.LoginFrameController;
import gui.MainFrame;
import gui.MainFrameController;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main
{

    final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws UnsupportedLookAndFeelException, LoginFailException
    {

        BasicLookAndFeel darcula = new DarculaLaf();
        UIManager.setLookAndFeel(darcula);

        // initialize global variable
        GlobalVariables.loginProfilesManager = new LoginProfilesManager();

        File file = new File(GlobalVariables.loginFileName);
        if (!file.exists())
        {
            // create login frame
            LoginFrame loginFrame = new LoginFrame();
            LoginFrameController loginFrameController = new LoginFrameController(loginFrame);
            loginFrameController.initListener();
            loginFrame.setVisible(true);
        }
        else
        {
            LoginFileManager.readProfiles();

            LoginProfile loginProfile = GlobalVariables.loginProfilesManager.getStartup();

            if (loginProfile != null)
            {
                String username = loginProfile.getUsername();
                String password = loginProfile.getPassword();
                String lanAddress = loginProfile.getLanAddress();
                String wanAddress = loginProfile.getWanAddress();
                String wanPort = loginProfile.getWanPort();

                Connection connection = new Connection(lanAddress, GlobalVariables.port);
                try
                {
                    connection.connect();
                    connection.login(username, password);

                    // create main frame when login succeeds
                    EventQueue.invokeLater(() ->
                    {
                        MainFrame mainFrame = new MainFrame(null);
                        mainFrame.setStatusMenuItems(username, password, lanAddress, wanAddress, wanPort);
                        mainFrame.setProfilesMenuItems(username, password, lanAddress, wanAddress, wanPort);

                        MainFrameController mainFrameController = new MainFrameController(mainFrame, connection);

                        mainFrame.setVisible(true);
                    });

                }
                catch (IOException | InvalidTelnetOptionException ex)
                {
                    JOptionPane.showMessageDialog(null, "Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);

                }
                catch (LoginFailException ex)
                {
                    JOptionPane.showMessageDialog(null, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
                    try
                    {
                        connection.disconnect();
                    }
                    catch (IOException ex1)
                    {
                        java.util.logging.Logger.getLogger(LoginFrameController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            }
            else
            {
                // create login frame
                LoginFrame loginFrame = new LoginFrame();
                LoginFrameController loginFrameController = new LoginFrameController(loginFrame);
                loginFrameController.initListener();
                loginFrame.setVisible(true);
            }

        }
    }
}
