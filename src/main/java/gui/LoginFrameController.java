package gui;

import javax.swing.JOptionPane;

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
            //login here
            JOptionPane.showMessageDialog(loginFrame, "Not Supported yet", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
