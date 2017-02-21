import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AdministratorLogOn extends Components
{
    private JFrame frame;
    public AdministratorLogOn()
    {

    }
    public void createLogOnDialogBox()
    {
        frame = createFrame("Log On To The Administration Portal");
        JPanel aPanel = new JPanel(new GridLayout(6, 1));
        aPanel.add(createLabel("Administrator Username"));
        JTextField administratorUsernameField = createTextField("");
        aPanel.add(administratorUsernameField);
        aPanel.add(createLabel("Administrator Password"));
        JPasswordField administratorPasswordField = createPasswordField("");
        aPanel.add(administratorPasswordField);
        JButton administratorLogInButton = createButton("Log In To The Administration Portal");
        administratorLogInButton.addActionListener(x ->
        {
            Database main = new Database();
            Administrator anAdministrator = new Administrator("", "");
            anAdministrator.setUsername(administratorUsernameField.getText());
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("username", anAdministrator.getUsername());
            ArrayList<ArrayList<String>> selectedAdministrator = main.getTableRows("Administrators", selectedParameters, new ArrayList<>(),
            "");
            if(selectedAdministrator.size() == 1)
            {
                String passwordText = "";
                char[] passwordCharacters = administratorPasswordField.getPassword();
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
        });
        aPanel.add(administratorLogInButton);
        frame.add(aPanel);
        frame.setVisible(true);
    }
}
