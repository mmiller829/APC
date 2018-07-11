package gui;

import apc.GlobalVariables;
import apc.LoginProfile;
import interfaces.SocketStateDisplay;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends javax.swing.JFrame implements SocketStateDisplay
{

    private final JTextField usernameStatusTextField;
    private final JPasswordField statusPasswordField;
    private final JTextField lanAddressStatusTextField;
    private final JTextField wanAddressStatusTextField;
    private final JTextField wanPortStatusTextField;

    private final JTextField usernameProfilesTextField;
    private final JPasswordField profilesPasswordField;
    private final JTextField lanAddressProfilesTextField;
    private final JTextField wanAddressProfilesTextField;
    private final JTextField wanPortProfilesTextField;
    private final JList profilesList;
    private final char passwordEcho;

    public MainFrame(Component parent)
    {
        initComponents();

        // textfields are used for looks
        usernameStatusTextField = new JTextField();
        usernameStatusTextField.setColumns(10);
        usernameStatusTextField.setEditable(false);
        jMenu2.add(usernameStatusTextField);

        statusPasswordField = new JPasswordField();
        passwordEcho = statusPasswordField.getEchoChar();
        statusPasswordField.setColumns(10);
        statusPasswordField.setEditable(false);
        statusPasswordField.setToolTipText("Click to toggle show password.");
        jMenu3.add(statusPasswordField);

        lanAddressStatusTextField = new JTextField();
        lanAddressStatusTextField.setColumns(10);
        lanAddressStatusTextField.setEditable(false);
        jMenu4.add(lanAddressStatusTextField);

        wanAddressStatusTextField = new JTextField();
        wanAddressStatusTextField.setColumns(10);
        wanAddressStatusTextField.setEditable(false);
        jMenu5.add(wanAddressStatusTextField);

        wanPortStatusTextField = new JTextField();
        wanPortStatusTextField.setColumns(10);
        wanPortStatusTextField.setEditable(false);
        jMenu6.add(wanPortStatusTextField);

        //status menu items
        usernameProfilesTextField = new JTextField();
        usernameProfilesTextField.setColumns(10);
        jMenu8.add(usernameProfilesTextField);

        profilesPasswordField = new JPasswordField();
        profilesPasswordField.setColumns(10);
        jMenu9.add(profilesPasswordField);

        lanAddressProfilesTextField = new JTextField();
        lanAddressProfilesTextField.setColumns(10);
        jMenu11.add(lanAddressProfilesTextField);

        wanAddressProfilesTextField = new JTextField();
        wanAddressProfilesTextField.setColumns(10);
        jMenu12.add(wanAddressProfilesTextField);

        wanPortProfilesTextField = new JTextField();
        wanPortProfilesTextField.setColumns(10);
        jMenu13.add(wanPortProfilesTextField);

        // list
        List<LoginProfile> list = new ArrayList(GlobalVariables.loginProfilesManager.getEntries());
        DefaultListModel model = new DefaultListModel();

        list.stream().forEach((profile) ->
        {
            model.addElement(profile);
        });

        profilesList = new JList(model);

        jMenu10.add(profilesList);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/APC-App-Icon.png"));
        setIconImage(icon.getImage());
        setTitle("APC 7902-pdu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

    }

    @Override
    public void updateSocketState()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JMenuItem getExitMenuItem()
    {
        return exitMenuItem;
    }

    public JMenuItem getSaveChangesMenuItem()
    {
        return saveChangesMenuItem;
    }

    public JTextField getUsernameTextField()
    {
        return usernameProfilesTextField;
    }

    public JPasswordField getProfilesPasswordField()
    {
        return profilesPasswordField;
    }

    public JPasswordField getStatusPasswordField()
    {
        return statusPasswordField;
    }

    public JTextField getLanAddressTextField()
    {
        return lanAddressProfilesTextField;
    }

    public JTextField getWanAddressTextField()
    {
        return wanAddressProfilesTextField;
    }

    public JTextField getWanPortTextField()
    {
        return wanPortProfilesTextField;
    }

    public JMenuItem getProfilesDelete()
    {
        return profilesDelete;
    }

    public JList getProfilesList()
    {
        return profilesList;
    }

    public JMenuItem getSetStartupMenuItem()
    {
        return profilesSetStartup;
    }

    public void setSaveChangesEnabled(boolean enabled)
    {
        SwingUtilities.invokeLater(() ->
        {
            saveChangesMenuItem.setEnabled(enabled);
        });
    }

    public void setSetStartupEnabled(boolean enabled)
    {
        SwingUtilities.invokeLater(() ->
        {
            profilesSetStartup.setEnabled(enabled);
        });
    }

    public void hidePassword(boolean hidePassword)
    {
        SwingUtilities.invokeLater(() ->
        {
            if (hidePassword)
            {
                statusPasswordField.setEchoChar(passwordEcho);
            }
            else
            {
                statusPasswordField.setEchoChar((char) 0);
            }
        });
    }

    public void updateProfilesList()
    {
        SwingUtilities.invokeLater(() ->
        {
            List<LoginProfile> list = new ArrayList(GlobalVariables.loginProfilesManager.getEntries());
            DefaultComboBoxModel model = new DefaultComboBoxModel();

            list.stream().forEach((profile) ->
            {
                model.addElement(profile);
            });

            profilesList.setModel(model);
        });
    }

    public void setStatusMenuItems(String username, String password, String lanAddress, String wanAddress, String wanPort)
    {
        usernameStatusTextField.setText(username);
        statusPasswordField.setText(password);
        lanAddressStatusTextField.setText(lanAddress);
        wanAddressStatusTextField.setText(wanAddress);
        wanPortStatusTextField.setText(wanPort);
    }

    public void setProfilesMenuItems(String username, String password, String lanAddress, String wanAddress, String wanPort)
    {
        usernameProfilesTextField.setText(username);
        profilesPasswordField.setText(password);
        lanAddressProfilesTextField.setText(lanAddress);
        wanAddressProfilesTextField.setText(wanAddress);
        wanPortProfilesTextField.setText(wanPort);
    }

    public void updateProfilesMenuItems(LoginProfile profile)
    {

        SwingUtilities.invokeLater(() ->
        {
            usernameProfilesTextField.setText(profile.getUsername());
            profilesPasswordField.setText(profile.getPassword());
            lanAddressProfilesTextField.setText(profile.getLanAddress());
            wanAddressProfilesTextField.setText(profile.getWanAddress());
            wanPortProfilesTextField.setText(profile.getWanPort());

            setSaveChangesEnabled(false);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        profilesMenu = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu10 = new javax.swing.JMenu();
        profilesSetStartup = new javax.swing.JMenuItem();
        profilesDelete = new javax.swing.JMenuItem();
        saveChangesMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Status");

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Users16.png"))); // NOI18N
        jMenu2.setText("Username");
        jMenu1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Password16.png"))); // NOI18N
        jMenu3.setText("Password");
        jMenu1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CircuitMgr16.png"))); // NOI18N
        jMenu4.setText("LAN Address");
        jMenu1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/multicast_icon.gif"))); // NOI18N
        jMenu5.setText("WAN Address");
        jMenu1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Port16.png"))); // NOI18N
        jMenu6.setText("WAN Port");
        jMenu1.add(jMenu6);
        jMenu1.add(jSeparator1);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit16.png"))); // NOI18N
        exitMenuItem.setText("Exit");
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        profilesMenu.setText("Profiles");

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Users16.png"))); // NOI18N
        jMenu8.setText("Username");
        profilesMenu.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Password16.png"))); // NOI18N
        jMenu9.setText("Password");
        profilesMenu.add(jMenu9);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CircuitMgr16.png"))); // NOI18N
        jMenu11.setText("LAN Address");
        profilesMenu.add(jMenu11);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/multicast_icon.gif"))); // NOI18N
        jMenu12.setText("WAN Address");
        profilesMenu.add(jMenu12);

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Port16.png"))); // NOI18N
        jMenu13.setText("WAN Port");
        profilesMenu.add(jMenu13);
        profilesMenu.add(jSeparator2);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AuditTrail16.png"))); // NOI18N
        jMenu10.setText("Profiles");
        profilesMenu.add(jMenu10);

        profilesSetStartup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/decor_check.png"))); // NOI18N
        profilesSetStartup.setText("Set as Startup Profile");
        profilesSetStartup.setEnabled(false);
        profilesMenu.add(profilesSetStartup);

        profilesDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Remove16.png"))); // NOI18N
        profilesDelete.setText("Delete");
        profilesMenu.add(profilesDelete);

        saveChangesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save16.png"))); // NOI18N
        saveChangesMenuItem.setText("Save");
        saveChangesMenuItem.setEnabled(false);
        profilesMenu.add(saveChangesMenuItem);

        jMenuBar1.add(profilesMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 766, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem profilesDelete;
    private javax.swing.JMenu profilesMenu;
    private javax.swing.JMenuItem profilesSetStartup;
    private javax.swing.JMenuItem saveChangesMenuItem;
    // End of variables declaration//GEN-END:variables
}
