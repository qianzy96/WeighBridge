package Frames;

import javax.swing.*;
import java.awt.*;

import Models.*;
import Utilities.Utilities;

public class LogOnFrame extends Components
{
    private JFrame frame;
    public LogOnFrame()
    {
        createLogOnFrame();
    }
    private void createLogOnFrame()
    {
        frame = createFrame("Log On To The Portal");
        JPanel aPanel = new JPanel(new GridLayout(6, 1));
        aPanel.add(createLabel("Your Username"));
        JTextField usernameField = createTextField("");
        aPanel.add(usernameField);
        aPanel.add(createLabel("Your Password"));
        JPasswordField passwordField = createPasswordField("");
        aPanel.add(passwordField);
        JButton logOnButton = createButton("Log On To The Portal");
        logOnButton.addActionListener(x ->
        {
            LogOn aLogOn = new LogOn();
            String passwordText = Utilities.convertCharArrayToString(passwordField.getPassword());
            if(aLogOn.isValidAdministrator(usernameField.getText(), passwordText))
            {
                SwingUtilities.invokeLater(() ->
                {
                    new AdministrationFrame();
                    frame.dispose();
                });
            }
            else if(aLogOn.isValidUser(usernameField.getText(), passwordText))
            {
                SwingUtilities.invokeLater(() ->
                {
                    new PortalFrame(Integer.toString(aLogOn.getUser().getCode()));
                    frame.dispose();
                });
            }
        });
        aPanel.add(logOnButton);
        frame.add(aPanel);
        frame.setVisible(true);
    }
}
