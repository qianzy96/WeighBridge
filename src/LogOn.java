import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class LogOn extends Components
{
    private JFrame frame;
    public LogOn()
    {

    }
    public void createLogOnDialogBox()
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
            Database main = new Database();
            Administrator anAdministrator = new Administrator("", "");
            anAdministrator.setUsername(usernameField.getText());
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("username", anAdministrator.getUsername());
            ArrayList<ArrayList<String>> selectedAdministrator = main.getTableRows("administrators", selectedParameters, new ArrayList<>(),
            "");
            if(selectedAdministrator.size() == 1)
            {
                String passwordText = "";
                char[] passwordCharacters = passwordField.getPassword();
                for(int index = 0; index < passwordCharacters.length; index++)
                    passwordText += passwordCharacters[index];
                anAdministrator.setPassword(passwordText);
                if(selectedAdministrator.get(0).get(1).trim().equals(anAdministrator.getUsername().trim())
                && selectedAdministrator.get(0).get(2).trim().equals(anAdministrator.getPassword()))
                {
                    SwingUtilities.invokeLater(() ->
                    {
                        Administration anAdministration = new Administration();
                        anAdministration.createRibbon();
                        frame.dispose();
                    });
                }
            }
            User anUser = new User(0, "", "", "", "", "", "");
            anUser.setUsername(usernameField.getText());
            ArrayList<ArrayList<String>> selectedUser = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
            if(selectedUser.size() == 1)
            {
                String passwordText = "";
                char[] passwordCharacters = passwordField.getPassword();
                for(int index = 0; index < passwordCharacters.length; index++)
                    passwordText += passwordCharacters[index];
                anUser.setPassword(passwordText);
                if(selectedUser.get(0).get(1).trim().equals(anUser.getUsername().trim()) && selectedUser.get(0).get(2).trim().equals(anUser.getPassword()))
                {
                    SwingUtilities.invokeLater(() ->
                    {
                        new Calculator();
                        frame.dispose();
                    });
                }
            }
        });
        aPanel.add(logOnButton);
        frame.add(aPanel);
        frame.setVisible(true);
    }
}