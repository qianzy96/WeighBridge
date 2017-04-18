package Models;
import Database.Database;
import Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Registration
{
    private Database main;
    public Registration()
    {
        main = new Database();
    }
    public ArrayList<ArrayList<String>> getSelectedAttributeFromUsersTable(String attributeTitle, String attributeValue)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put(attributeTitle, attributeValue);
        ArrayList<ArrayList<String>> selectedUser = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
        selectedUser.addAll(main.getTableRows("administrators", selectedParameters, new ArrayList<>(), ""));
        return selectedUser;
    }
    public User insertNewUser(ArrayList<String> userAttributes)
    {
        int usersCode = main.getMaxValueOfColumn("users", "code");
        userAttributes.add(0,   Integer.toString(usersCode + 1));
        User anUser = new User(userAttributes);
        main.insertTableRow("users", anUser.toList());
        return anUser;
    }
}