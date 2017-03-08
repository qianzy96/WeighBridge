package HTMLPages;

import HTMLControls.MetroFluentMenu;
import Utilities.Utilities;
import com.teamdev.jxbrowser.chromium.JSONString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LogOn
{
    public LogOn()
    {

    }
    public JSONString processLogOn(String username, String password)
    {
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);
        MetroFluentMenu aFluentMenu = new MetroFluentMenu("ABOUT", new ArrayList<>(Arrays.asList("FILE", "EDIT")));
        JSONString aString = new JSONString("");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", aFluentMenu.toHTML().write());
        parameters.put("response", "success");
        System.out.println("UTILITIES TO JSON: " + Utilities.convertHashMapToJSON(parameters));
        return Utilities.convertHashMapToJSON(parameters);
    }
}
