package gui;

import apc.GlobalVariables;
import apc.LoginProfile;
import apc.Socket;
import apc.Tooltip;
import apc.TooltipItem;
import interfaces.SocketStateDisplay;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/APC-App-Icon.png"));
        setIconImage(icon.getImage());
        setTitle("APC 7902-pdu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

    }

    @Override
    public void updateSocketState(ArrayList<Socket> sockets)
    {
        SwingUtilities.invokeLater(() ->
        {
            for (Socket socket : sockets)
            {
                switch (socket.getNumber())
                {
                    case "1":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket1Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket1Image.setIcon(icon);

                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket1Image.setToolTipText(tooltip.getDisplayString());
                        socket1Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket1Checkbox.setText(socket.getName());
                        socket1Checkbox.setEnabled(true);
                        socket1Image.setEnabled(true);
                        break;
                    }
                    case "2":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket2Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket2Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket2Image.setToolTipText(tooltip.getDisplayString());
                        socket2Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket2Checkbox.setText(socket.getName());
                        socket2Checkbox.setEnabled(true);
                        socket2Image.setEnabled(true);
                        break;
                    }
                    case "3":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket3Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket3Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket3Image.setToolTipText(tooltip.getDisplayString());
                        socket3Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket3Checkbox.setText(socket.getName());
                        socket3Checkbox.setEnabled(true);
                        socket3Image.setEnabled(true);
                        break;
                    }
                    case "4":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket4Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket4Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }
                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket4Image.setToolTipText(tooltip.getDisplayString());
                        socket4Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket4Checkbox.setText(socket.getName());
                        socket4Checkbox.setEnabled(true);
                        socket4Image.setEnabled(true);
                        break;
                    }
                    case "5":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket5Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket5Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket5Image.setToolTipText(tooltip.getDisplayString());
                        socket5Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket5Checkbox.setText(socket.getName());
                        socket5Checkbox.setEnabled(true);
                        socket5Image.setEnabled(true);
                        break;
                    }
                    case "6":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket6Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket6Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket6Image.setToolTipText(tooltip.getDisplayString());
                        socket6Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket6Checkbox.setText(socket.getName());
                        socket6Checkbox.setEnabled(true);
                        socket6Image.setEnabled(true);
                        break;
                    }
                    case "7":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket7Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket7Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket7Image.setToolTipText(tooltip.getDisplayString());
                        socket7Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket7Checkbox.setText(socket.getName());
                        socket7Checkbox.setEnabled(true);
                        socket7Image.setEnabled(true);
                        break;
                    }
                    case "8":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket8Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket8Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket8Image.setToolTipText(tooltip.getDisplayString());
                        socket8Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket8Checkbox.setText(socket.getName());
                        socket8Checkbox.setEnabled(true);
                        socket8Image.setEnabled(true);
                        break;
                    }
                    case "9":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket9Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket9Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket9Image.setToolTipText(tooltip.getDisplayString());
                        socket9Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket9Checkbox.setText(socket.getName());
                        socket9Checkbox.setEnabled(true);
                        socket9Image.setEnabled(true);
                        break;
                    }
                    case "10":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket10Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket10Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket10Image.setToolTipText(tooltip.getDisplayString());
                        socket10Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket10Checkbox.setText(socket.getName());
                        socket10Checkbox.setEnabled(true);
                        socket10Image.setEnabled(true);
                        break;
                    }
                    case "11":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket11Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket11Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket11Image.setToolTipText(tooltip.getDisplayString());
                        socket11Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket11Checkbox.setText(socket.getName());
                        socket11Checkbox.setEnabled(true);
                        socket11Image.setEnabled(true);
                        break;
                    }
                    case "12":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket12Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket12Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket12Image.setToolTipText(tooltip.getDisplayString());
                        socket12Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket12Checkbox.setText(socket.getName());
                        socket12Checkbox.setEnabled(true);
                        socket12Image.setEnabled(true);
                        break;
                    }
                    case "13":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket13Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket13Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket13Image.setToolTipText(tooltip.getDisplayString());
                        socket13Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket13Checkbox.setText(socket.getName());
                        socket13Checkbox.setEnabled(true);
                        socket13Image.setEnabled(true);
                        break;
                    }
                    case "14":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket14Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket14Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket14Image.setToolTipText(tooltip.getDisplayString());
                        socket14Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket14Checkbox.setText(socket.getName());
                        socket14Checkbox.setEnabled(true);
                        socket14Image.setEnabled(true);
                        break;
                    }
                    case "15":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket15Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket15Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket15Image.setToolTipText(tooltip.getDisplayString());
                        socket15Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket15Checkbox.setText(socket.getName());
                        socket15Checkbox.setEnabled(true);
                        socket15Image.setEnabled(true);
                        break;
                    }
                    case "16":
                    {
                        switch (socket.getState())
                        {
                            case "ON":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/greenoutlet.png"));
                                socket16Image.setIcon(icon);

                                break;
                            }
                            case "OFF":
                            {
                                ImageIcon icon = new ImageIcon(getClass().getResource("/Images/redoutlet.png"));
                                socket16Image.setIcon(icon);
                                break;
                            }
                            default:
                            {
                            }
                        }

                        TooltipItem name = new TooltipItem("Name: ", socket.getName());
                        TooltipItem state = new TooltipItem("State: ", socket.getState());

                        Tooltip tooltip = new Tooltip();
                        tooltip.add(name);
                        tooltip.add(state);

                        socket16Image.setToolTipText(tooltip.getDisplayString());
                        socket16Checkbox.setToolTipText(tooltip.getDisplayString());

                        socket16Checkbox.setText(socket.getName());
                        socket16Checkbox.setEnabled(true);
                        socket16Image.setEnabled(true);
                        break;
                    }
                }
            }
        });
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

    public JButton getOnButton()
    {
        return onButton;
    }

    public JButton getOffButton()
    {
        return offButton;
    }

    public ArrayList<JCheckBox> getCheckboxes()
    {
        ArrayList<JCheckBox> checkboxes = new ArrayList();

        checkboxes.add(socket1Checkbox);
        checkboxes.add(socket2Checkbox);
        checkboxes.add(socket3Checkbox);
        checkboxes.add(socket4Checkbox);
        checkboxes.add(socket5Checkbox);
        checkboxes.add(socket6Checkbox);
        checkboxes.add(socket7Checkbox);
        checkboxes.add(socket8Checkbox);
        checkboxes.add(socket9Checkbox);
        checkboxes.add(socket10Checkbox);
        checkboxes.add(socket11Checkbox);
        checkboxes.add(socket12Checkbox);
        checkboxes.add(socket13Checkbox);
        checkboxes.add(socket14Checkbox);
        checkboxes.add(socket15Checkbox);
        checkboxes.add(socket16Checkbox);

        return checkboxes;
    }

    public ArrayList<JLabel> getimageLabels()
    {
        ArrayList<JLabel> imageLabels = new ArrayList();

        imageLabels.add(socket1Image);
        imageLabels.add(socket2Image);
        imageLabels.add(socket3Image);
        imageLabels.add(socket4Image);
        imageLabels.add(socket5Image);
        imageLabels.add(socket6Image);
        imageLabels.add(socket7Image);
        imageLabels.add(socket8Image);
        imageLabels.add(socket9Image);
        imageLabels.add(socket10Image);
        imageLabels.add(socket11Image);
        imageLabels.add(socket12Image);
        imageLabels.add(socket13Image);
        imageLabels.add(socket14Image);
        imageLabels.add(socket15Image);
        imageLabels.add(socket16Image);

        return imageLabels;
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

        jPanel34 = new javax.swing.JPanel();
        onButton = new javax.swing.JButton();
        offButton = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        socket10Checkbox = new javax.swing.JCheckBox();
        socket10Image = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        socket11Checkbox = new javax.swing.JCheckBox();
        socket11Image = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        socket9Checkbox = new javax.swing.JCheckBox();
        socket9Image = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        socket12Checkbox = new javax.swing.JCheckBox();
        socket12Image = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        socket13Checkbox = new javax.swing.JCheckBox();
        socket13Image = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        socket14Checkbox = new javax.swing.JCheckBox();
        socket14Image = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        socket15Checkbox = new javax.swing.JCheckBox();
        socket15Image = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        socket16Checkbox = new javax.swing.JCheckBox();
        socket16Image = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        socket2Checkbox = new javax.swing.JCheckBox();
        socket2Image = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        socket3Checkbox = new javax.swing.JCheckBox();
        socket3Image = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        socket1Checkbox = new javax.swing.JCheckBox();
        socket1Image = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        socket4Checkbox = new javax.swing.JCheckBox();
        socket4Image = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        socket5Checkbox = new javax.swing.JCheckBox();
        socket5Image = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        socket6Checkbox = new javax.swing.JCheckBox();
        socket6Image = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        socket7Checkbox = new javax.swing.JCheckBox();
        socket7Image = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        socket8Checkbox = new javax.swing.JCheckBox();
        socket8Image = new javax.swing.JLabel();
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

        onButton.setText("On");
        onButton.setToolTipText("Turn on all checked outlets");

        offButton.setText("Off");
        offButton.setToolTipText("Turn off all checked outlets");

        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("10"));

        socket10Checkbox.setEnabled(false);

        socket10Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket10Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket10Image.setEnabled(false);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket10Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket10Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addComponent(socket10Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket10Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("11"));

        socket11Checkbox.setEnabled(false);

        socket11Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket11Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket11Image.setEnabled(false);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket11Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket11Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addComponent(socket11Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket11Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder("9"));

        socket9Checkbox.setEnabled(false);

        socket9Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket9Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket9Image.setEnabled(false);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket9Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket9Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addComponent(socket9Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket9Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("12"));

        socket12Checkbox.setEnabled(false);

        socket12Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket12Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket12Image.setEnabled(false);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket12Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket12Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addComponent(socket12Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket12Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder("13"));

        socket13Checkbox.setEnabled(false);

        socket13Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket13Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket13Image.setEnabled(false);

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket13Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket13Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addComponent(socket13Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket13Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder("14"));

        socket14Checkbox.setEnabled(false);

        socket14Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket14Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket14Image.setEnabled(false);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket14Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket14Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addComponent(socket14Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket14Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder("15"));

        socket15Checkbox.setEnabled(false);

        socket15Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket15Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket15Image.setEnabled(false);

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket15Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket15Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addComponent(socket15Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket15Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder("16"));

        socket16Checkbox.setEnabled(false);

        socket16Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket16Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket16Image.setEnabled(false);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket16Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket16Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addComponent(socket16Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket16Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder("2"));

        socket2Checkbox.setEnabled(false);

        socket2Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket2Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket2Image.setEnabled(false);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket2Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket2Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addComponent(socket2Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket2Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder("3"));

        socket3Checkbox.setEnabled(false);

        socket3Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket3Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket3Image.setEnabled(false);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket3Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket3Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addComponent(socket3Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket3Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder("1"));

        socket1Checkbox.setEnabled(false);

        socket1Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket1Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket1Image.setEnabled(false);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket1Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket1Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addComponent(socket1Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket1Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder("4"));

        socket4Checkbox.setEnabled(false);

        socket4Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket4Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket4Image.setEnabled(false);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket4Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket4Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addComponent(socket4Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket4Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder("5"));

        socket5Checkbox.setEnabled(false);

        socket5Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket5Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket5Image.setEnabled(false);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket5Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket5Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addComponent(socket5Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket5Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder("6"));

        socket6Checkbox.setEnabled(false);

        socket6Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket6Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket6Image.setEnabled(false);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket6Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket6Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addComponent(socket6Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket6Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder("7"));

        socket7Checkbox.setEnabled(false);

        socket7Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket7Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket7Image.setEnabled(false);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket7Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket7Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addComponent(socket7Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket7Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder("8"));

        socket8Checkbox.setEnabled(false);

        socket8Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        socket8Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/outlet.png"))); // NOI18N
        socket8Image.setEnabled(false);

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(socket8Image)
                .addGap(13, 13, 13))
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(socket8Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addComponent(socket8Image, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socket8Checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(onButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(offButton))
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(onButton)
                    .addComponent(offButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton offButton;
    private javax.swing.JButton onButton;
    private javax.swing.JMenuItem profilesDelete;
    private javax.swing.JMenu profilesMenu;
    private javax.swing.JMenuItem profilesSetStartup;
    private javax.swing.JMenuItem saveChangesMenuItem;
    private javax.swing.JCheckBox socket10Checkbox;
    private javax.swing.JLabel socket10Image;
    private javax.swing.JCheckBox socket11Checkbox;
    private javax.swing.JLabel socket11Image;
    private javax.swing.JCheckBox socket12Checkbox;
    private javax.swing.JLabel socket12Image;
    private javax.swing.JCheckBox socket13Checkbox;
    private javax.swing.JLabel socket13Image;
    private javax.swing.JCheckBox socket14Checkbox;
    private javax.swing.JLabel socket14Image;
    private javax.swing.JCheckBox socket15Checkbox;
    private javax.swing.JLabel socket15Image;
    private javax.swing.JCheckBox socket16Checkbox;
    private javax.swing.JLabel socket16Image;
    private javax.swing.JCheckBox socket1Checkbox;
    private javax.swing.JLabel socket1Image;
    private javax.swing.JCheckBox socket2Checkbox;
    private javax.swing.JLabel socket2Image;
    private javax.swing.JCheckBox socket3Checkbox;
    private javax.swing.JLabel socket3Image;
    private javax.swing.JCheckBox socket4Checkbox;
    private javax.swing.JLabel socket4Image;
    private javax.swing.JCheckBox socket5Checkbox;
    private javax.swing.JLabel socket5Image;
    private javax.swing.JCheckBox socket6Checkbox;
    private javax.swing.JLabel socket6Image;
    private javax.swing.JCheckBox socket7Checkbox;
    private javax.swing.JLabel socket7Image;
    private javax.swing.JCheckBox socket8Checkbox;
    private javax.swing.JLabel socket8Image;
    private javax.swing.JCheckBox socket9Checkbox;
    private javax.swing.JLabel socket9Image;
    // End of variables declaration//GEN-END:variables
}
