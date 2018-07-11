/*
 * Read and write the LoginProfilesManager to and from file
 */
package apc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFileManager
{

    public static void saveProfiles() throws IOException
    {
        FileOutputStream fout = new FileOutputStream(GlobalVariables.loginFileName);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(GlobalVariables.loginProfilesManager);
    }

    public static void readProfiles()
    {
        try
        {
            FileInputStream fis = new FileInputStream(GlobalVariables.loginFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            GlobalVariables.loginProfilesManager = (LoginProfilesManager) ois.readObject();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(LoginFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(LoginFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LoginFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
