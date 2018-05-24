/*
 * This is the entry point for the Application
 */
package apc;

import com.bulenkov.darcula.DarculaLaf;
import gui.LoginFrame;
import java.io.File;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main
{

    final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws UnsupportedLookAndFeelException
    {
        BasicLookAndFeel darcula = new DarculaLaf();
        UIManager.setLookAndFeel(darcula);

        File file = new File(GlobalVariables.loginFileName);
        if (!file.exists())
        {
            new LoginFrame();
        }
        else
        {
            // log in
        }

    }

}
