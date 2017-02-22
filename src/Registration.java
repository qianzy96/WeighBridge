import javax.swing.*;
import java.awt.*;

public class Registration extends Components
{
    private JFrame frame;
    public Registration()
    {

    }
    public void createRegistrationDialogBox()
    {
        frame = createFrame("Registration");
        JPanel aPanel = new JPanel(new GridLayout(13, 1));
        aPanel.add(createLabel("Your Username"));
        JTextField usernameTextField = createTextField("");
        aPanel.add(usernameTextField);
        aPanel.add(createLabel("Your Password"));
        JTextField passwordTextField = createTextField("");
        aPanel.add(passwordTextField);
        aPanel.add(createLabel("Your First Name"));
        JTextField firstNameTextField = createTextField("");
        aPanel.add(firstNameTextField);
        aPanel.add(createLabel("Your Last Name"));
        JTextField lastNameTextField = createTextField("");
        aPanel.add(lastNameTextField);
        aPanel.add(createLabel("Your Email Address"));
        JTextField emailAddressTextField = createTextField("");
        aPanel.add(emailAddressTextField);
        
    }
}
