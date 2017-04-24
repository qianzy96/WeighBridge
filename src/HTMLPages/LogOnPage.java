package HTMLPages;
import HTMLControls.*;
import Models.LogOn;
import Utilities.Utilities;
import com.teamdev.jxbrowser.chromium.JSONString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class LogOnPage
{
    public LogOnPage()
    {

    }
    public JSONString processLogOn(String username, String password)
    {
        HashMap<String, String> parameters = new HashMap<>();
        LogOn aLogOn = new LogOn();
        if(aLogOn.isValidAdministrator(username, password))
        {
            AdministrationPage anAdministrationPage = new AdministrationPage();
            parameters.put("html", anAdministrationPage.createControlForAdministrationPage().toString());
            parameters.put("response", "success");
            parameters.put("title", "Logged In");
            parameters.put("content", "Logged In As " + username);
        }
        else if(aLogOn.isValidUser(username, password))
        {
            PortalPage aPortalPage = new PortalPage();
            aPortalPage.setUser(aLogOn.getUser());
            parameters.put("html", aPortalPage.createPortalPage().toString());
            parameters.put("response", "success");
            parameters.put("title", "Logged In");
            parameters.put("content", "Logged In As " + username);
        }
        else
        {
            parameters.put("response", "error");
            parameters.put("title", "Not Logged In");
            parameters.put("content", "Invalid Credientials Supplied");
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createLogOnPage()
    {
        MetroAccordion logOnAccordion = new MetroAccordion();
        MetroLayout logOnLayout = new MetroLayout();
        MetroTextField username = new MetroTextField("Please enter your username", "user", "text", "username");
        MetroTextField password = new MetroTextField("Please enter your password", "security", "password", "password");
        MetroCommandButton logOnButton = new MetroCommandButton("Log On", "Log On To The Portal", "enter",
        "processUserLogOn();", "success");
        MetroCommandButton cancelButton = new MetroCommandButton("Cancel", "Return To Home Page", "exit",
        "loadHTML5Edition();", "danger");
        logOnLayout.addEmptyRows(2);
        logOnLayout.addRow(username);
        logOnLayout.addEmptyRows(2);
        logOnLayout.addRow(password);
        logOnLayout.addEmptyRows(2);
        logOnLayout.addRow(new ArrayList<>(Arrays.asList(logOnButton, cancelButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        logOnAccordion.addFrame("Log On", logOnLayout, "enter");
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("html", logOnAccordion.toString());
        return Utilities.convertHashMapToJSON(selectedParameters);
    }
}