import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Portal extends Components
{
    private JFrame frame;
    private JComponent component;
    private String userID;
    public Portal(String userID)
    {
        this.userID = userID;
        createMenu();
    }
    private void createMenu()
    {
        frame = createFrame("Portal");
        JPanel aPanel = new JPanel(new GridLayout(2, 2));
        JButton calculatorButton = createTile("Calculator", "", 4);
        calculatorButton.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                new Calculator(userID);
                frame.dispose();
            });
        });
        aPanel.add(calculatorButton);
        JButton dashboardButton = createTile("Prices", "", 4);
        dashboardButton.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                new Dashboard(userID);
                frame.dispose();
            });
        });
        aPanel.add(dashboardButton);
        JButton editProfileButton = createTile("Edit Profile", "", 4);
        editProfileButton.addActionListener(x ->
        {
            Database main = new Database();
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("code", userID);
            ArrayList<ArrayList<String>> userDetails = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
            if(userDetails.size() > 0)
            {
                JPanel editProfilePanel = new JPanel(new GridLayout(13, 1));
                editProfilePanel.add(createLabel("Your Username"));
                JTextField usernameTextField = createTextField(userDetails.get(0).get(1));
                editProfilePanel.add(usernameTextField);
                editProfilePanel.add(createLabel("Your Password"));
                JPasswordField passwordTextField = createPasswordField(userDetails.get(0).get(2));
                editProfilePanel.add(passwordTextField);
                editProfilePanel.add(createLabel("Your First Name"));
                JTextField firstNameTextField = createTextField(userDetails.get(0).get(3));
                editProfilePanel.add(firstNameTextField);
                editProfilePanel.add(createLabel("Your Last Name"));
                JTextField lastNameTextField = createTextField(userDetails.get(0).get(4));
                editProfilePanel.add(lastNameTextField);
                editProfilePanel.add(createLabel("Your Email Address"));
                JTextField emailAddressTextField = createTextField(userDetails.get(0).get(5));
                editProfilePanel.add(emailAddressTextField);
                editProfilePanel.add(createLabel("Your Phone Number"));
                JTextField phoneNumberTextField = createTextField(userDetails.get(0).get(6));
                editProfilePanel.add(phoneNumberTextField);
                JButton updateUserDetailsButton = createTile("Update User Details", "", 1);
                updateUserDetailsButton.addActionListener(y ->
                {
                    HashMap<String, String> parameters = new HashMap<>();
                    parameters.put("username", usernameTextField.getText());
                    ArrayList<ArrayList<String>> username = main.getTableRows("users", parameters, new ArrayList<>(), "");
                    username.addAll(main.getTableRows("administrators", parameters, new ArrayList<>(),""));
                    parameters = new HashMap<>();
                    parameters.put("email", emailAddressTextField.getText());
                    ArrayList<ArrayList<String>> email = main.getTableRows("users", parameters, new ArrayList<>(), "");
                    email.addAll(main.getTableRows("administrators", parameters, new ArrayList<>(),""));
                    parameters = new HashMap<>();
                    parameters.put("phonenumber", phoneNumberTextField.getText());
                    ArrayList<ArrayList<String>> phoneNumber = main.getTableRows("users", parameters, new ArrayList<>(), "");
                    phoneNumber.addAll(main.getTableRows("administrators", parameters, new ArrayList<>(),""));
                    if(username.size() == 0 && email.size() == 0 && phoneNumber.size() == 0)
                    {
                        String passwordTextFieldText = "";
                        for(char aCharacter : passwordTextField.getPassword())
                            passwordTextFieldText += aCharacter;
                        HashMap<String, String> updatedParameters = new HashMap<>();
                        updatedParameters.put("username", usernameTextField.getText());
                        updatedParameters.put("password", passwordTextFieldText);
                        updatedParameters.put("firstname", firstNameTextField.getText());
                        updatedParameters.put("lastname", lastNameTextField.getText());
                        updatedParameters.put("email", emailAddressTextField.getText());
                        updatedParameters.put("phonenumber", phoneNumberTextField.getText());
                        HashMap<String, String> restrictiveParameters = new HashMap<>();
                        restrictiveParameters.put("code", userID);
                        main.updateTableRow("users", updatedParameters, restrictiveParameters);
                        createMenu();
                    }
                    else if(username.size() == 0)
                        usernameTextField.setBackground(Color.RED);
                    else if(email.size() == 0)
                        emailAddressTextField.setBackground(Color.RED);
                    else if(phoneNumber.size() == 0)
                        phoneNumberTextField.setBackground(Color.RED);
                });
                editProfilePanel.add(updateUserDetailsButton);
                addComponent(editProfilePanel);
            }
        });
        aPanel.add(editProfileButton);
        JButton logOutButton = createTile("Log Out", "", 4);
        logOutButton.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                new WeighBridge();
                frame.dispose();
            });
        });
        aPanel.add(logOutButton);
        frame.add(aPanel);
        frame.setVisible(true);
    }
    private void addComponent(JComponent aComponent)
    {
        if(component != null)
            frame.remove(component);
        component = aComponent;
        frame.add(component, BorderLayout.CENTER);
        frame.invalidate();
        frame.revalidate();
    }
}