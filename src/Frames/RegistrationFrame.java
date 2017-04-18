package Frames;

import Users.User;
import Models.Registration;
import Utilities.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RegistrationFrame extends Components
{
    private JFrame frame;
    private Registration aRegistration;
    public RegistrationFrame()
    {
        aRegistration = new Registration();
        createRegistrationDialogBox();
    }
    private void createRegistrationDialogBox()
    {
        frame = createFrame("Registration");
        JPanel aPanel = new JPanel(new GridLayout(13, 1));
        aPanel.add(createLabel("Your Username"));
        JTextField usernameTextField = createTextField("");
        aPanel.add(usernameTextField);
        aPanel.add(createLabel("Your Password"));
        JPasswordField passwordTextField = createPasswordField("");
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
        aPanel.add(createLabel("Your Phone Number"));
        JTextField phoneNumberTextField = createTextField("");
        aPanel.add(phoneNumberTextField);
        JButton registrationButton = createTile("Register", "", 1);
        registrationButton.addActionListener(x ->
        {
            String passwordTextFieldText = Utilities.convertCharArrayToString(passwordTextField.getPassword());
            if(usernameTextField.getText().length() > 1 && passwordTextFieldText.length() > 1 && firstNameTextField.getText().length() > 1 &&
            lastNameTextField.getText().length() > 1 && emailAddressTextField.getText().length() > 1 && phoneNumberTextField.getText().length() > 1)
            {
                ArrayList<ArrayList<String>> user = aRegistration.getSelectedAttributeFromUsersTable("username", usernameTextField.getText());
                ArrayList<ArrayList<String>> email = aRegistration.getSelectedAttributeFromUsersTable("email", emailAddressTextField.getText());
                ArrayList<ArrayList<String>> phoneNumber = aRegistration.getSelectedAttributeFromUsersTable("phonenumber", phoneNumberTextField.getText());
                if(user.size() == 0 && email.size() == 0 && phoneNumber.size() == 0)
                {
                    /*int usersCode = main.getMaxValueOfColumn("users", "code");
                    User anUser = new User(usersCode + 1, usernameTextField.getText(), passwordTextFieldText, firstNameTextField.getText(),
                            lastNameTextField.getText(), emailAddressTextField.getText(), phoneNumberTextField.getText());
                    main.insertTableRow("users", anUser.toList());*/
                    User anUser = aRegistration.insertNewUser(new ArrayList<>(Arrays.asList(usernameTextField.getText(), passwordTextFieldText, firstNameTextField.getText(),
                    lastNameTextField.getText(), emailAddressTextField.getText(), phoneNumberTextField.getText())));
                    SwingUtilities.invokeLater(() ->
                    {
                        new PortalFrame(Integer.toString(anUser.getCode()));
                        frame.dispose();
                    });
                }
                if(user.size() > 0)
                    usernameTextField.setBackground(Color.RED);
                if(email.size() > 0)
                    emailAddressTextField.setBackground(Color.RED);
                if(phoneNumber.size() > 0)
                    phoneNumberTextField.setBackground(Color.RED);
            }
            else
            {
                ArrayList<JTextField> components = new ArrayList<>(Arrays.asList(usernameTextField, passwordTextField, firstNameTextField, lastNameTextField,
                emailAddressTextField, phoneNumberTextField));
                for(JTextField aTextField : components)
                    if(aTextField.getText().length() < 2)
                        aTextField.setBackground(Color.RED);
            }
        });
        aPanel.add(registrationButton);
        frame.add(aPanel);
        frame.setVisible(true);
    }
}
