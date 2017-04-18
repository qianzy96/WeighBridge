package Models;

import Users.User;

import Database.Database;
import java.util.ArrayList;
import java.util.HashMap;
public class Portal
{
    private Database main;
    public Portal()
    {
        main = new Database();
    }
    public ArrayList<String> getSelectedRowOfTable(String tableName, String uniqueID)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", uniqueID);
        ArrayList<ArrayList<String>> selectedRow = main.getTableRows(tableName, selectedParameters, new ArrayList<>(), "");
        if(selectedRow.size() > 0)
            return selectedRow.get(0);
        return new ArrayList<>();
    }
    public User getSelectedUser(String username)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("username", username);
        ArrayList<ArrayList<String>> selectedRow = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
        if(selectedRow.size() > 0)
            return new User(Integer.parseInt(selectedRow.get(0).get(0)), selectedRow.get(0).get(1), selectedRow.get(0).get(2), selectedRow.get(0).get(3),
            selectedRow.get(0).get(4), selectedRow.get(0).get(5), selectedRow.get(0).get(6));
        return new User();
    }
    public ArrayList<ArrayList<String>> checkUsersTableForExistingAttribute(String attributeTitle, String attributeValue)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(attributeTitle, attributeValue);
        ArrayList<ArrayList<String>> existingAttributeRows = main.getTableRows("users", parameters, new ArrayList<>(), "");
        existingAttributeRows.addAll(main.getTableRows("administrators", parameters, new ArrayList<>(),""));
        return existingAttributeRows;
    }
    public void updateUsersTable(HashMap<String, String> updatedParameters, HashMap<String, String> restrictiveParameters)
    {
        main.updateTableRow("users", updatedParameters, restrictiveParameters);
    }
}