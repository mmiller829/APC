/*
 * This is the entry point for the Application
 */
package apc;

import com.bulenkov.darcula.DarculaLaf;
import gui.LoginFrame;
import gui.LoginFrameController;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main
{

    final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws UnsupportedLookAndFeelException, LoginFailException
    {

        BasicLookAndFeel darcula = new DarculaLaf();
        UIManager.setLookAndFeel(darcula);

        // create login frame
        LoginFrame loginFrame = new LoginFrame();
        LoginFrameController loginFrameController = new LoginFrameController(loginFrame);
        loginFrame.setVisible(true);
    }

}
