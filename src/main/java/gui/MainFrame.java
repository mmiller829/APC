package gui;

import interfaces.SocketStateDisplay;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends javax.swing.JFrame implements SocketStateDisplay
{
    
    private final JTextField usernameTextField;
    private final JPasswordField passwordField;
    private final JTextField lanAddressTextField;
    private final JTextField wanAddressTextField;
    private final JTextField wanPortTextField;
    
    public MainFrame(Component parent)
    {
        initComponents();
        usernameTextField = new JTextField();
        usernameTextField.setColumns(10);
        jMenu2.add(usernameTextField);
        
        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        jMenu3.add(passwordField);
        
        lanAddressTextField = new JTextField();
        lanAddressTextField.setColumns(10);
        jMenu4.add(lanAddressTextField);
        
        wanAddressTextField = new JTextField();
        wanAddressTextField.setColumns(10);
        jMenu5.add(wanAddressTextField);
        
        wanPortTextField = new JTextField();
        wanPortTextField.setColumns(10);
        jMenu6.add(wanPortTextField);
        
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
    
    public void setConnectionMenuItems(String username, String password, String lanAddress, String wanAddress, String wanPort)
    {
        SwingUtilities.invokeLater(() ->
        {
            usernameTextField.setText(username);
            passwordField.setText(password);
            lanAddressTextField.setText(lanAddress);
            wanAddressTextField.setText(wanAddress);
            wanPortTextField.setText(wanPort);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Connection");

        jMenu2.setText("Username");
        jMenu1.add(jMenu2);

        jMenu3.setText("Password");
        jMenu1.add(jMenu3);

        jMenu4.setText("LAN Address");
        jMenu1.add(jMenu4);

        jMenu5.setText("WAN Address");
        jMenu1.add(jMenu5);

        jMenu6.setText("WAN Port");
        jMenu1.add(jMenu6);
        jMenu1.add(jSeparator1);

        exitMenuItem.setText("Exit");
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

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
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
