package Models;

import Frames.Components;
import Models.Calculator;
import Models.Dashboard;

import javax.swing.*;
import java.awt.*;
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