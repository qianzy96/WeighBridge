package HTMLPages;
import Users.User;
import HTMLControls.*;
import Models.Portal;
import Utilities.Utilities;
import com.teamdev.jxbrowser.chromium.JSONString;
import java.util.*;
public class PortalPage
{
    private static User user;
    public PortalPage()
    {

    }
    public void setUser(User anUser)
    {
        PortalPage.user = anUser;
    }
    public JSONString loadPortalPage()
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createPortalPage().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public MetroAccordion createPortalPage()
    {
        MetroAccordion portalAccordion = new MetroAccordion();
        MetroLayout portalLayout = new MetroLayout();
        CalculatorPage calculatorPage = new CalculatorPage();
        calculatorPage.setUser(user);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.setUser(user);
        MessagePage messagePage = new MessagePage();
        messagePage.setUser(user);
        MetroHeading portalHeading = new MetroHeading("Logged In As " + user.getUsername(), "");
        MetroTile calculatorTile = new MetroTile("getCalculatorPage();", "cyan", "Calculator", "calculator2", "");
        MetroTile pricesTile = new MetroTile("createDashboardPage();", "cyan", "Prices", "eur", "");
        MetroTile messagesTile = new MetroTile("createMessagePage();", "cyan", "Messages", "mail-read", "");
        MetroTile newsTile = new MetroTile("createNewsPage();", "cyan", "News", "feed3", "");
        MetroTile editProfileTile = new MetroTile("getEditUserSettingsPage();", "cyan", "Edit Profile", "pencil", "");
        MetroTile logOutTile = new MetroTile("loadHTML5Edition();", "cyan", "Log Out", "exit", "");
        portalLayout.addRow(portalHeading);
        portalLayout.addEmptyRows(2);
        portalLayout.addRow(new ArrayList<>(Arrays.asList(calculatorTile, pricesTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        portalLayout.addEmptyRows(2);
        portalLayout.addRow(new ArrayList<>(Arrays.asList(messagesTile, editProfileTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        portalLayout.addEmptyRows(2);
        portalLayout.addRow(new ArrayList<>(Arrays.asList(newsTile, logOutTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        portalAccordion.addFrame("Portal For " + user.getUsername(), portalLayout, "home");
        return portalAccordion;
    }
    public JSONString editUserDetails()
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createControlToEditUserDetails().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    private MetroAccordion createControlToEditUserDetails()
    {
        Portal aPortal = new Portal();
        user = aPortal.getSelectedUser(user.getUsername());
        MetroAccordion editUserAccordion = new MetroAccordion();
        MetroLayout editUserLayout = new MetroLayout();
        MetroCommandButton returnToPortal = new MetroCommandButton("Return", "Return To Portal", "exit",
        "getPortalPage();", "danger");
        MetroHeading heading = new MetroHeading("Edit Your Profile", "");
        MetroUpdatePanel editDetailPanel = new MetroUpdatePanel("editDetailPanel", null);
        MetroTileWithControl username = createTile("Your Username", user.getUsername(), "user","editDetail('username', 'Username', 'user');");
        MetroTileWithControl password = createTile("Your Password", user.getPassword(), "security","editDetail('password', 'Password', 'security');");
        MetroTileWithControl firstName = createTile("Your First Name", user.getFirstName(), "user","editDetail('firstname', 'First Name', 'user');");
        MetroTileWithControl lastName = createTile("Your Last Name", user.getLastName(), "user","editDetail('lastname', 'Last Name', 'user');");
        MetroTileWithControl emailAddress = createTile("Your Email Address", user.getEmailAddress(), "mail-read",
        "editDetail('email', 'Email Address', 'mail-read');");
        MetroTileWithControl phoneNumber = createTile("Your Phone Number", user.getPhoneNumber(), "phone",
        "editDetail('phonenumber', 'Phone Number', 'phone');");
        editUserLayout.addEmptyRows(2);
        editUserLayout.addRow(new ArrayList<>(Arrays.asList(returnToPortal, heading)), new ArrayList<>(Arrays.asList(1, 4, 1, 0, 6, 0)));
        editUserLayout.addEmptyRows(2);
        editUserLayout.addRow(editDetailPanel);
        editUserLayout.addEmptyRows(2);
        editUserLayout.addRow(new ArrayList<>(Arrays.asList(username, password)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        editUserLayout.addEmptyRows(2);
        editUserLayout.addRow(new ArrayList<>(Arrays.asList(firstName, lastName)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        editUserLayout.addEmptyRows(2);
        editUserLayout.addRow(new ArrayList<>(Arrays.asList(emailAddress, phoneNumber)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        editUserLayout.addEmptyRows(2);
        editUserAccordion.addFrame("Edit Your Details", editUserLayout, "pencil");
        return editUserAccordion;
    }
    public JSONString editDetail(String identifier, String title, String icon)
    {
        MetroAccordion editDetailAccordion = new MetroAccordion();
        MetroLayout editDetailLayout = new MetroLayout();
        MetroTextField editDetailTextField = new MetroTextField("Your " + title, icon, "text", "editDetail");
        MetroCommandButton saveDetail = new MetroCommandButton("Update", "Update Your " + title, "checkmark",
        "saveUpdatedDetail('" + identifier + "');", "success");
        MetroCommandButton cancelDetail = new MetroCommandButton("Cancel", "Cancel Your Changes", "exit",
        "clearPanel('editDetailPanel');", "danger");
        editDetailLayout.addEmptyRows(2);
        editDetailLayout.addRow(editDetailTextField);
        editDetailLayout.addEmptyRows(2);
        editDetailLayout.addRow(new ArrayList<>(Arrays.asList(saveDetail, cancelDetail)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        editDetailAccordion.addFrame("Update Your " + title, editDetailLayout, "pencil");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", editDetailAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveUpdatedDetail(String identifier, String value)
    {
        Portal aPortal = new Portal();
        HashMap<String, String> parameters = new HashMap<>();
        if(value.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Length");
            parameters.put("content", "A valid input should have at least 2 characters");
        }
        if(parameters.size() == 0)
        {
            if(identifier.equals("username"))
            {
                ArrayList<ArrayList<String>> existingUserDetails = aPortal.checkUsersTableForExistingAttribute("username", value);
                if(existingUserDetails.size() > 0)
                {
                    parameters.put("response", "error");
                    parameters.put("title", "Invalid Username");
                    parameters.put("content", "The username titled " + value + " is already taken");
                }
                ArrayList<ArrayList<String>> existingEmailDetails = aPortal.checkUsersTableForExistingAttribute("email", value);
                if(existingEmailDetails.size() > 0)
                {
                    parameters.put("response", "error");
                    parameters.put("title", "Invalid Email Address");
                    parameters.put("content", "The email address titled " + value + " is already taken");
                }
                ArrayList<ArrayList<String>> existingPhoneDetails = aPortal.checkUsersTableForExistingAttribute("phonenumber", value);
                if(existingPhoneDetails.size() > 0)
                {
                    parameters.put("response", "error");
                    parameters.put("title", "Invalid Phone Number");
                    parameters.put("content", "The phone number titled " + value + " is already taken");
                }
            }
        }
        if(parameters.size() == 0)
        {
            HashMap<String, String> updatedValues = new HashMap<>();
            updatedValues.put(identifier, value);
            HashMap<String, String> restrictiveValues = new HashMap<>();
            restrictiveValues.put("code", Integer.toString(user.getCode()));
            aPortal.updateUsersTable(updatedValues, restrictiveValues);
            parameters.put("html", createControlToEditUserDetails().toString());
            parameters.put("response", "success");
            parameters.put("title", "Valid " + identifier);
            parameters.put("content", "The value for " + identifier + "has been updated to " + value);
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    private MetroTileWithControl createTile(String title, String value, String icon, String onClickEvent)
    {
        MetroSideBar aSideBar = new MetroSideBar("navy");
        //aSideBar.addItem(title, "", icon);
        aSideBar.addItem(value, "", icon);
        aSideBar.addItem("", "", "");
        //aSideBar.addItem("", "", icon);
        MetroPanel aPanel = new MetroPanel(title, "", icon, aSideBar);
        MetroTileWithControl aTile = new MetroTileWithControl(onClickEvent, aPanel,"");
        return aTile;
    }
}
