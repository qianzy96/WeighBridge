package HTMLPages;
import HTMLControls.*;
import Models.Administration;
import Utilities.*;
import com.teamdev.jxbrowser.chromium.JSONString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdministrationPage
{
    public AdministrationPage()
    {

    }
    public JSONString createAdministrationPage()
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createControlForAdministrationPage().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public MetroLayout createControlForAdministrationPage()
    {
        MetroLayout administrationPageLayout = new MetroLayout();
        MetroFluentMenu aFluentMenu = new MetroFluentMenu("aFluentMenu", "Main Menu", "getAdministrationMainMenu();",
        new ArrayList<>(Arrays.asList("Drivers & Commodities", "Weights", "Users", "Consignees", "Docket Types & Contracts", "Settings & Batch Numbers")));
        MetroFluentMenuPanelGroup driversMenuGroup = createMenuGroup("drivers", "Drivers", "Driver");
        MetroFluentMenuPanelGroup commoditiesMenuGroup = createMenuGroup("commodities", "Commodities", "Commodity");
        MetroFluentMenuPanelGroup firstWeightsMenuGroup = createMenuGroup("firstweights", "First Weights", "First Weight");
        MetroFluentMenuPanelGroup secondWeightsMenuGroup = createMenuGroup("secondweights", "Second Weights", "Second Weight");
        MetroFluentMenuPanelGroup administratorsMenuGroup = createMenuGroup("administrators", "Administrators", "Administrator");
        MetroFluentMenuPanelGroup usersMenuGroup = createMenuGroup("users", "Users", "User");
        MetroFluentMenuPanelGroup customersMenuGroup = createMenuGroup("customers", "Customers", "Customer");
        MetroFluentMenuPanelGroup suppliersMenuGroup = createMenuGroup("suppliers", "Suppliers", "Supplier");
        MetroFluentMenuPanelGroup docketTypesMenuGroup = createMenuGroup("dockettypes", "Docket Types", "Docket Type");
        MetroFluentMenuPanelGroup contractsMenuGroup = createMenuGroup("contracts", "Contracts", "Contract");
        MetroFluentMenuPanelGroup settingsMenuGroup = createMenuGroup("settings", "Settings", "Setting");
        MetroFluentMenuPanelGroup batchNumbersMenuGroup = createMenuGroup("batchnumbers", "Batch Numbers", "Batch Number");
        aFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(driversMenuGroup, commoditiesMenuGroup)));
        aFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(firstWeightsMenuGroup, secondWeightsMenuGroup)));
        aFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(administratorsMenuGroup, usersMenuGroup)));
        aFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(customersMenuGroup, suppliersMenuGroup)));
        aFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(docketTypesMenuGroup, contractsMenuGroup)));
        aFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(settingsMenuGroup, batchNumbersMenuGroup)));
        administrationPageLayout.addRow(aFluentMenu);
        administrationPageLayout.addRow(new MetroUpdatePanel("administrationPanel", null));
        return administrationPageLayout;
    }
    private MetroFluentMenuPanelGroup createMenuGroup(String tableName, String pluralWord, String singularWord)
    {
        String aWord = "A ";
        if(singularWord.startsWith("A") || singularWord.startsWith("E") || singularWord.startsWith("I") || singularWord.startsWith("O") || singularWord.startsWith("U"))
            aWord = "An ";
        ArrayList<MetroFluentButton> fluentMenuButtoms = new ArrayList<>();
        fluentMenuButtoms.add(new MetroFluentButton("View " + pluralWord, "database", "getTableContents('" + tableName + "');"));
        fluentMenuButtoms.add(new MetroFluentButton("Add " + aWord + singularWord, "plus", "addNewRowToTable('" + tableName + "');"));
        fluentMenuButtoms.add(new MetroFluentButton("Delete " + aWord + singularWord, "bin", "deleteExistingRow('" + tableName + "');"));
        fluentMenuButtoms.add(new MetroFluentButton("Update " + aWord + singularWord, "pencil", "updateRows('" + tableName + "');"));
        return new MetroFluentMenuPanelGroup(pluralWord, fluentMenuButtoms);
    }
    public JSONString createMainMenu()
    {
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        MetroLayout mainMenuLayout = new MetroLayout();
        MetroTile logOutTile = new MetroTile("loadHTML5Edition();", "cyan", "Log Out", "exit", "");
        MetroTile mainMenuTile = new MetroTile("getHomePage();", "cyan", "Main Menu", "menu", "");
        MetroTile exitTile = new MetroTile("exit();", "cyan", "Exit", "exit", "");
        mainMenuLayout.addEmptyRows(2);
        mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(logOutTile, mainMenuTile, exitTile)), new ArrayList<>(Arrays.asList(0, 3, 1, 0, 3, 1, 0, 3, 1)));
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", mainMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createTable(String tableName)
    {
        Administration anAdministration = new Administration();
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName),
        anAdministration.getTableContents(tableName), new ArrayList<>());
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", administrationTable.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createNewRow(String tableName)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> tableTitles = anAdministration.getTableTitles(tableName);
        MetroAccordion createNewRowAccordion = new MetroAccordion();
        MetroLayout createNewRowLayout = new MetroLayout();
        tableTitles.forEach(x ->
        {
            MetroTextField aTextField = new MetroTextField("Please enter a value for " + x, "pencil", "text", x);
            createNewRowLayout.addRow(aTextField);
            createNewRowLayout.addEmptyRows(2);
        });
        JSONString formattedTableTitle = Utilities.convertListToJavaScriptArray(tableTitles);
        MetroCommandButton saveNewRowButton = new MetroCommandButton("Save", "Save New Row", "plus",
        "saveNewRow('" + tableName + "', " + formattedTableTitle.getValue() + ");", "success");
        MetroCommandButton cancelNewRowButton = new MetroCommandButton("Cancel", "Return To Table", "exit",
        "getTableContents('" + tableName + "');", "danger");
        createNewRowLayout.addRow(new ArrayList<>(Arrays.asList(saveNewRowButton, cancelNewRowButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        createNewRowAccordion.addFrame("Add New Row", createNewRowLayout, "plus");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createNewRowAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createTableForUpdatingRows(String tableName)
    {
        Administration anAdministration = new Administration();
        ArrayList<ArrayList<String>> tableContents = anAdministration.getTableContents(tableName);
        ArrayList<String> tableClickEvents = new ArrayList<>();
        tableContents.forEach(x -> tableClickEvents.add("updateExistingRow('" + tableName + "', '" + x.get(0) + "');"));
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName), tableContents, tableClickEvents);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", administrationTable.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString updateExistingRow(String tableName, String uniqueIdentifier)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> tableTitles = anAdministration.getTableTitles(tableName);
        ArrayList<ArrayList<String>> selectedRow = anAdministration.getSelectedRow(tableName, uniqueIdentifier);
        MetroAccordion updateExistingRowAccordion = new MetroAccordion();
        MetroLayout updateExistingRowLayout = new MetroLayout();
        if(selectedRow.size() > 0)
        {
            for(int counter = 0; counter < tableTitles.size(); counter++)
            {
                MetroTextField aTextField = new MetroTextField("Please enter an updated value for " + tableTitles.get(counter), "pencil",
                "text", tableTitles.get(counter), selectedRow.get(0).get(counter));
                updateExistingRowLayout.addRow(aTextField);
                updateExistingRowLayout.addEmptyRows(2);
            }
        }
        JSONString formattedTableTitle = Utilities.convertListToJavaScriptArray(tableTitles);
        MetroCommandButton updateExistingRowButton = new MetroCommandButton("Update", "Update Existing Row", "pencil",
        "updateExistingRowConfirmation('" + tableName + "', " + formattedTableTitle + ", '" + uniqueIdentifier + "');", "success");
        MetroCommandButton cancelExistingRowButton = new MetroCommandButton("Cancel", "Return To Table", "exit",
        "updateRows('" + tableName + "');", "danger");
        updateExistingRowLayout.addRow(new ArrayList<>(Arrays.asList(updateExistingRowButton, cancelExistingRowButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        updateExistingRowAccordion.addFrame("Update Existing Row", updateExistingRowLayout, "pencil");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", updateExistingRowAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewRow(String tableName, String[] tableValues)
    {
        System.out.println("SAVE NEW ROW METHOD CALLED");
        System.out.println("TABLE NAME: " + tableName);
        for(int counter = 0; counter < tableValues.length; counter++)
            System.out.println("A TABLE VALUE: " + tableValues[counter]);
        HashMap<String, String> parameters = new HashMap<>();
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString updateExistingRow(String tableName, String[] tableValues, String uniqueIdentifier)
    {
        System.out.println("UPDATE EXISTING ROWS METHOD CALLED");
        HashMap<String, String> parameters = new HashMap<>();
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createTableForDeletions(String tableName)
    {
        Administration anAdministration = new Administration();
        ArrayList<ArrayList<String>> tableContents = anAdministration.getTableContents(tableName);
        ArrayList<String> tableClickEvents = new ArrayList<>();
        tableContents.forEach(x -> tableClickEvents.add("deleteSelectedRow('" + tableName + "', '" + x.get(0) + "');"));
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName),
        anAdministration.getTableContents(tableName), tableClickEvents);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", administrationTable.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedRow(String tableName, String uniqueIdentifier)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> tableTitles = anAdministration.getTableTitles(tableName);
        ArrayList<ArrayList<String>> tableContents = anAdministration.getSelectedRow(tableName, uniqueIdentifier);
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName),
        anAdministration.getSelectedRow(tableName, uniqueIdentifier), new ArrayList<>());
        MetroCommandButton deleteSelectedRowConfirmationButton = new MetroCommandButton("Delete", "Delete Selected Row", "checkmark",
        "", "success");
        MetroCommandButton deleteSelectedRowCancellationButton = new MetroCommandButton("Cancel", "Return To Table", "bin",
        "deleteExistingRow('" + tableName + "');", "danger");
        MetroAccordion deleteSelectedRowAccordion = new MetroAccordion();
        MetroLayout deleteSelectedRowLayout = new MetroLayout();
        deleteSelectedRowLayout.addEmptyRows(2);
        deleteSelectedRowLayout.addRow(new MetroHeading("Delete Selected Row", ""));
        deleteSelectedRowLayout.addEmptyRows(2);
        deleteSelectedRowLayout.addRow(administrationTable);
        deleteSelectedRowLayout.addEmptyRows(2);
        deleteSelectedRowLayout.addRow(new ArrayList<>(Arrays.asList(deleteSelectedRowConfirmationButton, deleteSelectedRowCancellationButton)),
        new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        deleteSelectedRowAccordion.addFrame("Delete Selected Row", deleteSelectedRowLayout, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", deleteSelectedRowAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedRowConfirmation(String tableName, String uniqueIdentifier)
    {
        Administration anAdministration = new Administration();
        anAdministration.deleteSelectedRow(tableName, uniqueIdentifier);
        return createTableForDeletions(tableName);
    }
}