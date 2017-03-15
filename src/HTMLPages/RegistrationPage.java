package HTMLPages;
import Entities.User;
import HTMLControls.*;
import Models.Registration;
import Utilities.*;
import com.teamdev.jxbrowser.chromium.JSONString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RegistrationPage
{
    public RegistrationPage()
    {

    }
    public JSONString createRegistrationPage()
    {
        MetroAccordion registrationAccordion = new MetroAccordion();
        MetroTextField username = new MetroTextField("Please enter your username", "user", "text", "username");
        MetroTextField password = new MetroTextField("Please enter your password", "security", "password", "password");
        MetroTextField firstName = new MetroTextField("Please enter your first name", "user", "text", "firstName");
        MetroTextField lastName = new MetroTextField("Please enter your last name", "user", "text", "lastName");
        MetroTextField emailAddress = new MetroTextField("Please enter your email address", "mail-read", "text", "emailAddress");
        MetroTextField phoneNumber = new MetroTextField("Please enter your phone number", "phone", "text", "phoneNumber");
        MetroCommandButton registerButton = new MetroCommandButton("Register", "Create A New Account", "user-plus",
        "processUserRegistration();", "success");
        MetroCommandButton cancelButton = new MetroCommandButton("Cancel", "Return To Home Page", "exit",
        "loadHTML5Edition();", "danger");
        MetroLayout registrationLayout = new MetroLayout();
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(username);
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(password);
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(firstName);
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(lastName);
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(emailAddress);
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(phoneNumber);
        registrationLayout.addEmptyRows(2);
        registrationLayout.addRow(new ArrayList<>(Arrays.asList(registerButton, cancelButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        registrationAccordion.addFrame("Create A New Account", registrationLayout, "user-plus");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", registrationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString processRegistration(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber)
    {
        HashMap<String, String> parameters = new HashMap<>();
        List<String> submittedValues = new ArrayList<>(Arrays.asList(username, password, firstName, lastName, emailAddress, phoneNumber));
        submittedValues.forEach(x ->
        {
            if(x.length() < 2)
            {
                parameters.put("response", "error");
                parameters.put("title", "Invalid Length");
                parameters.put("content", "A valid input should have at least 2 characters");
            }
        });
        if(parameters.size() == 0)
        {
            Registration aRegistration = new Registration();
            ArrayList<ArrayList<String>> existingUserDetails = aRegistration.getSelectedAttributeFromUsersTable("username", username);
            if(existingUserDetails.size() > 0)
            {
                parameters.put("response", "error");
                parameters.put("title", "Invalid Username");
                parameters.put("content", "The username titled " + username + " is already taken");
            }
            ArrayList<ArrayList<String>> existingEmailDetails = aRegistration.getSelectedAttributeFromUsersTable("email", emailAddress);
            if(existingEmailDetails.size() > 0)
            {
                parameters.put("response", "error");
                parameters.put("title", "Invalid Email Address");
                parameters.put("content", "The email address titled " + emailAddress + " is already taken");
            }
            ArrayList<ArrayList<String>> existingPhoneDetails = aRegistration.getSelectedAttributeFromUsersTable("phonenumber", phoneNumber);
            if(existingPhoneDetails.size() > 0)
            {
                parameters.put("response", "error");
                parameters.put("title", "Invalid Phone Number");
                parameters.put("content", "The phone number titled " + phoneNumber + " is already taken");
            }
            if(parameters.size() == 0)
            {
                User anUser = aRegistration.insertNewUser(new ArrayList<>(submittedValues));
                PortalPage aPortalPage = new PortalPage();
                aPortalPage.setUser(anUser);
                parameters.put("response", "success");
                parameters.put("title", "Account Successfully Created");
                parameters.put("content", "The account with username " + username + " has been created");
                parameters.put("html", aPortalPage.createPortalPage().toString());
            }
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
}
