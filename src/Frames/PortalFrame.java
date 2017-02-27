package Frames;

import Models.Calculator;
import Models.Dashboard;
import Models.Portal;
import Models.WeighBridge;
import Utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PortalFrame extends Components
{
    private JFrame frame;
    private JComponent component;
    private String userID;
    private Portal aPortal;
    public PortalFrame(String userID)
    {
        this.aPortal = new Portal();
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
                new CalculatorFrame(userID);
                frame.dispose();
            });
        });
        aPanel.add(calculatorButton);
        JButton dashboardButton = createTile("Prices", "", 4);
        dashboardButton.addActionListener(x ->
        {
            SwingUtilities.invokeLater(() ->
            {
                new DashboardFrame(userID);
                frame.dispose();
            });
        });
        aPanel.add(dashboardButton);
        JButton editProfileButton = createTile("Edit Profile", "", 4);
        editProfileButton.addActionListener(x ->
        {
            ArrayList<String> userDetails = aPortal.getSelectedRowOfTable("users", userID);
            if(userDetails.size() > 0)
            {
                JPanel editProfilePanel = new JPanel(new GridLayout(13, 1));
                editProfilePanel.add(createLabel("Your Username"));
                JTextField usernameTextField = createTextField(userDetails.get(1));
                editProfilePanel.add(usernameTextField);
                editProfilePanel.add(createLabel("Your Password"));
                JPasswordField passwordTextField = createPasswordField(userDetails.get(2));
                editProfilePanel.add(passwordTextField);
                editProfilePanel.add(createLabel("Your First Name"));
                JTextField firstNameTextField = createTextField(userDetails.get(3));
                editProfilePanel.add(firstNameTextField);
                editProfilePanel.add(createLabel("Your Last Name"));
                JTextField lastNameTextField = createTextField(userDetails.get(4));
                editProfilePanel.add(lastNameTextField);
                editProfilePanel.add(createLabel("Your Email Address"));
                JTextField emailAddressTextField = createTextField(userDetails.get(5));
                editProfilePanel.add(emailAddressTextField);
                editProfilePanel.add(createLabel("Your Phone Number"));
                JTextField phoneNumberTextField = createTextField(userDetails.get(6));
                editProfilePanel.add(phoneNumberTextField);
                JButton updateUserDetailsButton = createTile("Update User Details", "", 1);
                updateUserDetailsButton.addActionListener(y ->
                {
                    ArrayList<ArrayList<String>> username = aPortal.checkUsersTableForExistingAttribute("username", usernameTextField.getText());
                    ArrayList<ArrayList<String>> email = aPortal.checkUsersTableForExistingAttribute("email", emailAddressTextField.getText());
                    ArrayList<ArrayList<String>> phoneNumber = aPortal.checkUsersTableForExistingAttribute("phonenumber", phoneNumberTextField.getText());
                    if(username.size() == 0 && email.size() == 0 && phoneNumber.size() == 0)
                    {
                        String passwordTextFieldText = Utilities.convertCharArrayToString(passwordTextField.getPassword());
                        HashMap<String, String> updatedParameters = new HashMap<>();
                        updatedParameters.put("username", usernameTextField.getText());
                        updatedParameters.put("password", passwordTextFieldText);
                        updatedParameters.put("firstname", firstNameTextField.getText());
                        updatedParameters.put("lastname", lastNameTextField.getText());
                        updatedParameters.put("email", emailAddressTextField.getText());
                        updatedParameters.put("phonenumber", phoneNumberTextField.getText());
                        HashMap<String, String> restrictiveParameters = new HashMap<>();
                        restrictiveParameters.put("code", userID);
                        aPortal.updateUsersTable(updatedParameters, restrictiveParameters);
                        createMenu();
                    }
                    else if(username.size() > 0)
                        usernameTextField.setBackground(Color.RED);
                    else if(email.size() > 0)
                        emailAddressTextField.setBackground(Color.RED);
                    else if(phoneNumber.size() > 0)
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
                new WeighBridgeFrame();
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
