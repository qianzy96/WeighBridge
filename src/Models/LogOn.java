package Models;

import Users.Administrator;
import Users.User;
import Database.*;
import java.util.ArrayList;
import java.util.HashMap;
public class LogOn
{
    private Database main;
    private Administrator anAdministrator;
    private User anUser;
    public LogOn()
    {
        main = new Database();
    }
    public Administrator getAdministrator()
    {
        return anAdministrator;
    }
    public User getUser()
    {
        return anUser;
    }
    public Boolean isValidAdministrator(String username, String password)
    {
        anAdministrator = new Administrator("", "");
        anAdministrator.setUsername(username);
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("username", anAdministrator.getUsername());
        ArrayList<ArrayList<String>> selectedAdministrator = main.getTableRows("administrators", selectedParameters, new ArrayList<>(), "");
        if (selectedAdministrator.size() == 1)
        {
            anAdministrator = new Administrator(Integer.parseInt(selectedAdministrator.get(0).get(0)), selectedAdministrator.get(0).get(1),
            selectedAdministrator.get(0).get(2));
            if (selectedAdministrator.get(0).get(1).trim().equals(username.trim())
            && selectedAdministrator.get(0).get(2).trim().equals(password.trim()))
                return true;
        }
        return false;
    }
    public Boolean isValidUser(String username, String password)
    {
        anUser = new User(0, "", "", "", "", "", "");
        anUser.setUsername(username);
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("username", anUser.getUsername());
        ArrayList<ArrayList<String>> selectedUser = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
        if(selectedUser.size() == 1)
        {
            anUser.setPassword(password);
            anUser = new User(Integer.parseInt(selectedUser.get(0).get(0)), selectedUser.get(0).get(1), selectedUser.get(0).get(2), selectedUser.get(0).get(3),
            selectedUser.get(0).get(4), selectedUser.get(0).get(5), selectedUser.get(0).get(6));
            if(selectedUser.get(0).get(1).trim().equals(username.trim()) && selectedUser.get(0).get(2).trim().equals(password.trim()))
                return true;
        }
        return false;
    }
}