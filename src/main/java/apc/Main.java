/*
 * This is the entry point for the Application
 */
package apc;

import gui.LoginFrame;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main
{

    final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        new LoginFrame();
    }

}
